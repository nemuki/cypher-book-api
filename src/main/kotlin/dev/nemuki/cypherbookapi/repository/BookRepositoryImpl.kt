package dev.nemuki.cypherbookapi.repository

import dev.nemuki.cypherbookapi.web.entity.Book
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class BookRepositoryImpl(val jdbcTemplate: JdbcTemplate) : BookRepository {
    override fun getAll(): List<Book> {
        return jdbcTemplate.query("select * from cypher.book") { rs, _ ->
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
    }
}
