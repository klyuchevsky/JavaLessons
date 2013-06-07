package com.suhorukov.klyuchevsky;

import java.io.*;
import java.util.*;

public class CalcModified {
    static Boolean isDebug = false; // is in debug mode

    public static void main(String[] args)
            throws IOException {
        String string; // string to store current command

        for (String arg : args) {
            if (arg.equals("debug")) {
                isDebug = true; // set debug mode
            }
        }

        Factory factory = Factory.createFactory(); // create factory of calculator commands

        System.out.println(factory.getAvailableCommands());
        Scanner sc = null;

        try {
            if (isDebug) {
                if (args.length >= 2) {
                    File file = new File(args[0]);
                    sc = new Scanner(file);
                } else sc = new Scanner(System.in);
            }

            if (!isDebug) {
                if (args.length != 0) {
                    File file = new File(args[0]);
                    sc = new Scanner(file);
                } else sc = new Scanner(System.in);
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

                if (factory.existCommand(cmdName)) {
                    Command command = factory.getCommandByName(cmdName);
                    int enoughParams = command.getEnoughParams();
                    if (factory.getStack().size() >= enoughParams) {
                        command.execute(string);
                    } else {
                        System.out.println("Недостаточно операндов в стеке.");
                        System.out.println("Требуется: >=" + enoughParams);
                        System.out.println("Стек содержит: " + factory.getStack().size());
                    }
                } else {
                    System.out.println("Неизвестная команда: " + cmdName);
                }
                System.out.println(factory.getStack());
                System.out.println(factory.getVariables());
            }

        } finally {
            if (sc != null) {
                sc.close();
            }
        }
    }

}
