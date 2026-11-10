package ru.tbank.seminar1.solid.ocp;

/**
 * ПЛОХО: нарушен принцип открытости/закрытости (OCP).
 *
 * <p>{@code RectangleAreaCalculator} использует длинную цепочку {@code if-else}
 * для вычисления площади каждой фигуры. Чтобы добавить НОВУЮ фигуру (например,
 * {@code Triangle}), приходится открывать этот уже существующий и проверенный класс
 * и модифицировать его. С каждой новой фигурой метод разрастается, а неаккуратная
 * правка рискует сломать уже работающие фигуры.
 *
 * <p>Открыт для расширения, но НЕ закрыт для модификации — противоположность тому,
 * чего хочет OCP.
 */
public class BadAreaCalculator {

    /** Возвращает площадь прямоугольника или круга; для любой другой фигуры потребуется новая ветка. */
    public double area(Object shape) {
        if (shape instanceof Rectangle r) {
            return r.width() * r.height();
        } else if (shape instanceof Circle c) {
            return Math.PI * c.radius() * c.radius();
        }
        throw new IllegalArgumentException("Unsupported shape: " + shape);
    }

    public record Rectangle(double width, double height) {}
    public record Circle(double radius) {}
}