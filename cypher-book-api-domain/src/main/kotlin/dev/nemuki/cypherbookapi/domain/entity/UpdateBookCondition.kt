package dev.nemuki.cypherbookapi.domain.entity

data class UpdateBookCondition(
    val title: String?,
    val author: String?,
    val publisher: String?,
    val price: Int?,
)
