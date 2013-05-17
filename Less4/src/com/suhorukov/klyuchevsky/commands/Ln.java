package com.suhorukov.klyuchevsky.commands;

import com.suhorukov.klyuchevsky.Arg;
import com.suhorukov.klyuchevsky.Command;
import com.suhorukov.klyuchevsky.In;

import java.util.Stack;

public class Ln implements Command {
    @In(getArg = Arg.STACK)
    private Stack<Double> stack;

    public void execute(String string) {
        double a;
        System.out.println("ln");
        a = stack.pop();
        stack.push(StrictMath.log(a));
    }

    public int getEnoughParams() {
        return 1;
    }
}
