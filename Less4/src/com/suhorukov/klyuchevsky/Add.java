package com.suhorukov.klyuchevsky;

import java.util.Map;
import java.util.Stack;

public class Add implements Command {
    public void execute(Stack<Double> stack, String string, Map<String, Double> variables) {
        double a;
        double b;
        int empty = 0;
        int oneParam = 1;
        int enoughParam = 2;
        System.out.println("+");

        if (stack.size() == empty) {
            System.out.println("Стек пуст, невозможно выполнить операцию");
            return;
        }

        if (stack.size() == oneParam) {
            System.out.println("Стек содержит только один параметр");
            return;
        }

        if (stack.size() >= enoughParam) {
            a = stack.pop();
            b = stack.pop();
            stack.push(a + b);
        }
    }
}
