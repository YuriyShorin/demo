package ru.tbank.seminar5.practice.parameters;

/**
 * ПРАКТИКА, V1 (неверный код): длинный список параметров.
 *
 * <p>Метод {@code register} принимает семь аргументов подряд. Задача: заменить
 * их одним объектом-запросом (record, например {@code RegistrationRequest}),
 * сохранив возвращаемое значение. Исправленную версию пишите в
 * {@link ConferenceRegistrationV2}, проверьте себя тестом
 * {@code ConferenceRegistrationTest}.
 */
public class ConferenceRegistrationV1 {

    public String register(String name, String email, String phone, String company,
                           String role, boolean newsletter, String city) {
        return "Регистрация: " + name + " (" + email + "), город " + city
                + (newsletter ? ", рассылка: да" : "");
    }
}