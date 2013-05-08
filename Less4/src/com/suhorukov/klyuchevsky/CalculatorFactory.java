package com.suhorukov.klyuchevsky;

import com.suhorukov.klyuchevsky.commands.*;

import java.io.*;
import java.util.*;


public class CalculatorFactory {

    public static void main(String[] args)
            throws IOException {
        Stack<Double> stack = new Stack<>();              // stack to store values
        String string;                                    // string to store current command
        Map<String, Double> variables = new HashMap<>();  // hashmap to store variables

        //Reading commands set from properties file and create their objects
        Factory factory = new Factory();

//        System.out.println(commands.keySet());
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
            Command command = factory.getCommandByName(cmdName);
            if (cmdName != null) {
                command.execute(string);
            } else {
                System.out.println("Неизвестная команда: " + cmdName);
            }

            System.out.println(stack.toString());
            System.out.println(variables);
        }
    }
}
