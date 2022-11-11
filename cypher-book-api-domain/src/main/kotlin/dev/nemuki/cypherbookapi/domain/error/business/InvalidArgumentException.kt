package dev.nemuki.cypherbookapi.domain.error.business

class InvalidArgumentException(
    messages: List<ValidationErrorMessage>,
) : BusinessException(messages.joinToString(" ")) {
    class ValidationErrorMessage(
        val field: String?,
        val description: String?,
    ) {
        override fun toString() = "$field: $description"
    }
}
