package ru.tbank.seminar5.examples.nesting;

/** Заказ с суммой. */
public class Order {

    private final double total;

    public Order(double total) {
        this.total = total;
    }

    public double getTotal() {
        return total;
    }
}