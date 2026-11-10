package ru.tbank.seminar5.examples.naming;

/**
 * ХОРОШО: осмысленные наименования.
 *
 * <p>Код читается как обычный текст: «посчитать комиссию → если денег хватает —
 * списать». Смысл каждого имени очевиден без комментариев, поэтому часть
 * поясняющих комментариев из V1 можно было вовсе убрать.
 */
public class PaymentProcessorV2 {

    private static final double COMMISSION_RATE = 0.10;

    /** Обрабатывает платёж: списывает комиссию, если денег хватает. */
    public boolean processPayment(User user, double amount) {
        double commission = amount * COMMISSION_RATE;
        if (!hasEnoughFunds(user, commission)) {
            return false;
        }
        withdraw(user, commission);
        return true;
    }

    private boolean hasEnoughFunds(User user, double amount) {
        return user.getBalance() >= amount;
    }

    private void withdraw(User user, double amount) {
        user.setBalance(user.getBalance() - amount);
    }

    public static void demo() {
        var processor = new PaymentProcessorV2();
        var user = new User(100);
        System.out.println("Платёж 50 при балансе 100 -> " + processor.processPayment(user, 50));
        System.out.println("Баланс после платежа       -> " + user.getBalance());
    }
}