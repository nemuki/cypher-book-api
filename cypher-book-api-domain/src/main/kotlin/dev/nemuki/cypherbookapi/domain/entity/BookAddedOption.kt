package dev.nemuki.cypherbookapi.domain.entity

import java.time.LocalDateTime

data class BookAddedOption(
    val isbn: String,
    val title: String,
    val author: String,
    val publisher: String,
    val price: Int,
    val option: BookOption?,
    val createdAt: LocalDateTime?,
    val updatedAt: LocalDateTime?,
)
