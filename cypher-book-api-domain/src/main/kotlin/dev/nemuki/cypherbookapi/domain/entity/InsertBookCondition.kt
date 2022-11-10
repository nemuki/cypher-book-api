package dev.nemuki.cypherbookapi.domain.entity

data class InsertBookCondition(
    val isbn: Isbn,
    val title: String,
    val author: String,
    val publisher: String,
    val price: Int,
)
