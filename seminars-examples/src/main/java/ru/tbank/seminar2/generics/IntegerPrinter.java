package ru.tbank.seminar2.generics;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class IntegerPrinter {

    private final Integer number;

    public void print() {
        System.out.println(number);
    }
}
