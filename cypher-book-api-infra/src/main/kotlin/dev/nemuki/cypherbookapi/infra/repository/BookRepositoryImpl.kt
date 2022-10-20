package dev.nemuki.cypherbookapi.infra.repository

import dev.nemuki.cypherbookapi.application.repository.BookRepository
import dev.nemuki.cypherbookapi.infra.entity.Book
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class BookRepositoryImpl(val jdbcTemplate: JdbcTemplate) : BookRepository {
    override fun getAll(): List<dev.nemuki.cypherbookapi.domain.entity.Book> {
        val books = jdbcTemplate.query("select * from cypher.book") { rs, _ ->
            Book(
                isbn = rs.getString("isbn"),
                title = rs.getString("title"),
                author = rs.getString("author"),
                publisher = rs.getString("publisher"),
                price = rs.getInt("price"),
                createdAt = rs.getTimestamp("created_at")?.toLocalDateTime(),
                updatedAt = rs.getTimestamp("updated_at")?.toLocalDateTime(),
            )
        }

        return books.toEntities()
    }

    private fun List<Book>.toEntities() = map {
        dev.nemuki.cypherbookapi.domain.entity.Book(
            isbn = it.isbn,
            title = it.title,
            author = it.author,
            publisher = it.publisher,
            price = it.price,
            createdAt = it.createdAt,
            updatedAt = it.updatedAt
        )
    }
}
