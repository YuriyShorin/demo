package ru.tbank.seminar5.examples.duplication;

import java.math.BigDecimal;

/** Товарная позиция: цена за единицу и количество. */
public record Order(BigDecimal price, int quantity) {
}