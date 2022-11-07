package dev.nemuki.cypherbookapi.application.usecase

import dev.nemuki.cypherbookapi.application.repository.BookRepository
import dev.nemuki.cypherbookapi.domain.entity.UpdateBookEntity
import org.springframework.stereotype.Component

@Component
class UpdateBook(private val bookRepository: BookRepository) {
    fun update(isbn: String, updateBookEntity: UpdateBookEntity) {
        val currentBookState = bookRepository.getByIsbn(isbn)
        bookRepository.update(isbn, currentBookState, updateBookEntity)
    }
}
