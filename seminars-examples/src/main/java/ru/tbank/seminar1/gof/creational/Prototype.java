package ru.tbank.seminar1.gof.creational;

/**
 * Порождающий паттерн Prototype — создаёт новые объекты копированием существующего
 * «прототипа» вместо создания через конструктор. Полезно, когда создание объекта
 * дорого, а копия почти готова.
 */
public class Prototype {

    /** Прототип, умеющий клонировать себя. */
    public static class Shape implements Cloneable {
        private String type;
        private int sides;

        public Shape(String type, int sides) {
            this.type = type;
            this.sides = sides;
        }

        // Пользовательский «клонирующий» конструктор — глубокая копия.
        public Shape(Shape source) {
            this.type = source.type;
            this.sides = source.sides;
        }

        public Shape cloneShape() {
            return new Shape(this);
        }

        public String describe() {
            return type + " with " + sides + " sides";
        }
    }

    public static void demo() {
        System.out.println("== Prototype ==");
        Shape triangle = new Shape("Triangle", 3);
        Shape triangleCopy = triangle.cloneShape(); // копируем прототип
        System.out.println("Original: " + triangle.describe());
        System.out.println("Clone:    " + triangleCopy.describe());
        System.out.println("Отдельные объекты (не одна и та же ссылка): " + (triangle != triangleCopy));
    }
}