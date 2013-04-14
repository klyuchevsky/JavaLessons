package com.suhorukov.klyuchevsky;

import java.util.Random;
import java.util.Scanner;

public class Exc2 {
    public static void main(String[] args) {
        Random random = new Random();
        int x = random.nextInt(100) + 1;
        Scanner sc = new Scanner(System.in);
        for (int i=0; i<8; i++){
            int inp = sc.nextInt();
            if (inp==x){
                System.out.println("Успех: " + x);
                break;
            } else if (inp<x){
                System.out.println("Меньше");
            } else System.out.println("Больше");
        }
    }

}
