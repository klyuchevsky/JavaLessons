package com.suhorukov.klyuchevsky.test;

import com.suhorukov.klyuchevsky.Command;
import com.suhorukov.klyuchevsky.commands.*;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class CalculatorTest {

    Double a = 1d;
    Double b = -8d;
    Double c = 12d;

    Stack<Double> stack = new Stack<>();
    Map<String, Double> variables = new HashMap<>();

    Command push = new Push();
    Command add = new Add();
    Command diminution = new Diminution();
    Command multiply = new Multiply();
    Command divide = new Divide();
    Command define = new Define();
    Command sqrt = new Sqrt();

    @Test
    public void quadraticEquation1() {
        define.execute(stack, "define a " + a, variables);
        define.execute(stack, "define b " + b, variables);
        define.execute(stack, "define c " + c, variables);
        assert (variables.get("a").equals(a));
        assert (variables.get("b").equals(b));
        assert (variables.get("c").equals(c));

        push.execute(stack, "push 2 a", variables);
        multiply.execute(stack, "*", variables);
        assert (stack.peek() == 2 * a);
        System.out.println(stack.peek());

        push.execute(stack, "push 4 a c", variables);
        multiply.execute(stack, "*", variables);
        multiply.execute(stack, "*", variables);
        assert (stack.peek() == 4 * a * c);
        System.out.println(stack.peek());

        push.execute(stack, "push b b ", variables);
        multiply.execute(stack, "*", variables);
        diminution.execute(stack, "-", variables);
        assert (stack.peek() == b * b - 4 * a * c);
        System.out.println(stack.peek());

        sqrt.execute(stack, "sqrt", variables);
        assert (stack.peek() == Math.sqrt(b * b - 4 * a * c));
        System.out.println(stack.peek());

        push.execute(stack, "push b 0", variables);
        diminution.execute(stack, "-", variables);
        assert (stack.peek() == -b);
        System.out.println(stack.peek());

        diminution.execute(stack, "-", variables);
        assert (stack.peek() == -b - Math.sqrt(b * b - 4 * a * c));
        System.out.println(stack.peek());

        divide.execute(stack, "/", variables);
        assert (stack.peek() == (-b - Math.sqrt(b * b - 4 * a * c)) / 2 * a);
        System.out.println("First answer: " + stack.peek());
    }

    @Test
    public void quadraticEquation2() {
        define.execute(stack, "define a " + a, variables);
        define.execute(stack, "define b " + b, variables);
        define.execute(stack, "define c " + c, variables);
        assert (variables.get("a").equals(a));
        assert (variables.get("b").equals(b));
        assert (variables.get("c").equals(c));

        push.execute(stack, "push 2 a", variables);
        multiply.execute(stack, "*", variables);
        assert (stack.peek() == 2 * a);
        System.out.println(stack.peek());

        push.execute(stack, "push 4 a c", variables);
        multiply.execute(stack, "*", variables);
        multiply.execute(stack, "*", variables);
        assert (stack.peek() == 4 * a * c);
        System.out.println(stack.peek());

        push.execute(stack, "push b b ", variables);
        multiply.execute(stack, "*", variables);
        diminution.execute(stack, "-", variables);
        assert (stack.peek() == b * b - 4 * a * c);
        System.out.println(stack.peek());

        sqrt.execute(stack, "sqrt", variables);
        assert (stack.peek() == Math.sqrt(b * b - 4 * a * c));
        System.out.println(stack.peek());

        push.execute(stack, "push b 0", variables);
        diminution.execute(stack, "-", variables);
        assert (stack.peek() == -b);
        System.out.println(stack.peek());

        add.execute(stack, "+", variables);
        assert (stack.peek() == -b + Math.sqrt(b * b - 4 * a * c));
        System.out.println(stack.peek());

        divide.execute(stack, "/", variables);
        assert (stack.peek() == (-b + Math.sqrt(b * b - 4 * a * c)) / 2 * a);
        System.out.println("Second answer: " + stack.peek());
    }
}
