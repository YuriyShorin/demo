package ru.tbank.seminar1.solid.ocp;

public record Rectangle(double width, double height) implements Shape {

    @Override
    public double area() {
        return width * height;
    }
}