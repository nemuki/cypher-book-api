package dev.nemuki.cypherbookapi.domain.error.business

// List, Get 処理が失敗した場合
class DatabaseAccessException(
    override val message: String,
) : BusinessException(message)
