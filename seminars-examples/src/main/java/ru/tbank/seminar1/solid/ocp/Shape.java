package ru.tbank.seminar1.solid.ocp;

/** ХОРОШО: фигуры сами предоставляют свою площадь, поэтому калькулятор не меняется. */
public sealed interface Shape permits Rectangle, Circle {

    double area();
}