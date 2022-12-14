package dev.nemuki.cypherbookapi.web.entity

import java.time.LocalDateTime

data class BookResponse(
    val isbn: String,
    val title: String,
    val author: String,
    val publisher: String,
    val price: Int,
    val createdAt: LocalDateTime?,
    val updatedAt: LocalDateTime?,
)
