package ru.tbank.seminar1.interfaces;

import java.util.concurrent.Callable;

/**
 * {@link Callable} — это просто функциональный интерфейс, возвращающий значение.
 * Тут он НЕ связан с потоками: реализуем его классом-наследником и вызываем
 * {@code call()} напрямую. Потоки/пулы лишь одно из применений; суть Callable —
 * «задача, у которой есть результат и которая может выбросить исключение».
 */
public class CallableInheritanceDemo {

    /** Класс, реализующий Callable (наследование интерфейса), без всяких потоков. */
    static class FactorialCallable implements Callable<Long> {

        private final int n;

        FactorialCallable(int n) {
            this.n = n;
        }

        @Override
        public Long call() {
            long result = 1;
            for (int i = 2; i <= n; i++) {
                result *= i;
            }
            return result;
        }
    }

    /** Ещё один наследник: демонстрирует, что call() может бросать Exception. */
    static class ThrowingCallable implements Callable<String> {

        @Override
        public String call() throws Exception {
            throw new IllegalArgumentException("что-то пошло не так");
        }
    }

    public static void main(String[] args) {
        // Вызываем call() напрямую — никаких пулов и потоков.
        // call() объявлен как throws Exception, поэтому вызов обрабатываем в try-catch.
        try {
            Callable<Long> factorial = new FactorialCallable(7);
            System.out.println("7! = " + factorial.call());
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        // call() объявлен как throws Exception, поэтому сигнатура метода должна это учесть.
        try {
            new ThrowingCallable().call();
        } catch (Exception e) {
            System.out.println("Перехвачено исключение: " + e.getMessage());
        }
    }
}