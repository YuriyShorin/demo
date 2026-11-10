package ru.tbank.seminar5.examples.nesting;

/**
 * ХОРОШО: вложенность устранена защитными условиями (guard clauses).
 *
 * <p>Составное условие «достоин ли клиент скидки» вынесено в отдельный метод
 * {@code isEligibleForVipDiscount}, а все исключительные случаи обрабатываются
 * ранним выходом. Каждая ветка читается сверху вниз без мысленной «развёртки»
 * вложенных if.
 */
public class DiscountEngineV2 {

    private static final double VIP_THRESHOLD = 1000;
    private static final double VIP_DISCOUNT = 0.8;

    public double priceFor(Customer customer, Order order) {
        if (isEligibleForVipDiscount(customer, order)) {
            return order.getTotal() * VIP_DISCOUNT;
        }
        return basePrice(order);
    }

    private boolean isEligibleForVipDiscount(Customer customer, Order order) {
        return customer != null && customer.isVip()
                && order != null && order.getTotal() >= VIP_THRESHOLD;
    }

    private double basePrice(Order order) {
        return order == null ? 0 : order.getTotal();
    }

    public static void demo() {
        var engine = new DiscountEngineV2();
        var vip = new Customer(true);
        var order = new Order(1500);
        System.out.println("VIP, заказ 1500 -> " + engine.priceFor(vip, order));
    }
}