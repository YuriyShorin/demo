# Project Context: demo

Maven multi-module project (`ru.tbank:demo`, Java/Kotlin) used as **teaching material for
T-Bank programming seminars**. It contains a runnable Spring Boot "task" REST API and a companion
module of self-contained seminar examples that exercise that API and demonstrate Java language
features and design principles.

## Repository Layout

```
pom.xml                     Parent POM: aggregator + shared Java/Kotlin/Lombok config
server/                     Spring Boot REST service ("task server") — the API under test
seminars-examples/          Standalone teaching examples, organized by seminar number
```

### Modules

- **`server`** (`ru.tbank:server`) — Spring Boot 4.1.0 service, Java 25, Lombok. In-memory task
  management API with no persistence.
- **`seminars-examples`** (`ru.tbank:seminars-examples`) — plain Java 21 (`release 21`), Lombok,
  Jackson, OkHttp, OpenFeign. Educational code grouped by seminar, self-runnable via `main()`.

## server — Task REST API

Spring Boot service (`ServerApplication`, `@SpringBootApplication`) exposing a task CRUD API
under `/tasks`. Data is held in memory only (`ConcurrentHashMap` + `AtomicLong` in `TaskService`);
no database. Runs on port 8080 (`server.port=8080`), app name `task-server`.

Package structure (`ru.tbank`):
- `controller/TaskController.java` — `@RestController`, `@RequestMapping("/tasks")`.
  - `GET /tasks` — all tasks, optional `?status=` filter (`TaskStatus`)
  - `GET /tasks/{id}`, `POST /tasks` (201 + `Location` header), `PUT /tasks/{id}` (full replace),
    `PATCH /tasks/{id}` (partial, `null` = unchanged), `DELETE /tasks/{id}` (204)
- `service/TaskService.java` — business logic; throws `ResponseStatusException`
  (400 for blank title / invalid PATCH / missing PUT status, 404 for unknown id)
- `dto/` — `TaskDto`, `CreateTaskRequest`, `UpdateTaskRequest`, `PatchTaskRequest`,
  `TaskStatus` enum (`TODO`, `IN_PROGRESS`, `DONE`)
- `error/GlobalErrors.java` — `@RestControllerAdvice` mapping `ResponseStatusException` and
  `HttpMessageNotReadableException` to a JSON `ApiError {status, message, path}`
- `filter/HttpTrafficFilter.java` — `@Order(1)` servlet `Filter` logging every request/response
  body and headers. Generates/echoes an `X-Request-Id` header (random UUID if absent) and redacts
  the `Authorization` header.

DTOs and `ApiError` use Lombok (`@Data`, `@NoArgsConstructor`, `@AllArgsConstructor`,
`@RequiredArgsConstructor`).

## seminars-examples — Teaching examples

Plain Java examples organized by package `ru.tbank.seminar<N>.<topic>`. Each has runnable
`main`/`demo()` methods. Comments and Javadoc are written in **Russian**.

- **`seminar1`** — Java language fundamentals:
  - `gof/` — all 23 GoF design patterns (creational/structural/behavioral), each a self-contained
    file with a `static void demo()`, orchestrated by `GoFDemo`.
  - `solid/` — SOLID principles (SRP, OCP, LSP, ISP, DIP) plus KISS and DRY, each with paired
    `Bad*` / `Good*` classes showing the anti-pattern vs. the fix, orchestrated by `SolidDemo`.
  - `interfaces/`, `lombok/` — functional-interface and Lombok examples.
- **`seminar2`** — generics, lambdas, `Optional`.
- **`seminar3`** — collections: `ListBenchmark`, `MapDemo`, `SetDemo`, `DequeDemo`.
- **`seminar4`** — I/O and HTTP:
  - `files/` — reading/writing text files (`TextFileReader`/`TextFileWriter`) with a word-count
    report pipeline (`WordCounter`, `Reporter`), entry point `Main`.
  - `clients/` — HTTP client implementations against the `server` `/tasks` API: `JdkTaskClient`
    (java.net.http), `OkHttpTaskClient` (OkHttp), `FeignTaskClient` (OpenFeign), all behind the
    common `TaskClient` interface. `DemoApplication.main(args)` runs a full CRUD scenario against
    each client (base URL defaults to `http://localhost:8080`, override via first arg).
