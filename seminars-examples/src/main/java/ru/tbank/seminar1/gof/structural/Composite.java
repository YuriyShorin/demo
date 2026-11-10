package ru.tbank.seminar1.gof.structural;

import java.util.ArrayList;
import java.util.List;

/**
 * Структурный паттерн Composite — строит иерархии «часть-целое», где листья и
 * составные узлы обрабатываются единообразно через общий интерфейс. Пример — файловая
 * система: файл и директория, содержащая файлы и другие директории.
 */
public class Composite {

    /** Общий интерфейс для листьев и составных узлов. */
    public interface FileSystemNode {
        String getName();

        void print(String indent);
    }

    /** Лист. */
    public static class File implements FileSystemNode {
        private final String name;

        public File(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void print(String indent) {
            System.out.println(indent + "- " + name);
        }
    }

    /** Составной узел, содержащий другие узлы. */
    public static class Directory implements FileSystemNode {
        private final String name;
        private final List<FileSystemNode> children = new ArrayList<>();

        public Directory(String name) {
            this.name = name;
        }

        public Directory add(FileSystemNode child) {
            children.add(child);
            return this;
        }

        public String getName() {
            return name;
        }

        public void print(String indent) {
            System.out.println(indent + "+ " + name);
            for (FileSystemNode child : children) {
                child.print(indent + "  "); // рекурсия: единообразно для файлов и папок
            }
        }
    }

    public static void demo() {
        System.out.println("== Composite ==");
        Directory root = new Directory("root");
        root.add(new File("readme.txt"));
        root.add(new Directory("src")
                .add(new File("Main.java"))
                .add(new Directory("util").add(new File("Helper.java"))));
        root.print("");
    }
}