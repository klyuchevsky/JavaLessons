package com.suhorukov.klyuchevsky;

import java.util.Scanner;

public class Ecx2ImplementInterface implements Ecx2Interface {
    public int enterNumber(Scanner sc) {
        return sc.nextInt();
    }

    public void print(String str) {
        System.out.println(str);
    }

    public void clear(Scanner sc) {
        sc.nextLine();
    }
}
