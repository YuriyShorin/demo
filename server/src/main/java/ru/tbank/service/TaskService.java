package ru.tbank.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.tbank.dto.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TaskService {
    private final Map<Long, TaskDto> tasks = new ConcurrentHashMap<>();
    private final AtomicLong ids = new AtomicLong();

    public List<TaskDto> findAll(TaskStatus status) {
        return tasks.values().stream().filter(t -> status == null || t.getStatus() == status)
                .sorted(Comparator.comparing(TaskDto::getId)).map(this::copy).toList();
    }

    public TaskDto find(long id) {
        return copy(existing(id));
    }

    public TaskDto create(CreateTaskRequest r) {
        requireText(r.getTitle(), "title");
        long id = ids.incrementAndGet();
        TaskDto t = new TaskDto(id, r.getTitle(), r.getDescription(), TaskStatus.TODO);
        tasks.put(id, t);
        return copy(t);
    }

    public TaskDto replace(long id, UpdateTaskRequest r) {
        requireText(r.getTitle(), "title");
        if (r.getStatus() == null) bad("status is required for PUT");
        TaskDto next = new TaskDto(id, r.getTitle(), r.getDescription(), r.getStatus());
        if (tasks.replace(id, next) == null) missing(id);
        return copy(next);
    }

    public TaskDto patch(long id, PatchTaskRequest r) {
        if (r.getTitle() == null && r.getDescription() == null && r.getStatus() == null)
            bad("PATCH must contain at least one non-null field");
        // null means unchanged in this teaching demo; clearing description is not supported
        TaskDto next = tasks.computeIfPresent(id, (k, old) -> {
            String title = r.getTitle() == null ? old.getTitle() : r.getTitle();
            requireText(title, "title");
            return new TaskDto(k, title, r.getDescription() == null ? old.getDescription() : r.getDescription(), r.getStatus() == null ? old.getStatus() : r.getStatus());
        });
        if (next == null) missing(id);
        return copy(next);
    }

    public void delete(long id) {
        if (tasks.remove(id) == null) missing(id);
    }

    private TaskDto existing(long id) {
        TaskDto t = tasks.get(id);
        if (t == null) missing(id);
        return t;
    }

    private TaskDto copy(TaskDto t) {
        return new TaskDto(t.getId(), t.getTitle(), t.getDescription(), t.getStatus());
    }

    private void requireText(String v, String field) {
        if (v == null || v.isBlank()) bad(field + " cannot be blank");
    }

    private void bad(String s) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, s);
    }

    private void missing(long id) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task " + id + " not found");
    }
}
