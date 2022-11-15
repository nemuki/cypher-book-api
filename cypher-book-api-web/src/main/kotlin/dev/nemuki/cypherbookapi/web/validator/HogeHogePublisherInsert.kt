package dev.nemuki.cypherbookapi.web.validator

import dev.nemuki.cypherbookapi.web.entity.InsertBookRequest
import javax.validation.Constraint
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext
import javax.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [HogeHogePublisherInsertValidator::class])
annotation class HogeHogePublisherInsert(
    val message: String = "ほげほげ書店はpriceが1000より大きい必要があります",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
)

class HogeHogePublisherInsertValidator : ConstraintValidator<HogeHogePublisherInsert, InsertBookRequest> {
    override fun isValid(value: InsertBookRequest, context: ConstraintValidatorContext?): Boolean {
        if (value.publisher == "ほげほげ書店" && value.price < VALID_PRICE) {
            return false
        }
        return true
    }

    companion object {
        const val VALID_PRICE = 1000
    }
}

