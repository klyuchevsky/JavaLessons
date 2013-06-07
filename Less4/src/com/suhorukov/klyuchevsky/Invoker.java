package com.suhorukov.klyuchevsky;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Stack;

public class Invoker implements InvocationHandler {
    private Command command;
//    private Stack stack;
//    private Map varMap;

    public Invoker(Command command, Stack stack, Map varMap) {
        this.command = command;
//        this.stack = stack;
//        this.varMap = varMap;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//        logger.debug("Stack before " + stack);
//        logger.debug("Context " + varMap);
//        logger.debug("Arguments " + Arrays.toString((String[]) args[0]));


        if (method.getName().equals("getEnoughParams")) {
            return command.getEnoughParams();
        }

        if (method.getName().equals("execute")) {
            command.execute((String) args[0]);
        }

//        System.out.println("in debug mode");
//        logger.debug("Stack after " + stack);
//        logger.debug("-----------------------------------------------------------------");
        return null;
    }
}

