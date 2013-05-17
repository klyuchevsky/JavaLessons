package com.suhorukov.klyuchevsky;

public interface Command {
    //execute command of calculator
    void execute(String string);

    //get enough params in stack to perform command
    int getEnoughParams();
}
