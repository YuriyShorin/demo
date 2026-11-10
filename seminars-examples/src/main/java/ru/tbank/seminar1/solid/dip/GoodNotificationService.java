package ru.tbank.seminar1.solid.dip;

/**
 * ХОРОШО: принцип инверсии зависимостей соблюдён.
 *
 * <p>Высокоуровневый {@code NotificationService} зависит только от абстракции
 * {@link MessageSender}, которая внедряется (здесь — через конструктор). Направление
 * зависимостей инвертировано: и сервис, и конкретные отправители указывают на абстракцию.
 * Добавление SMS или Telegram — это написание нового класса и передача его внутрь,
 * сервис при этом не меняется. Тестировать легко, внедрив заглушку.
 */
public class GoodNotificationService {

    private final MessageSender sender;

    public GoodNotificationService(MessageSender sender) {
        this.sender = sender;
    }

    public void notify(String message) {
        sender.send(message);
    }
}