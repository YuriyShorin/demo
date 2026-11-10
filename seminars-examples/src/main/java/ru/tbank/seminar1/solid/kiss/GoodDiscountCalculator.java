package ru.tbank.seminar1.solid.kiss;

/**
 * ХОРОШО: принцип KISS (Keep It Simple, Stupid) — самое простое решение, которое
 * работает.
 *
 * <p>Та же скидка (10% при покупке от 10 единиц) выражена двумя проверками без
 * лишних абстракций. Код читается сверху вниз, поведение очевидно, добавить
 * новое правило так же просто, как дописать ещё один {@code if}.
 */
public class GoodDiscountCalculator {

    private static final int BULK_QUANTITY = 10;
    private static final double BULK_DISCOUNT = 0.9;

    public double calculate(Order order) {
        double total = order.quantity() * order.pricePerUnit();
        if (order.quantity() >= BULK_QUANTITY) {
            total *= BULK_DISCOUNT;
        }
        return total;
    }

    public static void demo() {
        var calc = new GoodDiscountCalculator();
        System.out.println("5  шт. по 100 -> " + calc.calculate(new Order("pen", 5, 100)));
        System.out.println("12 шт. по 100 -> " + calc.calculate(new Order("pen", 12, 100)));
    }
}