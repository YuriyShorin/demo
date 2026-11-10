package ru.tbank.error;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.converter.HttpMessageNotReadableException;

@RestControllerAdvice
public class GlobalErrors {
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ApiError> response(ResponseStatusException e, HttpServletRequest request) {
        int code = e.getStatusCode().value();
        return ResponseEntity.status(code).body(new ApiError(code, e.getReason(), request.getRequestURI()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> json(HttpMessageNotReadableException e, HttpServletRequest request) {
        return ResponseEntity.badRequest().body(new ApiError(400, "Invalid JSON or enum value", request.getRequestURI()));
    }
}
