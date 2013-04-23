package com.suhorukov.klyuchevsky;

import java.util.Map;
import java.util.Stack;

public class Define implements Command {
    public void execute(Stack<Double> stack, String string, Map<String, Double> variables) {
        String words[] = string.split(" ");
        try {
            if (words.length < 3) {
                System.out.println("Введено недостаточно параметров");
                return;
            }

            if (Character.isDigit(words[1].charAt(0))) {
                System.out.println("Имя переменной не может начинаться с цифры");
                return;
            }
            variables.put(words[1], Double.parseDouble(words[2]));
            System.out.println("define " + words[1] + " " + words[2]);
        } catch (NumberFormatException e) {
            System.out.println("Неверное значение переменной: " + words[2]);
        }
    }
}
