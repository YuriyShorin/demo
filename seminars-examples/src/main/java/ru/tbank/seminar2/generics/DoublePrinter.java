package ru.tbank.seminar2.generics;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DoublePrinter {

    private final Double number;

    public void print() {
        System.out.println(number);
    }
}
