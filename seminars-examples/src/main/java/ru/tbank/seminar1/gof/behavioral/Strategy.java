package ru.tbank.seminar1.gof.behavioral;

/**
 * Поведенческий паттерн Strategy — выделяет семейство взаимозаменяемых алгоритмов
 * и позволяет подменять их во время выполнения. Клиент зависит от общего интерфейса
 * стратегии, а не от конкретной реализации.
 */
public class Strategy {

    /** Общий интерфейс стратегии оплаты. */
    public interface PaymentStrategy {
        void pay(int amount);
    }

    public static class CreditCardPayment implements PaymentStrategy {
        public void pay(int amount) {
            System.out.println("Paid " + amount + " by credit card");
        }
    }

    public static class PayPalPayment implements PaymentStrategy {
        public void pay(int amount) {
            System.out.println("Paid " + amount + " via PayPal");
        }
    }

    /** Контекст, использующий стратегию. */
    public static class ShoppingCart {
        private PaymentStrategy strategy;

        public ShoppingCart(PaymentStrategy strategy) {
            this.strategy = strategy;
        }

        public void setStrategy(PaymentStrategy strategy) {
            this.strategy = strategy; // стратегию можно менять на лету
        }

        public void checkout(int amount) {
            strategy.pay(amount);
        }
    }

    public static void demo() {
        System.out.println("== Strategy ==");
        ShoppingCart cart = new ShoppingCart(new CreditCardPayment());
        cart.checkout(100);
        cart.setStrategy(new PayPalPayment()); // переключение алгоритма
        cart.checkout(250);
    }
}