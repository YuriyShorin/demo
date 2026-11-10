package ru.tbank.seminar4.files;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

/**
 * Запуск: java ... Main [input.txt] [report.txt] [readString|buffered|lines] [writeString|buffered|bytes]
 * Без параметров используются samples/essay.txt и report.txt.
 */
public class Main {
    public static void main(String[] args) {
        Path input = args.length > 0 ? Path.of(args[0]) : Path.of("/Users/y.i.shorin/IdeaProjects/demo/seminars-examples/src/main/java/ru/tbank/seminar4/files/samples/essay.txt");
        Path output = Path.of(args.length > 1 ? args[1] : "report.txt");
        String readMethod = args.length > 2 ? args[2] : "readString";
        String writeMethod = args.length > 3 ? args[3] : "writeString";

        TextFileReader textFileReader = new TextFileReader(input);

        try {
            String text = switch (readMethod) {
                case "readString" -> textFileReader.readString();
                case "buffered" -> textFileReader.readBuffered();
                case "lines" -> textFileReader.readLines();
                default -> throw new IllegalArgumentException("Неизвестный способ чтения: " + readMethod);
            };

            WordCounter wordCounter = new WordCounter(text);
            Map<String, Integer> counts = wordCounter.count();

            Reporter reporter = new Reporter(counts);
            String report = reporter.makeReport();

            TextFileWriter textFileWriter = new TextFileWriter(output, report);

            switch (writeMethod) {
                case "writeString" -> textFileWriter.writeString();
                case "buffered" -> textFileWriter.writeBuffered();
                case "bytes" -> textFileWriter.writeBytes();
                default -> throw new IllegalArgumentException("Неизвестный способ записи: " + writeMethod);
            }

            System.out.println("Обработка завершена. Уникальных слов длиной > 2: " + counts.size());
            System.out.println("Отчёт: " + output.toAbsolutePath());
        } catch (IOException | IllegalArgumentException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }
}
