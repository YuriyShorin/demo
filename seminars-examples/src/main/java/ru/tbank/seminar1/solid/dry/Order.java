package ru.tbank.seminar1.solid.dry;

/** Заказ, по которому считается итоговая сумма. */
public record Order(String item, int quantity, double pricePerUnit) {}