package dev.nemuki.cypherbookapi.web.entity

import dev.nemuki.cypherbookapi.web.validator.HogeHogePublisherInsert
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

@HogeHogePublisherInsert
class InsertBookRequest(
    @get:NotEmpty
    @field:NotNull
    @field:Pattern(regexp = "^[0-9]{13}$")
    val isbn: String,

    @get:NotEmpty
    @field:NotNull
    @field:Size(max = 100)
    val title: String,

    @get:NotEmpty
    @field:NotNull
    @field:Size(max = 100)
    val author: String,

    @get:NotEmpty
    @field:NotNull
    @field:Size(max = 100)
    val publisher: String,

    @field:NotNull
    val price: Int,
)
