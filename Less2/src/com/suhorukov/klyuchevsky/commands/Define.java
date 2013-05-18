package com.suhorukov.klyuchevsky.commands;

import com.suhorukov.klyuchevsky.Command;

import java.util.Map;
import java.util.Stack;

public class Define implements Command {
    public void execute(Stack<Double> stack, String string, Map<String, Double> variables) {
        String words[] = string.split(" ");

        if (words.length < 3) {
            System.out.println("Недостаточно параметров для выполнения операции.");
            System.out.println("Введите имя и значение переменной.");
            return;
        }

        String commandName = words[0];
        String varName = words[1];
        String varValue = words[2];

        System.out.println(commandName + " " + varName + " " + varValue);

        try {
            if (Character.isDigit(varName.charAt(0))) {
                System.out.println("Имя переменной не может начинаться с цифры");
                return;
            }
            variables.put(varName, Double.parseDouble(varValue));
        } catch (NumberFormatException e) {
            System.out.println("Неверное значение переменной: " + varValue);
        }
    }

    public int getEnoughStackDepth() {
        return 0;
    }
}
