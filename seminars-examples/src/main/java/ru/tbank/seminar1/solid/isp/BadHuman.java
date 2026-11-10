package ru.tbank.seminar1.solid.isp;

/**
 * ПЛОХО: {@code Human} вынужден реализовывать {@link BadWorker#charge()}, который ему
 * не нужен, а {@code Robot} — {@link BadWorker#eat()}, которым он не может пользоваться.
 */
public class BadHuman implements BadWorker {

    @Override
    public void work() {
        System.out.println("Human works");
    }

    @Override
    public void eat() {
        System.out.println("Human eats");
    }

    @Override
    public void charge() {
        throw new UnsupportedOperationException("Humans don't need charging");
    }
}