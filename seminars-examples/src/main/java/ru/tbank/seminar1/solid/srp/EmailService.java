package ru.tbank.seminar1.solid.srp;

/** Зона уведомлений, вынесена в отдельный класс. */
public class EmailService {

    public void sendWelcome(User user) {
        System.out.println("Sending welcome email to " + user.email());
    }

    public void sendPromo(User user) {
        System.out.println("Sending promo to " + user.email());
    }
}