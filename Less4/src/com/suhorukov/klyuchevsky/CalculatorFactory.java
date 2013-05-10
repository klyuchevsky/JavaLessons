package com.suhorukov.klyuchevsky;

import java.io.*;
import java.util.*;

public class CalculatorFactory {

    //    private Stack<Double> stack = new Stack<>();             // stack to store values
//    private Map<String, Double> variables = new HashMap<>(); // hashmap to store variables
    private Factory factory;                                 // factory of calculator commands

    public CalculatorFactory()
            throws IOException {
        factory = new Factory();
    }

    public static void main(String[] args)
            throws IOException {
        String string; // string to store current command
        CalculatorFactory calc = new CalculatorFactory();

        System.out.println(calc.factory.commands.keySet());
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
            Command command = calc.factory.getCommandByName(cmdName);
            if (cmdName != null) {
                command.execute(string);
            } else {
                System.out.println("Неизвестная команда: " + cmdName);
            }

            System.out.println(calc.factory.stack.toString());
            System.out.println(calc.factory.variables);
        }
    }
}
