package dev.nemuki.cypherbookapi.application.usecase

import dev.nemuki.cypherbookapi.application.repository.BookRepository
import dev.nemuki.cypherbookapi.domain.entity.Book
import dev.nemuki.cypherbookapi.domain.entity.BookAddedOption
import org.springframework.stereotype.Component

@Component
class FetchBook (private val bookRepository: BookRepository) {
    fun fetchAll(): List<Book> = bookRepository.getAll()
    fun fetchByIsbn(isbn: String): BookAddedOption = bookRepository.getByIsbn(isbn)
}
