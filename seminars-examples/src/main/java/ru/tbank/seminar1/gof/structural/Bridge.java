package ru.tbank.seminar1.gof.structural;

/**
 * Структурный паттерн Bridge — разделяет абстракцию и реализацию на две независимые
 * иерархии, которые можно изменять по отдельности. Здесь «форма» отделена от «способа
 * отрисовки» (рендерера).
 */
public class Bridge {

    /** Иерархия реализации. */
    public interface Renderer {
        void draw(String shapeName);
    }

    public static class VectorRenderer implements Renderer {
        public void draw(String shapeName) {
            System.out.println("Drawing " + shapeName + " as vector");
        }
    }

    public static class RasterRenderer implements Renderer {
        public void draw(String shapeName) {
            System.out.println("Drawing " + shapeName + " as pixels");
        }
    }

    /** Иерархия абстракции. */
    public static abstract class Shape {
        protected final Renderer renderer;

        public Shape(Renderer renderer) {
            this.renderer = renderer;
        }

        public abstract String name();

        public void draw() {
            renderer.draw(name());
        }
    }

    public static class Circle extends Shape {
        public Circle(Renderer renderer) {
            super(renderer);
        }

        public String name() {
            return "Circle";
        }
    }

    public static class Square extends Shape {
        public Square(Renderer renderer) {
            super(renderer);
        }

        public String name() {
            return "Square";
        }
    }

    public static void demo() {
        System.out.println("== Bridge ==");
        // Любая форма с любым рендерером — без взрыва количества классов.
        new Circle(new VectorRenderer()).draw();
        new Circle(new RasterRenderer()).draw();
        new Square(new VectorRenderer()).draw();
        new Square(new RasterRenderer()).draw();
    }
}