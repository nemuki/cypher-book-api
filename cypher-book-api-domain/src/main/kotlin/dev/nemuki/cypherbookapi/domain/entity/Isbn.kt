package dev.nemuki.cypherbookapi.domain.entity

data class Isbn(
    val isbn: String,
) {
    init {
        if (isbn.length != 13) {
            throw IllegalArgumentException()
        }
        try {
            var tot = 0
            for (i in 0..11) {
                val digit = isbn.substring(i, i + 1).toInt()
                tot += if (i % 2 == 0) digit * 1 else digit * 3
            } //checksum must be 0-9. If calculated as 10 then = 0
            var checksum = 10 - tot % 10
            if (checksum == 10) {
                checksum = 0
            }
            if (checksum != isbn.substring(12).toInt()) {
                throw IllegalArgumentException()
            }
        } catch (nfe: NumberFormatException) {
            //to catch invalid ISBNs that have non-numeric characters in them
            throw IllegalArgumentException()
        }
    }
}