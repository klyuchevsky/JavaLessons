package com.suhorukov.klyuchevsky;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

public class HTMLFileGenerator {
    public static void main(String[] args) {
        File folder = null;
        if (args.length < 1) {
            System.out.println("Не указана директория");
            return;
        }

        System.out.println(args[0]);

        folder = new File(args[0]);
        if (folder.exists()) {
            if (folder.isFile()) {
                folder = new File(folder.getParent());
            }
        } else {
            System.out.println("Неверно указана директория:");
            System.out.println(args[0]);
            return;
        }

        System.out.println(folder);

        System.out.println("Выводим список файлов:");
        File[] files = folder.listFiles();

        Arrays.sort(files, new Comparator<File>() {
            public int compare(File f1, File f2) {
                int result;
                if (!f1.isDirectory() && !f2.isDirectory()) {
                    return f1.compareTo(f2);
                }

                if (f1.isDirectory() && f2.isDirectory()) {
                    return f1.compareTo(f2);
                }

                if (f1.isDirectory() && !f2.isDirectory()) {
                    return -1;
                } else return 1;

            }
        });

        for (File file : files) {
            System.out.println(file);
        }


    }

}
