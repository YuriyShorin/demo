package ru.tbank.seminar1.gof;

import ru.tbank.seminar1.gof.behavioral.ChainOfResponsibility;
import ru.tbank.seminar1.gof.behavioral.Command;
import ru.tbank.seminar1.gof.behavioral.Interpreter;
import ru.tbank.seminar1.gof.behavioral.IteratorPattern;
import ru.tbank.seminar1.gof.behavioral.Mediator;
import ru.tbank.seminar1.gof.behavioral.Memento;
import ru.tbank.seminar1.gof.behavioral.Observer;
import ru.tbank.seminar1.gof.behavioral.State;
import ru.tbank.seminar1.gof.behavioral.Strategy;
import ru.tbank.seminar1.gof.behavioral.TemplateMethod;
import ru.tbank.seminar1.gof.behavioral.Visitor;
import ru.tbank.seminar1.gof.creational.AbstractFactory;
import ru.tbank.seminar1.gof.creational.Builder;
import ru.tbank.seminar1.gof.creational.FactoryMethod;
import ru.tbank.seminar1.gof.creational.Prototype;
import ru.tbank.seminar1.gof.creational.Singleton;
import ru.tbank.seminar1.gof.structural.Adapter;
import ru.tbank.seminar1.gof.structural.Bridge;
import ru.tbank.seminar1.gof.structural.Composite;
import ru.tbank.seminar1.gof.structural.Decorator;
import ru.tbank.seminar1.gof.structural.Facade;
import ru.tbank.seminar1.gof.structural.Flyweight;
import ru.tbank.seminar1.gof.structural.Proxy;

/**
 * Запускает примеры всех 23 паттернов GoF: порождающие (5), структурные (7)
 * и поведенческие (11).
 */
public class GoFDemo {

    public static void main(String[] args) {
        creational();
        structural();
        behavioral();
    }

    static void creational() {
        System.out.println("### Порождающие ###");
        Singleton.demo();
        FactoryMethod.demo();
        AbstractFactory.demo();
        Builder.demo();
        Prototype.demo();
    }

    static void structural() {
        System.out.println();
        System.out.println("### Структурные ###");
        Adapter.demo();
        Decorator.demo();
        Facade.demo();
        Proxy.demo();
        Composite.demo();
        Bridge.demo();
        Flyweight.demo();
    }

    static void behavioral() {
        System.out.println();
        System.out.println("### Поведенческие ###");
        Strategy.demo();
        State.demo();
        TemplateMethod.demo();
        Observer.demo();
        Command.demo();
        IteratorPattern.demo();
        Mediator.demo();
        Memento.demo();
        ChainOfResponsibility.demo();
        Interpreter.demo();
        Visitor.demo();
    }
}