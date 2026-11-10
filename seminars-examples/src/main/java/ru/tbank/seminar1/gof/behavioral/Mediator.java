package ru.tbank.seminar1.gof.behavioral;

/**
 * Поведенческий паттерн Mediator — уменьшает связанность между множеством объектов,
 * направляя их взаимодействие через центральный объект-посредник. Пример — чат-комната,
 * через которую общаются пользователи (вместо прямых ссылок друг на друга).
 */
public class Mediator {

    /** Посредник: централизует взаимодействие. */
    public static class ChatRoom {
        public void showMessage(String user, String message) {
            System.out.println("[" + java.time.LocalTime.now().withNano(0) + "] "
                    + user + ": " + message);
        }
    }

    /** Участник, взаимодействующий только через посредника. */
    public static class User {
        private final String name;
        private final ChatRoom room;

        public User(String name, ChatRoom room) {
            this.name = name;
            this.room = room;
        }

        public void send(String message) {
            room.showMessage(name, message);
        }
    }

    public static void demo() {
        System.out.println("== Mediator ==");
        ChatRoom room = new ChatRoom();
        User alice = new User("Alice", room);
        User bob = new User("Bob", room);
        // Пользователи не знают друг о друге — только о комнате.
        alice.send("Hi Bob!");
        bob.send("Hey Alice!");
    }
}