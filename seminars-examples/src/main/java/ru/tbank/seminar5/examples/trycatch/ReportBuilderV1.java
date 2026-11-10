package ru.tbank.seminar5.examples.trycatch;

import java.io.IOException;

/**
 * ПЛОХО: try/catch на каждом шаге.
 *
 * <p>Каждый шаг конвейера (разбор → форматирование → публикация) обёрнут в
 * собственный try/catch, который проглатывает ошибку (печатает в консоль) и
 * возвращает пустую строку. Из-за этого:
 * <ul>
 *   <li>вызывающий код не узнаёт, что обработка фактически не удалась — метод
 *       «тихо» вернёт пустую строку;</li>
 *   <li>при цепочке шагов одно скрытое падение тянет за собой остальные, и
 *       причину приходится искать в логах;</li>
 *   <li>условие на каждый шаг раздувает метод и прячет основной поток логики.</li>
 * </ul>
 */
public class ReportBuilderV1 {

    private final ReportParser parser = new ReportParser();
    private final ReportWriter writer = new ReportWriter();
    private final ReportPublisher publisher = new ReportPublisher();

    /** Строит отчёт. При сбое «тихо» возвращает пустую строку. */
    public String build(String input) {
        Report report;
        try {
            report = parser.parse(input);
        } catch (ReportParseException e) {
            System.out.println("Разбор не удался: " + e.getMessage());
            return ""; // ошибка проглочена — вызывающий не узнает
        }

        String formatted;
        try {
            formatted = writer.format(report);
        } catch (IOException e) {
            System.out.println("Форматирование не удалось: " + e.getMessage());
            return ""; // ошибка проглочена — вызывающий не узнает
        }

        try {
            publisher.publish(formatted);
        } catch (IOException e) {
            System.out.println("Публикация не удалась: " + e.getMessage());
            return ""; // ошибка проглочена — вызывающий не узнает
        }
        return formatted;
    }

    public static void demo() {
        var builder = new ReportBuilderV1();
        System.out.println("Валидный ввод -> '" + builder.build("отчёт за ноябрь") + "'");
        System.out.println("Битый ввод     -> '" + builder.build("!") + "'");
    }
}