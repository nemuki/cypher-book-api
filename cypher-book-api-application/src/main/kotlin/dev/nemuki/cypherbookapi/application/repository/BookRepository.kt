package dev.nemuki.cypherbookapi.application.repository

import dev.nemuki.cypherbookapi.domain.entity.Book
import dev.nemuki.cypherbookapi.domain.entity.BookGet
import dev.nemuki.cypherbookapi.domain.entity.UpdateBookCondition

interface BookRepository {
    fun getAll(): List<Book>
    fun getByIsbn(isbn: String): BookGet
    fun insert(book: Book)
    fun update(isbn: String, currentBookState: BookGet, updateBookCondition: UpdateBookCondition)
}
