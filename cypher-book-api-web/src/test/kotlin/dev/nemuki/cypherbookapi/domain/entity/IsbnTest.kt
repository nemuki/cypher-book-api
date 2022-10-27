package dev.nemuki.cypherbookapi.domain.entity

import dev.nemuki.cypherbookapi.domain.error.business.InvalidIsbnException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class IsbnTest {

    @Test
    @DisplayName("正常系・ISBNのチェックディジットに成功する場合")
    fun successIsbnCheckDigit() {
        // 事前準備
        val excepted = "9784798142470"

        // 実行
        val actual = Isbn("9784798142470").isbn

        // 検証
        Assertions.assertEquals(excepted, actual)
    }

    @Test
    @DisplayName("異常系・ISBNのチェックディジットに失敗する場合")
    fun failureIsbnCheckDigit() {
        // 実施・検証
        assertThrows<InvalidIsbnException> {
            Isbn("9784798142471")
        }.let {
            Assertions.assertEquals("9784798142471 is invalid", it.message)
        }
    }
}
