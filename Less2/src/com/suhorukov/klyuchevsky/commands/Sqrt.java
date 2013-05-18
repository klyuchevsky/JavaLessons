package com.suhorukov.klyuchevsky.commands;

import com.suhorukov.klyuchevsky.Command;

import java.util.Map;
import java.util.Stack;

public class Sqrt implements Command {
    public void execute(Stack<Double> stack, String string, Map<String, Double> variables) {
        double a;
        System.out.println("sqrt");
        if (stack.peek() < 0) {
            System.out.println("sqrt" + stack.peek());
            System.out.println("Стек содержит отрицательное число, извлечение корня невозможно");
            return;
        }
        a = stack.pop();
        stack.push(StrictMath.sqrt(a));
    }

    public int getEnoughStackDepth() {
        return 1;
    }
}

