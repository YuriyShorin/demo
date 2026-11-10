package ru.tbank.seminar4.clients.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTaskRequest {
    private String title;
    private String description;
}
