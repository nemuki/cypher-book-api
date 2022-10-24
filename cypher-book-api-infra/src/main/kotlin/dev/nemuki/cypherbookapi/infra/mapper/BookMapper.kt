package dev.nemuki.cypherbookapi.infra.mapper

import dev.nemuki.cypherbookapi.infra.entity.Book
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param

@Mapper
interface BookMapper {
    fun selectAll(): List<Book>
    fun findByIsbn(@Param("isbn") isbn: String): Book?
}
