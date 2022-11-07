package dev.nemuki.cypherbookapi.application.repository

import dev.nemuki.cypherbookapi.domain.entity.Book
import dev.nemuki.cypherbookapi.domain.entity.BookUpdateCondition

interface BookRepository {
    fun getAll(): List<Book>
    fun getByIsbn(isbn: String): Book
    fun insert(book: Book)
    fun update(isbn: String, currentBookState: Book, bookUpdateCondition: BookUpdateCondition)
}
