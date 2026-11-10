package ru.tbank.seminar5.examples.trycatch;

import java.io.IOException;

/** Публикует готовый отчёт. Может упасть с {@link IOException}. */
public class ReportPublisher {

    public void publish(String formatted) throws IOException {
        if (formatted == null || formatted.isBlank()) {
            throw new IOException("Нечего публиковать");
        }
        System.out.println("Опубликовано: " + formatted);
    }
}