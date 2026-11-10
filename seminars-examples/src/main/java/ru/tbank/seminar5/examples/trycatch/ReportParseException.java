package ru.tbank.seminar5.examples.trycatch;

/** Ошибка разбора входных данных. */
public class ReportParseException extends Exception {

    public ReportParseException(String message) {
        super(message);
    }
}