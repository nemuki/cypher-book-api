package dev.nemuki.cypherbookapi.application.usecase

import dev.nemuki.cypherbookapi.application.repository.BookRepository
import dev.nemuki.cypherbookapi.domain.entity.InsertBookCondition
import org.springframework.stereotype.Component

@Component
class InsertBook(private val bookRepository: BookRepository) {
    fun insert(insertBookCondition: InsertBookCondition) = bookRepository.insert(insertBookCondition)
}
