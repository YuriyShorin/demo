package ru.tbank.seminar5.examples.nesting;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Характеризационные тесты для рефакторинга «сильная вложенность».
 *
 * <p>V1 (вложенные if) и V2 (защитные условия) должны вести себя одинаково во
 * всех комбинациях входов. Тест перебирает ключевые случаи и сравнивает
 * результаты двух версий.
 */
class DiscountEngineTest {

    private final DiscountEngineV1 v1 = new DiscountEngineV1();
    private final DiscountEngineV2 v2 = new DiscountEngineV2();

    @Test
    void v1AndV2BehaveIdenticallyAcrossCases() {
        assertEquals(v1.priceFor(new Customer(true), new Order(1500)),
                v2.priceFor(new Customer(true), new Order(1500)));
        assertEquals(v1.priceFor(new Customer(true), new Order(500)),
                v2.priceFor(new Customer(true), new Order(500)));
        assertEquals(v1.priceFor(new Customer(false), new Order(1500)),
                v2.priceFor(new Customer(false), new Order(1500)));
        assertEquals(v1.priceFor(null, new Order(1500)),
                v2.priceFor(null, new Order(1500)));
        assertEquals(v1.priceFor(new Customer(true), null),
                v2.priceFor(new Customer(true), null));
        assertEquals(v1.priceFor(null, null), v2.priceFor(null, null));
    }

    @Test
    void appliesVipDiscountOnlyOnLargeOrders() {
        assertEquals(1200, v2.priceFor(new Customer(true), new Order(1500)));
        assertEquals(500, v2.priceFor(new Customer(true), new Order(500)));
        assertEquals(0, v2.priceFor(null, null));
    }
}