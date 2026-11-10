package ru.tbank.seminar5.practice.trycatch;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Характеризационный тест для практики «try/catch на каждом шаге».
 *
 * <p>Фиксирует, что неверный код {@link PaymentImportV1} «тихо» возвращает 0 при
 * любой ошибке. Задача практики — пробросить исключения наверх (fail fast);
 * тогда тесты на невалидный ввод нужно переписать под ожидаемое исключение,
 * а тест на валидный ввод останется прежним. Чтобы проверить свою версию,
 * замените внизу {@code PaymentImportV1} на {@code PaymentImportV2}.
 */
class PaymentImportTest {

    private final PaymentImportV1 importer = new PaymentImportV1();

    @Test
    void importsWhenValid() {
        assertEquals(10, importer.importPayments("10"));
    }

    @Test
    void silentlyReturnsZeroOnParseError() {
        assertEquals(0, importer.importPayments("abc"));
    }

    @Test
    void silentlyReturnsZeroOnProcessingError() {
        assertEquals(0, importer.importPayments("5000"));
    }
}