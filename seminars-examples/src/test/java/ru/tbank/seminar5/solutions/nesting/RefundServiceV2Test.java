package ru.tbank.seminar5.solutions.nesting;

import org.junit.jupiter.api.Test;

import ru.tbank.seminar5.examples.nesting.Customer;
import ru.tbank.seminar5.examples.nesting.Order;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Тест на решение {@link RefundServiceV2}: правила возврата те же, что у V1,
 * но без вложенных if.
 */
class RefundServiceV2Test {

    private final RefundServiceV2 service = new RefundServiceV2();

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