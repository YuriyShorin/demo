package ru.tbank.seminar5.practice.naming;

import java.util.HashMap;
import java.util.Map;

/**
 * ПРАКТИКА, V2 (заготовка): плохие наименования.
 *
 * <p>Публичный контракт тот же, что у {@link InventoryManagerV1}. Задача:
 * переименовать так, чтобы код читался как текст. Публичные методы
 * {@code a}/{@code b}/{@code c} можно переименовать — тогда поправьте и
 * вызовы в тесте {@code InventoryManagerTest}.
 */
public class InventoryManagerV2 {

    private final Map<String, Integer> m = new HashMap<>();

    public void a(String n, int q) {
        // TODO: переименуйте всё и реализуйте добавление на склад
        throw new UnsupportedOperationException("TODO: рефакторинг наименований");
    }

    public int b(String n) {
        // TODO: переименуйте и реализуйте получение остатка
        throw new UnsupportedOperationException("TODO: рефакторинг наименований");
    }

    public boolean c(String n) {
        // TODO: переименуйте и реализуйте проверку наличия
        throw new UnsupportedOperationException("TODO: рефакторинг наименований");
    }
}