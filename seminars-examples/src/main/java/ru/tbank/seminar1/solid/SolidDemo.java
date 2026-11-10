package ru.tbank.seminar1.solid;

import ru.tbank.seminar1.solid.dip.EmailSender;
import ru.tbank.seminar1.solid.dip.GoodNotificationService;
import ru.tbank.seminar1.solid.dip.SmsSender;
import ru.tbank.seminar1.solid.dry.BadInvoiceService;
import ru.tbank.seminar1.solid.dry.GoodInvoiceService;
import ru.tbank.seminar1.solid.isp.GoodHuman;
import ru.tbank.seminar1.solid.isp.GoodRobot;
import ru.tbank.seminar1.solid.kiss.BadDiscountCalculator;
import ru.tbank.seminar1.solid.kiss.GoodDiscountCalculator;
import ru.tbank.seminar1.solid.lsp.BadRectangle;
import ru.tbank.seminar1.solid.lsp.GoodRectangle;
import ru.tbank.seminar1.solid.lsp.GoodSquare;
import ru.tbank.seminar1.solid.ocp.Circle;
import ru.tbank.seminar1.solid.ocp.GoodAreaCalculator;
import ru.tbank.seminar1.solid.ocp.Rectangle;
import ru.tbank.seminar1.solid.srp.EmailService;
import ru.tbank.seminar1.solid.srp.GoodRegistrationService;
import ru.tbank.seminar1.solid.srp.User;
import ru.tbank.seminar1.solid.srp.UserRepository;
import ru.tbank.seminar1.solid.srp.UserValidator;

/** Небольшой запускаемый пример, демонстрирующий «хорошую» сторону каждого принципа. */
public class SolidDemo {

    public static void main(String[] args) {
        srp();
        ocp();
        lsp();
        isp();
        dip();
        kiss();
        dry();
    }

    static void srp() {
        System.out.println("== SRP ==");
        var service = new GoodRegistrationService(
                new UserValidator(), new UserRepository(), new EmailService());
        service.register(new User("Alice", "alice@example.com", true));
    }

    static void ocp() {
        System.out.println("== OCP ==");
        var calc = new GoodAreaCalculator();
        System.out.println("Rectangle 3x4 area = " + calc.area(new Rectangle(3, 4)));
        System.out.println("Circle r=2 area    = " + calc.area(new Circle(2)));
    }

    static void lsp() {
        System.out.println("== LSP ==");
        System.out.println("Good rectangle 5x3 area = " + new GoodRectangle(5, 3).area());
        System.out.println("Good square 5 area       = " + new GoodSquare(5).area());
        System.out.println("--- Bad example: Square masquerading as Rectangle ---");
        BadRectangle.demo();
    }

    static void isp() {
        System.out.println("== ISP ==");
        new GoodHuman().work();
        new GoodHuman().eat();
        new GoodRobot().work();
        new GoodRobot().charge();
    }

    static void dip() {
        System.out.println("== DIP ==");
        new GoodNotificationService(new EmailSender()).notify("Hello");
        new GoodNotificationService(new SmsSender()).notify("Hello");
    }

    static void kiss() {
        System.out.println("== KISS ==");
        System.out.println("--- Bad example: over-engineered ---");
        BadDiscountCalculator.demo();
        System.out.println("--- Good example: simple ---");
        GoodDiscountCalculator.demo();
    }

    static void dry() {
        System.out.println("== DRY ==");
        System.out.println("--- Bad example: duplicated logic ---");
        BadInvoiceService.demo();
        System.out.println("--- Good example: single source of truth ---");
        GoodInvoiceService.demo();
    }
}