package ru.tbank.seminar4.clients.client;

import ru.tbank.seminar4.clients.dto.CreateTaskRequest;
import ru.tbank.seminar4.clients.dto.PatchTaskRequest;
import ru.tbank.seminar4.clients.dto.TaskDto;
import ru.tbank.seminar4.clients.dto.TaskStatus;
import ru.tbank.seminar4.clients.dto.UpdateTaskRequest;
import ru.tbank.seminar4.clients.utils.Json;

import java.util.List;

/**
 * Общая часть «императивных» HTTP-клиентов (JDK HttpClient и OkHttp).
 *
 * <p>У таких клиентов одинаковый набор CRUD-методов интерфейса {@link TaskClient},
 * а отличается только низкоуровневый HTTP-вызов. Поэтому весь CRUD реализован здесь один раз
 * через абстрактный метод {@link #call} (принцип DRY), а конкретная библиотека подставляет
 * только сам вызов.</p>
 */
public abstract class AbstractTaskClient implements TaskClient {

    /** Базовый URL сервиса, например {@code http://localhost:8080}. */
    protected final String base;

    protected AbstractTaskClient(String base) {
        this.base = base;
    }

    // --- CRUD из интерфейса TaskClient, реализованный через общий метод call() ---

    @Override
    public List<TaskDto> findAll() {
        return Json.tasks(call("GET", "/tasks", null));
    }

    @Override
    public List<TaskDto> findByStatus(TaskStatus status) {
        return Json.tasks(call("GET", "/tasks?status=" + status, null));
    }

    @Override
    public TaskDto find(long id) {
        return Json.task(call("GET", "/tasks/" + id, null));
    }

    @Override
    public TaskDto create(CreateTaskRequest request) {
        return Json.task(call("POST", "/tasks", request));
    }

    @Override
    public TaskDto replace(long id, UpdateTaskRequest request) {
        return Json.task(call("PUT", "/tasks/" + id, request));
    }

    @Override
    public TaskDto patch(long id, PatchTaskRequest request) {
        return Json.task(call("PATCH", "/tasks/" + id, request));
    }

    @Override
    public void delete(long id) {
        call("DELETE", "/tasks/" + id, null);
    }

    /**
     * Выполняет HTTP-запрос и возвращает тело ответа как строку.
     * Бросает исключение, если статус вне диапазона 2xx.
     *
     * @param method HTTP-метод (GET, POST, PUT, PATCH, DELETE)
     * @param path   путь, например {@code /tasks/1}
     * @param dto    тело запроса или {@code null}, если тела нет
     */
    protected abstract String call(String method, String path, Object dto);
}