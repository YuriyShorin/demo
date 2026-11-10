package ru.tbank.seminar5;

import ru.tbank.seminar5.examples.duplication.OrderCalculatorV1;
import ru.tbank.seminar5.examples.duplication.OrderCalculatorV2;
import ru.tbank.seminar5.examples.naming.PaymentProcessorV1;
import ru.tbank.seminar5.examples.naming.PaymentProcessorV2;
import ru.tbank.seminar5.examples.nesting.DiscountEngineV1;
import ru.tbank.seminar5.examples.nesting.DiscountEngineV2;
import ru.tbank.seminar5.examples.parameters.BookingServiceV1;
import ru.tbank.seminar5.examples.parameters.BookingServiceV2;
import ru.tbank.seminar5.examples.trycatch.ReportBuilderV1;
import ru.tbank.seminar5.examples.trycatch.ReportBuilderV2;

/**
 * Оркестратор семинара по рефакторингу: прогоняет пять приёмов «плохо/хорошо».
 *
 * <p>Для каждого приёма показывается неверный код (V1) и исправленный (V2).
 * Готовые примеры лежат в подпакете {@code examples}, а Practice-заглушки для
 * самостоятельной практики — в отдельном подпакете {@code practice}.
 */
public class RefactoringDemo {

    public static void main(String[] args) {
        duplication();
        naming();
        parameters();
        nesting();
        tryCatch();
    }

    static void duplication() {
        System.out.println("== Дублирование ==");
        System.out.println("--- Плохо (V1) ---");
        OrderCalculatorV1.demo();
        System.out.println("--- Хорошо (V2) ---");
        OrderCalculatorV2.demo();
    }

    static void naming() {
        System.out.println("== Плохие наименования ==");
        System.out.println("--- Плохо (V1) ---");
        PaymentProcessorV1.demo();
        System.out.println("--- Хорошо (V2) ---");
        PaymentProcessorV2.demo();
    }

    static void parameters() {
        System.out.println("== Много параметров ==");
        System.out.println("--- Плохо (V1) ---");
        BookingServiceV1.demo();
        System.out.println("--- Хорошо (V2) ---");
        BookingServiceV2.demo();
    }

    static void nesting() {
        System.out.println("== Сильная вложенность ==");
        System.out.println("--- Плохо (V1) ---");
        DiscountEngineV1.demo();
        System.out.println("--- Хорошо (V2) ---");
        DiscountEngineV2.demo();
    }

    static void tryCatch() {
        System.out.println("== Try/catch на каждом шаге ==");
        System.out.println("--- Плохо (V1): ошибки проглатываются ---");
        ReportBuilderV1.demo();
        System.out.println("--- Хорошо (V2): ошибки пробрасываются ---");
        ReportBuilderV2.demo();
    }
}