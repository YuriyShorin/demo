package ru.tbank.seminar5.solutions.parameters;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Тест на решение {@link ConferenceRegistrationV2}: тот же результат, что у V1,
 * но метод принимает объект-запрос {@link RegistrationRequest} вместо семи
 * аргументов.
 */
class ConferenceRegistrationV2Test {

    private final ConferenceRegistrationV2 registration = new ConferenceRegistrationV2();

    @Test
    void buildsRegistrationSummary() {
        var request = new RegistrationRequest("Иван", "ivan@example.com", "+7 900 000-00-00",
                "Т-Банк", "Разработчик", true, "Москва");
        String result = registration.register(request);
        assertTrue(result.contains("Иван"));
        assertTrue(result.contains("ivan@example.com"));
        assertTrue(result.contains("Москва"));
        assertTrue(result.contains("рассылка: да"));
    }

    @Test
    void omitsNewsletterWhenNotSubscribed() {
        var request = new RegistrationRequest("Иван", "ivan@example.com", "+7 900 000-00-00",
                "Т-Банк", "Разработчик", false, "Москва");
        assertFalse(registration.register(request).contains("рассылка: да"));
    }
}