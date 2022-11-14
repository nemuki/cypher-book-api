package dev.nemuki.cypherbookapi.web.entity

import dev.nemuki.cypherbookapi.domain.error.business.InvalidArgumentException
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

data class InsertBookRequest(
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
) {
    init {
        if (publisher == "ほげほげ書店") {
            if (price < 1000) {
                throw InvalidArgumentException(
                    listOf(
                        InvalidArgumentException.ValidationErrorMessage(
                            field = "price",
                            description = "ほげほげ書店はpriceが1000より大きい必要があります"
                        )
                    )
                )
            }
        }
    }
}
