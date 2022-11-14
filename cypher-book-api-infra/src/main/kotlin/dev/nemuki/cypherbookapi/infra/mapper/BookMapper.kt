package dev.nemuki.cypherbookapi.infra.mapper

import dev.nemuki.cypherbookapi.infra.entity.Book
import dev.nemuki.cypherbookapi.infra.entity.InsertBook
import org.apache.ibatis.annotations.Mapper

@Mapper
interface BookMapper {
    fun selectAll(): List<Book>
    fun findByIsbn(isbn: String): Book?
    fun insert(insertBook: InsertBook)
}
