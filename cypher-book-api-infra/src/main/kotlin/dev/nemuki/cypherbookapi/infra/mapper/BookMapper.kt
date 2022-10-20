package dev.nemuki.cypherbookapi.infra.mapper

import dev.nemuki.cypherbookapi.infra.entity.Book
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select

@Mapper
interface BookMapper {
    @Select(
        """
            SELECT * from cypher.book
        """
    )
    fun selectAll(): List<Book>
}
