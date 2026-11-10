package ru.tbank.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import ru.tbank.dto.*;
import ru.tbank.service.TaskService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService service;

    @GetMapping
    public List<TaskDto> all(@RequestParam(value = "status", required = false) TaskStatus status) {
        return service.findAll(status);
    }

    @GetMapping("/{id}")
    public TaskDto one(@PathVariable("id") long id) {
        return service.find(id);
    }

    @PostMapping
    public ResponseEntity<TaskDto> create(@RequestBody CreateTaskRequest request) {
        TaskDto result = service.create(request);
        return ResponseEntity.created(URI.create("/tasks/" + result.getId())).body(result);
    }

    @PutMapping("/{id}")
    public TaskDto put(@PathVariable("id") long id, @RequestBody UpdateTaskRequest request) {
        return service.replace(id, request);
    }

    @PatchMapping("/{id}")
    public TaskDto patch(@PathVariable("id") long id, @RequestBody PatchTaskRequest request) {
        return service.patch(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") long id) {
        service.delete(id);
    }
}
