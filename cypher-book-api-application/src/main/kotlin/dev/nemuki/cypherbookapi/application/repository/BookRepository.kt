package dev.nemuki.cypherbookapi.application.repository

import dev.nemuki.cypherbookapi.domain.entity.Book
import dev.nemuki.cypherbookapi.domain.entity.InsertBookCondition
import dev.nemuki.cypherbookapi.domain.entity.UpdateBookCondition

interface BookRepository {
    fun getAll(): List<Book>
    fun getByIsbn(isbn: String): Book
    fun insert(insertBookCondition: InsertBookCondition)
    fun update(isbn: String, currentBookState: Book, updateBookCondition: UpdateBookCondition)
}
