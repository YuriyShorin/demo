package ru.tbank.seminar1.solid.dip;

/**
 * ПЛОХО: нарушен принцип инверсии зависимостей (DIP).
 *
 * <p>Высокоуровневый {@code NotificationService} напрямую создаёт конкретный
 * низкоуровневый {@link EmailSender} через {@code new}. Он зависит от конкретной
 * реализации, а не от абстракции. Чтобы добавить отправку по SMS/Telegram, придётся
 * открывать и менять этот класс, а тестировать его сложно, потому что настоящий
 * {@code EmailSender} нельзя подменить заглушкой.
 */
public class BadNotificationService {

    private final EmailSender sender = new EmailSender(); // жёстко зашитая зависимость

    public void notify(String message) {
        sender.send(message);
    }

    public static class EmailSender {
        public void send(String message) {
            System.out.println("Email: " + message);
        }
    }
}