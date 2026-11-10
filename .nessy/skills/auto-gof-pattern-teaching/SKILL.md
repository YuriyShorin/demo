---
name: gof-pattern-teaching
description: Implement each GoF design pattern as a self-contained single file with a demo() method, organized by category (creational/structural/behavioral)
source: auto-skill
extracted_at: '2026-10-21T08:25:18.986Z'
---

# GoF Pattern Teaching Structure

Use this approach when implementing the 23 GoF design patterns for teaching, lectures, or reference. Each pattern is a single self-contained file with a `demo()` method.

## Organization

```
gof/
├── GoFDemo.java                    # Orchestrator
├── creational/                     # 5 patterns
│   ├── Singleton.java
│   ├── FactoryMethod.java
│   ├── AbstractFactory.java
│   ├── Builder.java
│   └── Prototype.java
├── structural/                     # 7 patterns
│   ├── Adapter.java
│   ├── Decorator.java
│   ├── Facade.java
│   ├── Proxy.java
│   ├── Composite.java
│   ├── Bridge.java
│   └── Flyweight.java
└── behavioral/                     # 11 patterns
    ├── Strategy.java
    ├── State.java
    ├── TemplateMethod.java
    ├── Observer.java
    ├── Command.java
    ├── IteratorPattern.java
    ├── Mediator.java
    ├── Memento.java
    ├── ChainOfResponsibility.java
    ├── Interpreter.java
    └── Visitor.java
```

## Single-file rule

Each pattern lives in **one file**. No separate interface/implementation splits, no multi-file packages. The file contains:
- All classes needed for the pattern (inner/static nested classes are fine)
- A `static void demo()` method that exercises the pattern
- Javadoc in the target language (Russian for this project)

## Demo method convention

```java
public class Singleton {
    // Pattern implementation...

    public static void demo() {
        // Create instances, exercise the pattern, print results
        // Output should clearly show the pattern's key behavior
    }
}
```

The `demo()` method:
- Takes no arguments, returns nothing
- Prints to `System.out` so it can be captured in console output
- Is self-contained — no external dependencies, no file I/O, no network
- Takes less than a second to run

## Orchestrator

The `GoFDemo` class groups patterns by category and prints section headers:

```java
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
        // ...
    }
    // structural(), behavioral() follow same pattern
}
```

## Pattern coverage

All 23 patterns should be implemented. Group them by category in the orchestrator:

| Category | Count | Patterns |
|---|---|---|
| Creational | 5 | Singleton, FactoryMethod, AbstractFactory, Builder, Prototype |
| Structural | 7 | Adapter, Decorator, Facade, Proxy, Composite, Bridge, Flyweight |
| Behavioral | 11 | Strategy, State, TemplateMethod, Observer, Command, Iterator, Mediator, Memento, ChainOfResponsibility, Interpreter, Visitor |

## When to apply

- Preparing lecture material on design patterns
- Building a reference implementation of all 23 GoF patterns
- Creating a demo repository that runs standalone without external dependencies
- Any time you need runnable, self-contained pattern examples

## When NOT to apply

- Production code — GoF patterns are concepts, not copy-paste implementations
- When a pattern would be over-engineering (prefer simplicity, see KISS principle)
- When the pattern doesn't fit the problem — not every problem needs a pattern
