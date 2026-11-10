package ru.tbank.seminar1.gof.behavioral;

/**
 * Поведенческий паттерн Template Method — определяет скелет алгоритма в базовом
 * классе, а отдельные шаги делегирует подклассам. Общая структура фиксирована,
 * детали переопределяются.
 */
public class TemplateMethod {

    /** Базовый класс: шаблонный метод определяет скелет алгоритма. */
    public static abstract class DataMiner {

        // Шаблонный метод: последовательность шагов фиксирована.
        public final void mine(String path) {
            openFile(path);
            String data = extractData();
            analyze(data);
            closeFile();
        }

        protected void openFile(String path) {
            System.out.println("Opening " + path);
        }

        protected abstract String extractData();

        protected void analyze(String data) {
            System.out.println("Analyzing: " + data);
        }

        protected void closeFile() {
            System.out.println("Closing file");
        }
    }

    public static class CsvMiner extends DataMiner {
        protected String extractData() {
            return "csv-data";
        }
    }

    public static class PdfMiner extends DataMiner {
        protected String extractData() {
            return "pdf-data";
        }
    }

    public static void demo() {
        System.out.println("== Template Method ==");
        System.out.println("-- CSV --");
        new CsvMiner().mine("data.csv");
        System.out.println("-- PDF --");
        new PdfMiner().mine("doc.pdf");
    }
}