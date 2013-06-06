package com.suhorukov.klyuchevsky;

import java.io.*;
import java.util.*;

public class CalculatorFactory {

    public static void main(String[] args)
            throws IOException {
        String string; // string to store current command
        Factory factory = Factory.createFactory(); // create factory of calculator commands
        System.out.println(factory.commands.keySet());
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

                if ("#".equals(cmdName)) {
                    cmdName = "/#";
                } // we must use "/#" command instead "#" in property file, and here we convert command to class name

                Command command = factory.getCommandByName(cmdName);
                if (factory.commands.containsKey(cmdName)) {
                    int enoughParams = command.getEnoughParams();
                    if (factory.stack.size() >= enoughParams) {
                        command.execute(string);
                    } else {
                        System.out.println("Недостаточно операндов в стеке.");
                        System.out.println("Требуется: >=" + enoughParams);
                        System.out.println("Стек содержит: " + factory.stack.size());
                    }
                } else {
                    System.out.println("Неизвестная команда: " + cmdName);
                }
                System.out.println(factory.stack.toString());
                System.out.println(factory.variables);
            }

        } finally {
            if (sc != null) {
                sc.close();
            }
        }
    }
}
