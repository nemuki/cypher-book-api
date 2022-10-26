package dev.nemuki.cypherbookapi.domain.entity

import dev.nemuki.cypherbookapi.domain.error.business.InvalidIsbnException

data class Isbn(
    val isbn: String,
) {
    init {
        // 1 -> 12 文字目までリスト化 → 足し算
        val checksum = isbn.substring(ISBN_CHECK_RANGE).toList().mapIndexed { index, digit ->
            if (index % 2 == 0) {
                Character.getNumericValue(digit)
            } else {
                Character.getNumericValue(digit) * ISBN_CHECK_WEIGHT
            }
        }.sum()

        if (checksum % ISBN_CHECK_MODULO != isbn.substring(ISBN_CHECK_DIGIT).toInt()) {
            throw InvalidIsbnException("$isbn is invalid")
        }
    }

    companion object {
        val ISBN_CHECK_RANGE = 0..11
        const val ISBN_CHECK_WEIGHT = 3
        const val ISBN_CHECK_MODULO = 10
        const val ISBN_CHECK_DIGIT = 12
    }
}
