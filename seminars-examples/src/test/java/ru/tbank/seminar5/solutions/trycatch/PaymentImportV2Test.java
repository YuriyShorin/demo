package ru.tbank.seminar5.solutions.trycatch;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Тест на решение {@link PaymentImportV2}: в отличие от V1 (который «тихо»
 * возвращал 0), здесь ошибки пробрасываются наверх (fail fast).
 */
class PaymentImportV2Test {

    private final PaymentImportV2 importer = new PaymentImportV2();

    @Test
    void importsWhenValid() {
        assertEquals(10, importer.importPayments("10"));
    }

    @Test
    void propagatesParseError() {
        assertThrows(NumberFormatException.class, () -> importer.importPayments("abc"));
    }

    @Test
    void propagatesProcessingError() {
        assertThrows(IllegalArgumentException.class, () -> importer.importPayments("5000"));
    }
}