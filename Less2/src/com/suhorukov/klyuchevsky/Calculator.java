package com.suhorukov.klyuchevsky;

import java.util.*;

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
        commands.put("#", new Commentary());
        commands.put("print", new Print());
        commands.put("define", new Define());
        commands.put("sqrt", new Sqrt());


        System.out.println(commands.keySet());
        System.out.println(commands.values());
        Scanner sc = new Scanner(System.in);

        while (true) {
            string = sc.nextLine();
            if ("quit".equals(string)) {
                break;
            }

            String[] words = string.split(" ");

            for (String w : words) {
                System.out.println(w);
            }

            if (commands.containsKey(words[0])) {

                Command x = commands.get(words[0]);
                x.execute(stack, string, variables);

            } else System.out.println("Неизвестная команда: " + words[0]);

            System.out.println(stack.toString());
            System.out.println(variables);
        }
    }
}
