package com.suhorukov.klyuchevsky;

import java.util.*;

public class Calculator {
    public static void main(String[] args) {
        Stack<Double> stack = new Stack<>();
        Map<String, Command> map = new HashMap<String, Command>();
        String string = "";
        Map<String, Double> variables = new HashMap<String, Double>();

        map.put("PUSH", new Push());
        map.put("POP", new Pop());
        System.out.println(map.keySet());
        System.out.println(map.values());

        while (true) {
            Scanner sc = new Scanner(System.in);
            string = sc.nextLine();
            String[] words = string.split(" ");


            for (String w : words) {
                System.out.println(w);
            }

//            System.out.println(words[0]);
            if (map.containsKey(words[0])) {

                Command x = map.get(words[0]);
                x.execute(stack, string, variables);

            }


//            new Push().execute();


        }


    }


}
