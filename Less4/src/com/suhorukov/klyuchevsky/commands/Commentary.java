package com.suhorukov.klyuchevsky.commands;

import com.suhorukov.klyuchevsky.Command;

import java.util.Map;
import java.util.Stack;

public class Commentary implements Command {
    public void execute(String string) {
        System.out.println(string);
    }
}
