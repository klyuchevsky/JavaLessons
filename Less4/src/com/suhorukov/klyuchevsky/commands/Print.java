package com.suhorukov.klyuchevsky.commands;

import com.suhorukov.klyuchevsky.Arg;
import com.suhorukov.klyuchevsky.Command;
import com.suhorukov.klyuchevsky.In;

import java.util.Stack;

public class Print implements Command {
    @In(getArg = Arg.STACK)
    private Stack<Double> stack;

    public void execute(String string) {
        System.out.println("print");
        System.out.println(stack.peek());
    }

    public int getEnoughParams() {
        return 1;
    }
}
