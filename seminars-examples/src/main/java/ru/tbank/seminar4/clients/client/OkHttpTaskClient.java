package ru.tbank.seminar4.clients.client;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import ru.tbank.seminar4.clients.utils.Json;
import ru.tbank.seminar4.clients.utils.Traffic;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class OkHttpTaskClient extends AbstractTaskClient {

    /** Клиент с общим таймаутом на весь вызов — переиспользуется для всех запросов. */
    private final OkHttpClient http = new OkHttpClient.Builder()
            .callTimeout(10, TimeUnit.SECONDS)
            .build();

    public OkHttpTaskClient(String base) {
        super(base);
    }

    @Override
    public String name() {
        return "OkHttp";
    }

    @Override
    protected String call(String method, String path, Object dto) {
        // 1. Собираем полный URL, id запроса и сериализуем тело (если есть).
        String url = base + path;
        String rid = Traffic.id();
        String json = dto == null ? null : Json.write(dto);

        // 2. Строим запрос: тело есть только у методов с payload.
        RequestBody body = json == null ? null : RequestBody.create(json, MediaType.get("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url(url)
                .header("X-Request-Id", rid)
                .header("Accept", "application/json")
                .method(method, body)
                .build();

        // 3. Логируем исходящий запрос.
        Traffic.request(name(), rid, method, url, request.headers().toMultimap(), json);

        // 4. Выполняем запрос и логируем ответ.
        long started = System.nanoTime();
        try (Response response = http.newCall(request).execute()) {
            String result = response.body().string();
            Traffic.response(name(), rid, response.code(), response.headers().toMultimap(), result, started);

            // 5. Статус вне 2xx считаем ошибкой.
            if (!response.isSuccessful()) {
                throw Traffic.fail(response.code(), result);
            }
            return result;
        } catch (IOException e) {
            System.err.println("OkHttp transport failed: " + e);
            throw new RuntimeException(e);
        }
    }
}