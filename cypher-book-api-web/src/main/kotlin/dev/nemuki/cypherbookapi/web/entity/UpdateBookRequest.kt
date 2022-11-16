package dev.nemuki.cypherbookapi.web.entity

import dev.nemuki.cypherbookapi.web.validator.HogeHogePublisherUpdate
import javax.validation.constraints.Min
import javax.validation.constraints.Size

@HogeHogePublisherUpdate
class UpdateBookRequest(
    @field:Size(max = 100)
    val title: String?,

    @field:Size(max = 100)
    val author: String?,

    @field:Size(max = 100)
    val publisher: String?,

    @field:Min(0)
    val price: Int?,
)
