package dev.nemuki.cypherbookapi.application.repository

import dev.nemuki.cypherbookapi.domain.entity.Book

interface BookRepository {
    fun getAll(): List<Book>
}
