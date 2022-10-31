package dev.nemuki.cypherbookapi.domain.entity

import dev.nemuki.cypherbookapi.domain.error.business.InvalidIsbnException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class IsbnTest {

    @ParameterizedTest
    @MethodSource("successCase")
    @DisplayName("正常系・ISBNのチェックディジットに成功する場合")
    fun successIsbnCheckDigit(isbn: String) {
        // 実行
        val actual = Isbn(isbn).isbn

        // 検証
        Assertions.assertEquals(isbn, actual)
    }

    fun successCase() = listOf(
        Arguments.of("9784798142470"),
        Arguments.of("9784297127473")
    )

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
