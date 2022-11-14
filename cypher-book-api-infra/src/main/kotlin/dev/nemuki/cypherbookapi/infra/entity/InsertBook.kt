package dev.nemuki.cypherbookapi.infra.entity

data class InsertBook(
    val isbn: String,
    val title: String,
    val author: String,
    val publisher: String,
    val price: Int,
)
