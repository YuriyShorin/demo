package ru.tbank.seminar1.gof.structural;

/**
 * Структурный паттерн Adapter — позволяет объектам с несовместимыми интерфейсами
 * работать вместе: адаптер оборачивает «чужой» класс и приводит его к ожидаемому
 * интерфейсу. Классика — круглое отверстие и квадратный колышек.
 */
public class Adapter {

    /** Ожидаемый клиентом интерфейс. */
    public interface RoundPeg {
        double getRadius();
    }

    public static class RoundPegImpl implements RoundPeg {
        private final double radius;

        public RoundPegImpl(double radius) {
            this.radius = radius;
        }

        public double getRadius() {
            return radius;
        }
    }

    /** Круглое отверстие. */
    public static class RoundHole {
        private final double radius;

        public RoundHole(double radius) {
            this.radius = radius;
        }

        public boolean fits(RoundPeg peg) {
            return this.radius >= peg.getRadius();
        }
    }

    /** «Чужой» класс с несовместимым интерфейсом: у него нет getRadius(). */
    public static class SquarePeg {
        private final double width;

        public SquarePeg(double width) {
            this.width = width;
        }

        public double getWidth() {
            return width;
        }
    }

    /** Адаптер: приводит SquarePeg к интерфейсу RoundPeg. */
    public static class SquarePegAdapter implements RoundPeg {
        private final SquarePeg squarePeg;

        public SquarePegAdapter(SquarePeg squarePeg) {
            this.squarePeg = squarePeg;
        }

        // Радиус описанной окружности квадрата.
        public double getRadius() {
            return squarePeg.getWidth() * Math.sqrt(2) / 2;
        }
    }

    public static void demo() {
        System.out.println("== Adapter ==");
        RoundHole hole = new RoundHole(5);
        SquarePeg small = new SquarePeg(2);
        SquarePeg large = new SquarePeg(20);

        // Без адаптера hole.fits(small) не скомпилируется — типы несовместимы.
        System.out.println("Small square fits: " + hole.fits(new SquarePegAdapter(small)));
        System.out.println("Large square fits: " + hole.fits(new SquarePegAdapter(large)));
    }
}