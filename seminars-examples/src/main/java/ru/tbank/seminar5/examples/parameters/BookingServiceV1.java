package ru.tbank.seminar5.examples.parameters;

import java.time.LocalDate;

/**
 * ПЛОХО: длинный список параметров.
 *
 * <p>Метод {@code bookRoom} принимает восемь аргументов подряд. Трудно понять,
 * что за чем идёт, легко перепутать местами (например, даты), а при добавлении
 * нового поля (телефон, смена) сигнатуру придётся менять и править все вызовы.
 */
public class BookingServiceV1 {

    public Booking bookRoom(String hotelId, String guestName, LocalDate checkIn,
                            LocalDate checkOut, int guests, boolean breakfast,
                            boolean parking, String notes) {
        if (checkIn == null || checkOut == null || checkOut.isBefore(checkIn)) {
            throw new IllegalArgumentException("Некорректные даты бронирования");
        }
        if (guests < 1) {
            throw new IllegalArgumentException("Количество гостей должно быть положительным");
        }
        return new Booking(hotelId, guestName, checkIn, checkOut, guests, breakfast, parking, notes);
    }

    public static void demo() {
        var service = new BookingServiceV1();
        var booking = service.bookRoom("H-42", "Алиса",
                LocalDate.of(2026, 11, 10), LocalDate.of(2026, 11, 12),
                2, true, false, "");
        System.out.println("Забронировано: " + booking.guestName() + " в " + booking.hotelId());
    }
}