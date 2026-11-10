package ru.tbank.seminar1.solid.kiss;

/** Заказ, на основе которого рассчитывается стоимость. */
public record Order(String item, int quantity, double pricePerUnit) {}