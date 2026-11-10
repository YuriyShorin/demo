package ru.tbank.seminar1.gof.behavioral;

/**
 * Поведенческий паттерн Visitor — добавляет новые операции над объектами, не изменяя
 * их классы. Операция выносится в отдельный объект-«посетитель», который через двойную
 * диспетчеризацию вызывает нужный метод для каждого типа элемента.
 */
public class Visitor {

    /** Элементы, принимающие посетителя. */
    public interface Shape {
        void accept(ShapeVisitor visitor);
    }

    public static class Circle implements Shape {
        private final double radius;

        public Circle(double radius) {
            this.radius = radius;
        }

        public double getRadius() {
            return radius;
        }

        public void accept(ShapeVisitor visitor) {
            visitor.visit(this);
        }
    }

    public static class Rectangle implements Shape {
        private final double width;
        private final double height;

        public Rectangle(double width, double height) {
            this.width = width;
            this.height = height;
        }

        public double getWidth() {
            return width;
        }

        public double getHeight() {
            return height;
        }

        public void accept(ShapeVisitor visitor) {
            visitor.visit(this);
        }
    }

    /** Посетитель с операцией над каждым типом. */
    public interface ShapeVisitor {
        void visit(Circle circle);

        void visit(Rectangle rectangle);
    }

    /** Конкретный посетитель: считает площадь. */
    public static class AreaVisitor implements ShapeVisitor {
        private double total;

        public void visit(Circle circle) {
            total += Math.PI * circle.getRadius() * circle.getRadius();
        }

        public void visit(Rectangle rectangle) {
            total += rectangle.getWidth() * rectangle.getHeight();
        }

        public double getTotal() {
            return total;
        }
    }

    public static void demo() {
        System.out.println("== Visitor ==");
        Shape[] shapes = {new Circle(2), new Rectangle(3, 4), new Circle(1)};
        AreaVisitor area = new AreaVisitor();
        for (Shape shape : shapes) {
            shape.accept(area); // новая операция без изменения классов фигур
        }
        System.out.println("Total area = " + area.getTotal());
    }
}