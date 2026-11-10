package ru.tbank.seminar5.examples.trycatch;

import java.io.IOException;

/**
 * ХОРОШО: fail fast — исключения пробрасываются наверх.
 *
 * <p>Метод {@code build} не ловит ошибки на каждом шаге: каждая операция либо
 * выполняется, либо выбрасывает исключение, которое всплывает к единственному
 * обработчику — вызывающему коду. Так ошибка не теряется, а конвейер остаётся
 * коротким и читаемым.
 */
public class ReportBuilderV2 {

    private final ReportParser parser = new ReportParser();
    private final ReportWriter writer = new ReportWriter();
    private final ReportPublisher publisher = new ReportPublisher();

    /** Строит отчёт. При сбое бросает {@link ReportParseException} или {@link IOException}. */
    public String build(String input) throws ReportParseException, IOException {
        Report report = parser.parse(input);
        String formatted = writer.format(report);
        publisher.publish(formatted);
        return formatted;
    }

    public static void demo() {
        var builder = new ReportBuilderV2();
        try {
            System.out.println("Валидный ввод -> '" + builder.build("отчёт за ноябрь") + "'");
        } catch (ReportParseException | IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}