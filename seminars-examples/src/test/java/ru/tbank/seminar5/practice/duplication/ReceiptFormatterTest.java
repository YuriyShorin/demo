package ru.tbank.seminar5.practice.duplication;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Характеризационный тест для практики «дублирование».
 *
 * <p>Тест фиксирует ожидаемый формат строк. Сначала он прогоняется против
 * неверного кода {@link ReceiptFormatterV1} и проходит. После того как ученик
 * напишет {@link ReceiptFormatterV2}, чтобы проверить себя, замените внизу
 * {@code ReceiptFormatterV1} на {@code ReceiptFormatterV2} — тест должен
 * остаться зелёным (поведение не изменилось).
 */
class ReceiptFormatterTest {

    private final ReceiptFormatterV1 formatter = new ReceiptFormatterV1();
    private final LocalDate date = LocalDate.of(2026, 11, 2);

    @Test
    void formatsLine() {
        assertEquals("Ручка — 123.45 ₽ (02.11.2026)",
                formatter.line("Ручка", new BigDecimal("123.45"), date));
    }

    @Test
    void formatsTotal() {
        assertEquals("ИТОГО 55.00 ₽ от 02.11.2026",
                formatter.total(new BigDecimal("55"), date));
    }
}