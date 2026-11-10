package ru.tbank.seminar1.solid.isp;

/**
 * ХОРОШО: {@code Human} реализует только те две роли, которые у него действительно есть, —
 * без бесполезных методов.
 */
public class GoodHuman implements Worker, Eater {

    @Override
    public void work() {
        System.out.println("Human works");
    }

    @Override
    public void eat() {
        System.out.println("Human eats");
    }
}