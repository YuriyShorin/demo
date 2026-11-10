package ru.tbank.seminar5.practice.naming;

import java.util.HashMap;
import java.util.Map;

/**
 * ПРАКТИКА, V1 (неверный код): плохие наименования.
 *
 * <p>Все имена бессмысленные: {@code m}, {@code a}, {@code b}, {@code c}.
 * Задача: переименовать так, чтобы код читался как обычный текст. Менять можно
 * только имена — поведение публичных методов {@code a}, {@code b} и {@code c}
 * должно сохраниться. Исправленную версию пишите в {@link InventoryManagerV2},
 * проверьте себя тестом {@code InventoryManagerTest}.
 */
public class InventoryManagerV1 {

    private final Map<String, Integer> m = new HashMap<>();

    public void a(String n, int q) {
        m.put(n, m.getOrDefault(n, 0) + q);
    }

    public int b(String n) {
        return m.getOrDefault(n, 0);
    }

    public boolean c(String n) {
        Integer v = m.get(n);
        return v != null && v > 0;
    }
}