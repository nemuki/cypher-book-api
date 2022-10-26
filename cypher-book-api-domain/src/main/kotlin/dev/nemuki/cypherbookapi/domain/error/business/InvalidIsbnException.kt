package dev.nemuki.cypherbookapi.domain.error.business

class InvalidIsbnException(
    override val message: String,
) : BusinessException(message)
