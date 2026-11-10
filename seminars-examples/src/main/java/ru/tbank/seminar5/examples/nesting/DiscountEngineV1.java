package ru.tbank.seminar5.examples.nesting;

/**
 * ПЛОХО: сильная вложенность.
 *
 * <p>Логика уходит вглубь на четыре уровня {@code if}. Чтобы понять, при каких
 * условиях применяется скидка, приходится мысленно «разворачивать» вложенные
 * условия. Чем больше веток — тем сложнее читать и тем проще ошибиться с
 * расстановкой скобок и фигурных скобок.
 */
public class DiscountEngineV1 {

    private static final double VIP_THRESHOLD = 1000;
    private static final double VIP_DISCOUNT = 0.8;

    public double priceFor(Customer customer, Order order) {
        double price = order == null ? 0 : order.getTotal();
        if (customer != null) {
            if (customer.isVip()) {
                if (order != null) {
                    if (order.getTotal() >= VIP_THRESHOLD) {
                        price = order.getTotal() * VIP_DISCOUNT;
                    }
                }
            }
        }
        return price;
    }

    public static void demo() {
        var engine = new DiscountEngineV1();
        var vip = new Customer(true);
        var order = new Order(1500);
        System.out.println("VIP, заказ 1500 -> " + engine.priceFor(vip, order));
    }
}