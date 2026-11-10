package ru.tbank.seminar3;

import java.util.*;

public class ListBenchmark {

    public static void main(String[] args) {
        int n = 100_000;

        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            arrayList.add(i);
            linkedList.add(i);
        }

        // 1. Случайный доступ — ArrayList вне конкуренции
        measure("ArrayList.get", () -> {
            long sum = 0;
            for (int i = 0; i < n; i++) sum += arrayList.get(i);
            return sum;
        });
        measure("LinkedList.get", () -> {
            long sum = 0;
            for (int i = 0; i < n; i++) sum += linkedList.get(i); // O(n) каждый раз!
            return sum;
        });

        // 2. Вставка в начало — здесь LinkedList выигрывает
        measure("ArrayList.add(0)", () -> {
            List<Integer> l = new ArrayList<>();
            for (int i = 0; i < 50_000; i++) l.add(0, i);
            return l.size();
        });
        measure("LinkedList.add(0)", () -> {
            List<Integer> l = new LinkedList<>();
            for (int i = 0; i < 50_000; i++) l.add(0, i);
            return l.size();
        });
    }

    static void measure(String label, java.util.function.Supplier<Object> task) {
        long start = System.nanoTime();
        Object result = task.get();
        long ms = (System.nanoTime() - start) / 1_000_000;
        System.out.printf("%-20s %5d ms (result=%s)%n", label, ms, result);
    }
}
