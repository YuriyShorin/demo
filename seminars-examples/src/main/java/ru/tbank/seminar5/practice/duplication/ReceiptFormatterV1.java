package ru.tbank.seminar5.practice.duplication;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * ПРАКТИКА, V1 (неверный код): дублирование.
 *
 * <p>Форматирование денег («123.45 ₽») и даты («дд.ММ.гггг») продублировано
 * в обоих методах.
 *
 * <p>Задача: устранить дублирование в исправленной версии — пишите её в
 * {@link ReceiptFormatterV2}. Поведение ({@link #line} и {@link #total}) менять
 * нельзя — проверьте себя тестом {@code ReceiptFormatterTest}.
 */
public class ReceiptFormatterV1 {

    private static final DateTimeFormatter DATE = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String line(String item, BigDecimal amount, LocalDate date) {
        String money = amount.setScale(2, RoundingMode.HALF_UP).toPlainString() + " ₽";
        return item + " — " + money + " (" + date.format(DATE) + ")";
    }

    public String total(BigDecimal amount, LocalDate date) {
        String money = amount.setScale(2, RoundingMode.HALF_UP).toPlainString() + " ₽";
        return "ИТОГО " + money + " от " + date.format(DATE);
    }
}