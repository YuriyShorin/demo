package ru.tbank.seminar4.files;

import lombok.AllArgsConstructor;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Три варианта записи текста в UTF-8 (все перезаписывают файл).
 */
@AllArgsConstructor
public class TextFileWriter {

    private final Path path;

    private final String text;

    /**
     * Записывает текст целиком (Java 11+).
     */
    public void writeString() throws IOException {
        Files.writeString(path, text, StandardCharsets.UTF_8);
    }

    /**
     * Записывает через буферизованный символьный поток.
     */
    public void writeBuffered() throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            writer.write(text);
        }
    }

    /**
     * Преобразует текст в байты UTF-8 и записывает через байтовый поток.
     */
    public void writeBytes() throws IOException {
        try (FileOutputStream output = new FileOutputStream(path.toFile())) {
            output.write(text.getBytes(StandardCharsets.UTF_8));
        }
    }
}
