package com.suhorukov.klyuchevsky;

import java.util.Random;
import java.util.Scanner;

public class Exc2 {
    public static void main(String[] args) {
        Random random = new Random();
        int x = random.nextInt(100) + 1; // unknown number
        int inp; // entered number by user
        int i = 0; // count of tries
        Scanner sc = new Scanner(System.in);
        while (true) {

            try {
                inp = sc.nextInt();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Ошибка ввода, попробуйте еще раз:");
                sc = new Scanner(System.in);
                continue;
            }

            i++;
            if (i == 8) {
                System.out.println("Неудача. Загаданное число = " + x);
                break;
            }

            if (inp == x) {
                System.out.println("Успех: " + x);
                break;
            } else if (inp < x) {
                System.out.println("Меньше");
            } else System.out.println("Больше");
        }
    }
}
