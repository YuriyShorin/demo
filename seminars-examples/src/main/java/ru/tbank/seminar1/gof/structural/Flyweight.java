package ru.tbank.seminar1.gof.structural;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Структурный паттерн Flyweight — экономит память, разделяя общее (внутреннее)
 * состояние между множеством объектов и оставляя уникальное (внешнее) у клиента.
 * Пример — лес из множества деревьев, которые различаются только позицией, но
 * разделяют общий тип (модель, текстуру).
 */
public class Flyweight {

    /** Разделяемое внутреннее состояние. */
    public static class TreeType {
        private final String name;
        private final String color;
        private final String texture;

        public TreeType(String name, String color, String texture) {
            this.name = name;
            this.color = color;
            this.texture = texture;
        }

        public void draw(int x, int y) {
            System.out.println("Drawing " + name + " (" + color + ") at (" + x + "," + y + ")");
        }
    }

    /** Фабрика лёгковесов: переиспользует уже созданные типы. */
    public static class TreeTypeFactory {
        private final Map<String, TreeType> cache = new java.util.HashMap<>();

        public TreeType getTreeType(String name, String color, String texture) {
            return cache.computeIfAbsent(name, k -> new TreeType(name, color, texture));
        }
    }

    /** Дерево: лёгковес + внешнее состояние (позиция). */
    public static class Tree {
        private final int x;
        private final int y;
        private final TreeType type;

        public Tree(int x, int y, TreeType type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }

        public void draw() {
            type.draw(x, y);
        }
    }

    public static void demo() {
        System.out.println("== Flyweight ==");
        TreeTypeFactory factory = new TreeTypeFactory();
        List<Tree> forest = new ArrayList<>();

        // 1000 деревьев, но всего 2 разделяемых типа.
        for (int i = 0; i < 5; i++) {
            forest.add(new Tree(i, i, factory.getTreeType("Oak", "green", "oak.png")));
        }
        for (int i = 0; i < 5; i++) {
            forest.add(new Tree(i, i + 10, factory.getTreeType("Pine", "dark", "pine.png")));
        }
        forest.get(0).draw();
        forest.get(5).draw();
        System.out.println("Всего деревьев: " + forest.size()
                + ", разделяемых типов: " + factory.cache.size());
    }
}