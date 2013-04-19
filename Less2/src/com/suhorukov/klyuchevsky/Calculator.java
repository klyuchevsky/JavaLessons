package com.suhorukov.klyuchevsky;

import java.util.*;

public class Calculator {
    public static void main(String[] args) {
        Stack<Double> stack = new Stack<>();
        Map<String, Object> map = new HashMap<String, Object>();
        String string = "";

        map.put("PUSH", new Push());
        map.put("POP", new Pop());
        System.out.println(map.keySet());
        System.out.println(map.values());

        while (true) {
            Scanner sc = new Scanner(System.in);
            string = sc.nextLine();
            String[] words = string.split(" ");

//            if ("PUSH".equals(words[1])){
//                System.out.println("test");
//            }

            for (String w : words) {
                System.out.println(w);
            }

//            System.out.println(words[0]);
            if (map.containsKey(words[0])) {
//                System.out.println(map.get(words[0]));
                Object x = map.get(words[0]);
                System.out.println(x);
                x.execute();
            }
            ;


//            new Push().execute();


        }


    }


}
