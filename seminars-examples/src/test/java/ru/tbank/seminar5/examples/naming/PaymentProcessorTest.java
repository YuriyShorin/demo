package ru.tbank.seminar5.examples.naming;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Характеризационные тесты для рефакторинга «плохие наименования».
 *
 * <p>Публичные методы V1 и V2 отличаются (метод переименован), поэтому тест
 * вызывает оба и проверяет эквивалентность поведения. После переименования
 * логика не должна измениться.
 */
class PaymentProcessorTest {

    @Test
    void processesPaymentWhenFundsEnough() {
        var user = new User(100);
        assertTrue(new PaymentProcessorV1().process(user, 50));
        assertEquals(95, user.getBalance()); // комиссия 10% от 50 = 5

        var user2 = new User(100);
        assertTrue(new PaymentProcessorV2().processPayment(user2, 50));
        assertEquals(95, user2.getBalance());
    }

    @Test
    void rejectsWhenFundsNotEnough() {
        var user = new User(1);
        assertFalse(new PaymentProcessorV1().process(user, 50));
        assertEquals(1, user.getBalance());

        var user2 = new User(1);
        assertFalse(new PaymentProcessorV2().processPayment(user2, 50));
        assertEquals(1, user2.getBalance());
    }
}