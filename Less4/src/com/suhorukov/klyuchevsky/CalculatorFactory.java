package com.suhorukov.klyuchevsky;

import java.io.*;
import java.util.*;

public class CalculatorFactory {
    static Properties prop = new Properties();

    static {
        try {
            File propFile = new File("Less4\\commands.properties");
            FileInputStream in = new FileInputStream(propFile);
            prop.load(in);
            in.close();
        } catch (IOException e) {
            System.out.println("Ошибка чтения");
        }
    }

    public static void main(String[] args) {
        Stack<Double> stack = new Stack<>();              // stack to store values
        Map<String, Command> commands = new HashMap<>();  // hashmap to store commands
        String string;                                    // string to store current command
        Map<String, Double> variables = new HashMap<>();  // hashmap to store variables

        //Reading commands set from properties file
        for (Map.Entry<Object, Object> entry : prop.entrySet()) {
            try {
                String name = entry.getValue().toString();
                String className = entry.getKey().toString();
                Class classFile = Class.forName(name);
                Command cmd = (Command) classFile.newInstance();
                commands.put(className, cmd);
            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                System.out.println("Не удалось создать класс");
                e.printStackTrace();
            }
        }

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
                x.execute(stack, string, variables);
            } else System.out.println("Неизвестная команда: " + cmdName);

            System.out.println(stack.toString());
            System.out.println(variables);
        }
    }
}
