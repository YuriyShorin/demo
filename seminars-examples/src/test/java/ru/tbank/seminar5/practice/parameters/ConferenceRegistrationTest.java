package ru.tbank.seminar5.practice.parameters;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Характеризационный тест для практики «длинный список параметров».
 *
 * <p>Фиксирует возвращаемое строкой регистрации. Сначала прогоняется против
 * {@link ConferenceRegistrationV1}. После написания {@link ConferenceRegistrationV2}
 * замените внизу {@code ConferenceRegistrationV1} на {@code ConferenceRegistrationV2}
 * — тест должен остаться зелёным.
 */
class ConferenceRegistrationTest {

    private final ConferenceRegistrationV1 registration = new ConferenceRegistrationV1();

    @Test
    void buildsRegistrationSummary() {
        String result = registration.register("Иван", "ivan@example.com", "+7 900 000-00-00",
                "Т-Банк", "Разработчик", true, "Москва");
        assertTrue(result.contains("Иван"));
        assertTrue(result.contains("ivan@example.com"));
        assertTrue(result.contains("Москва"));
        assertTrue(result.contains("рассылка: да"));
    }

    @Test
    void omitsNewsletterWhenNotSubscribed() {
        String result = registration.register("Иван", "ivan@example.com", "+7 900 000-00-00",
                "Т-Банк", "Разработчик", false, "Москва");
        assertFalse(result.contains("рассылка: да"));
    }
}