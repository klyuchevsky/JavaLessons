package com.suhorukov.klyuchevsky;
import java.util.Scanner;

public class Exc1 {
    public static void main(String[] args) {
        System.out.println(System.getProperty("file.encoding"));
        label:
        while (true){
            Scanner sc = new Scanner(System.in);
            String s = sc.nextLine();
            if (s.equals("quit")){
                System.out.println("Выход из приложения");
                break label;
            }
            System.out.println(s);
        }

    }

}
