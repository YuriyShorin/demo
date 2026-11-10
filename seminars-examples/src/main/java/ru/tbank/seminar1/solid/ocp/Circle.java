package ru.tbank.seminar1.solid.ocp;

public record Circle(double radius) implements Shape {

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }
}