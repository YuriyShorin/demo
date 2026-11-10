package ru.tbank.seminar1.gof.creational;

/**
 * Порождающий паттерн Builder — пошагово конструирует сложный объект, отделяя
 * построение от представления. Позволяет создавать объект с разными наборами
 * полей без громоздких конструкторов.
 */
public class Builder {

    /** Строимый продукт. */
    public static class Pizza {
        private final String dough;
        private final String sauce;
        private final String topping;

        private Pizza(PizzaBuilder builder) {
            this.dough = builder.dough;
            this.sauce = builder.sauce;
            this.topping = builder.topping;
        }

        public String describe() {
            return "Pizza[dough=" + dough + ", sauce=" + sauce + ", topping=" + topping + "]";
        }
    }

    /** Билдер с «текучим» (fluent) интерфейсом. */
    public static class PizzaBuilder {
        private String dough = "thin";
        private String sauce = "tomato";
        private String topping = "cheese";

        public PizzaBuilder dough(String dough) {
            this.dough = dough;
            return this;
        }

        public PizzaBuilder sauce(String sauce) {
            this.sauce = sauce;
            return this;
        }

        public PizzaBuilder topping(String topping) {
            this.topping = topping;
            return this;
        }

        public Pizza build() {
            return new Pizza(this);
        }
    }

    public static void demo() {
        System.out.println("== Builder ==");
        Pizza margherita = new PizzaBuilder().dough("thin").sauce("tomato").topping("cheese").build();
        Pizza pepperoni = new PizzaBuilder().dough("thick").sauce("barbecue").topping("pepperoni").build();
        Pizza defaultPizza = new PizzaBuilder().build(); // используются значения по умолчанию
        System.out.println(margherita.describe());
        System.out.println(pepperoni.describe());
        System.out.println(defaultPizza.describe());
    }
}