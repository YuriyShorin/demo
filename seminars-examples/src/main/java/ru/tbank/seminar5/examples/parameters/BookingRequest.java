package ru.tbank.seminar5.examples.parameters;

import java.time.LocalDate;

/**
 * Параметры бронирования, вынесенные в отдельный объект-запрос (вместо
 * восьми аргументов метода).
 *
 * <p>Валидация переехала в компактный конструктор record, поэтому сервис всегда
 * получает уже корректный запрос: некорректный объект просто не создастся.
 */
public record BookingRequest(String hotelId, String guestName, LocalDate checkIn,
                             LocalDate checkOut, int guests, boolean breakfast,
                             boolean parking, String notes) {

    public BookingRequest {
        if (checkIn == null || checkOut == null || checkOut.isBefore(checkIn)) {
            throw new IllegalArgumentException("Некорректные даты бронирования");
        }
        if (guests < 1) {
            throw new IllegalArgumentException("Количество гостей должно быть положительным");
        }
    }
}