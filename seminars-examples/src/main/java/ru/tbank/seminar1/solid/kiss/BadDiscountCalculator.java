package ru.tbank.seminar1.solid.kiss;

import java.util.ArrayList;
import java.util.List;

/**
 * ПЛОХО: нарушен принцип KISS (Keep It Simple, Stupid) — «проще, глупее».
 *
 * <p>Правило скидки тривиальное: при покупке от 10 единиц даём скидку 10%.
 * Вместо трёх строк {@code if} здесь построена иерархия абстракций:
 * интерфейс {@code Modifier}, два класса-модификатора, список правил,
 * которые применяются в цикле.
 *
 * <p>Чем это вредно:
 * <ul>
 *   <li>для чтения кода нужно держать в голове четыре типа вместо одной проверки;</li>
 *   <li>добавление новой, даже простой, скидки требует нового класса;</li>
 *   <li>порядок применения правил не очевиден и легко сломать незаметно;</li>
 *   <li>лишняя абстракция не упрощает, а скрывает простую логику.</li>
 * </ul>
 */
public class BadDiscountCalculator {

    private interface Modifier {
        double apply(double total, int quantity);
    }

    private static final class BulkModifier implements Modifier {
        @Override
        public double apply(double total, int quantity) {
            return quantity >= 10 ? total * 0.9 : total;
        }
    }

    private static final class NoopModifier implements Modifier {
        @Override
        public double apply(double total, int quantity) {
            return total;
        }
    }

    private final List<Modifier> modifiers = new ArrayList<>();

    public BadDiscountCalculator() {
        modifiers.add(new NoopModifier());
        modifiers.add(new BulkModifier());
    }

    public double calculate(Order order) {
        double total = order.quantity() * order.pricePerUnit();
        for (Modifier modifier : modifiers) {
            total = modifier.apply(total, order.quantity());
        }
        return total;
    }

    public static void demo() {
        var calc = new BadDiscountCalculator();
        System.out.println("5  шт. по 100 -> " + calc.calculate(new Order("pen", 5, 100)));
        System.out.println("12 шт. по 100 -> " + calc.calculate(new Order("pen", 12, 100)));
    }
}