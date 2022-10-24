package dev.nemuki.cypherbookapi.domain.error.business

class DataNotFoundException(
    override val message: String
) : BusinessException(message)
