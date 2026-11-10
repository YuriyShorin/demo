---
name: auto-refactoring-seminar
description: Create teaching seminar examples for refactoring — V1/V2 demo pairs in examples/, V1/V2 practice stubs in practice/, and characterization tests that verify behavior is preserved after refactoring
source: auto-skill
extracted_at: '2026-11-02T20:30:00.000Z'
---

# Refactoring Seminar — V1/V2 + Practice + Characterization Tests

Use this when creating a teaching seminar on refactoring techniques. Each topic gets:
- **examples:** V1 (wrong) + V2 (correct) demos with characterization tests
- **practice:** V1 (wrong code only) + V2 (blank skeleton with TODO) with a characterization test that the student swaps from V1 to V2
- **solutions:** reference implementations of the V2 classes (for the instructor)

## Prerequisites

Before creating files, ensure `seminars-examples/pom.xml` has JUnit 5 and surefire:

```xml
<properties>
    <junit.version>5.11.4</junit.version>
    <surefire.version>3.5.2</surefire.version>
</properties>
<dependencies>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter</artifactId>
        <version>${junit.version}</version>
        <scope>test</scope>
    </dependency>
</dependencies>
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-surefire-plugin</artifactId>
            <version>${surefire.version}</version>
        </plugin>
    </build>
```

## Directory structure

```
seminars-examples/src/main/java/ru/tbank/seminar5/
├── RefactoringDemo.java          # Orchestrator: runs all topics (examples only)
├── examples/                     # ✅ fully complete V1/V2 demo pairs
│   ├── duplication/   Order, OrderCalculatorV1, OrderCalculatorV2
│   ├── naming/        User, PaymentProcessorV1, PaymentProcessorV2
│   ├── parameters/    Booking, BookingRequest, BookingServiceV1, BookingServiceV2
│   ├── nesting/       Customer, Order, DiscountEngineV1, DiscountEngineV2
│   └── trycatch/      Report, ReportParseException, ReportParser, ReportWriter,
│                      ReportPublisher, ReportBuilderV1, ReportBuilderV2
├── practice/                     # ✍️ student exercises — V1 (wrong) + V2 (blank TODO)
│   ├── duplication/   ReceiptFormatterV1 + ReceiptFormatterV2
│   ├── naming/        InventoryManagerV1 + InventoryManagerV2
│   ├── parameters/    ConferenceRegistrationV1 + ConferenceRegistrationV2
│   ├── nesting/       RefundServiceV1 + RefundServiceV2
│   └── trycatch/      PaymentImportV1 + PaymentImportV2
└── solutions/                    # 📖 reference implementations of V2 (instructor only)
    ├── duplication/   ReceiptFormatterV2
    ├── naming/        InventoryManagerV2
    ├── parameters/    ConferenceRegistrationV2 + RegistrationRequest
    ├── nesting/       RefundServiceV2
    └── trycatch/      PaymentImportV2
```

Tests mirror the structure:
```
src/test/java/ru/tbank/seminar5/
├── examples/<topic>/   XxxTest.java           # V1↔V2 equivalence + edge cases
├── practice/<topic>/   XxxTest.java           # Tests V1; javadoc instructs swapping to V2
└── solutions/<topic>/  XxxV2Test.java         # Tests the reference V2 implementation
```

## File conventions

### examples — V1 (wrong code) — `XxxV1.java`

- **Purpose:** demonstrate the anti-pattern with a `static void demo()` method
- **Javadoc:** starts with `ПЛОХО:` explaining what's wrong and why it matters
- **Demo:** calls the wrong method, prints results
- **No tests against V1 alone** — V1 is intentionally broken in structure, not behavior (except try/catch where swallowing errors IS the behavior difference)

### examples — V2 (correct code) — `XxxV2.java`

- **Purpose:** show the refactored version
- **Javadoc:** starts with `ХОРОШО:` explaining what was fixed
- **Demo:** same inputs as V1, same outputs — behavior preserved
- **Tested:** characterization tests assert V1 and V2 produce identical results

### practice — V1 (wrong code) — `XxxV1.java`

- **Purpose:** student exercises — only wrong code, no fix
- **Javadoc:** starts with `ПРАКТИКА, V1 (неверный код):` with clear task description; references `XxxV2`
- **No `demo()` method** — student implements the fix themselves

### practice — V2 (blank skeleton) — `XxxV2.java`

- **Purpose:** same public API as V1, but bodies throw `UnsupportedOperationException("TODO: ...")`
- **Javadoc:** starts with `ПРАКТИКА, V2 (заготовка):` with task description; references `XxxV1`
- **Student fills in the TODO bodies** — same signatures as V1, preserving the contract

### Characterization tests — `src/test/java/.../XxxTest.java`

- **For examples:** test both V1 and V2 produce identical results; also test edge cases
- **For practice:** test V1's current (wrong) behavior; javadoc instructs student to swap `XxxV1` → `XxxV2` in the test to verify their refactoring didn't break behavior
- **For try/catch practice:** V1 silently returns 0 on error; V2 should throw — test on V1 passes, test on V2 needs rewriting (noted in javadoc)

## Orchestrator — `RefactoringDemo.java`

Follows the `SolidDemo.java` pattern. Only runs **examples** (not practice):

```java
public class RefactoringDemo {
    public static void main(String[] args) {
        duplication();
        naming();
        parameters();
        nesting();
        tryCatch();
    }

    static void duplication() {
        System.out.println("== Дублирование ==");
        System.out.println("--- Плохо (V1) ---");
        OrderCalculatorV1.demo();
        System.out.println("--- Хорошо (V2) ---");
        OrderCalculatorV2.demo();
    }
    // ... same pattern for each topic
}
```

Each topic prints: section header → "Плохо (V1)" → V1.demo() → "Хорошо (V2)" → V2.demo()

## When to apply

- Creating a seminar on refactoring techniques
- Any topic where you want to show "before → after" with preserved behavior
- When you need practice exercises for students (V1 wrong code + V2 blank skeleton with characterization tests)

## What NOT to do

- Don't change behavior between V1 and V2 in examples — characterization tests catch this
- Don't add `demo()` to practice stubs — students implement the fix themselves
- Don't make examples too complex — keep them close to real developer refactoring scenarios
- Don't forget to update imports when practice V2 references domain classes from examples (e.g. `RefundServiceV2` uses `examples.nesting.Customer`)
