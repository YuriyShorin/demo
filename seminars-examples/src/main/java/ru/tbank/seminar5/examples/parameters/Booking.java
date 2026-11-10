package ru.tbank.seminar5.examples.parameters;

import java.time.LocalDate;

/** Забронированный номер: все данные бронирования. */
public record Booking(String hotelId, String guestName, LocalDate checkIn,
                      LocalDate checkOut, int guests, boolean breakfast,
                      boolean parking, String notes) {
}