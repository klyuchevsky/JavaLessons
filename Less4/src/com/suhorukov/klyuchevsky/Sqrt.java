package com.suhorukov.klyuchevsky;

import java.util.Map;
import java.util.Stack;

public class Sqrt implements Command {
    public void execute(Stack<Double> stack, String string, Map<String, Double> variables) {
        double a;
        int empty = 0;

        System.out.println("sqrt");

        if (stack.size() == empty) {
            System.out.println("Стек пуст, невозможно выполнить операцию");
            return;
        }

        if (stack.peek() < 0) {
            System.out.println("sqrt" + stack.peek());
            System.out.println("Стек содержит отрицательное число, извлечение корня невозможно");
            return;
        }

        a = stack.pop();
        stack.push(StrictMath.sqrt(a));
    }
}

