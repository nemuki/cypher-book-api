package dev.nemuki.cypherbookapi.domain.error.business

@Suppress("UnnecessaryAbstractClass")
abstract class BusinessException(
    message: String
) : Exception(message)
