package com.suhorukov.klyuchevsky;

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
            try (FileInputStream stream = new FileInputStream(args[0])) {
                sc = new Scanner(stream);
            } catch (FileNotFoundException e) {
                System.out.println("Не удается найти файл");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        while (true) {
            string = sc.nextLine();
            if ("quit".equals(string)) {
                break;
            }

            String[] words = string.split(" ");

            if (commands.containsKey(words[0])) {

                Command x = commands.get(words[0]);
                x.execute(stack, string, variables);

            } else System.out.println("Неизвестная команда: " + words[0]);

            System.out.println(stack.toString());
            System.out.println(variables);
        }
    }
}
