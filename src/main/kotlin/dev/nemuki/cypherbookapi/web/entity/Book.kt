package dev.nemuki.cypherbookapi.web.entity

import java.util.Date

data class Book(
    val isbn: String,
    val title: String,
    val author: String,
    val publisher: String,
    val price: Int,
    val createdAt: Date?,
    val updatedAt: Date?,
)
