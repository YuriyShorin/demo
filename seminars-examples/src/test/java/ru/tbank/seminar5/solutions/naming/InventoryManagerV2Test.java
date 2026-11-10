package ru.tbank.seminar5.solutions.naming;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Тест на решение {@link InventoryManagerV2}: поведение то же, что у V1, но с
 * осмысленными именами методов.
 */
class InventoryManagerV2Test {

    @Test
    void tracksStockAcrossUpdates() {
        var inventory = new InventoryManagerV2();
        inventory.addStock("pen", 5);
        inventory.addStock("pen", 3);
        inventory.addStock("book", 2);
        assertEquals(8, inventory.stockOf("pen"));
        assertEquals(2, inventory.stockOf("book"));
        assertEquals(0, inventory.stockOf("notebook"));
    }

    @Test
    void reportsAvailability() {
        var inventory = new InventoryManagerV2();
        inventory.addStock("pen", 5);
        assertTrue(inventory.isInStock("pen"));
        assertFalse(inventory.isInStock("book"));
        assertFalse(inventory.isInStock("notebook"));
    }
}