package ru.tbank.seminar4.clients.client;

import feign.*;
import ru.tbank.seminar4.clients.dto.PatchTaskRequest;
import ru.tbank.seminar4.clients.dto.TaskDto;
import ru.tbank.seminar4.clients.dto.TaskStatus;
import ru.tbank.seminar4.clients.dto.UpdateTaskRequest;
import ru.tbank.seminar4.clients.dto.CreateTaskRequest;

import java.util.List;

// Plain OpenFeign: NO Spring annotations or Spring dependencies.
public interface FeignApi {
    @RequestLine("GET /tasks")
    List<TaskDto> all();

    @RequestLine("GET /tasks?status={status}")
    List<TaskDto> byStatus(@Param("status") TaskStatus status);

    @RequestLine("GET /tasks/{id}")
    TaskDto find(@Param("id") long id);

    @RequestLine("POST /tasks")
    @Headers("Content-Type: application/json")
    TaskDto create(CreateTaskRequest request);

    @RequestLine("PUT /tasks/{id}")
    @Headers("Content-Type: application/json")
    TaskDto replace(@Param("id") long id, UpdateTaskRequest request);

    @RequestLine("PATCH /tasks/{id}")
    @Headers("Content-Type: application/json")
    TaskDto patch(@Param("id") long id, PatchTaskRequest request);

    @RequestLine("DELETE /tasks/{id}")
    void delete(@Param("id") long id);
}
