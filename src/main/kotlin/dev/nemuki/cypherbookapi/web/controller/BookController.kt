package dev.nemuki.cypherbookapi.web.controller

import dev.nemuki.cypherbookapi.repository.BookRepository
import dev.nemuki.cypherbookapi.web.entity.Book
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class BookController(val bookRepository: BookRepository) {
    @GetMapping("/book")
    fun getBookList(): List<Book> {
        return bookRepository.getBook()
    }
}
