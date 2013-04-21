package com.suhorukov.klyuchevsky;

import java.util.Random;
import java.util.Scanner;

public class Exc2 {

    public static void main(String[] args) {
        int x; // unknown number
        int inp; // entered number by user
        int tries = 0; // count of tries

        Random random = new Random();
        x = random.nextInt(100) + 1;
        Scanner sc = new Scanner(System.in);
        // create instance of class to manage scanner and output messages
        Ecx2ImplementInterface manage = new Ecx2ImplementInterface();

        while (true) {

            try {
                inp = manage.enterNumber(sc);
            } catch (java.util.InputMismatchException e) {
                manage.print("Ошибка ввода, попробуйте еще раз:");
                manage.clear(sc);
                continue;
            }

            tries++;

            if (tries == 8) {
                manage.print("Неудача. Загаданное число = " + x);
                break;
            }

            if (inp == x) {
                manage.print("Успех: " + x);
                break;
            } else if (inp < x) {
                manage.print("Меньше");
            } else manage.print("Больше");
        }
    }
}
