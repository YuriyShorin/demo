package ru.tbank.seminar4.clients.utils;

import ru.tbank.seminar4.clients.exception.HttpStatusException;

import java.util.Map;
import java.util.UUID;

public final class Traffic {

    public static String id() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

    public static void request(String lib, String id, String method, String url, Map<String, ?> headers, String body) {
        System.out.printf("%n========== %s REQUEST [%s] ==========%n%s %s%nHeaders: %s%nBody: %s%n", lib, id, method, url, headers, body == null || body.isEmpty() ? "<empty>" : body);
    }

    public static void response(String lib, String id, int status, Map<String, ?> headers, String body, long started) {
        System.out.printf("========== %s RESPONSE [%s] ==========%nStatus: %d%nHeaders: %s%nBody: %s%nDuration: %d ms%n========================================%n", lib, id, status, headers, body == null || body.isEmpty() ? "<empty>" : body, (System.nanoTime() - started) / 1_000_000);
    }

    public static RuntimeException fail(int status, String body) {
        return new HttpStatusException(status, body);
    }
}
