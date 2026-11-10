package ru.tbank.seminar2.optional;

import java.util.Map;
import java.util.Optional;

/**
 * Простые примеры {@link Optional} для студентов.
 * <p>
 * {@code Optional<T>} — «коробка», в которой лежит значение типа {@code T} либо
 * ничего. Зачем он нужен:
 * <ul>
 *   <li>явно говорит в сигнатуре метода: «результата может не быть»;</li>
 *   <li>заставляет обработать пустой случай вместо того, чтобы молча
 *       упасть на {@code NullPointerException};</li>
 *   <li>даёт декларативный способ задать значение по умолчанию или
 *       выполнить действие только если значение есть.</li>
 * </ul>
 * <p>Важно: это НЕ замена любой {@code null}-проверке. {@code Optional} — для
 * возвращаемых значений методов. Поля, параметры и элементы коллекций им
 * делать не принято.
 */
public class OptionalDemo {

    /** Поиск пользователя по id: результат может отсутствовать. */
    record User(String name, String email) {}

    // Простая «база»: только два пользователя.
    static final Map<Integer, User> USERS = Map.of(
            1, new User("Ivan", "ivan@example.com"),
            2, new User("Maria", null) // у Maria нет email
    );

    public static void main(String[] args) {
        creating();
        consuming();
        chaining();
    }

    /** Как создать Optional: of, empty, ofNullable. */
    static void creating() {
        // of — только если значение точно не null, иначе сразу NPE.
        Optional<String> a = Optional.of("точно есть");

        // empty — пустая коробка.
        Optional<String> b = Optional.empty();

        // ofNullable — примет и null (станет empty), и значение.
        Optional<String> c = Optional.ofNullable(null);
        Optional<String> d = Optional.ofNullable("привет");

        System.out.println("of(точно есть)  = " + a);
        System.out.println("empty()         = " + b);
        System.out.println("ofNullable(null)= " + c);
        System.out.println("ofNullable(прив)= " + d);
        System.out.println();
    }

    /** Как достать значение: isPresent, get, ifPresent, orElse. */
    static void consuming() {
        // Поищем пользователя, которого нет.
        Optional<User> notFound = findUser(99);

        System.out.println("isPresent()  = " + notFound.isPresent());
        System.out.println("isEmpty()    = " + notFound.isEmpty());

        // orElse — значение по умолчанию (вычисляется ВСЕГДА, даже если значение есть).
        User fallback = notFound.orElse(new User("Гость", "guest@example.com"));
        System.out.println("orElse       = " + fallback.name());

        // orElseGet — то же, но лениво: Supplier вызывается только если пусто.
        User lazy = notFound.orElseGet(() -> new User("Гость", "guest@example.com"));

        // ifPresent — выполнить действие, только если значение есть.
        findUser(1).ifPresent(u -> System.out.println("ifPresent    = найден " + u.name()));

        // get — достать значение напрямую. Опасно: на пустом бросит исключение.
        User present = findUser(1).get();
        System.out.println("get          = " + present.name());
        System.out.println();
    }

    /** Полезные методы-цепочки: map, filter, orElseThrow. */
    static void chaining() {
        // map — преобразовать значение, если оно есть; пустое остаётся пустым.
        Optional<String> upperName = findUser(1).map(u -> u.name().toUpperCase());
        System.out.println("map(имя вверх)  = " + upperName);

        // filter — оставить значение, только если условие выполнено.
        Optional<User> hasEmail = findUser(1).filter(u -> u.email() != null);
        Optional<User> noEmail = findUser(2).filter(u -> u.email() != null);
        System.out.println("filter(есть mail) для Maria = " + noEmail);

        // Цепочка: найти -> взять имя -> перевести в верхний регистр.
        String upperNameOrDash = findUser(1)
                .map(u -> u.name())
                .map(String::toUpperCase)
                .orElse("---");
        System.out.println("цепочка         = " + upperNameOrDash);

        // orElseThrow — выбросить своё исключение, если значения нет.
        User present = findUser(1).orElseThrow(() -> new IllegalStateException("нет пользователя"));
        System.out.println("orElseThrow     = " + present.name());
    }

    /** Возвращаем Optional — так метод явно говорит, что результата может не быть. */
    static Optional<User> findUser(int id) {
        return Optional.ofNullable(USERS.get(id));
    }
}