package com.suhorukov.klyuchevsky.commands;

import com.suhorukov.klyuchevsky.Command;

import java.util.Map;
import java.util.Stack;

public class Print implements Command {
    public void execute(Stack<Double> stack, String string, Map<String, Double> variables) {
        System.out.println("print");
        System.out.println(stack.peek());
    }

    public int getEnoughParams() {
        return 0;
    }
}
