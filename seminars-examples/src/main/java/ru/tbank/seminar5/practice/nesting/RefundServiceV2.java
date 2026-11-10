package ru.tbank.seminar5.practice.nesting;

import ru.tbank.seminar5.examples.nesting.Customer;
import ru.tbank.seminar5.examples.nesting.Order;

/**
 * ПРАКТИКА, V2 (заготовка): сильная вложенность.
 *
 * <p>Публичный контракт тот же, что у {@link RefundServiceV1}. Задача: убрать
 * вложенность, сохранив поведение {@link #isRefundable}. Хороший приём —
 * защитные условия (ранний выход) и вынос составного условия в отдельный метод.
 */
public class RefundServiceV2 {

    public boolean isRefundable(Order order, Customer customer, int daysSincePurchase) {
        // TODO: уберите вложенность, сохранив прежнее поведение
        throw new UnsupportedOperationException("TODO: рефакторинг вложенности");
    }
}