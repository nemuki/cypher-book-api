package dev.nemuki.cypherbookapi.application.usecase

import dev.nemuki.cypherbookapi.application.repository.BookRepository
import dev.nemuki.cypherbookapi.domain.entity.BookUpdateCondition
import org.springframework.stereotype.Component

@Component
class UpdateBook(private val bookRepository: BookRepository) {
    fun update(isbn: String, bookUpdateCondition: BookUpdateCondition) {
        val currentBookState = bookRepository.getByIsbn(isbn)
        bookRepository.update(isbn, currentBookState, bookUpdateCondition)
    }
}
