package ru.tbank.seminar5.practice.trycatch;

/**
 * ПРАКТИКА, V1 (неверный код): try/catch на каждом шаге.
 *
 * <p>Каждый шаг импорта платёжного файла обёрнут в try/catch, который
 * проглатывает ошибку и подставляет значение по умолчанию (0). Вызывающий код
 * не узнает, что импорт фактически не удался.
 *
 * <p>Задача: убрать try/catch на каждом шаге — пусть исключения пробрасываются
 * наверх (fail fast), а обработка останется на одном уровне. Поведение метода
 * {@link #importPayments} на валидном вводе сохранить. Исправленную версию
 * пишите в {@link PaymentImportV2}, проверьте себя тестом {@code PaymentImportTest}.
 */
public class PaymentImportV1 {

    public int importPayments(String content) {
        int count;
        try {
            count = Integer.parseInt(content); // может бросить NumberFormatException
        } catch (NumberFormatException e) {
            System.out.println("Не удалось прочитать количество: " + e.getMessage());
            count = 0;
        }

        int processed;
        try {
            processed = process(count);
        } catch (RuntimeException e) {
            System.out.println("Не удалось обработать: " + e.getMessage());
            processed = 0;
        }
        return processed;
    }

    private int process(int count) {
        if (count > 1000) {
            throw new RuntimeException("Слишком много платежей: " + count);
        }
        return count;
    }
}