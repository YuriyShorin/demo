package ru.tbank.seminar5.solutions.parameters;

/**
 * РЕШЕНИЕ, V2: длинный список параметров заменён объектом-запросом.
 *
 * <p>Семь параметров сгруппированы в record {@link RegistrationRequest}. Метод
 * {@code register} принимает один объект и строит строку из его полей — у
 * аргументов появляются имена, их нельзя перепутать местами.
 */
public class ConferenceRegistrationV2 {

    public String register(RegistrationRequest request) {
        return "Регистрация: " + request.name() + " (" + request.email() + "), город "
                + request.city() + (request.newsletter() ? ", рассылка: да" : "");
    }
}