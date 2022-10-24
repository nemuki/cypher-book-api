package dev.nemuki.cypherbookapi.web.controller

import dev.nemuki.cypherbookapi.application.usecase.FetchBook
import dev.nemuki.cypherbookapi.domain.entity.Book
import dev.nemuki.cypherbookapi.web.entity.BookResponse
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.validation.constraints.Pattern

@Validated
@RestController
@RestControllerAdvice
class BookController(
    private val fetchBook: FetchBook
) {
    @GetMapping("/books")
    fun getAllBooks(): List<BookResponse> {
        val books = fetchBook.fetchAll()
        return books.toResponse()
    }

    @GetMapping("/books/{isbn}")
    fun getBook(@PathVariable("isbn") @Pattern(regexp = "^[0-9]{13}$") isbn: String): List<BookResponse> {
        val books = fetchBook.fetch(isbn)
        return books.toResponse()
    }

    private fun List<Book>.toResponse(): List<BookResponse> = map {
        BookResponse(
            isbn = it.isbn,
            title = it.title,
            author = it.author,
            publisher = it.publisher,
            price = it.price,
            createdAt = it.createdAt,
            updatedAt = it.updatedAt
        )
    }
}
