package dev.nemuki.cypherbookapi.repository

import dev.nemuki.cypherbookapi.entity.Book

interface BookRepository {
    fun getAll(): List<Book>
}
