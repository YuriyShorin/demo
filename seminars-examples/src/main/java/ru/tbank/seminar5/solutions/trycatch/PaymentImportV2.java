package ru.tbank.seminar5.solutions.trycatch;

/**
 * РЕШЕНИЕ, V2: fail fast — исключения пробрасываются наверх.
 *
 * <p>Вместо try/catch на каждом шаге метод напрямую парсит и обрабатывает.
 * При ошибке исключение ({@link NumberFormatException} или
 * {@link IllegalArgumentException}) всплывает к единственному обработчику —
 * вызывающему коду, и ошибка не теряется.
 */
public class PaymentImportV2 {

    public int importPayments(String content) {
        int count = Integer.parseInt(content); // может бросить NumberFormatException
        return process(count);
    }

    private int process(int count) {
        if (count > 1000) {
            throw new IllegalArgumentException("Слишком много платежей: " + count);
        }
        return count;
    }
}