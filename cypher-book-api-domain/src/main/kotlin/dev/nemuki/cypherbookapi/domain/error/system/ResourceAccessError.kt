package dev.nemuki.cypherbookapi.domain.error.system

// データベースへのアクセスが失敗した場合
class ResourceAccessError(
    message: String,
    cause: Throwable?
) : SystemError(
    message.ifEmpty { cause?.message ?: "" }
) {
    override fun toString() =
        "ResourceAccessError\tmessage=$message"
}
