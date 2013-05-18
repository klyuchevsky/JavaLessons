package com.suhorukov.klyuchevsky;

import com.suhorukov.klyuchevsky.commands.*;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class Calculator {
    public static void main(String[] args)
            throws Exception {
        Stack<Double> stack = new Stack<>(); // stack to store values
        Map<String, Command> commands = new HashMap<>(); // hashmap to store commands
        String string; // string to store current command
        Map<String, Double> variables = new HashMap<>(); // hashmap to store variables

        commands.put("push", new Push());
        commands.put("pop", new Pop());
        commands.put("+", new Add());
        commands.put("-", new Diminution());
        commands.put("*", new Multiply());
        commands.put("/", new Divide());
        commands.put("sqrt", new Sqrt());
        commands.put("#", new Commentary());
        commands.put("print", new Print());
        commands.put("define", new Define());

        System.out.println(commands.keySet());
        Scanner sc = null;

        try {
            if (args.length != 0) {
                File file = new File(args[0]);
                sc = new Scanner(file);
            } else {
                sc = new Scanner(System.in);
            }

            while (true) {
                if (sc.hasNextLine()) {
                    string = sc.nextLine();
                } else {
                    sc = new Scanner(System.in);
                    continue;
                }

                string = string.replaceAll("\\s+", " ");
                string = string.trim();

                if ("quit".equals(string)) {
                    System.out.println("Выход из приложения");
                    break;
                }

                String[] words = string.split(" ");
                String cmdName = words[0];

                if (commands.containsKey(cmdName)) {
                    Command x = commands.get(cmdName);
                    int enoughParams = x.getEnoughStackDepth();
                    if (stack.size() >= enoughParams) {
                        x.execute(stack, string, variables);
                    } else {
                        System.out.println("Недостаточно операндов в стеке.");
                        System.out.println("Требуется: >=" + enoughParams);
                        System.out.println("Стек содержит: " + stack.size());
                    }
                } else System.out.println("Неизвестная команда: " + cmdName);

                System.out.println(stack.toString());
                System.out.println(variables);
            }

        } finally {
            if (sc != null) {
                sc.close();
            }
        }
    }
}



