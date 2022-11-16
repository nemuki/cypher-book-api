package dev.nemuki.cypherbookapi.web.entity

import com.fasterxml.jackson.annotation.JsonInclude
import dev.nemuki.cypherbookapi.domain.entity.BookExtraOption
import java.time.LocalDateTime

data class BookAddedOptionResponse(
    val isbn: String,
    val title: String,
    val author: String,
    val publisher: String,
    val price: Int,
    @JsonInclude(JsonInclude.Include.NON_NULL)
    val extra: BookExtraOption?,
    val createdAt: LocalDateTime?,
    val updatedAt: LocalDateTime?,
)
