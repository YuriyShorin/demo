package ru.tbank.seminar3;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.Stack;

public class DequeDemo {
    public static void main(String[] args) {
        // Как стек (LIFO)
        Deque<String> stack = new ArrayDeque<>();
        stack.push("первый");
        stack.push("второй");
        System.out.println(stack.pop()); // второй

        // Как очередь (FIFO)
        Queue<String> queue = new ArrayDeque<>();
        queue.offer("заявка-1");
        queue.offer("заявка-2");
        System.out.println(queue.poll()); // заявка-1
    }
}
