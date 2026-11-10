package ru.tbank.seminar3;

import java.util.*;

public class SetDemo {
    public static void main(String[] args) {
        List<Integer> input = List.of(-1, 15, 5, 4, 3, 6, 0, 4, 2, 1, 5, 8, 9, 7);

        List<String> list = List.of("ABB", "BAB", "BBA");

        Set<String> hash1 = new HashSet<>(list);
        Set<String> linked1= new LinkedHashSet<>(list);
        Set<String> tree1 = new TreeSet<>(list);

        System.out.println("HashSet:       " + hash1);   // порядок непредсказуем
        System.out.println("LinkedHashSet: " + linked1); // порядок вставки
        System.out.println("TreeSet:       " + tree1);   // отсортированный

        Set<Integer> hash = new HashSet<>(input);
        Set<Integer> linked = new LinkedHashSet<>(input);
        Set<Integer> tree = new TreeSet<>(input);

        System.out.println("HashSet:       " + hash);   // порядок непредсказуем
        System.out.println("LinkedHashSet: " + linked); // порядок вставки
        System.out.println("TreeSet:       " + tree);   // отсортированный
    }
}
