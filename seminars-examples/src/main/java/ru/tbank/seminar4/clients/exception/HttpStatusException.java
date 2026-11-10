package ru.tbank.seminar4.clients.exception;

import lombok.Getter;

@Getter
public class HttpStatusException extends RuntimeException {
    private final int status;

    public HttpStatusException(int status, String body) {
        super("HTTP " + status + ": " + body);
        this.status = status;
    }
}
