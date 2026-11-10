package ru.tbank.seminar4.files;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Создаёт отчёт по частоте слов.
 */
@AllArgsConstructor
public class Reporter {

    private final Map<String, Integer> counts;

    /**
     * Создаёт отчёт.
     */
    public String makeReport() {
        StringBuilder report = new StringBuilder();
        for (Map.Entry<String, Integer> entry : sorted(counts)) {
            report.append(entry.getKey())
                    .append(" — ")
                    .append(entry.getValue())
                    .append('\n');
        }
        return report.toString();
    }

    /**
     * Все слова, включая встретившиеся один раз; равные частоты — по алфавиту.
     */
    private List<Map.Entry<String, Integer>> sorted(Map<String, Integer> counts) {
        List<Map.Entry<String, Integer>> result = new ArrayList<>(counts.entrySet());
        result.sort(Map.Entry.<String, Integer>comparingByValue()
                .reversed()
                .thenComparing(Map.Entry.comparingByKey()));
        return result;
    }
}
