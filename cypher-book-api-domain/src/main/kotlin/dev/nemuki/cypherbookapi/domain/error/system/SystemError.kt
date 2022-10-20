package dev.nemuki.cypherbookapi.domain.error.system

/**
 * システム的なエラーはこのクラスを継承する
 * Controller/Adviceでキャッチして適切に処理する必要がある
 */
@Suppress("UnnecessaryAbstractClass")
abstract class SystemError(
    message: String
) : Error(message)
