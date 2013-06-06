package com.suhorukov.klyuchevsky.commands;

import com.suhorukov.klyuchevsky.Command;

public class Commentary implements Command {
    public void execute(String string) {
        System.out.println(string);
    }

    public int getEnoughParams() {
        return 0;
    }
}