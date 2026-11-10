package ru.tbank.seminar5.practice.trycatch;

/**
 * ПРАКТИКА, V2 (заготовка): try/catch на каждом шаге.
 *
 * <p>Публичный контракт тот же, что у {@link PaymentImportV1}. Задача: убрать
 * try/catch на каждом шаге — пусть исключения пробрасываются наверх (fail fast).
 * Тогда на валидном вводе результат прежний, а на ошибочном — исключение
 * (а не молчаливый 0).
 */
public class PaymentImportV2 {

    public int importPayments(String content) {
        // TODO: реализуйте fail fast без try/catch на каждом шаге
        throw new UnsupportedOperationException("TODO: рефакторинг обработки ошибок");
    }

    private int process(int count) {
        // TODO: перенесите/оставьте логику обработки
        throw new UnsupportedOperationException("TODO: рефакторинг обработки ошибок");
    }
}