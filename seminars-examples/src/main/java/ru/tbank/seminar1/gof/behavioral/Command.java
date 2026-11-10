package ru.tbank.seminar1.gof.behavioral;

/**
 * Поведенческий паттерн Command — инкапсулирует запрос как объект, что позволяет
 * параметризовать вызывающий код, откладывать выполнение и собирать истории операций.
 */
public class Command {

    /** Получатель, который реально выполняет работу. */
    public static class Light {
        public void on() {
            System.out.println("Light is ON");
        }

        public void off() {
            System.out.println("Light is OFF");
        }
    }

    /** Общий интерфейс команды. */
    public interface CommandI {
        void execute();
    }

    public static class TurnOnCommand implements CommandI {
        private final Light light;

        public TurnOnCommand(Light light) {
            this.light = light;
        }

        public void execute() {
            light.on();
        }
    }

    public static class TurnOffCommand implements CommandI {
        private final Light light;

        public TurnOffCommand(Light light) {
            this.light = light;
        }

        public void execute() {
            light.off();
        }
    }

    /** Вызывающий код (инвокер) не знает, что именно выполняет команда. */
    public static class RemoteControl {
        private CommandI command;

        public void setCommand(CommandI command) {
            this.command = command;
        }

        public void pressButton() {
            command.execute();
        }
    }

    public static void demo() {
        System.out.println("== Command ==");
        Light light = new Light();
        RemoteControl remote = new RemoteControl();

        remote.setCommand(new TurnOnCommand(light));
        remote.pressButton();

        remote.setCommand(new TurnOffCommand(light));
        remote.pressButton();
    }
}