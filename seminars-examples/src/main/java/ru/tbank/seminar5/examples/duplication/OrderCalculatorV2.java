package ru.tbank.seminar5.examples.duplication;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * ХОРОШО: дублирование устранено — единый источник истины.
 *
 * <p>Общая часть («сумма позиции») вынесена в приватный метод
 * {@code subtotal}, а округление — в {@code round}. Правила существуют в одном
 * месте, поэтому правка применяется сразу ко всем потребителям, а расхождения
 * между «скидкой» и «налогом» исключены.
 */
public class OrderCalculatorV2 {

    private static final BigDecimal TAX_RATE = new BigDecimal("0.20");
    private static final int SCALE = 2;

    public BigDecimal totalWithDiscount(Order order, BigDecimal discountRate) {
        BigDecimal discounted = subtotal(order).multiply(BigDecimal.ONE.subtract(discountRate));
        return round(discounted);
    }

    public BigDecimal totalWithTax(Order order) {
        BigDecimal taxed = subtotal(order).multiply(BigDecimal.ONE.add(TAX_RATE));
        return round(taxed);
    }

    /** Сумма позиции без каких-либо надбавок. */
    private BigDecimal subtotal(Order order) {
        return order.price().multiply(BigDecimal.valueOf(order.quantity()));
    }

    /** Округление денежной суммы до копеек. */
    private BigDecimal round(BigDecimal amount) {
        return amount.setScale(SCALE, RoundingMode.HALF_UP);
    }

    public static void demo() {
        var calculator = new OrderCalculatorV2();
        var order = new Order(new BigDecimal("10.00"), 3);
        System.out.println("totalWithDiscount(10%) = " + calculator.totalWithDiscount(order, new BigDecimal("0.10")));
        System.out.println("totalWithTax           = " + calculator.totalWithTax(order));
    }
}