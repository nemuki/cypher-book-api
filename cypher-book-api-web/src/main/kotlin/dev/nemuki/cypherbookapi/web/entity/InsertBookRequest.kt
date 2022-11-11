package dev.nemuki.cypherbookapi.web.entity

import org.springframework.lang.NonNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

data class InsertBookRequest(
    @field:NonNull
    @field:Pattern(regexp = "^[0-9]{13}$")
    val isbn: String,

    @field:NonNull
    @field:Size(max = 100)
    val title: String,

    @field:NonNull
    @field:Size(max = 100)
    val author: String,

    @field:NonNull
    @field:Size(max = 100)
    val publisher: String,

    @field:NonNull
    val price: Int,
)
