package ru.tbank.seminar4.files;

import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Считает слова длиннее двух символов и сортирует по частоте.
 */
@AllArgsConstructor
public class WordCounter {
    private static final Pattern WORD = Pattern.compile("\\p{L}+");

    private final Set<String> prepositions = Set.of("без", "между", "при", "что", "чем", "под");
    private final String text;

    public Map<String, Integer> count() {
        Map<String, Integer> counts = new HashMap<>();
        Matcher matcher = WORD.matcher(text.toLowerCase(Locale.ROOT));
        while (matcher.find()) {
            String word = matcher.group();
            if (word.length() > 2 && !prepositions.contains(word)) {
                counts.merge(word, 1, Integer::sum);
            }
        }
        return counts;
    }
}
