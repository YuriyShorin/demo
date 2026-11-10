package ru.tbank.seminar5.examples.parameters;

/**
 * ХОРОШО: длинный список параметров заменён объектом-запросом.
 *
 * <p>Вместо восьми аргументов метод принимает один объект {@link BookingRequest}.
 * Сигнатура компактна, аргументы нельзя перепутать местами (у них есть имена),
 * а валидация живёт в одном месте — в конструкторе запроса.
 */
public class BookingServiceV2 {

    public Booking bookRoom(BookingRequest request) {
        return new Booking(request.hotelId(), request.guestName(), request.checkIn(),
                request.checkOut(), request.guests(), request.breakfast(),
                request.parking(), request.notes());
    }

    public static void demo() {
        var service = new BookingServiceV2();
        var request = new BookingRequest("H-42", "Алиса",
                java.time.LocalDate.of(2026, 11, 10), java.time.LocalDate.of(2026, 11, 12),
                2, true, false, "");
        var booking = service.bookRoom(request);
        System.out.println("Забронировано: " + booking.guestName() + " в " + booking.hotelId());
    }
}