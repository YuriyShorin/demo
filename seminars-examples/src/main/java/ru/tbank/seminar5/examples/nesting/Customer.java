package ru.tbank.seminar5.examples.nesting;

/** Клиент магазина с признаком VIP-статуса. */
public class Customer {

    private final boolean vip;

    public Customer(boolean vip) {
        this.vip = vip;
    }

    public boolean isVip() {
        return vip;
    }
}