package ru.tbank.error;

import lombok.*;

@Data
@AllArgsConstructor
public class ApiError {
    private int status;
    private String message;
    private String path;
}
