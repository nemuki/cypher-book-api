package dev.nemuki.cypherbookapi.domain.entity

import dev.nemuki.cypherbookapi.domain.error.business.InvalidIsbnException

data class Isbn(
    val isbn: String,
) {
    init {
        // 1 -> 12 文字目までリスト化 → 足し算
        val isbnSum = isbn.substring(0, 11).toList().mapIndexed { index, digit ->
            if (index % 2 == 0) {
                digit.code
            } else {
                digit.code * 3
            }
        }.sum()

        if (isbnSum % 10 != isbn.substring(12).toInt()) {
            throw InvalidIsbnException("$isbn is invalid")
        }
    }
}
