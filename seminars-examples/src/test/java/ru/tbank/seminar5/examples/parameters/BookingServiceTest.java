package ru.tbank.seminar5.examples.parameters;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Характеризационные тесты для рефакторинга «длинный список параметров».
 *
 * <p>V1 принимает восемь аргументов, V2 — объект-запрос {@link BookingRequest}.
 * Тест проверяет, что оба варианта создают эквивалентное бронирование и одинаково
 * отклоняют некорректные данные.
 */
class BookingServiceTest {

    private static final LocalDate IN = LocalDate.of(2026, 11, 10);
    private static final LocalDate OUT = LocalDate.of(2026, 11, 12);

    @Test
    void v1AndV2CreateEquivalentBooking() {
        var v1 = new BookingServiceV1().bookRoom("H-42", "Алиса", IN, OUT, 2, true, false, "");

        var request = new BookingRequest("H-42", "Алиса", IN, OUT, 2, true, false, "");
        var v2 = new BookingServiceV2().bookRoom(request);

        assertEquals(v1, v2);
        assertEquals("H-42", v2.hotelId());
        assertEquals("Алиса", v2.guestName());
        assertEquals(2, v2.guests());
    }

    @Test
    void v1RejectsInvalidDates() {
        assertThrows(IllegalArgumentException.class, () ->
                new BookingServiceV1().bookRoom("H-42", "Алиса", OUT, IN, 2, true, false, ""));
    }

    @Test
    void v2RequestRejectsInvalidDates() {
        assertThrows(IllegalArgumentException.class, () ->
                new BookingRequest("H-42", "Алиса", OUT, IN, 2, true, false, ""));
    }

    @Test
    void v2RequestRejectsZeroGuests() {
        assertThrows(IllegalArgumentException.class, () ->
                new BookingRequest("H-42", "Алиса", IN, OUT, 0, true, false, ""));
    }
}