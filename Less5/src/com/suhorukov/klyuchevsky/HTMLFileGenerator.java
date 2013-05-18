package com.suhorukov.klyuchevsky;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

public class HTMLFileGenerator {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Не указана директория");
            return;
        }

        System.out.println(args[0]);
        HTMLFileGenerator htmlGen = new HTMLFileGenerator();
        File folder = new File(args[0]);

        try (Writer out = new OutputStreamWriter(new FileOutputStream("index.html"))) {
            htmlGen.generateHTML(folder, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generateHTML(File folder, Writer out) throws IOException {
        if (folder.exists()) {
            if (folder.isFile()) {
                folder = new File(folder.getParent());
            }
        } else {
            System.out.println("Неверно указана директория:");
            return;
        }

        System.out.println(folder);

        File[] files = folder.listFiles();

        if (files != null) {
            Arrays.sort(files, new Comparator<File>() {
                public int compare(File f1, File f2) {
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
        }

        out.write("<!DOCTYPE html>\n");
        out.write("<head>\n");
        out.write("<meta charset=\"utf-8\">\n");
        out.write("</head>\n\n");
        out.write("<body>");
        out.write("<h1>Содержание " + folder + "</h1>\n");
        out.write("<hr>\n");
        out.write("<table cellpadding=\"1\"> \n");
        out.write("<tr><td><h3>Имя</h3></td><td><h3>Размер</h3></td><td><h3>Последнее изменение</h3></td></tr>\n");
        out.write("<tr><td>" + "<a href=" + '"' + folder.getParent() + '"' + ">...</a></td><td></td><td></td></tr>\n");
        if (files != null) {
            for (File file : files) {
                out.write("<tr><td><a href=" + '"' + file.getName() + '"' + ">" + file.getName() + "</a></td>");
                out.write("<td>");
                if (file.isFile()) {
                    out.write(file.length() + " Byte");
                }
                out.write("</td>");
                out.write("<td>" + new java.text.SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(file.lastModified()) + "</td></tr>\n");
            }
        }
        out.write("</table>\n");
        out.write("</body>");
    }
}
