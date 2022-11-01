package dev.nemuki.cypherbookapi.web.advice

import dev.nemuki.cypherbookapi.domain.error.business.DataNotFoundException
import dev.nemuki.cypherbookapi.domain.error.business.DatabaseAccessException
import dev.nemuki.cypherbookapi.domain.error.business.InvalidIsbnException
import dev.nemuki.cypherbookapi.domain.error.system.ResourceAccessError
import dev.nemuki.cypherbookapi.web.entity.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.validation.ConstraintViolationException

@RestControllerAdvice
class ExceptionHandlerAdvice {
    @ExceptionHandler(ResourceAccessError::class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleDataAccessException() = ErrorResponse("Failed to access database")

    @ExceptionHandler(DatabaseAccessException::class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleDatabaseAccessException(ex: DatabaseAccessException) = ErrorResponse(ex.message)

    @ExceptionHandler(ConstraintViolationException::class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    fun handleValidationException(ex: ConstraintViolationException) =
        ErrorResponse("invalid parameter: detail: [${ex.localizedMessage}]")

    @ExceptionHandler(DataNotFoundException::class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    fun handleDataNotFoundException(ex: DataNotFoundException) =
        ErrorResponse(ex.message)

    @ExceptionHandler(InvalidIsbnException::class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    fun invalidIsbnExceptionException(ex: InvalidIsbnException) =
        ErrorResponse(ex.message)
}
