package ru.tbank.seminar5.practice.duplication;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * ПРАКТИКА, V2 (заготовка): дублирование.
 *
 * <p>Публичный контракт тот же, что у {@link ReceiptFormatterV1}. Задача:
 * убрать дублирование форматирования суммы и даты, сохранив прежний формат
 * строк. Хороший приём — вынести общие части в приватные методы (например,
 * {@code formatMoney} и {@code formatDate}).
 */
public class ReceiptFormatterV2 {

    public String line(String item, BigDecimal amount, LocalDate date) {
        // TODO: реализуйте без дублирования, сохранив прежний формат строки
        throw new UnsupportedOperationException("TODO: рефакторинг дублирования");
    }

    public String total(BigDecimal amount, LocalDate date) {
        // TODO: реализуйте без дублирования, сохранив прежний формат строки
        throw new UnsupportedOperationException("TODO: рефакторинг дублирования");
    }
}