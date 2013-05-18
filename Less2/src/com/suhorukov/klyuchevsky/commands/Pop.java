package com.suhorukov.klyuchevsky.commands;

import com.suhorukov.klyuchevsky.Command;

import java.util.Map;
import java.util.Stack;

public class Pop implements Command {
    public void execute(Stack<Double> stack, String string, Map<String, Double> map) {
        System.out.println("pop");
        stack.pop();
    }

    public int getEnoughStackDepth() {
        return 1;
    }
}
