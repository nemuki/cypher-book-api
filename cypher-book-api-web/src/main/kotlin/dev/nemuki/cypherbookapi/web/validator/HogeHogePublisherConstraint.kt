package dev.nemuki.cypherbookapi.web.validator

import javax.validation.Constraint
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext
import javax.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [HogeHogePublisherValidator::class])
annotation class HogeHogePublisherConstraint(
    val message: String = "ほげほげ書店はpriceが1000より大きい必要があります",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
    val price: Int,
)

class HogeHogePublisherValidator : ConstraintValidator<HogeHogePublisherConstraint, String> {
    private lateinit var price: String

    override fun initialize(constraintAnnotation: HogeHogePublisherConstraint) {
        price = constraintAnnotation.price.toString()
    }

    override fun isValid(value: String, context: ConstraintValidatorContext?): Boolean {
        if (value == "ほげほげ書店" && price.toInt() < VALID_PRICE) {
            return false
        }
        return true
    }

    companion object {
        const val VALID_PRICE = 1000
    }
}

