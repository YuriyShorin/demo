package ru.tbank.seminar5.examples.duplication;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * ПЛОХО: дублирование кода.
 *
 * <p>В обоих методах повторяется одна и та же последовательность: «цена ×
 * количество → применить множитель → округлить до копеек». Правило подсчёта
 * суммы и правило округления живут в двух местах сразу.
 *
 * <p>Чем вредно:
 * <ul>
 *   <li>изменится правило подсчёта — правку придётся вносить в двух местах;</li>
 *   <li>легко поправить в одном методе и забыть про другой, и методы разъедутся;</li>
 *   <li>третий метод-потребитель добавит третью копию — копипаста разрастается.</li>
 * </ul>
 */
public class OrderCalculatorV1 {

    private static final BigDecimal TAX_RATE = new BigDecimal("0.20");

    /** Итог со скидкой: сумма позиции с учётом скидки, округлённая до копеек. */
    public BigDecimal totalWithDiscount(Order order, BigDecimal discountRate) {
        BigDecimal base = order.price().multiply(BigDecimal.valueOf(order.quantity()));
        BigDecimal discounted = base.multiply(BigDecimal.ONE.subtract(discountRate));
        return discounted.setScale(2, RoundingMode.HALF_UP);
    }

    /** Итог с налогом: сумма позиции с налогом, округлённая до копеек. */
    public BigDecimal totalWithTax(Order order) {
        BigDecimal base = order.price().multiply(BigDecimal.valueOf(order.quantity()));
        BigDecimal taxed = base.multiply(BigDecimal.ONE.add(TAX_RATE));
        return taxed.setScale(2, RoundingMode.HALF_UP);
    }

    public static void demo() {
        var calculator = new OrderCalculatorV1();
        var order = new Order(new BigDecimal("10.00"), 3);
        System.out.println("totalWithDiscount(10%) = " + calculator.totalWithDiscount(order, new BigDecimal("0.10")));
        System.out.println("totalWithTax           = " + calculator.totalWithTax(order));
    }
}