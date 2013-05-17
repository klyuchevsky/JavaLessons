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
        String[] words = string.split(" ");
        for (String str : words) {
            System.out.print(str + " ");
        }
        System.out.print("\n");

        if (words.length < 2) {
            System.out.println("Недостаточно параметров для выполнения операции.");
            System.out.println("Введите значение или имя переменной.");
            return;
        }

        for (i = 1; i < words.length; i++)
            try {
                if (variables.containsKey(words[i])) {
                    words[i] = variables.get(words[i]).toString();
                }
                stack.push(Double.parseDouble(words[i]));
            } catch (NumberFormatException e) {
                System.out.println("Не могу положить в стек значение: " + words[i]);
            }
    }

    public int getEnoughParams() {
        return 0;
    }
}
