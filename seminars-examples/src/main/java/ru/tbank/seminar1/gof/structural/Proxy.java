package ru.tbank.seminar1.gof.structural;

/**
 * Структурный паттерн Proxy — подставляет суррогат вместо реального объекта, чтобы
 * контролировать доступ к нему. Здесь — ленивая загрузка: тяжёлый объект создаётся
 * только когда он действительно нужен.
 */
public class Proxy {

    public interface Image {
        void display();
    }

    /** Реальный «тяжёлый» объект. */
    public static class RealImage implements Image {
        private final String filename;

        public RealImage(String filename) {
            this.filename = filename;
            loadFromDisk(); // дорогая операция
        }

        private void loadFromDisk() {
            System.out.println("Loading " + filename + " from disk");
        }

        public void display() {
            System.out.println("Displaying " + filename);
        }
    }

    /** Заместитель: откладывает создание реального объекта до первого display(). */
    public static class ImageProxy implements Image {
        private final String filename;
        private RealImage realImage;

        public ImageProxy(String filename) {
            this.filename = filename;
        }

        public void display() {
            if (realImage == null) {
                realImage = new RealImage(filename);
            }
            realImage.display();
        }
    }

    public static void demo() {
        System.out.println("== Proxy ==");
        Image photo = new ImageProxy("photo.jpg");
        // Объект ещё не загружен. display() создаст RealImage только сейчас.
        photo.display();
        photo.display(); // повторный вызов без повторной загрузки
    }
}