package ru.tbank.seminar1.solid.dry;

/**
 * ХОРОШО: принцип DRY (Don't Repeat Yourself) — единый источник истины.
 *
 * <p>Общая часть расчёта (сумма → скидка → налог) вынесена в один приватный
 * метод {@code finalAmount}, а оба публичных метода лишь делегируют ему.
 * Правило существует в одном месте, поэтому правка применяется сразу ко всем
 * потребителям и расхождения между «корзиной» и «счётом» исключены.
 */
public class GoodInvoiceService {

    private static final double DISCOUNT_THRESHOLD = 100;
    private static final double DISCOUNT_RATE = 0.9;
    private static final double TAX_RATE = 0.2;

    public double checkoutTotal(Order order) {
        return finalAmount(order);
    }

    public double invoiceTotal(Order order) {
        return finalAmount(order);
    }

    private double finalAmount(Order order) {
        double base = order.quantity() * order.pricePerUnit();
        double discounted = base > DISCOUNT_THRESHOLD ? base * DISCOUNT_RATE : base;
        return Math.round(discounted * (1 + TAX_RATE) * 100) / 100.0;
    }

    public static void demo() {
        var service = new GoodInvoiceService();
        var order = new Order("pen", 12, 100);
        System.out.println("checkoutTotal = " + service.checkoutTotal(order));
        System.out.println("invoiceTotal  = " + service.invoiceTotal(order));
    }
}