package ru.tbank.seminar4.clients.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import ru.tbank.seminar4.clients.dto.TaskDto;

import java.io.IOException;
import java.util.List;

public final class Json {
    public static final ObjectMapper MAPPER = new ObjectMapper();

    public static String write(Object o) {
        try {
            return MAPPER.writeValueAsString(o);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public static TaskDto task(String s) {
        return read(s, TaskDto.class);
    }

    public static List<TaskDto> tasks(String s) {
        try {
            return MAPPER.readValue(s, new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static <T> T read(String s, Class<T> type) {
        try {
            return MAPPER.readValue(s, type);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
