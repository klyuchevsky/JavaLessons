package com.suhorukov.klyuchevsky;

import java.util.Map;
import java.util.Stack;

public interface Command {
    void execute(Stack<Double> stack, String string, Map<String, Double> variables);

    int getNumParams();
}€
