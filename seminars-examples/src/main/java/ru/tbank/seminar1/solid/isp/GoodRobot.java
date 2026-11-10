package ru.tbank.seminar1.solid.isp;

/**
 * ХОРОШО: {@code Robot} реализует только те две роли, которые у него есть, —
 * без бесполезных методов.
 */
public class GoodRobot implements Worker, Rechargeable {

    @Override
    public void work() {
        System.out.println("Robot works");
    }

    @Override
    public void charge() {
        System.out.println("Robot charges");
    }
}