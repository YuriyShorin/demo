package ru.tbank.seminar5.examples.trycatch;

/**
 * Разбирает текстовые данные в отчёт. Бросает {@link ReportParseException} при
 * некорректном вводе.
 */
public class ReportParser {

    public Report parse(String content) throws ReportParseException {
        if (content == null || content.isBlank()) {
            throw new ReportParseException("Входные данные пусты");
        }
        if (content.startsWith("!")) {
            throw new ReportParseException("Некорректный формат: " + content);
        }
        return new Report(content);
    }
}