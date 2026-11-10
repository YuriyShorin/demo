package ru.tbank.seminar4.clients.client;

import ru.tbank.seminar4.clients.utils.Json;
import ru.tbank.seminar4.clients.utils.Traffic;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class JdkTaskClient extends AbstractTaskClient {

    /** Клиент с таймаутом подключения — переиспользуется для всех вызовов. */
    private final HttpClient http = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(3))
            .build();

    public JdkTaskClient(String base) {
        super(base);
    }

    @Override
    public String name() {
        return "Java HttpClient";
    }

    @Override
    protected String call(String method, String path, Object dto) {
        // 1. Собираем полный URL и сериализуем тело запроса (если оно есть).
        String url = base + path;
        String rid = Traffic.id();
        String json = dto == null ? null : Json.write(dto);

        // 2. Строим HTTP-запрос: общие заголовки + тело для методов с payload.
        HttpRequest.Builder builder = HttpRequest.newBuilder(URI.create(url))
                .timeout(Duration.ofSeconds(10))
                .header("X-Request-Id", rid)
                .header("Accept", "application/json");
        if (json != null) {
            builder.header("Content-Type", "application/json");
        }
        HttpRequest request = builder.method(method,
                json == null ? HttpRequest.BodyPublishers.noBody() : HttpRequest.BodyPublishers.ofString(json)).build();

        // 3. Логируем исходящий запрос.
        Traffic.request(name(), rid, method, url, request.headers().map(), json);

        // 4. Выполняем запрос и логируем ответ.
        long started = System.nanoTime();
        try {
            HttpResponse<String> response = http.send(request, HttpResponse.BodyHandlers.ofString());
            Traffic.response(name(), rid, response.statusCode(), response.headers().map(), response.body(), started);

            // 5. Статус вне 2xx считаем ошибкой.
            if (response.statusCode() < 200 || response.statusCode() >= 300) {
                throw Traffic.fail(response.statusCode(), response.body());
            }
            return response.body();
        } catch (InterruptedException e) {
            // Восстанавливаем флаг прерывания потока и оборачиваем в RuntimeException.
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        } catch (java.io.IOException e) {
            System.err.println("Java HttpClient transport failed: " + e);
            throw new RuntimeException(e);
        }
    }
}