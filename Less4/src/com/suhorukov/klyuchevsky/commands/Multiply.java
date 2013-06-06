package com.suhorukov.klyuchevsky.commands;

import com.suhorukov.klyuchevsky.Arg;
import com.suhorukov.klyuchevsky.Command;
import com.suhorukov.klyuchevsky.In;

import java.util.Stack;

public class Multiply implements Command {
    @In(getArg = Arg.STACK)
    private Stack<Double> stack;

    public void execute(String string) {
        double a;
        double b;
        System.out.println("*");
        a = stack.pop();
        b = stack.pop();
        stack.push(a * b);
    }

    public int getEnoughParams() {
        return 2;
    }
}
