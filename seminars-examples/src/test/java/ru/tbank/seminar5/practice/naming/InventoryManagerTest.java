package ru.tbank.seminar5.practice.naming;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Характеризационный тест для практики «плохие наименования».
 *
 * <p>Фиксирует поведение бессмысленно названных методов {@code a}/{@code b}/{@code c}.
 * После переименования в {@link InventoryManagerV2} тест должен остаться зелёным.
 * Чтобы проверить V2, замените внизу {@code InventoryManagerV1} на
 * {@code InventoryManagerV2} (если переименуете методы — поправьте и вызовы).
 */
class LInventoryManagerTest {

    @Test
    void tracksStockAcrossUpdates() {
        var inventory = new InventoryManagerV1();
        inventory.a("pen", 5);
        inventory.a("pen", 3);
        inventory.a("book", 2);
        assertEquals(8, inventory.b("pen"));
        assertEquals(2, inventory.b("book"));
        assertEquals(0, inventory.b("notebook"));
    }

    @Test
    void reportsAvailability() {
        var inventory = new InventoryManagerV1();
        inventory.a("pen", 5);
        assertTrue(inventory.c("pen"));
        assertFalse(inventory.c("book"));
        assertFalse(inventory.c("notebook"));
    }
}