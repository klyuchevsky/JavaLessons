package com.suhorukov.klyuchevsky;

import com.suhorukov.klyuchevsky.commands.*;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class Calculator {
    public static void main(String[] args) {
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
        Scanner sc = new Scanner(System.in);

        if (args.length != 0) {
            File file = new File(args[0]);
            try {
                sc = new Scanner(file);
            } catch (FileNotFoundException e) {
                System.out.println("Не удается найти файл");
            }
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
                int x = x.getNumParams();
                if (string.split(" ").length != x) {
                    ///weeewew!!!!!
                }
                x.execute(stack, string, variables);

            } else System.out.println("Неизвестная команда: " + cmdName);

            System.out.println(stack.toString());
            System.out.println(variables);
        }

        finally{
            sc.close();
        }
    }
}
