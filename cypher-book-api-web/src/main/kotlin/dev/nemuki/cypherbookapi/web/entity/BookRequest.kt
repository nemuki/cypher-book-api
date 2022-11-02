package dev.nemuki.cypherbookapi.web.entity

import org.springframework.lang.NonNull
import javax.validation.constraints.Size

data class BookRequest(
    @NonNull
    @Size(min = 13, max = 13)
    val isbn: String,

    @NonNull
    @Size(max = 100)
    val title: String,

    @NonNull
    @Size(max = 100)
    val author: String,

    @NonNull
    @Size(max = 100)
    val publisher: String,

    @NonNull
    val price: Int,
)
