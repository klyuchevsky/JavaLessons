package com.suhorukov.klyuchevsky;

import java.util.Map;
import java.util.Stack;

public class Commentary implements Command {
    public void execute(Stack<Double> stack, String string, Map<String, Double> variables) {
        System.out.println(string);
    }
}
