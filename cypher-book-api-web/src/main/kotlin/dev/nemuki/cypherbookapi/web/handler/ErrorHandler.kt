package dev.nemuki.cypherbookapi.web.handler

import dev.nemuki.cypherbookapi.domain.error.system.ResourceAccessError
import dev.nemuki.cypherbookapi.web.entity.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.validation.ConstraintViolationException

@RestControllerAdvice
class ErrorHandler {
    @ExceptionHandler(ResourceAccessError::class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleDataAccessException() = ErrorResponse("Failed to access database")

    @ExceptionHandler(ConstraintViolationException::class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    fun handleValidationException(ex: ConstraintViolationException) =
        ErrorResponse("invalid parameter: detail: [${ex.localizedMessage}]")
}
