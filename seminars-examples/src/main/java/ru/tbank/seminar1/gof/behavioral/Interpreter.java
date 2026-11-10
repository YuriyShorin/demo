package ru.tbank.seminar1.gof.behavioral;

/**
 * Поведенческий паттерн Interpreter — определяет грамматику и интерпретатор для
 * простого языка. Здесь — вычисление булевых выражений вида "true AND (NOT false)".
 */
public class Interpreter {

    /** Абстрактное выражение. */
    public interface Expression {
        boolean interpret();
    }

    public static class TerminalExpression implements Expression {
        private final boolean value;

        public TerminalExpression(boolean value) {
            this.value = value;
        }

        public boolean interpret() {
            return value;
        }
    }

    public static class AndExpression implements Expression {
        private final Expression left;
        private final Expression right;

        public AndExpression(Expression left, Expression right) {
            this.left = left;
            this.right = right;
        }

        public boolean interpret() {
            return left.interpret() && right.interpret();
        }
    }

    public static class OrExpression implements Expression {
        private final Expression left;
        private final Expression right;

        public OrExpression(Expression left, Expression right) {
            this.left = left;
            this.right = right;
        }

        public boolean interpret() {
            return left.interpret() || right.interpret();
        }
    }

    public static class NotExpression implements Expression {
        private final Expression expr;

        public NotExpression(Expression expr) {
            this.expr = expr;
        }

        public boolean interpret() {
            return !expr.interpret();
        }
    }

    public static void demo() {
        System.out.println("== Interpreter ==");
        Expression and = new AndExpression(new TerminalExpression(true), new TerminalExpression(false));
        Expression or = new OrExpression(new TerminalExpression(false), new TerminalExpression(true));
        Expression not = new NotExpression(new TerminalExpression(false));
        Expression compound = new AndExpression(new TerminalExpression(true),
                new NotExpression(new TerminalExpression(false)));

        System.out.println("true AND false = " + and.interpret());
        System.out.println("false OR true  = " + or.interpret());
        System.out.println("NOT false      = " + not.interpret());
        System.out.println("true AND NOT false = " + compound.interpret());
    }
}