package dev.nemuki.cypherbookapi.web.controller

import dev.nemuki.cypherbookapi.domain.entity.Book
import dev.nemuki.cypherbookapi.usecase.FetchBook
import dev.nemuki.cypherbookapi.web.entity.ErrorResponse
import org.springframework.dao.DataAccessException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestController
@RestControllerAdvice
class BookController(
    private val fetchBook: FetchBook
) {
    @GetMapping("/books")
    fun getAllBooks(): List<Book> {
        return fetchBook()
    }

    @ExceptionHandler(DataAccessException::class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleDataAccessException() = ErrorResponse("Failed to access database")
}
