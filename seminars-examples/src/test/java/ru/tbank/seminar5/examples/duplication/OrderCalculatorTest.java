package ru.tbank.seminar5.examples.duplication;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Характеризационные тесты для рефакторинга «устранение дублирования».
 *
 * <p>Тесты фиксируют ожидаемое поведение публичного API. V1 (неверный код)
 * функционально корректен, но задублирован — тесты против него проходят. Тот же
 * набор ожиданий применяется к V2: если рефакторинг не изменил поведение, V2
 * тоже проходит. Это и есть проверка «переключиться на правильный и убедиться,
 * что поведение не сломалось».
 */
class OrderCalculatorTest {

    private final Order order = new Order(new BigDecimal("10.00"), 3);
    private final BigDecimal discount = new BigDecimal("0.10");

    @Test
    void v1AndV2GiveSameDiscountTotal() {
        var v1 = new OrderCalculatorV1().totalWithDiscount(order, discount);
        var v2 = new OrderCalculatorV2().totalWithDiscount(order, discount);
        assertEquals(new BigDecimal("27.00"), v1);
        assertEquals(v1, v2);
    }

    @Test
    void v1AndV2GiveSameTaxTotal() {
        var v1 = new OrderCalculatorV1().totalWithTax(order);
        var v2 = new OrderCalculatorV2().totalWithTax(order);
        assertEquals(new BigDecimal("36.00"), v1);
        assertEquals(v1, v2);
    }

    @Test
    void roundsHalfUpToKopeks() {
        // 10.33 * 3 = 30.99; 30.99 * 1.2 = 37.188 -> 37.19
        assertEquals(new BigDecimal("37.19"),
                new OrderCalculatorV2().totalWithTax(new Order(new BigDecimal("10.33"), 3)));
    }
}