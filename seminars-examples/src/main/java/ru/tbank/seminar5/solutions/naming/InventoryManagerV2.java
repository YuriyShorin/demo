package ru.tbank.seminar5.solutions.naming;

import java.util.HashMap;
import java.util.Map;

/**
 * РЕШЕНИЕ, V2: осмысленные наименования.
 *
 * <p>Код читается как обычный текст: {@code addStock}, {@code stockOf},
 * {@code isInStock}. Поле {@code stock} вместо {@code m}, параметры с понятными
 * именами. Публичные методы переименованы (в V1 это были {@code a}/{@code b}/{@code c}),
 * поэтому при проверке тестом вызовы нужно поправить под новые имена.
 */
public class InventoryManagerV2 {

    private final Map<String, Integer> stock = new HashMap<>();

    public void addStock(String itemName, int quantity) {
        stock.put(itemName, stock.getOrDefault(itemName, 0) + quantity);
    }

    public int stockOf(String itemName) {
        return stock.getOrDefault(itemName, 0);
    }

    public boolean isInStock(String itemName) {
        Integer quantity = stock.get(itemName);
        return quantity != null && quantity > 0;
    }
}