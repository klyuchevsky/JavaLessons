package com.suhorukov.klyuchevsky;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.util.*;

public class Factory {
    private static Factory factory = new Factory();    // class Factory is singleton
    private Stack<Double> stack = new Stack<>();             // stack to store values
    private Map<String, Double> variables = new HashMap<>(); // hashmap to store variables
    private Map<String, Command> commands = new HashMap<>(); // hashmap to store commands

    private Factory() {
        create();
    }

    public void create() {
        Properties prop = new Properties();

        // Reading properties from file
        try (FileInputStream in = new FileInputStream("commands.properties")) {
            prop.load(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (Map.Entry<Object, Object> entry : prop.entrySet()) {
            String name = entry.getValue().toString();
            String className = entry.getKey().toString();
            try {
                Class classFile = Class.forName(name);
                Command cmd = (Command) classFile.newInstance();
                if (CalcModified.isDebug) {
                    Command proxy = (Command) Proxy.newProxyInstance(Command.class.getClassLoader(), new Class[]{Command.class}, new Invoker(cmd, stack, variables));
                    commands.put(className, proxy);
                } else {
                    commands.put(className, cmd);
                }

                // Check for fields annotation
                Field[] fields = classFile.getDeclaredFields();
                if (fields != null) {
                    for (Field field : fields) {
                        if (field.isAnnotationPresent(In.class)) {
                            In annotation = field.getAnnotation(In.class);
                            Arg argType = annotation.getArg();

                            switch (argType) {
                                case STACK:
                                    field.setAccessible(true);
                                    field.set(cmd, stack);
                                    break;
                                case VARIABLES:
                                    field.setAccessible(true);
                                    field.set(cmd, variables);
                                    break;
                                default:
                                    throw new RuntimeException("Ошибка обработки аннотации " + argType);
                            }
                        }
                    }
                }

            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                System.out.println("Не удалось создать класс: " + className);
                e.printStackTrace();
            }
        }
    }

    public static Factory createFactory() {
        return factory;
    }

    public Command getCommandByName(String cmdName) {
        return commands.get(cmdName);
    }

    public Collection<String> getAvailableCommands() {
        return Collections.unmodifiableCollection(commands.keySet());
    }

    public boolean existCommand(String cmdName) {
        return commands.containsKey(cmdName);
    }

    public Stack getStack() {
        return stack;
    }

    public Map getVariables() {
        return variables;
    }
}