- **`seminar5`** — refactoring. Each topic lives in **three mirrored** packages — `examples/`,
  `practice/`, `solutions/` (topics: `duplication`, `naming`, `parameters`, `nesting`, `trycatch`):
  - `examples/<topic>/` — fully-implemented **V1** (bad code) / **V2** (fixed) pairs with `demo()`
    and Russian comments, orchestrated by `RefactoringDemo`.
  - `practice/<topic>/` — student exercise: `<X>V1` (the wrong code to study) plus a `<X>V2`
    **blank** with the same public API whose bodies throw `UnsupportedOperationException("TODO ...")`.
  - `solutions/<topic>/` — reference implementations of the `<X>V2` classes.
  - Naming convention across the seminar: **wrong code = `V1`, fixed code = `V2`**. Every topic has
    a **characterization test** (in `src/test/.../seminar5/...`) that locks in behavior against the
    V1 class; to verify a fix, re-point the test at the V2 class — behavior must be unchanged.
    This V1/V2 + characterization-test template is the intended pattern for future seminars.

The two modules are coupled by design: `seminars-examples`' `seminar4.clients` and
`seminar4.files` demonstrate calling the `server` module's API and its file I/O, so the `server`
should be running when exercising those examples.

## Building and Running

There is **no Maven wrapper** (`mvnw`) — the `.mvn` dir is empty. Use the system `mvn`.

- Build all modules: `mvn clean install` (or `mvn clean package`)
- Build/compile a single module: `mvn -pl <module> clean compile`
- Run the task server: the `server` module does **not** configure the
  `spring-boot-maven-plugin`, so `mvn spring-boot:run` will not work. Start the service by running
  `ru.tbank.ServerApplication.main(...)` from an IDE, or add the plugin if a CLI run is needed.
  The server listens on `http://localhost:8080`.
- Run a seminar example: execute the relevant `main` (e.g. `GoFDemo`, `SolidDemo`,
  `DemoApplication`) — compile first with `mvn -pl seminars-examples clean compile`.
- **Environment note (this machine):** `mvn` is **not on `PATH`** and there is no `mvnw`. The working
  Maven binary is IntelliJ IDEA's bundled one at
  `~/Applications/IntelliJ IDEA.app/Contents/plugins/maven-plugin/lib/maven3/bin/mvn`. From a plain
  shell, Maven Central is unreachable (TLS `certificate_unknown`, corporate interception) and some
  default-lifecycle plugin jars aren't cached in `~/.m2`, so `mvn test` / `mvn clean test` **fails**
  here. To verify compilation and run JUnit tests without Maven: compile `src/main` with `javac`, then
  compile `src/test` against the cached `~/.m2/repository` JUnit jars
  (junit-jupiter-api/engine 5.11.4, junit-platform-launcher 1.11.3, etc.) and execute via a small
  JUnit Platform Launcher runner. This is how `seminar5` was verified.

## Development Conventions

- **Build tool:** Maven; parent POM configures `kotlin-maven-plugin` (Kotlin 2.3.20) and
  `maven-compiler-plugin` (Java 25, `release` overrides per module) with Lombok as an annotation
  processor. Lombok is a `provided` dependency at the parent level.
- **Language mix:** `server` is Java; `seminars-examples` is Java 21 with Kotlin tooling wired in
  but no Kotlin sources yet.
- **Dependencies** are declared explicitly in each module's `pom.xml` (versions pinned in
  `<properties>`); no BOM import except `spring-boot-dependencies` in `server`.
- **Lombok** is used throughout for boilerplate (`@Data`, constructors, `@RequiredArgsConstructor`,
  `@Slf4j`).
- **Tests:** `seminars-examples` has JUnit 5 tests (JUnit Jupiter 5.11.4, test scope; `maven-surefire-plugin`
  configured in its `pom.xml`) under `src/test/java`. Seminar teaching tests use the
  **characterization-test** pattern: they assert expected behavior against the "wrong" V1 class so the
  same test can be re-pointed at the fixed version to prove behavior was preserved. The `server` module
  has no tests.
- **Comments/Javadoc:** seminar example files carry explanatory comments and Javadoc (often in
  Russian) as teaching aids; the `server` module code is intentionally minimal and mostly
  comment-free.
- **Error handling:** the server returns structured JSON errors (`ApiError`) for client and
  malformed-JSON errors via a global `@RestControllerAdvice`; the teaching clients throw runtime
  exceptions on non-2xx responses.