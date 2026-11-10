package ru.tbank.seminar2.generics;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Printer<T> {

    private T toPrint;

    public void print() {
        System.out.println(toPrint);
    }

    public T get() {
        return toPrint;
    }

    public void set(T toPrint) {
        this.toPrint = toPrint;
    }
}
