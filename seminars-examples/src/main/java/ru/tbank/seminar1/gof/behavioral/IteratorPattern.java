package ru.tbank.seminar1.gof.behavioral;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Поведенческий паттерн Iterator — предоставляет способ последовательного обхода
 * коллекции, не раскрывая её внутреннюю структуру. Здесь — свой итератор по массиву
 * (демонстрация принципа; в Java уже есть встроенный java.util.Iterator).
 */
public class IteratorPattern {

    /** Коллекция, отдающая итератор. */
    public static class NameCollection implements Iterable<String> {
        private final String[] names;

        public NameCollection(String[] names) {
            this.names = names;
        }

        public Iterator<String> iterator() {
            return new NameIterator();
        }

        /** Итератор скрывает способ хранения (массив). */
        private class NameIterator implements Iterator<String> {
            private int index = 0;

            public boolean hasNext() {
                return index < names.length;
            }

            public String next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return names[index++];
            }
        }
    }

    public static void demo() {
        System.out.println("== Iterator ==");
        NameCollection collection = new NameCollection(new String[]{"Alice", "Bob", "Carol"});
        for (String name : collection) { // единообразный обход через итератор
            System.out.println(name);
        }
    }
}