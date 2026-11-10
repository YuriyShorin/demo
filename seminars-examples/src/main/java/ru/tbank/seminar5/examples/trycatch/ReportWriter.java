package ru.tbank.seminar5.examples.trycatch;

import java.io.IOException;

/** Сериализует отчёт в строку. Может упасть с {@link IOException}. */
public class ReportWriter {

    public String format(Report report) throws IOException {
        if (report == null || report.content().contains("ERR")) {
            throw new IOException("Не удалось сериализовать отчёт");
        }
        return "OK:" + report.content();
    }
}