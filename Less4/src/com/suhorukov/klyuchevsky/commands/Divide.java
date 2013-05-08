package com.suhorukov.klyuchevsky.commands;

import com.suhorukov.klyuchevsky.Command;

import java.util.Map;
import java.util.Stack;

public class Divide implements Command {
    @In(getArg = Arg.STACK)
    private Stack<Double> stack;
    @In(getArg = Arg.VARIABLES)
    private Map<String, Double> variables;

    public void execute(String string) {
        double a;
        double b;
        int empty = 0;
        int oneParam = 1;
        int enoughParam = 2;

        System.out.println("/");

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
            stack.push(a / b);
        }
    }
}