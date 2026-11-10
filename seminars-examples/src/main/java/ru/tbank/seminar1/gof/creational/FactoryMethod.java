package ru.tbank.seminar1.gof.creational;

/**
 * Порождающий паттерн Factory Method — определяет интерфейс создания объекта,
 * но позволяет подклассам решать, какой класс инстанцировать. Клиент работает
 * с общим интерфейсом продукта, не зная конкретный класс.
 */
public class FactoryMethod {

    /** Общий интерфейс продукта. */
    public interface Animal {
        String speak();
    }

    public static class Dog implements Animal {
        public String speak() {
            return "Woof!";
        }
    }

    public static class Cat implements Animal {
        public String speak() {
            return "Meow!";
        }
    }

    /** Абстрактный создатель с фабричным методом. */
    public static abstract class AnimalFactory {
        public abstract Animal create();

        // Клиентский код использует общий интерфейс.
        public void announce() {
            System.out.println("Animal says: " + create().speak());
        }
    }

    public static class DogFactory extends AnimalFactory {
        public Animal create() {
            return new Dog();
        }
    }

    public static class CatFactory extends AnimalFactory {
        public Animal create() {
            return new Cat();
        }
    }

    public static void demo() {
        System.out.println("== Factory Method ==");
        AnimalFactory dogFactory = new DogFactory();
        AnimalFactory catFactory = new CatFactory();
        dogFactory.announce();
        catFactory.announce();
    }
}