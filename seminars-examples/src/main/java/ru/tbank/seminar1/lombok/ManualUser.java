package ru.tbank.seminar1.lombok;

import java.util.Objects;

/**
 * БЕЗ Lombok: обычный POJO, написанный вручную.
 *
 * <p>Для простого класса-данных с четырьмя полями приходится вручную писать:
 * геттеры и сеттеры, конструктор без аргументов, конструктор со всеми полями,
 * {@code toString()}, {@code equals()}/{@code hashCode()} и статический билдер.
 * Большая часть этого кода — рутина, которая:
 * <ul>
 *   <li>раздувает файл и отвлекает от реальной логики;</li>
 *   <li>легко содержит ошибки (забытый {@code null}-check, неверный {@code equals});</li>
 *   <li>портится при каждом добавлении поля — нужно править всё вышеперечисленное сразу.</li>
 * </ul>
 */
public class ManualUser {

    private Long id;
    private String name;
    private String email;
    private int age;

    public ManualUser() {
    }

    public ManualUser(Long id, String name, String email, int age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "ManualUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ManualUser that)) {
            return false;
        }
        return age == that.age
                && Objects.equals(id, that.id)
                && Objects.equals(name, that.name)
                && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, age);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String name;
        private String email;
        private int age;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public ManualUser build() {
            return new ManualUser(id, name, email, age);
        }
    }
}