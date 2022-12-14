package dev.nemuki.cypherbookapi.infra.repository

import dev.nemuki.cypherbookapi.application.repository.BookRepository
import dev.nemuki.cypherbookapi.domain.error.business.AlreadyExistsException
import dev.nemuki.cypherbookapi.domain.error.business.DataNotFoundException
import dev.nemuki.cypherbookapi.domain.error.business.DatabaseAccessException
import dev.nemuki.cypherbookapi.infra.entity.Book
import dev.nemuki.cypherbookapi.infra.entity.BookExtraOption
import dev.nemuki.cypherbookapi.infra.entity.InsertBook
import dev.nemuki.cypherbookapi.infra.entity.UpdateBook
import dev.nemuki.cypherbookapi.infra.mapper.BookMapper
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.dao.DataAccessException
import org.springframework.dao.DuplicateKeyException
import org.springframework.stereotype.Repository
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate

@Repository
class BookRepositoryImpl(
    val bookMapper: BookMapper,
    val restTemplate: RestTemplate,
    @Value("\${gateway.book-extra.host}") private val host: String,
) : BookRepository {
    override fun getAll(): List<dev.nemuki.cypherbookapi.domain.entity.Book> {
        val books = try {
            bookMapper.selectAll()
        } catch (ex: DataAccessException) {
            throw DatabaseAccessException("BookRepository#getAllでエラーが発生しました")
        }

        return books.toEntities()
    }

    override fun getByIsbn(isbn: String): dev.nemuki.cypherbookapi.domain.entity.BookGet {
        val book = try {
            bookMapper.findByIsbn(isbn)
        } catch (ex: DataAccessException) {
            throw DatabaseAccessException("BookRepository#getByIsbnでエラーが発生しました")
        } ?: throw DataNotFoundException("No Book found. isbn=${isbn}")

        val option = getBookExtraOptionByIsbn(book.isbn)

        return addBookExtraOption(book, option)
    }

    private fun getBookExtraOptionByIsbn(isbn: String): BookExtraOption? {
        val uri = "${host}/books/${isbn}/extra"
        val result = try {
            restTemplate.getForObject(uri, BookExtraOption::class.java)
        } catch (ex: HttpClientErrorException) {
            logger.error(ex.localizedMessage)
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
        currentBookState: dev.nemuki.cypherbookapi.domain.entity.BookGet,
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

    private fun addBookExtraOption(book: Book, option: BookExtraOption?) =
        dev.nemuki.cypherbookapi.domain.entity.BookGet(
            isbn = book.isbn,
            title = book.title,
            author = book.author,
            publisher = book.publisher,
            price = book.price,
            extra = option?.toEntity(),
            createdAt = book.createdAt,
            updatedAt = book.updatedAt,
        )

    private fun Book.toEntity() =
        dev.nemuki.cypherbookapi.domain.entity.Book(
            isbn = isbn,
            title = title,
            author = author,
            publisher = publisher,
            price = price,
            createdAt = createdAt,
            updatedAt = updatedAt,
        )

    private fun List<Book>.toEntities() = map {
        it.toEntity()
    }

    private fun BookExtraOption.toEntity() = dev.nemuki.cypherbookapi.domain.entity.BookExtraOption(rating, genre)

    companion object {
        private val logger = LoggerFactory.getLogger(BookRepositoryImpl::class.java)
    }
}
