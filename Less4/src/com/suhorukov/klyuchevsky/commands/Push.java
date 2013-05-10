package com.suhorukov.klyuchevsky.commands;

import com.suhorukov.klyuchevsky.Arg;
import com.suhorukov.klyuchevsky.Command;
import com.suhorukov.klyuchevsky.In;


import java.util.Map;
import java.util.Stack;

public class Push implements Command {
    @In(getArg = Arg.STACK)
    private Stack<Double> stack;

    @In(getArg = Arg.VARIABLES)
    private Map<String, Double> variables;

    public void execute(String string) {

        int i;
        int enoughParam = 2;
        String[] words = string.split(" ");
        for (String str : words) {
            System.out.print(str + " ");
        }
        System.out.print("\n");

        if (words.length < enoughParam) {
            System.out.println("Недостаточно параметров, введите значение для помещения в стек");
            return;
        }

        for (i = 1; i < words.length; i++)
            try {
                if (!Character.isDigit(words[i].charAt(0))) {
                    if (variables.containsKey(words[i])) {
                        words[i] = variables.get(words[i]).toString();
                    }
                }
                stack.push(Double.parseDouble(words[i]));
            } catch (NumberFormatException e) {
                System.out.println("Не могу положить в стек значение: " + words[i]);
            }
    }
}
