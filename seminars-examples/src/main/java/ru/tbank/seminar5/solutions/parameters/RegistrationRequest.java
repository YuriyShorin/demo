package ru.tbank.seminar5.solutions.parameters;

/**
 * Параметры регистрации, вынесенные в отдельный объект-запрос (вместо семи
 * аргументов метода {@code register}).
 */
public record RegistrationRequest(String name, String email, String phone,
                                  String company, String role, boolean newsletter,
                                  String city) {
}