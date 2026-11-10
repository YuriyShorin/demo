package ru.tbank.seminar5.solutions.nesting;

import ru.tbank.seminar5.examples.nesting.Customer;
import ru.tbank.seminar5.examples.nesting.Order;

/**
 * РЕШЕНИЕ, V2: вложенность устранена защитными условиями (guard clauses).
 *
 * <p>Каждый исключительный случай обрабатывается ранним выходом {@code return
 * false}, поэтому метод читается сверху вниз без «развёртки» вложенных if.
 * Магические числа вынесены в константы.
 */
public class RefundServiceV2 {

    private static final int REFUND_WINDOW_DAYS = 14;
    private static final double MAX_REFUND_AMOUNT = 50000;

    public boolean isRefundable(Order order, Customer customer, int daysSincePurchase) {
        if (order == null || customer == null) {
            return false;
        }
        if (customer.isVip()) {
            return false;
        }
        if (daysSincePurchase > REFUND_WINDOW_DAYS) {
            return false;
        }
        return order.getTotal() < MAX_REFUND_AMOUNT;
    }
}