package ru.tbank.seminar5.practice.nesting;

import org.junit.jupiter.api.Test;

import ru.tbank.seminar5.examples.nesting.Customer;
import ru.tbank.seminar5.examples.nesting.Order;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Характеризационный тест для практики «сильная вложенность».
 *
 * <p>Фиксирует правила возврата. Сначала прогоняется против {@link RefundServiceV1}.
 * После написания {@link RefundServiceV2} замените внизу {@code RefundServiceV1}
 * на {@code RefundServiceV2} — тест должен остаться зелёным.
 */
class RefundServiceTest {

    private final RefundServiceV1 service = new RefundServiceV1();

    @Test
    void allowsRefundWithinWindow() {
        assertTrue(service.isRefundable(new Order(100), new Customer(false), 5));
    }

    @Test
    void rejectsAfterWindow() {
        assertFalse(service.isRefundable(new Order(100), new Customer(false), 20));
    }

    @Test
    void rejectsVipCustomers() {
        assertFalse(service.isRefundable(new Order(100), new Customer(true), 5));
    }

    @Test
    void rejectsLargeOrders() {
        assertFalse(service.isRefundable(new Order(60000), new Customer(false), 5));
    }

    @Test
    void rejectsNullInputs() {
        assertFalse(service.isRefundable(null, new Customer(false), 5));
        assertFalse(service.isRefundable(new Order(100), null, 5));
    }
}