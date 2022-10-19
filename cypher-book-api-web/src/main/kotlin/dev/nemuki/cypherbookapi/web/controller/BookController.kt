package dev.nemuki.cypherbookapi.web.controller

import dev.nemuki.cypherbookapi.entity.Book
import org.springframework.dao.DataAccessException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestController
@RestControllerAdvice
class BookController(val bookRepository: BookRepository) {
    @GetMapping("/books")
    fun getAllBooks(): List<Book> {
        return bookRepository.getAll()
    }

    @ExceptionHandler(DataAccessException::class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleDataAccessException() = ErrorResponse("Failed to access database")
}
