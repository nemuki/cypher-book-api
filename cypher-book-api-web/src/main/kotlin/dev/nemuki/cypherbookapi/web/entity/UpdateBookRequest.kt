package dev.nemuki.cypherbookapi.web.entity

import javax.validation.constraints.Size

data class UpdateBookRequest(
    @Size(max = 100)
    val title: String?,

    @Size(max = 100)
    val author: String?,

    @Size(max = 100)
    val publisher: String?,

    val price: Int?,
)
