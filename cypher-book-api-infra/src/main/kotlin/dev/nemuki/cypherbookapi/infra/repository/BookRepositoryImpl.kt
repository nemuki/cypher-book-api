package dev.nemuki.cypherbookapi.infra.repository

import dev.nemuki.cypherbookapi.application.repository.BookRepository
import dev.nemuki.cypherbookapi.domain.error.business.DataNotFoundException
import dev.nemuki.cypherbookapi.domain.error.system.ResourceAccessError
import dev.nemuki.cypherbookapi.infra.entity.Book
import dev.nemuki.cypherbookapi.infra.mapper.BookMapper
import org.springframework.dao.DataAccessException
import org.springframework.stereotype.Repository

@Repository
class BookRepositoryImpl(
    val bookMapper: BookMapper,
) : BookRepository {
    override fun getAll(): List<dev.nemuki.cypherbookapi.domain.entity.Book> {
        val books = try {
            bookMapper.selectAll()
        } catch (ex: DataAccessException) {
            throw ResourceAccessError("fetch error", ex)
        }

        return books.toEntities()
    }

    override fun getByIsbn(isbn: String): dev.nemuki.cypherbookapi.domain.entity.Book {
        val book = try {
            bookMapper.findByIsbn(isbn)
        } catch (ex: DataAccessException) {
            throw ResourceAccessError("fetch error", ex)
        } ?: throw DataNotFoundException("No Book found. isbn=${isbn}")

        return book.toEntity()
    }

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
}
