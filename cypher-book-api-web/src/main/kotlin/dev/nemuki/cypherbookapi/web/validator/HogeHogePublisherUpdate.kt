package dev.nemuki.cypherbookapi.web.validator

import dev.nemuki.cypherbookapi.web.entity.UpdateBookRequest
import javax.validation.Constraint
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext
import javax.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [HogeHogePublisherUpdateValidator::class])
annotation class HogeHogePublisherUpdate(
    val message: String = "ほげほげ書店はpriceが1000より大きい必要があります",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
)

class HogeHogePublisherUpdateValidator : ConstraintValidator<HogeHogePublisherUpdate, UpdateBookRequest> {
    override fun isValid(value: UpdateBookRequest, context: ConstraintValidatorContext?): Boolean {
        if (value.publisher != null && value.price != null) {
            if (value.publisher == "ほげほげ書店" && value.price < VALID_PRICE) {
                return false
            }
        }
        return true
    }

    companion object {
        const val VALID_PRICE = 1000
    }
}

