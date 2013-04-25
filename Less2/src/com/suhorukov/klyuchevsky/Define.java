package com.suhorukov.klyuchevsky;

import java.util.Map;
import java.util.Stack;

public class Define implements Command {
    public void execute(Stack<Double> stack, String string, Map<String, Double> variables) {
        int enoughParam = 3;
        int varFirstChar = 0;
        String words[] = string.split(" ");
        String commandName = words[0];
        String varName = words[1];
        String varValue = words[2];
        System.out.println(commandName + " " + varName + " " + varValue);
        try {
            if (words.length < enoughParam) {
                System.out.println("Введено недостаточно параметров");
                return;
            }

            if (Character.isDigit(varName.charAt(varFirstChar))) {
                System.out.println("Имя переменной не может начинаться с цифры");
                return;
            }
            variables.put(varName, Double.parseDouble(varValue));
        } catch (NumberFormatException e) {
            System.out.println("Неверное значение переменной: " + varValue);
        }
    }
}
