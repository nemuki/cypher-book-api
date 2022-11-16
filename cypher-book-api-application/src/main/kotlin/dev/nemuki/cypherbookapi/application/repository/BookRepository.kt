package dev.nemuki.cypherbookapi.application.repository

import dev.nemuki.cypherbookapi.domain.entity.Book
import dev.nemuki.cypherbookapi.domain.entity.BookAddedOption
import dev.nemuki.cypherbookapi.domain.entity.UpdateBookCondition

interface BookRepository {
    fun getAll(): List<Book>
    fun getByIsbn(isbn: String): BookAddedOption
    fun insert(book: Book)
    fun update(isbn: String, currentBookState: BookAddedOption, updateBookCondition: UpdateBookCondition)
}
