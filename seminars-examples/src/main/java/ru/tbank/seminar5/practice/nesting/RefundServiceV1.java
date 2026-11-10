package ru.tbank.seminar5.practice.nesting;

import ru.tbank.seminar5.examples.nesting.Customer;
import ru.tbank.seminar5.examples.nesting.Order;

/**
 * ПРАКТИКА, V1 (неверный код): сильная вложенность.
 *
 * <p>Задача: убрать вложенность, не меняя поведение — используйте защитные
 * условия (ранний выход) и вынесите составное условие в отдельный метод.
 * Исправленную версию пишите в {@link RefundServiceV2}, проверьте себя тестом
 * {@code RefundServiceTest}.
 *
 * <p>Доменные классы {@link Customer} и {@link Order} берутся из готового
 * примера по вложенности (пакет {@code examples.nesting}).
 */
public class RefundServiceV1 {

    public boolean isRefundable(Order order, Customer customer, int daysSincePurchase) {
        if (order != null) {
            if (customer != null) {
                if (!customer.isVip()) {
                    if (daysSincePurchase <= 14) {
                        if (order.getTotal() < 50000) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}