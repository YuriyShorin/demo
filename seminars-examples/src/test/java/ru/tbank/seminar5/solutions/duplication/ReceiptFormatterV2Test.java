package ru.tbank.seminar5.solutions.duplication;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Тест на решение {@link ReceiptFormatterV2}: формат строк тот же, что у V1,
 * значит дублирование устранено без изменения поведения.
 */
class ReceiptFormatterV2Test {

    private final ReceiptFormatterV2 formatter = new ReceiptFormatterV2();
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