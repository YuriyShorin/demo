package ru.tbank.seminar1.solid.lsp;

/** ХОРОШО: прямоугольник сохраняет оба независимых измерения. */
public class GoodRectangle implements Shape {

    private double width;
    private double height;

    public GoodRectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public double area() {
        return width * height;
    }
}