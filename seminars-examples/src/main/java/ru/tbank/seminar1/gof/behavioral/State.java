package ru.tbank.seminar1.gof.behavioral;

/**
 * Поведенческий паттерн State — позволяет объекту менять своё поведение при смене
 * внутреннего состояния. Внешне это выглядит как будто объект сменил класс. Пример —
 * документ с состояниями Draft (черновик) и Published (опубликован).
 */
public class State {

    /** Интерфейс состояния. */
    public interface DocumentState {
        void publish(Document doc);
    }

    public static class DraftState implements DocumentState {
        public void publish(Document doc) {
            System.out.println("Document '" + doc.title + "' published");
            doc.setState(new PublishedState()); // переход в другое состояние
        }
    }

    public static class PublishedState implements DocumentState {
        public void publish(Document doc) {
            System.out.println("Document '" + doc.title + "' is already published");
        }
    }

    /** Контекст, делегирующий поведение текущему состоянию. */
    public static class Document {
        private final String title;
        private DocumentState state = new DraftState();

        public Document(String title) {
            this.title = title;
        }

        public void setState(DocumentState state) {
            this.state = state;
        }

        public void publish() {
            state.publish(this);
        }
    }

    public static void demo() {
        System.out.println("== State ==");
        Document doc = new Document("My article");
        doc.publish(); // из черновика — публикуем
        doc.publish(); // уже опубликован — поведение изменилось
    }
}