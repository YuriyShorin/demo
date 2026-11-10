package ru.tbank.seminar4.clients.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatchTaskRequest {
    private String title;
    private String description;
    private TaskStatus status;
}
