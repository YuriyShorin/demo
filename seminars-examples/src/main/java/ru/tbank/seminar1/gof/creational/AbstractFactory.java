package ru.tbank.seminar1.gof.creational;

/**
 * Порождающий паттерн Abstract Factory — создаёт семейства связанных объектов,
 * не привязываясь к их конкретным классам. Клиент выбирает фабрику (например, под
 * операционную систему) и получает согласованный набор компонентов.
 */
public class AbstractFactory {

    public interface Button {
        void render();
    }

    public interface Checkbox {
        void render();
    }

    /** Абстрактная фабрика семейства. */
    public interface UiFactory {
        Button createButton();

        Checkbox createCheckbox();
    }

    public static class WinButton implements Button {
        public void render() {
            System.out.println("Render Windows button");
        }
    }

    public static class WinCheckbox implements Checkbox {
        public void render() {
            System.out.println("Render Windows checkbox");
        }
    }

    public static class MacButton implements Button {
        public void render() {
            System.out.println("Render macOS button");
        }
    }

    public static class MacCheckbox implements Checkbox {
        public void render() {
            System.out.println("Render macOS checkbox");
        }
    }

    public static class WindowsFactory implements UiFactory {
        public Button createButton() {
            return new WinButton();
        }

        public Checkbox createCheckbox() {
            return new WinCheckbox();
        }
    }

    public static class MacFactory implements UiFactory {
        public Button createButton() {
            return new MacButton();
        }

        public Checkbox createCheckbox() {
            return new MacCheckbox();
        }
    }

    public static void demo() {
        System.out.println("== Abstract Factory ==");
        UiFactory windows = new WindowsFactory();
        UiFactory mac = new MacFactory();
        System.out.println("Windows set:");
        windows.createButton().render();
        windows.createCheckbox().render();
        System.out.println("macOS set:");
        mac.createButton().render();
        mac.createCheckbox().render();
    }
}