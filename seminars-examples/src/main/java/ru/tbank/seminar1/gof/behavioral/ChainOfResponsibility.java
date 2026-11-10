package ru.tbank.seminar1.gof.behavioral;

/**
 * Поведенческий паттерн Chain of Responsibility — передаёт запрос по цепочке
 * обработчиков, пока один из них не сможет его обработать. Пример — уровни логгирования:
 * DEBUG -> INFO -> ERROR.
 */
public class ChainOfResponsibility {

    /** Базовый обработчик. */
    public static abstract class Logger {
        public static final int DEBUG = 1;
        public static final int INFO = 2;
        public static final int ERROR = 3;

        private final int level;
        private Logger next;

        public Logger(int level) {
            this.level = level;
        }

        public Logger setNext(Logger next) {
            this.next = next;
            return next;
        }

        public void log(int level, String message) {
            if (this.level <= level) {
                write(message);
            }
            if (next != null) {
                next.log(level, message); // передаём дальше по цепочке
            }
        }

        protected abstract void write(String message);
    }

    public static class DebugLogger extends Logger {
        public DebugLogger() {
            super(DEBUG);
        }

        protected void write(String message) {
            System.out.println("DEBUG: " + message);
        }
    }

    public static class InfoLogger extends Logger {
        public InfoLogger() {
            super(INFO);
        }

        protected void write(String message) {
            System.out.println("INFO: " + message);
        }
    }

    public static class ErrorLogger extends Logger {
        public ErrorLogger() {
            super(ERROR);
        }

        protected void write(String message) {
            System.out.println("ERROR: " + message);
        }
    }

    public static void demo() {
        System.out.println("== Chain of Responsibility ==");
        Logger chain = new DebugLogger();
        chain.setNext(new InfoLogger()).setNext(new ErrorLogger());

        chain.log(Logger.DEBUG, "debug message");
        System.out.println("---");
        chain.log(Logger.ERROR, "error message");
    }
}