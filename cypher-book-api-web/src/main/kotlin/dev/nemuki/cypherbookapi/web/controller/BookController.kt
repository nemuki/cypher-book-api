package dev.nemuki.cypherbookapi.web.controller

import dev.nemuki.cypherbookapi.application.usecase.FetchBook
import dev.nemuki.cypherbookapi.application.usecase.InsertBook
import dev.nemuki.cypherbookapi.domain.entity.Book
import dev.nemuki.cypherbookapi.domain.entity.Isbn
import dev.nemuki.cypherbookapi.domain.error.business.InvalidArgumentException
import dev.nemuki.cypherbookapi.web.entity.BookResponse
import dev.nemuki.cypherbookapi.web.entity.InsertBookRequest
import dev.nemuki.cypherbookapi.web.entity.SuccessResponse
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.validation.constraints.Pattern

@Validated
@RestController
@RestControllerAdvice
class BookController(
    private val fetchBook: FetchBook,
    private val insertBook: InsertBook,
) {
    @GetMapping("/books")
    fun getAllBooks(): List<BookResponse> {
        val books = fetchBook.fetchAll()
        return books.toResponse()
    }

    @GetMapping("/books/{isbn}")
    fun getBookByIsbn(@PathVariable("isbn") @Pattern(regexp = "^[0-9]{13}$") isbn: String): BookResponse {
        val book = fetchBook.fetchByIsbn(Isbn(isbn).isbn)
        return book.toResponse()
    }

    @PostMapping("/books")
    fun insertBook(
        @RequestBody @Validated insertBookRequest: InsertBookRequest,
        bindingResult: BindingResult,
    ): SuccessResponse {
        if (bindingResult.hasErrors()) {
            val validationMessages = bindingResult.allErrors.map {
                InvalidArgumentException.ValidationErrorMessage(
                    field = it.objectName,
                    description = it.defaultMessage
                )
            }
            throw InvalidArgumentException(validationMessages)
        }
        insertBook.insert(insertBookRequest.toEntity())
        return SuccessResponse("insert success")
    }

    private fun Book.toResponse() = BookResponse(
        isbn = isbn,
        title = title,
        author = author,
        publisher = publisher,
        price = price,
        createdAt = createdAt,
        updatedAt = updatedAt
    )

    private fun InsertBookRequest.toEntity() = Book(
        isbn = Isbn(isbn).isbn,
        title = title,
        author = author,
        publisher = publisher,
        price = price,
        createdAt = null,
        updatedAt = null
    )

    private fun List<Book>.toResponse(): List<BookResponse> = map {
        it.toResponse()
    }
}
