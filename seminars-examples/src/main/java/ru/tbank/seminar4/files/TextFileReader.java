package ru.tbank.seminar4.files;

import lombok.AllArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Три варианта чтения текстового файла в UTF-8.
 */
@AllArgsConstructor
public class TextFileReader {

    private final Path path;

    /**
     * Читает файл целиком (Java 11+).
     */
    public String readString() throws IOException {
        return Files.readString(path, StandardCharsets.UTF_8);
    }

    /**
     * Читает файл по строкам, используя BufferedReader.
     */
    public String readBuffered() throws IOException {
        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line).append('\n');
            }
        }
        return result.toString();
    }

    /**
     * Читает строки посредством Stream API; поток обязательно закрываем.
     */
    public String readLines() throws IOException {
        try (Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)) {
            return lines.collect(Collectors.joining("\n"));
        }
    }
}
