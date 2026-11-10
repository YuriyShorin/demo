package ru.tbank.seminar1.solid.dip;

/**
 * ХОРОШО: абстракция, от которой зависят и высокоуровневый сервис,
 * и низкоуровневые реализации.
 */
public interface MessageSender {

    void send(String message);
}