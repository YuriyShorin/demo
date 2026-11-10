package ru.tbank.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Slf4j
@Component
@Order(1)
public class HttpTrafficFilter implements Filter {

    private static final Set<String> NOISE_HEADERS = Set.of(
            "connection", "host", "user-agent", "content-length", "accept-encoding",
            "accept-language", "accept-charset", "upgrade", "http2-settings",
            "upgrade-insecure-requests", "keep-alive");

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest raw = (HttpServletRequest) req;
        ContentCachingRequestWrapper request = new ContentCachingRequestWrapper(raw, 1024);
        ContentCachingResponseWrapper response = new ContentCachingResponseWrapper((HttpServletResponse) res);

        String rid = request.getHeader("X-Request-Id");
        if (rid == null || rid.isBlank()) rid = UUID.randomUUID().toString().substring(0, 8);
        response.setHeader("X-Request-Id", rid);

        long started = System.nanoTime();
        try {
            chain.doFilter(request, response);
        } finally {
            String url = request.getRequestURI() + (request.getQueryString() == null ? "" : "?" + request.getQueryString());
            String block = """
                    ========== SERVER REQUEST [%s] ==========
                    %s %s
                    Headers: %s
                    Body: %s
                    ========== SERVER RESPONSE [%s] ==========
                    Status: %d
                    Headers: %s
                    Body: %s
                    Duration: %d ms
                    ===========================================""".formatted(
                    rid, request.getMethod(), url, headers(request), body(request.getContentAsByteArray()),
                    rid, response.getStatus(), headers(response), body(response.getContentAsByteArray()),
                    (System.nanoTime() - started) / 1_000_000);
            log.info("\n{}", block);
            response.copyBodyToResponse();
        }
    }

    private static Map<String, List<String>> headers(HttpServletRequest request) {
        Map<String, List<String>> map = new LinkedHashMap<>();
        Collections.list(request.getHeaderNames()).stream()
                .filter(name -> !NOISE_HEADERS.contains(name.toLowerCase(Locale.ROOT)))
                .forEach(name -> {
                    List<String> values = Collections.list(request.getHeaders(name));
                    map.put(name, name.equalsIgnoreCase("authorization") ? List.of("<redacted>") : values);
                });
        return map;
    }

    private static Map<String, List<String>> headers(ContentCachingResponseWrapper response) {
        Map<String, List<String>> map = new LinkedHashMap<>();
        response.getHeaderNames().stream()
                .filter(name -> !NOISE_HEADERS.contains(name.toLowerCase(Locale.ROOT)))
                .forEach(name -> map.put(name, new java.util.ArrayList<>(response.getHeaders(name))));
        return map;
    }

    private static String body(byte[] b) {
        return b.length == 0 ? "<empty>" : new String(b, StandardCharsets.UTF_8);
    }
}