package dev.nemuki.cypherbookapi.domain.entity

data class InsertBookCondition(
    val isbn: String,
    val title: String,
    val author: String,
    val publisher: String,
    val price: Int,
)
