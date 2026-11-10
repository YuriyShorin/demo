---
name: http-client-comparison
description: Compare multiple HTTP client implementations (JDK HttpClient, OkHttp, OpenFeign) against the same API contract for teaching or evaluation
source: auto-skill
extracted_at: '2026-10-21T08:25:18.986Z'
---

# HTTP Client Comparison

Use this approach when teaching HTTP clients, evaluating alternatives, or demonstrating that different implementations produce the same results against a shared API contract.

## Structure

```
clients/
├── TaskClient.java               # Common interface defining all API operations
├── JdkTaskClient.java            # java.net.http.HttpClient implementation
├── OkHttpTaskClient.java         # Square OkHttp implementation
├── FeignTaskClient.java          # OpenFeign declarative interface
├── dto/                          # Shared DTOs (Copy from server or duplicate)
│   ├── TaskDto.java
│   ├── CreateTaskRequest.java
│   ├── UpdateTaskRequest.java
│   ├── PatchTaskRequest.java
│   └── TaskStatus.java
├── exception/HttpStatusException.java
├── utils/Json.java               # Jackson serialization helpers
└── utils/Traffic.java            # Request/response logging utility
DemoApplication.java              # Runs all clients against the same scenario
```

## Common interface

Define a single interface that all implementations must satisfy:

```java
public interface TaskClient {
    String name();                        // For demo output identification
    List<TaskDto> findAll();
    List<TaskDto> findByStatus(TaskStatus status);
    TaskDto find(long id);
    TaskDto create(CreateTaskRequest request);
    TaskDto replace(long id, UpdateTaskRequest request);
    TaskDto patch(long id, PatchTaskRequest request);
    void delete(long id);
}
```

The `name()` method is specific to the demo pattern — it lets the orchestrator print which client is running. In production code, skip this.

## Shared DTOs

DTOs are **duplicated** in the client module (not shared via a common dependency). This is intentional for teaching:
- Each client module is self-contained and independently runnable
- Demonstrates that the API contract is defined by the wire format, not by shared classes
- In production, use a shared model module or code generation (OpenAPI) instead

## Demo application pattern

The orchestrator runs a full CRUD scenario against each client sequentially:

```java
public static void main(String[] args) {
    String base = args.length == 0 ? "http://localhost:8080" : args[0];
    List<TaskClient> clients = List.of(
        new JdkTaskClient(base),
        new OkHttpTaskClient(base),
        new FeignTaskClient(base)
    );
    for (TaskClient client : clients) runScenario(client);
}
```

Each scenario: create → find → list → filter → replace → patch → verify → delete → verify-deleted (expect 404).

## Client implementation patterns

### Shared base class for imperative clients (JDK + OkHttp)

When JDK HttpClient and OkHttp implementations share the same CRUD operations, extract the
common code into an abstract base class. This eliminates the DRY violation of 7 identical CRUD
methods duplicated across two files — a pattern you teach in seminar1.

```java
public abstract class AbstractTaskClient implements TaskClient {
    protected final String base;

    protected AbstractTaskClient(String base) {
        this.base = base;
    }

    // All CRUD methods implemented once, delegating to abstract call()
    @Override public List<TaskDto> findAll() {
        return Json.tasks(call("GET", "/tasks", null));
    }
    // ... find, create, replace, patch, delete follow same pattern
    // ... using Json.task() / Json.tasks() for deserialization

    protected abstract String call(String method, String path, Object dto);
}
```

Concrete clients extend the base and implement only `call()`:

```java
public class JdkTaskClient extends AbstractTaskClient {
    private final HttpClient http = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(3)).build();

    @Override protected String call(String method, String path, Object dto) {
        // 1. URL + serialize body
        // 2. Build HttpRequest
        // 3. Log request via Traffic.request()
        // 4. Execute and log response via Traffic.response()
        // 5. Throw on non-2xx
    }
}
```

**When to apply:** Both clients use the same imperative `call(method, path, body)` pattern.
**When NOT to apply:** Feign uses a declarative proxy model (`api.xxx()`), so it doesn't fit
`call(...)`. Leave Feign's CRUD methods delegating to the generated proxy.

### JDK HttpClient
- Uses `java.net.http.HttpClient` and `HttpRequest`
- Manual JSON serialization/deserialization via Jackson
- No dependency beyond Jackson
- Most boilerplate, zero external HTTP library

### OkHttp
- Uses `okhttp3.OkHttpClient` and `Request.Builder`
- Manual JSON via Jackson + custom `RequestBody.create()`
- Requires `okhttp-jvm` dependency
- More concise than JDK, but still manual

### OpenFeign
- Declarative interface with `@RequestLine` / `@Headers` annotations
- Jackson decoder via `feign-jackson` module
- Requires `feign-core`, `feign-java11`, `feign-jackson`
- Least boilerplate; interface is the contract

## Logging and code structure conventions

When refactoring HTTP client code, apply these patterns for clarity:

1. **Numbored logical steps** inside `call()`: (1) URL + serialization → (2) build request →
   (3) log request → (4) execute + log response → (5) check status.
2. **Blank lines between steps** so each phase is visually separable.
3. **Inline comments** on each step explaining *what* and *why*, not restating the obvious.
4. **Feign logging** — wrap the transport in a private `loggingClient(Client)` method rather
   than inlining the lambda in the builder chain.
5. **Comments in Russian** matching the project's existing style.

## Traffic logging

Both the server and the clients log request/response traffic:
- Server: `HttpTrafficFilter` — servlet filter wrapping request/response, logs headers (redacting auth), body, duration, generates `X-Request-Id`
- Client: `Traffic` utility — static methods logging client name, request ID, method, URL, headers, body, response status, duration

This makes it easy to trace a request end-to-end through the demo.

## When to apply

- Teaching different HTTP client approaches to a team
- Evaluating which HTTP client to adopt for a new project
- Creating demo code that exercises a REST API from multiple angles
- Demonstrating that the API contract is implementation-agnostic

## When NOT to apply

- Production code — pick one HTTP client and stick with it
- When the API is complex (GraphQL, streaming, multipart) — the simple CRUD interface doesn't cover those cases
