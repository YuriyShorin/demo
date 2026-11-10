---
name: in-memory-crud-api
description: Build a Spring Boot in-memory CRUD REST API with Lombok DTOs, global error handling, and HTTP traffic logging filter
source: auto-skill
extracted_at: '2026-10-21T08:25:18.986Z'
---

# In-Memory CRUD REST API

Use this approach when prototyping a REST API, building a teaching demo, or creating a mock service that needs CRUD operations without a database.

## Architecture

```
ru.tbank/
├── ServerApplication.java          # @SpringBootApplication, main entry
├── controller/TaskController.java  # @RestController, @RequestMapping("/tasks")
├── service/TaskService.java        # @Service, in-memory storage (ConcurrentHashMap)
├── dto/                            # Data transfer objects
│   ├── TaskDto.java                # Main entity DTO
│   ├── CreateTaskRequest.java      # POST body
│   ├── UpdateTaskRequest.java      # PUT body (full replace)
│   ├── PatchTaskRequest.java       # PATCH body (partial update)
│   └── TaskStatus.java             # Enum: TODO, IN_PROGRESS, DONE
├── error/
│   ├── GlobalErrors.java           # @RestControllerAdvice
│   └── ApiError.java               # {status, message, path}
└── filter/HttpTrafficFilter.java   # @Order(1) servlet Filter, logs request/response
```

## Key decisions

### In-memory storage

Use `ConcurrentHashMap<Long, T>` + `AtomicLong` for IDs. This is appropriate for:
- Teaching demos where persistence is irrelevant
- Prototyping API contracts before adding a database
- Mock services for client-side testing

Thread safety is critical — use `ConcurrentHashMap` (not `HashMap`), `AtomicLong` (not `long` counter), and defensive copies on reads (`copy()` method returns a new DTO instance).

### DTO separation

Always separate request DTOs from the entity DTO:

| DTO | Purpose | Fields |
|---|---|---|
| `TaskDto` | Response / internal representation | id, title, description, status |
| `CreateTaskRequest` | POST body | title, description (no id) |
| `UpdateTaskRequest` | PUT body | title, description, status (all required) |
| `PatchTaskRequest` | PATCH body | title?, description?, status? (null = unchanged) |

This avoids exposing the `id` field on creation and makes the difference between full replace (PUT) and partial update (PATCH) explicit.

### HTTP method semantics

| Method | Endpoint | Status | Behavior |
|---|---|---|---|
| GET | `/tasks` | 200 | All tasks, optional `?status=` filter |
| GET | `/tasks/{id}` | 200 / 404 | Single task |
| POST | `/tasks` | 201 + `Location` header | Create, returns created DTO |
| PUT | `/tasks/{id}` | 200 / 404 | Full replace — all fields required |
| PATCH | `/tasks/{id}` | 200 / 404 | Partial — null fields are unchanged |
| DELETE | `/tasks/{id}` | 204 | No body |

### Error handling

Use `ResponseStatusException` in the service layer (not try/catch in the controller). A single `@RestControllerAdvice` catches these and maps them to a consistent JSON error format:

```java
@RestControllerAdvice
public class GlobalErrors {
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ApiError> handle(ResponseStatusException e, HttpServletRequest request) {
        return ResponseEntity.status(e.getStatusCode().value())
            .body(new ApiError(e.getStatusCode().value(), e.getReason(), request.getRequestURI()));
    }
}
```

Also handle `HttpMessageNotReadableException` for malformed JSON or invalid enum values.

### Traffic logging filter

A `@Order(1)` `Filter` wrapping requests/responses in `ContentCachingRequestWrapper` / `ContentCachingResponseWrapper` logs:
- Method, URI, query string
- Headers (redacting `Authorization`)
- Request/response body
- Duration in milliseconds
- Generates/echoes `X-Request-Id` header (random UUID if absent)

## Lombok conventions

- `@Data` + `@NoArgsConstructor` + `@AllArgsConstructor` on DTOs (generates all accessors, `equals`, `hashCode`, `toString`, and all constructors)
- `@RequiredArgsConstructor` on controllers/services (generates constructor for `final` fields)
- `@Slf4j` on filters/components (generates `private static final Logger log`)

## When NOT to apply

- Production services that need persistence, transactions, or data integrity
- APIs with large datasets (in-memory storage doesn't scale)
- Services requiring search, filtering beyond simple enum match, or pagination
