package ru.tbank.seminar2.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Простые примеры лямбда-выражений для студентов.
 * <p>
 * Лямбда — это компактная запись анонимной функции. Она описывает ТОЛЬКО
 * поведение (что сделать), а всё остальное — типы параметров, реализация
 * интерфейса — подставляется компилятором из контекста.
 * <p>
 * Формальный синтаксис: {@code (параметры) -> тело}
 * <ul>
 *   <li>{@code (int a, int b) -> a + b} — возвращает {@code a + b};</li>
 *   <li>{@code x -> x * 2} — один параметр, скобки можно опустить;</li>
 *   <li>{@code () -> 42} — без параметров, нужны пустые скобки;</li>
 *   <li>{@code (a, b) -> { ...; return ...; }} — блочное тело, нужен {@code return}.</li>
 * </ul>
 * <p>Чтобы лямбду можно было присвоить переменной или передать в метод, рядом
 * должен быть функциональный интерфейс — интерфейс ровно с одним абстрактным
 * методом. Самые частые уже лежат в {@code java.util.function}.
 */
public class LambdaDemo {

    public static void main(String[] args) {
        basics();
        functionalInterfaces();
        lambdasWithCollections();
    }

    /** Синтаксис: параметры, опущенные скобки, блочное тело. */
    static void basics() {
        // Функциональный интерфейс из java.util.function:
        // Function<T, R> — принимает T, возвращает R.
        Function<Integer, Integer> doubleIt = x -> x * 2;
        Function<Integer, Integer> addThree = (Integer x) -> x + 3;

        // Блочное тело: несколько операторов, обязателен return.
        Function<Integer, Integer> abs = x -> {
            if (x < 0) {
                return -x;
            }
            return x;
        };

        System.out.println("doubleIt(21)   = " + doubleIt.apply(21));
        System.out.println("addThree(10)   = " + addThree.apply(10));
        System.out.println("abs(-5)        = " + abs.apply(-5));
        System.out.println();
    }

    /** Четыре базовых интерфейса из java.util.function на одном примере. */
    static void functionalInterfaces() {
        // Supplier<T> — ничего не принимает, отдаёт значение.
        Supplier<String> greeting = () -> "Привет!";

        // Consumer<T> — принимает значение, ничего не возвращает (побочный эффект).
        Consumer<String> printer = msg -> System.out.println(msg);

        // Predicate<T> — принимает значение, возвращает boolean.
        Predicate<Integer> isEven = n -> n % 2 == 0;

        // Function<T, R> — принимает T, возвращает R.
        Function<String, Integer> length = s -> s.length();

        printer.accept(greeting.get());
        System.out.println("isEven(7) = " + isEven.test(7) + ", isEven(8) = " + isEven.test(8));
        System.out.println("length(\"Java\") = " + length.apply("Java"));
        System.out.println();
    }

    /** Где лямбды реально нужны: Stream API и методы Collections. */
    static void lambdasWithCollections() {
        List<String> names = new ArrayList<>(Arrays.asList("ivan", "maria", "peter", "anna"));

        // forEach — пройти по элементам, Consumer<T>.
        System.out.println("Все имена:");
        names.forEach(name -> System.out.println("  " + name));

        // removeIf — удалить по условию, Predicate<T>.
        names.removeIf(name -> name.startsWith("a"));
        System.out.println("После removeIf(startsWith a): " + names);

        // sort — сравнение, Comparator<T> (тоже функциональный интерфейс).
        names.sort((a, b) -> b.compareTo(a)); // по убыванию
        System.out.println("По убыванию: " + names);

        // Stream API: filter / map / sorted строят цепочку из лямбд.
        List<Integer> numbers = List.of(5, 12, 7, 20, 3, 15);
        List<Integer> result = numbers.stream()
                .filter(n -> n > 10)          // оставить > 10
                .map(n -> n * 2)              // удвоить
                .sorted((a, b) -> a - b)      // отсортировать по возрастанию
                .toList();
        System.out.println("Фильтр >10, *2, сорт: " + result);
    }
}