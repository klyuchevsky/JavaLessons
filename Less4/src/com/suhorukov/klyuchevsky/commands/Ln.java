package com.suhorukov.klyuchevsky.commands;

import com.suhorukov.klyuchevsky.Command;

import java.util.Map;
import java.util.Stack;

public class Ln implements Command {
    public void execute(Stack<Double> stack, String string, Map<String, Double> variables) {
        double a;
        int empty = 0;
        System.out.println("ln");
        if (stack.size() == empty) {
            System.out.println("Стек пуст, невозможно выполнить операцию");
            return;
        }
        a = stack.pop();
        stack.push(StrictMath.log(a));
    }
}
