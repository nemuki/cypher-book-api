package dev.nemuki.cypherbookapi.usecase

import dev.nemuki.cypherbookapi.domain.entity.Book
import dev.nemuki.cypherbookapi.repository.BookRepository
import org.springframework.stereotype.Component

@Component
class FetchBook (private val bookRepository: BookRepository) {
    operator fun invoke(): List<Book> {
        return bookRepository.getAll()
    }
}
