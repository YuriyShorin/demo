package ru.tbank.seminar1.gof.behavioral;

/**
 * Поведенческий паттерн Memento — сохраняет и восстанавливает состояние объекта,
 * не нарушая его инкапсуляцию. Классика — отмена (undo) в текстовом редакторе:
 * «хранитель» (caretaker) хранит снимки (memento) состояния редактора.
 */
public class Memento {

    /** Снимок состояния. Неизменяемый, доступен только владельцу. */
    public static class EditorMemento {
        private final String text;

        public EditorMemento(String text) {
            this.text = text;
        }

        private String getText() {
            return text;
        }
    }

    /** Originator: владелец состояния, умеет создавать снимки и восстанавливаться из них. */
    public static class Editor {
        private String text = "";

        public void write(String words) {
            text += words;
        }

        public String getText() {
            return text;
        }

        public EditorMemento save() {
            return new EditorMemento(text);
        }

        public void restore(EditorMemento memento) {
            text = memento.getText();
        }
    }

    /** Хранитель (caretaker): хранит историю снимков, но не трогает их содержимое. */
    public static class History {
        private final java.util.Stack<EditorMemento> stack = new java.util.Stack<>();

        public void push(EditorMemento memento) {
            stack.push(memento);
        }

        public EditorMemento pop() {
            return stack.pop();
        }
    }

    public static void demo() {
        System.out.println("== Memento ==");
        Editor editor = new Editor();
        History history = new History();

        history.push(editor.save());
        editor.write("Hello ");
        history.push(editor.save());
        editor.write("world!");
        System.out.println("Current: " + editor.getText());

        editor.restore(history.pop());
        System.out.println("After 1 undo: " + editor.getText());
        editor.restore(history.pop());
        System.out.println("After 2 undo: " + editor.getText());
    }
}