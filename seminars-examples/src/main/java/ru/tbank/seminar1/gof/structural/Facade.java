package ru.tbank.seminar1.gof.structural;

/**
 * Структурный паттерн Facade — предоставляет простой интерфейс к сложной подсистеме.
 * Клиент общается с фасадом, не зная о внутреннем устройстве множества классов.
 */
public class Facade {

    public static class Cpu {
        public void start() {
            System.out.println("CPU: starting");
        }
    }

    public static class Memory {
        public void load() {
            System.out.println("Memory: loading");
        }
    }

    public static class HardDrive {
        public void read() {
            System.out.println("HardDrive: reading");
        }
    }

    /** Фасад — скрывает сложность подсистемы за одним простым методом. */
    public static class ComputerFacade {
        private final Cpu cpu = new Cpu();
        private final Memory memory = new Memory();
        private final HardDrive hardDrive = new HardDrive();

        public void start() {
            System.out.println("Computer: booting...");
            cpu.start();
            memory.load();
            hardDrive.read();
            System.out.println("Computer: ready");
        }
    }

    public static void demo() {
        System.out.println("== Facade ==");
        ComputerFacade computer = new ComputerFacade();
        computer.start(); // клиенту не нужны Cpu/Memory/HardDrive напрямую
    }
}