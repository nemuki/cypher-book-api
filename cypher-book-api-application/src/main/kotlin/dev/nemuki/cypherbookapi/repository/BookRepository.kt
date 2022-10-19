package dev.nemuki.cypherbookapi.repository

import dev.nemuki.cypherbookapi.domain.entity.Book

interface BookRepository {
    fun getAll(): List<Book>
}
