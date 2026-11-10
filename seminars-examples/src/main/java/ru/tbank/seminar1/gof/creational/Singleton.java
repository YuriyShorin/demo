package ru.tbank.seminar1.gof.creational;

/**
 * Порождающий паттерн Singleton — гарантирует, что у класса есть ровно один экземпляр,
 * и предоставляет глобальную точку доступа к нему.
 */
public class Singleton {

    private static final Singleton INSTANCE = new Singleton();

    private int counter;

    private Singleton() {
        // приватный конструктор: создание извне запрещено
    }

    public static Singleton getInstance() {
        return INSTANCE;
    }

    public void increment() {
        counter++;
    }

    public int getCounter() {
        return counter;
    }

    public static void demo() {
        System.out.println("== Singleton ==");
        Singleton a = Singleton.getInstance();
        Singleton b = Singleton.getInstance();
        System.out.println("a == b (один и тот же объект): " + (a == b));
        a.increment();
        b.increment();
        System.out.println("counter после двух инкрементов через оба ссылки: " + a.getCounter());
    }
}