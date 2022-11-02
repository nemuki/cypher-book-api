package dev.nemuki.cypherbookapi.infra.mapper

import dev.nemuki.cypherbookapi.infra.entity.Book
import org.apache.ibatis.annotations.Mapper

@Mapper
interface BookMapper {
    fun selectAll(): List<Book>
    fun findByIsbn(isbn: String): Book?
    fun insert(isbn: String, title: String, author: String, publisher: String, price: Int)
}
