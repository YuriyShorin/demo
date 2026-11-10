package ru.tbank.seminar5.solutions.duplication;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * РЕШЕНИЕ, V2: дублирование устранено.
 *
 * <p>Форматирование денег и даты вынесено в приватные методы
 * {@code formatMoney} и {@code formatDate}; публичные методы только собирают
 * строку из готовых кусочков. Формат существует в одном месте — правка
 * применяется сразу ко всем потребителям.
 */
public class ReceiptFormatterV2 {

    private static final DateTimeFormatter DATE = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int SCALE = 2;

    public String line(String item, BigDecimal amount, LocalDate date) {
        return item + " — " + formatMoney(amount) + " (" + formatDate(date) + ")";
    }

    public String total(BigDecimal amount, LocalDate date) {
        return "ИТОГО " + formatMoney(amount) + " от " + formatDate(date);
    }

    private String formatMoney(BigDecimal amount) {
        return amount.setScale(SCALE, RoundingMode.HALF_UP).toPlainString() + " ₽";
    }

    private String formatDate(LocalDate date) {
        return date.format(DATE);
    }
}