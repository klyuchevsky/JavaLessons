package com.suhorukov.klyuchevsky;

import java.util.Map;
import java.util.Stack;

public class Push implements Command {
    public void execute(Stack<Double> stack, String string, Map<String, Double> variables) {
        int i;
        String[] words = string.split(" ");
        for (i = 1; i < words.length; i++)
            try {
                stack.push(Double.parseDouble(words[i]));
            } catch (NumberFormatException e) {
                System.out.println("Не могу положить в стек значение: " + words[i]);
            }
    }
}
