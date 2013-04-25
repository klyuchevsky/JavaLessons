package com.suhorukov.klyuchevsky;

import java.util.Map;
import java.util.Stack;

public class Push implements Command {
    public void execute(Stack<Double> stack, String string, Map<String, Double> variables) {
        int i;
        String[] words = string.split(" ");
        for (String str : words) {
            System.out.print(str + " ");
        }
        System.out.print("\n");

        if (words.length < 2) {
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
