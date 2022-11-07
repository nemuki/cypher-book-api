package dev.nemuki.cypherbookapi.domain.entity

data class BookUpdateCondition(
    val title: String?,
    val author: String?,
    val publisher: String?,
    val price: Int?,
)
