package ru.tbank.seminar1.solid.isp;

/**
 * ПЛОХО: {@code Robot} вынужден реализовывать {@link BadWorker#eat()}, которым он не
 * может пользоваться.
 */
public class BadRobot implements BadWorker {

    @Override
    public void work() {
        System.out.println("Robot works");
    }

    @Override
    public void eat() {
        throw new UnsupportedOperationException("Robots don't eat");
    }

    @Override
    public void charge() {
        System.out.println("Robot charges");
    }
}