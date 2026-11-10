package ru.tbank.seminar5.examples.naming;

/**
 * ПЛОХО: плохие наименования.
 *
 * <p>Имена ничего не говорят о смысле: константа {@code c}, локальные переменные
 * {@code f}, {@code ok}, приватный метод {@code w}, параметры {@code u}, {@code a}.
 * Через месяц сам автор не вспомнит, что это такое, а новому разработчику придётся
 * восстанавливать смысл по поведению, а не по имени.
 */
public class PaymentProcessorV1 {

    private static final double c = 0.10; // доля комиссии от суммы платежа

    /** Обрабатывает платёж: списывает комиссию, если денег хватает. */
    public boolean process(User u, double a) {
        double f = a * c;               // сумма комиссии
        boolean ok = u.getBalance() >= f; // хватает ли денег на комиссию
        if (ok) {
            w(u, f);                    // списать комиссию
            return true;
        }
        return false;
    }

    private void w(User u, double f) {
        u.setBalance(u.getBalance() - f);
    }

    public static void demo() {
        var processor = new PaymentProcessorV1();
        var user = new User(100);
        System.out.println("Платёж 50 при балансе 100 -> " + processor.process(user, 50));
        System.out.println("Баланс после платежа       -> " + user.getBalance());
    }
}