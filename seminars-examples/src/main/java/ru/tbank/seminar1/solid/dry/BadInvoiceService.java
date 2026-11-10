package ru.tbank.seminar1.solid.dry;

/**
 * ПЛОХО: нарушен принцип DRY (Don't Repeat Yourself) — «не повторяйся».
 *
 * <p>Логика «базовая сумма → скидка при крупном заказе → налог 20%» полностью
 * скопирована в два метода: {@code checkoutTotal} и {@code invoiceTotal}.
 *
 * <p>Чем это вредно:
 * <ul>
 *   <li>правка правила (например, новая скидка) должна быть продублирована в двух местах;</li>
 *   <li>легко исправить в одном методе и забыть про другой — методы разъедутся;</li>
 *   <li>копипаста растёт: третий метод-потребитель добавит третью копию.</li>
 * </ul>
 */
public class BadInvoiceService {

    public double checkoutTotal(Order order) {
        double base = order.quantity() * order.pricePerUnit();
        double discounted = base > 100 ? base * 0.9 : base;
        return Math.round(discounted * 1.2 * 100) / 100.0;
    }

    public double invoiceTotal(Order order) {
        double base = order.quantity() * order.pricePerUnit();
        double discounted = base > 100 ? base * 0.9 : base;
        return Math.round(discounted * 1.2 * 100) / 100.0;
    }

    public static void demo() {
        var service = new BadInvoiceService();
        var order = new Order("pen", 12, 100);
        System.out.println("checkoutTotal = " + service.checkoutTotal(order));
        System.out.println("invoiceTotal  = " + service.invoiceTotal(order));
    }
}