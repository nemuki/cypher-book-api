package dev.nemuki.cypherbookapi.infra.repository

import dev.nemuki.cypherbookapi.application.repository.BookRepository
import dev.nemuki.cypherbookapi.domain.error.business.AlreadyExistsException
import dev.nemuki.cypherbookapi.domain.error.business.DataNotFoundException
import dev.nemuki.cypherbookapi.domain.error.business.DatabaseAccessException
import dev.nemuki.cypherbookapi.infra.entity.BookAddedOption
import dev.nemuki.cypherbookapi.infra.entity.BookOption
import dev.nemuki.cypherbookapi.infra.entity.InsertBook
import dev.nemuki.cypherbookapi.infra.entity.UpdateBook
import dev.nemuki.cypherbookapi.infra.mapper.BookMapper
import org.springframework.dao.DataAccessException
import org.springframework.dao.DuplicateKeyException
import org.springframework.stereotype.Repository
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate

@Repository
class BookRepositoryImpl(
    val bookMapper: BookMapper,
    val restTemplate: RestTemplate,
) : BookRepository {
    override fun getAll(): List<dev.nemuki.cypherbookapi.domain.entity.Book> {
        val books = try {
            bookMapper.selectAll()
        } catch (ex: DataAccessException) {
            throw DatabaseAccessException("BookRepository#getAllでエラーが発生しました")
        }

        val options = books.map {
            BookAddedOption(
                isbn = it.isbn,
                title = it.title,
                author = it.author,
                publisher = it.publisher,
                price = it.price,
                option = getBookOptionByIsbn(
                    it.isbn
                ),
                createdAt = it.createdAt,
                updatedAt = it.updatedAt,
            )
        }

        return options.toEntities()
    }

    override fun getByIsbn(isbn: String): dev.nemuki.cypherbookapi.domain.entity.Book {
        val book = try {
            bookMapper.findByIsbn(isbn)
        } catch (ex: DataAccessException) {
            throw DatabaseAccessException("BookRepository#getByIsbnでエラーが発生しました")
        } ?: throw DataNotFoundException("No Book found. isbn=${isbn}")

        val options = BookAddedOption(
            isbn = book.isbn,
            title = book.title,
            author = book.author,
            publisher = book.publisher,
            price = book.price,
            option = getBookOptionByIsbn(
                book.isbn
            ),
            createdAt = book.createdAt,
            updatedAt = book.updatedAt,
        )

        return options.toEntity()
    }

    private fun getBookOptionByIsbn(isbn: String): BookOption? {
        val uri = "http://localhost:3000/books/${isbn}/options"
        val result = try {
            restTemplate.getForObject(uri, BookOption::class.java)
        } catch (ex: HttpClientErrorException) {
            null
        }
        return result
    }

    override fun insert(book: dev.nemuki.cypherbookapi.domain.entity.Book) {
        val isbn = book.isbn
        try {
            bookMapper.insert(
                InsertBook(
                    isbn = isbn,
                    title = book.title,
                    author = book.author,
                    publisher = book.publisher,
                    price = book.price,
                )
            )
        } catch (ex: DuplicateKeyException) {
            throw AlreadyExistsException("$isbn は登録済みのISBNです。")
        } catch (ex: DataAccessException) {
            throw DatabaseAccessException("BookRepository#insertでエラーが発生しました")
        }
    }

    override fun update(
        isbn: String,
        currentBookState: dev.nemuki.cypherbookapi.domain.entity.Book,
        updateBookCondition: dev.nemuki.cypherbookapi.domain.entity.UpdateBookCondition,
    ) {
        try {
            bookMapper.update(
                UpdateBook(
                    isbn = isbn,
                    title = updateBookCondition.title ?: currentBookState.title,
                    author = updateBookCondition.author ?: currentBookState.author,
                    publisher = updateBookCondition.publisher ?: currentBookState.publisher,
                    price = updateBookCondition.price ?: currentBookState.price,
                )
            )
        } catch (ex: DataAccessException) {
            throw DatabaseAccessException("BookRepository#updateでエラーが発生しました")
        }
    }

    private fun BookAddedOption.toEntity() =
        dev.nemuki.cypherbookapi.domain.entity.Book(
            isbn = isbn,
            title = title,
            author = author,
            publisher = publisher,
            price = price,
            option = option?.toEntity(),
            createdAt = createdAt,
            updatedAt = updatedAt,
        )

    private fun List<BookAddedOption>.toEntities() = map {
        it.toEntity()
    }

    private fun BookOption.toEntity() = dev.nemuki.cypherbookapi.domain.entity.BookOption(rating, genre)
}
