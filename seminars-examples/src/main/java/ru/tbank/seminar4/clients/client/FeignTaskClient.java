package ru.tbank.seminar4.clients.client;

import feign.Client;
import feign.Feign;
import feign.Request;
import feign.Response;
import feign.Retryer;
import feign.http2client.Http2Client;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import ru.tbank.seminar4.clients.dto.CreateTaskRequest;
import ru.tbank.seminar4.clients.dto.PatchTaskRequest;
import ru.tbank.seminar4.clients.dto.TaskDto;
import ru.tbank.seminar4.clients.dto.TaskStatus;
import ru.tbank.seminar4.clients.dto.UpdateTaskRequest;
import ru.tbank.seminar4.clients.utils.Json;
import ru.tbank.seminar4.clients.utils.Traffic;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FeignTaskClient implements TaskClient {

    private final FeignApi api;

    public FeignTaskClient(String base) {
        this.api = Feign.builder()
                // Оборачиваем реальный транспорт так, чтобы логировать каждый запрос и ответ.
                .client(loggingClient(new Http2Client()))
                // Сериализация и десериализация JSON через общий ObjectMapper.
                .encoder(new JacksonEncoder(Json.MAPPER))
                .decoder(new JacksonDecoder(Json.MAPPER))
                // Каждому запросу добавляем заголовок X-Request-Id.
                .requestInterceptor(template -> template.header("X-Request-Id", Traffic.id()))
                // Таймауты: подключение 3 c, чтение ответа 10 c.
                .options(new Request.Options(3, TimeUnit.SECONDS, 10, TimeUnit.SECONDS, true))
                // Без ретраев — пусть ошибки всплывают сразу.
                .retryer(Retryer.NEVER_RETRY)
                // Создаём прокси-реализацию интерфейса FeignApi.
                .target(FeignApi.class, base);
    }

    /**
     * Оборачивает транспорт так, чтобы перед реальным вызовом логировался запрос,
     * а после — ответ. Ответ пересобирается с уже прочитанным телом, чтобы
     * Feign-декодер мог его разобрать.
     */
    private static Client loggingClient(Client transport) {
        return (request, options) -> {
            // id запроса берём из заголовка, который добавил requestInterceptor.
            String rid = request.headers().getOrDefault("X-Request-Id", List.of("?")).iterator().next();

            Traffic.request("Feign", rid, request.httpMethod().name(), request.url(), request.headers(),
                    request.body() == null ? null : new String(request.body(), StandardCharsets.UTF_8));

            long started = System.nanoTime();

            // Выполняем реальный запрос и читаем тело ответа, чтобы показать его в логе.
            Response response = transport.execute(request, options);
            byte[] bytes = response.body() == null ? new byte[0] : response.body().asInputStream().readAllBytes();
            response.close();

            String result = new String(bytes, StandardCharsets.UTF_8);
            Traffic.response("Feign", rid, response.status(), response.headers(), result, started);

            // Возвращаем ответ с уже прочитанным телом — Feign-декодер разберёт его дальше.
            return response.toBuilder().body(bytes).build();
        };
    }

    @Override
    public String name() {
        return "Feign";
    }

    @Override
    public List<TaskDto> findAll() {
        return api.all();
    }

    @Override
    public List<TaskDto> findByStatus(TaskStatus s) {
        return api.byStatus(s);
    }

    @Override
    public TaskDto find(long id) {
        return api.find(id);
    }

    @Override
    public TaskDto create(CreateTaskRequest r) {
        return api.create(r);
    }

    @Override
    public TaskDto replace(long id, UpdateTaskRequest r) {
        return api.replace(id, r);
    }

    @Override
    public TaskDto patch(long id, PatchTaskRequest r) {
        return api.patch(id, r);
    }

    @Override
    public void delete(long id) {
        api.delete(id);
    }
}