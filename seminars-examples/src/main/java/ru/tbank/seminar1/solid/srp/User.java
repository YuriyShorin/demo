package ru.tbank.seminar1.solid.srp;

/**
 * Простой объект-данных, общий для обоих примеров SRP.
 */
public record User(String name, String email, boolean subscribed) {
}