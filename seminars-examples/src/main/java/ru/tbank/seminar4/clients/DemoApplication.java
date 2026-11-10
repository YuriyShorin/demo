package ru.tbank.seminar4.clients;

import ru.tbank.seminar4.clients.client.FeignTaskClient;
import ru.tbank.seminar4.clients.client.JdkTaskClient;
import ru.tbank.seminar4.clients.client.OkHttpTaskClient;
import ru.tbank.seminar4.clients.client.TaskClient;
import ru.tbank.seminar4.clients.dto.CreateTaskRequest;
import ru.tbank.seminar4.clients.dto.PatchTaskRequest;
import ru.tbank.seminar4.clients.dto.TaskDto;
import ru.tbank.seminar4.clients.dto.TaskStatus;
import ru.tbank.seminar4.clients.dto.UpdateTaskRequest;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DemoApplication {

    public static void main(String[] args) {
        String base = args.length == 0 ? "http://localhost:8080" : args[0];
        List<TaskClient> clients = List.of(
                new JdkTaskClient(base),
                new OkHttpTaskClient(base),
                new FeignTaskClient(base));

        // Каждый клиент прогоняет один и тот же «рабочий день» со своим HTTP-стеком.
        for (TaskClient client : clients) {
            runDay(client);
        }
    }

    // --- Имитация небольшого рабочего дня с задачами ---

    private static void runDay(TaskClient client) {
        header("ДЕМО: " + client.name());

        // 1. Утром планируем три задачи на день.
        TaskDto report = client.create(new CreateTaskRequest("Сдать отчёт", "Дописать раздел аналитики"));
        TaskDto ticket = client.create(new CreateTaskRequest("Завести тикет", "Починить таймаут в клиенте"));
        TaskDto readme = client.create(new CreateTaskRequest("Обновить README", "Добавить пример запуска"));

        // 2. Смотрим доску в начале дня — все задачи ещё в TODO.
        printBoard(client, "Доска в начале дня");

        // 3. Берём отчёт в работу.
        client.patch(report.getId(), new PatchTaskRequest(null, null, TaskStatus.IN_PROGRESS));
        note("Взяли в работу \"" + report.getTitle() + "\"");

        // 4. Показываем только открытые задачи (фильтр по статусу).
        printOpen(client);

        // 5. Закончили отчёт.
        client.patch(report.getId(), new PatchTaskRequest(null, null, TaskStatus.DONE));
        note("Завершили отчёт");

        // 6. Берём тикет и уточняем его название (полная замена — PUT).
        client.replace(ticket.getId(),
                new UpdateTaskRequest("Завести тикет: баг таймаута", "Починить таймаут в клиенте", TaskStatus.IN_PROGRESS));
        note("Уточнили тикет и начали работу");

        // 7. Доска в середине дня: один готов, один в работе, один в очереди.
        printBoard(client, "Доска в середине дня");

        // 8. Завершаем оставшиеся задачи.
        client.patch(ticket.getId(), new PatchTaskRequest(null, null, TaskStatus.DONE));
        client.patch(readme.getId(), new PatchTaskRequest(null, null, TaskStatus.DONE));

        // 9. К концу дня всё сделано.
        printBoard(client, "Доска в конце дня");

        // 10. Убираем за собой все созданные задачи, чтобы демо можно было запускать повторно.
        client.delete(report.getId());
        client.delete(ticket.getId());
        client.delete(readme.getId());
    }

    // --- Утилиты для красивого вывода ---

    private static void header(String s) {
        System.out.println("\n\n################################");
        System.out.println("# " + s);
        System.out.println("############################################");
    }

    private static void note(String s) {
        System.out.println(">> " + s);
    }

    private static void printOpen(TaskClient client) {
        List<TaskDto> open = client.findByStatus(TaskStatus.TODO);
        System.out.println(">> Открытые задачи: " + describe(open));
    }

    /** Печатает все задачи, сгруппированные по статусу, как канбан-доску. */
    private static void printBoard(TaskClient client, String title) {
        List<TaskDto> all = client.findAll();
        System.out.println();
        System.out.println("--- " + title + " ---");
        for (TaskStatus status : TaskStatus.values()) {
            List<TaskDto> tasks = all.stream()
                    .filter(t -> t.getStatus() == status)
                    .sorted(Comparator.comparing(TaskDto::getId))
                    .toList();
            System.out.printf("%-12s %s%n", status, describe(tasks));
        }
    }

    private static String describe(List<TaskDto> tasks) {
        if (tasks == null || tasks.isEmpty()) {
            return "(пусто)";
        }
        return tasks.stream()
                .map(t -> "#" + t.getId() + " " + t.getTitle())
                .collect(Collectors.joining("; "));
    }
}