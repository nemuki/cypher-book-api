package dev.nemuki.cypherbookapi.repository

import dev.nemuki.cypherbookapi.web.entity.Book

interface BookRepository {
    fun getAll(): List<Book>
}