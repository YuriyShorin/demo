package ru.tbank.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatchTaskRequest {
    private String title;
    private String description;
    private TaskStatus status;
}
