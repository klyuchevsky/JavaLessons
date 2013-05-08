package com.suhorukov.klyuchevsky.commands;

import com.suhorukov.klyuchevsky.Command;

import java.util.Map;
import java.util.Stack;

public class Print implements Command {
    @In(getArg = Arg.STACK)
    private Stack<Double> stack;
    @In(getArg = Arg.VARIABLES)
    private Map<String, Double> variables;

    public void execute(String string) {
        System.out.println("print");
        System.out.println(stack.peek());
    }
}
