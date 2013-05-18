package com.suhorukov.klyuchevsky.commands;

import com.suhorukov.klyuchevsky.Command;

import java.util.Map;
import java.util.Stack;

public class Multiply implements Command {
    public void execute(Stack<Double> stack, String string, Map<String, Double> variables) {
        double a;
        double b;
        System.out.println("*");
        a = stack.pop();
        b = stack.pop();
        stack.push(a * b);
    }

    public int getEnoughStackDepth() {
        return 2;
    }
}