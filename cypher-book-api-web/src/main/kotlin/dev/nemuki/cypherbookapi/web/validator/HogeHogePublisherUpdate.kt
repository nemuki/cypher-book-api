package dev.nemuki.cypherbookapi.web.validator

import dev.nemuki.cypherbookapi.web.entity.UpdateBookRequest
import dev.nemuki.cypherbookapi.web.validator.PublisherValidationConstants.FLOOR_PRICE
import dev.nemuki.cypherbookapi.web.validator.PublisherValidationConstants.VERIFY_PUBLISHER_NAME
import javax.validation.Constraint
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext
import javax.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [HogeHogePublisherUpdateValidator::class])
annotation class HogeHogePublisherUpdate(
    val message: String = "${VERIFY_PUBLISHER_NAME}はpriceが${FLOOR_PRICE}より大きい必要があります",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
)

class HogeHogePublisherUpdateValidator : ConstraintValidator<HogeHogePublisherUpdate, UpdateBookRequest> {
    override fun isValid(value: UpdateBookRequest, context: ConstraintValidatorContext?): Boolean {
        if (value.publisher != null && value.price != null) {
            if (value.publisher == VERIFY_PUBLISHER_NAME && value.price < FLOOR_PRICE) {
                return false
            }
        }
        return true
    }
}

