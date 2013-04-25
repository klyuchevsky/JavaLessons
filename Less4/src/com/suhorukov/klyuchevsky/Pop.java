package com.suhorukov.klyuchevsky;

import java.util.Map;
import java.util.Stack;

public class Pop implements Command {
    public void execute(Stack<Double> stack, String string, Map<String, Double> map) {
        System.out.println("pop");
        if (!stack.empty()) {
            stack.pop();
        } else System.out.println("Стек пуст, невозможно извлечь элемент");
    }
}
