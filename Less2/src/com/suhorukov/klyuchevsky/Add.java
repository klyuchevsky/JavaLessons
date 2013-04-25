package com.suhorukov.klyuchevsky;

import java.util.Map;
import java.util.Stack;

public class Add implements Command {
    public void execute(Stack<Double> stack, String string, Map<String, Double> variables) {
        double a;
        double b;

        System.out.println("+");

        if (stack.size() == 0) {
            System.out.println("Стек пуст, невозможно выполнить операцию");
            return;
        }

        if (stack.size() == 1) {
            System.out.println("Стек содержит только один параметр");
            return;
        }

        if (stack.size() >= 2) {
            a = stack.pop();
            b = stack.pop();
            stack.push(a + b);
        }
    }
}
