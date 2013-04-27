package com.suhorukov.klyuchevsky;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Factory {


    private Map<String, Command> commands = new HashMap<>();

    public Factory()
            throws IOException {
        Properties prop = new Properties();
        try (FileInputStream in = new FileInputStream("commands.properties")) {
            prop.load(in);
        }

        for (Map.Entry<Object, Object> entry : prop.entrySet()) {
            String name = entry.getValue().toString();
            String className = entry.getKey().toString();
            try {
                Class classFile = Class.forName(name);
                Command cmd = (Command) classFile.newInstance();
                commands.put(className, cmd);
            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                System.out.println("Не удалось создать класс: " + className);
                e.printStackTrace();
            }
        }
    }

    public Command getCommandByName(String cmdName) {
        return commands.get(cmdName);
    }
}
