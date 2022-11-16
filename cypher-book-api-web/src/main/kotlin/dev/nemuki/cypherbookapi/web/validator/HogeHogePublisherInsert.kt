package dev.nemuki.cypherbookapi.web.validator

import dev.nemuki.cypherbookapi.web.entity.InsertBookRequest
import dev.nemuki.cypherbookapi.web.validator.PublisherValidationConstants.FLOOR_PRICE
import dev.nemuki.cypherbookapi.web.validator.PublisherValidationConstants.PUBLISHER_NAME
import javax.validation.Constraint
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext
import javax.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [HogeHogePublisherInsertValidator::class])
annotation class HogeHogePublisherInsert(
    val message: String = "${PUBLISHER_NAME}はpriceが${FLOOR_PRICE}より大きい必要があります",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
)

class HogeHogePublisherInsertValidator : ConstraintValidator<HogeHogePublisherInsert, InsertBookRequest> {
    override fun isValid(value: InsertBookRequest, context: ConstraintValidatorContext?): Boolean {
        if (value.publisher == PUBLISHER_NAME && value.price < FLOOR_PRICE) {
            return false
        }
        return true
    }
}

