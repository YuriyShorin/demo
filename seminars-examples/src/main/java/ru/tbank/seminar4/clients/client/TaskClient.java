package ru.tbank.seminar4.clients.client;

import ru.tbank.seminar4.clients.dto.PatchTaskRequest;
import ru.tbank.seminar4.clients.dto.TaskDto;
import ru.tbank.seminar4.clients.dto.TaskStatus;
import ru.tbank.seminar4.clients.dto.UpdateTaskRequest;
import ru.tbank.seminar4.clients.dto.CreateTaskRequest;

import java.util.List;

public interface TaskClient {
    String name();

    List<TaskDto> findAll();

    List<TaskDto> findByStatus(TaskStatus status);

    TaskDto find(long id);

    TaskDto create(CreateTaskRequest request);

    TaskDto replace(long id, UpdateTaskRequest request);

    TaskDto patch(long id, PatchTaskRequest request);

    void delete(long id);
}
