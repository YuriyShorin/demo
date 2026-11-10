package ru.tbank.seminar1.solid.lsp;

/**
 * ПЛОХО: нарушен принцип подстановки Лисков (LSP).
 *
 * <p>Классическая задача «{@code Rectangle} / {@code Square}». {@link Square} сделан
 * подклассом {@link Rectangle} и переопределяет сеттеры так, чтобы при изменении
 * одной стороны менялась и другая, — чтобы оставаться квадратом.
 *
 * <p>Но вызывающий код, который по праву ожидает поведение {@link Rectangle}, получает
 * сюрприз: установка ширины 5 и высоты 3 на {@code Square} молча даёт фигуру 5x5.
 * Код, написанный под {@link Rectangle}, ломается, когда ему подсовывают {@code Square}, —
 * подкласс НЕ является подставляемым вместо своего базового класса.
 */
public class BadRectangle {

    protected int width;
    protected int height;

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int area() {
        return width * height;
    }

    public static class Square extends BadRectangle {

        @Override
        public void setWidth(int width) {
            this.width = width;
            this.height = width; // чтобы оставаться квадратом
        }

        @Override
        public void setHeight(int height) {
            this.height = height;
            this.width = height; // чтобы оставаться квадратом
        }
    }

    /** Метод, написанный под {@link BadRectangle}; ломается для {@code Square}. */
    public static int computeArea(BadRectangle r) {
        r.setWidth(5);
        r.setHeight(3);
        return r.area();
    }

    public static void demo() {
        int rectArea = computeArea(new BadRectangle());
        int squareArea = computeArea(new Square());
        System.out.println("Rectangle area (expect 15): " + rectArea);
        System.out.println("Square via Rectangle code (should also be 15, but is): " + squareArea);
    }
}