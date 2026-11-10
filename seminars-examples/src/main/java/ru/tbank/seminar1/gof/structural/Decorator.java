package ru.tbank.seminar1.gof.structural;

/**
 * Структурный паттерн Decorator — динамически добавляет объекту новое поведение,
 * оборачивая его в объекты-декораторы. Альтернатива наследованию: можно комбинировать
 * «добавки» в любых сочетаниях.
 */
public class Decorator {

    /** Базовый интерфейс. */
    public interface Coffee {
        String description();

        double cost();
    }

    public static class Espresso implements Coffee {
        public String description() {
            return "Espresso";
        }

        public double cost() {
            return 1.0;
        }
    }

    /** Абстрактный декоратор, оборачивающий Coffee. */
    public static abstract class CoffeeDecorator implements Coffee {
        protected final Coffee coffee;

        public CoffeeDecorator(Coffee coffee) {
            this.coffee = coffee;
        }
    }

    public static class WithMilk extends CoffeeDecorator {
        public WithMilk(Coffee coffee) {
            super(coffee);
        }

        public String description() {
            return coffee.description() + " + milk";
        }

        public double cost() {
            return coffee.cost() + 0.5;
        }
    }

    public static class WithSugar extends CoffeeDecorator {
        public WithSugar(Coffee coffee) {
            super(coffee);
        }

        public String description() {
            return coffee.description() + " + sugar";
        }

        public double cost() {
            return coffee.cost() + 0.2;
        }
    }

    public static void demo() {
        System.out.println("== Decorator ==");
        Coffee espresso = new Espresso();
        Coffee latte = new WithMilk(espresso);
        Coffee sweetLatte = new WithSugar(new WithMilk(espresso));
        System.out.println(espresso.description() + " = " + espresso.cost());
        System.out.println(latte.description() + " = " + latte.cost());
        System.out.println(sweetLatte.description() + " = " + sweetLatte.cost());
    }
}