package dev.nemuki.cypherbookapi.domain.error.business

class AlreadyExistsException(
    override val message: String,
) : BusinessException(message)
