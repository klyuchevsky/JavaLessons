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
