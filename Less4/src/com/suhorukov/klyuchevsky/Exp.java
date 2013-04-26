package com.suhorukov.klyuchevsky;

import java.util.Map;
import java.util.Stack;

public class Exp implements Command {
    public void execute(Stack<Double> stack, String string, Map<String, Double> variables) {
        double a;
        int empty = 0;
        System.out.println("exp");
        if (stack.size() == empty) {
            System.out.println("Стек пуст, невозможно выполнить операцию");
            return;
        }
        a = stack.pop();
        stack.push(StrictMath.exp(a));
    }
}





