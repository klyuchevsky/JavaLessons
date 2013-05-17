package com.suhorukov.klyuchevsky;

import java.util.Map;
import java.util.Stack;

public interface Command {
    //execute command of calculator
    void execute(Stack<Double> stack, String string, Map<String, Double> variables);

    //get enough params in stack to perform command
    int getEnoughParams();
}
