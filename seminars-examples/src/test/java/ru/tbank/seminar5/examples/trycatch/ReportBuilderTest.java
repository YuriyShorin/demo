package ru.tbank.seminar5.examples.trycatch;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Характеризационные тесты для рефакторинга «try/catch на каждом шаге».
 *
 * <p>Ключевое различие: V1 проглатывает ошибки и возвращает пустую строку,
 * V2 пробрасывает их (fail fast). Тесты фиксируют оба поведения, показывая,
 * что рефакторинг именно «починил» обработку ошибок, а не просто переставил код.
 */
class ReportBuilderTest {

    @Test
    void validInputProducesResultInBoth() throws Exception {
        assertEquals("OK:отчёт", new ReportBuilderV1().build("отчёт"));
        assertEquals("OK:отчёт", new ReportBuilderV2().build("отчёт"));
    }

    @Test
    void v1SilentlyReturnsEmptyStringOnParseError() {
        assertEquals("", new ReportBuilderV1().build("!"));
    }

    @Test
    void v2PropagatesParseError() {
        assertThrows(ReportParseException.class, () -> new ReportBuilderV2().build("!"));
    }

    @Test
    void v2PropagatesFormatError() {
        assertThrows(IOException.class, () -> new ReportBuilderV2().build("ERR"));
    }
}