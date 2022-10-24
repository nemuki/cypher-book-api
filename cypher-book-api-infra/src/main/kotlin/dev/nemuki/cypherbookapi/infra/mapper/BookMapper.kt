package dev.nemuki.cypherbookapi.infra.mapper

import dev.nemuki.cypherbookapi.infra.entity.Book
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select

@Mapper
interface BookMapper {
    @Select(
        """
            SELECT * FROM cypher.book
        """
    )
    fun selectAll(): List<Book>

    @Select(
        """
            SELECT * FROM cypher.book WHERE isbn = #{isbn}
        """
    )
    fun findByIsbn(@Param("isbn") isbn: String): List<Book>
}
