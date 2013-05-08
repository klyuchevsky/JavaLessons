package com.suhorukov.klyuchevsky;

import com.suhorukov.klyuchevsky.commands.Push;
import junit.framework.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import static junit.framework.Assert.assertEquals;

public class CalculatorTest {
    Stack<Double> stack = new Stack();
    Map<String, Command> commands = new HashMap<>(); // hashmap to store commands
    String string; // string to store current command
    Map<String, Double> variables = new HashMap<>(); // hashmap to store variables

    Command push = new Push();

    //    @Test CalculatorTest calculatorTest;
    public void test() {
        push.execute(stack, "push 3", variables);
        //stack.push(3.);
        assertEquals(3.0, stack.peek());

    }

}
