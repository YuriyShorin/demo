package ru.tbank.seminar1.solid.dip;

/** Низкоуровневая реализация, зависящая от абстракции. */
public class SmsSender implements MessageSender {

    @Override
    public void send(String message) {
        System.out.println("SMS: " + message);
    }
}