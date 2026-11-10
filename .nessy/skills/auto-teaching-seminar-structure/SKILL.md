---
name: teaching-seminar-structure
description: Organize teaching seminar examples with Good/Bad comparison pairs, self-contained demo methods, and seminar-numbered package structure
source: auto-skill
extracted_at: '2026-10-21T08:25:18.986Z'
---

# Teaching Seminar Example Structure

Use this approach when creating educational code examples, lecture demos, or training materials.

## Directory structure

```
seminars-examples/src/main/java/ru/tbank/
├── seminar1/
│   ├── gof/                          # All 23 GoF patterns
│   │   ├── GoFDemo.java              # Orchestrator: runs all demos
│   │   ├── creational/               # Singleton, FactoryMethod, AbstractFactory, Builder, Prototype
│   │   ├── structural/               # Adapter, Decorator, Facade, Proxy, Composite, Bridge, Flyweight
│   │   └── behavioral/               # Strategy, State, TemplateMethod, Observer, Command, Iterator, Mediator, Memento, ChainOfResponsibility, Interpreter, Visitor
│   ├── solid/                        # SOLID + KISS + DRY principles
│   │   ├── SolidDemo.java            # Orchestrator: runs all principle demos
│   │   ├── srp/                      # Single Responsibility
│   │   ├── ocp/                      # Open/Closed
│   │   ├── lsp/                      # Liskov Substitution
│   │   ├── isp/                      # Interface Segregation
│   │   ├── dip/                      # Dependency Inversion
│   │   ├── kiss/                     # Keep It Simple, Stupid
│   │   └── dry/                      # Don't Repeat Yourself
│   ├── generics/                     # Generics, type parameters
│   ├── lambda/                       # Lambda expressions
│   ├── optional/                     # Optional usage
│   └── interfaces/                   # Functional interfaces, SAM types
├── seminar2/                         # Next seminar topic
├── seminar3/                         # Collections, benchmarks
└── seminar4/                         # I/O, HTTP clients
```

## Pattern: Good/Bad comparison pairs

For each principle or anti-pattern, create two files:

| File | Purpose |
|---|---|
| `BadXxx.java` | Demonstrates the anti-pattern with a `static void demo()` method |
| `GoodXxx.java` | Shows the corrected version, ideally with a `static void demo()` method |

The orchestrator (`SolidDemo.java`) prints a section header, then calls `BadXxx.demo()` followed by `GoodXxx.demo()` so the contrast is visible in console output.

## Pattern: Self-contained demo files

Each GoF pattern (and each SOLID principle) lives in a **single file**:
- The class name matches the pattern name (`Singleton.java`, `Decorator.java`, etc.)
- Contains a `static void demo()` method that exercises the pattern and prints results
- No external dependencies beyond standard Java
- Javadoc/comments in Russian (or the target language of the audience)

## Orchestrator convention

Create a `*Demo.java` class with:
- A `main(String[] args)` entry point
- Grouped static methods by category (e.g., `creational()`, `structural()`, `behavioral()`)
- Each method prints a section header (`### Порождающие ###`) then calls `.demo()` on every member
- No command-line arguments needed — just run and observe

## When to apply

- Preparing lecture material on design patterns or SOLID
- Building training exercises where participants see "before vs after"
- Creating a demo repository that runs standalone without external services
- Any time you need self-contained, runnable teaching code
