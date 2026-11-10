package ru.tbank.seminar5.examples.naming;

/** Учётная запись клиента с балансом (упрощённо). */
public class User {

    private double balance;

    public User(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}