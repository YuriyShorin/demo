package ru.tbank.seminar1.interfaces;

import java.util.ArrayList;
import java.util.List;

/**
 * Зачем нужен {@link Iterable}:
 * <ul>
 *   <li>это «ключ» к конструкции {@code for-each} ({@code for (x : xs)}) — компилятор
 *       требует, чтобы {@code xs} реализовывал {@link Iterable} (или был массивом);</li>
 *   <li>любая коллекция из Java Collections Framework реализует {@code Iterable};</li>
 *   <li>своему классу достаточно реализовать {@code iterator()}, и он сразу получает
 *       for-each, {@code StreamSupport}, удобство работы со стандартной библиотекой.</li>
 * </ul>
 * <p>Это тот самый паттерн Iterator из GoF, встроенный в язык.
 */
public class IterableDemo {

    /** Свой класс, который можно обходить через for-each, просто реализовав Iterable. */
    static class TodoList implements Iterable<String> {
        private final List<String> items = new ArrayList<>();

        TodoList add(String item) {
            items.add(item);
            return this;
        }

        @Override
        public java.util.Iterator<String> iterator() {
            return items.iterator();
        }
    }

    public static void main(String[] args) {
        TodoList todos = new TodoList().add("Написать лекцию").add("Сделать примеры").add("Запустить демо");

        // Стандартная библиотека и for-each работают, потому что класс реализует Iterable.
        for (String todo : todos) {
            System.out.println("- " + todo);
        }
    }
}