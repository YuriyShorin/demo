package ru.tbank.seminar3;

import java.util.*;

public class MapDemo {
    public static void main(String[] args) {

        // 1. Создание
        Map<String, Integer> map = new HashMap<>();
        map.put("Аня", 30);
        map.put("Борис", 25);
        map.put("Вера", 30);
        System.out.println(map);

        // 2. Безопасное чтение
        System.out.println(map.get("Аня")); // 30
        System.out.println(map.get("Глеб")); // null
        System.out.println(map.getOrDefault("Глеб", 0)); // 0 - без NPE
        System.out.println(map.containsKey("Аня")); // true

        // 3. Полезные методы вместо проверок на null

        // putIfAbsent - добавит только если ключа нет
        map.putIfAbsent("Аня", 99); // не заменит, Аня уже есть
        map.putIfAbsent("Глеб", 20); // добавит
        System.out.println(map);
    }
}