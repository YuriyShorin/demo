package ru.tbank.seminar1.lombok;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * С Lombok: тот же самый POJO, но весь шаблонный код генерируется аннотациями.
 *
 * <p>Каждая аннотация — одна строка, заменяющая целые блоки кода:
 * <ul>
 *   <li>{@code @Getter}/{@code @Setter} — геттеры и сеттеры для всех полей;</li>
 *   <li>{@code @NoArgsConstructor} / {@code @AllArgsConstructor} — конструкторы;</li>
 *   <li>{@code @ToString} — {@code toString()};</li>
 *   <li>{@code @EqualsAndHashCode} — {@code equals()} и {@code hashCode()};</li>
 *   <li>{@code @Builder} — статический билдер.</li>
 * </ul>
 *
 * <p>Файл сжимается примерно в 4 раза. При добавлении нового поля ничего не надо править —
 * Lombok сгенерирует всё заново на следующей компиляции, а сгенерированный код
 * (это обычный Java) будет ровно таким же, как написанный вручную.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class LombokUser {

    private Long id;
    private String name;
    private String email;
    private int age;

    private void someMethod() {
      LombokUser lombokUser = new LombokUser(1L, "Alice", "alice@example.com", 30);
      lombokUser.setAge(31);
    }
}

