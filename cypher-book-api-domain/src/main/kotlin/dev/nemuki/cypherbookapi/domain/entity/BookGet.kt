package dev.nemuki.cypherbookapi.domain.entity

import java.time.LocalDateTime

data class BookGet(
    val isbn: String,
    val title: String,
    val author: String,
    val publisher: String,
    val price: Int,
    val extra: BookExtraOption?,
    val createdAt: LocalDateTime?,
    val updatedAt: LocalDateTime?,
)
