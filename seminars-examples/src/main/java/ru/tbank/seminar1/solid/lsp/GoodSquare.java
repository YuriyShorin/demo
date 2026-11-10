package ru.tbank.seminar1.solid.lsp;

/**
 * ХОРОШО: квадрат больше НЕ является подклассом прямоугольника. Это «соседний» класс,
 * реализующий тот же контракт {@link Shape}, поэтому он полностью подставляем — любой код,
 * работающий с {@code Shape}, работает с обеими фигурами без сюрпризов.
 */
public class GoodSquare implements Shape {

    private double side;

    public GoodSquare(double side) {
        this.side = side;
    }

    public void setSide(double side) {
        this.side = side;
    }

    @Override
    public double area() {
        return side * side;
    }
}