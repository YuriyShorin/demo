package ru.tbank.seminar1.solid.srp;

/**
 * ХОРОШО: теперь у каждого класса ровно одна ответственность и одна причина для изменения.
 */
public class UserValidator {

    public void validate(User user) {
        if (user.name().isBlank()) {
            throw new IllegalArgumentException("Name is required");
        }
        if (!user.email().contains("@")) {
            throw new IllegalArgumentException("Invalid email");
        }
    }
}