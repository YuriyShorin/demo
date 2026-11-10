package ru.tbank.seminar5.practice.parameters;

/**
 * ПРАКТИКА, V2 (заготовка): длинный список параметров.
 *
 * <p>Публичный контракт тот же, что у {@link ConferenceRegistrationV1}. Задача:
 * избавиться от семи параметров. Хороший приём — ввести record (например,
 * {@code RegistrationRequest}) и внутри метода делегировать ему, сохранив
 * прежний результат.
 */
public class ConferenceRegistrationV2 {

    public String register(String name, String email, String phone, String company,
                           String role, boolean newsletter, String city) {
        // TODO: сгруппируйте параметры в объект-запрос и сохраните прежний результат
        throw new UnsupportedOperationException("TODO: рефакторинг длинного списка параметров");
    }
}