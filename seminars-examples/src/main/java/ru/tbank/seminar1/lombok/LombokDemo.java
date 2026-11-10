package ru.tbank.seminar1.lombok;

/**
 * Демонстрация того, что Lombok-класс ведёт себя так же, как вручную написанный:
 * у обоих есть конструкторы, геттеры/сеттеры, {@code toString()}, {@code equals()}/
 * {@code hashCode()} и билдер. Разница только в объёме кода, который пришлось писать.
 */
public class LombokDemo {

    public static void main(String[] args) {
        // Оба класса собраны через билдер, у обоих одинаковые геттеры и toString().
        ManualUser manual = ManualUser.builder()
                .id(1L)
                .name("Alice")
                .email("alice@example.com")
                .age(30)
                .build();

        LombokUser lombok = LombokUser.builder()
                .id(1L)
                .name("Alice")
                .email("alice@example.com")
                .age(30)
                .build();

        System.out.println("manual.toString(): " + manual);
        System.out.println("lombok.toString(): " + lombok);

        // Конструктор со всеми полями тоже генерируется у обоих.
        LombokUser l2 = new LombokUser(2L, "Bob", "bob@example.com", 25);
        System.out.println("lombok all-args ctor: " + l2);

        // equals/hashCode: разные объекты с равными полями равны.
        LombokUser copy = LombokUser.builder()
                .id(1L)
                .name("Alice")
                .email("alice@example.com")
                .age(30)
                .build();
        System.out.println("lombok.equals(copy): " + lombok.equals(copy));

        // Сеттеры тоже доступны.
        lombok.setAge(31);
        System.out.println("lombok age after setter: " + lombok.getAge());
    }
}