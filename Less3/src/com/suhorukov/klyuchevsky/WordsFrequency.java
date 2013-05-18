package com.suhorukov.klyuchevsky;

import java.io.*;
import java.util.*;

public class WordsFrequency {
    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.out.println("Введите имя файла для подсчета частоты слов");
            return;
        }
        try (Reader r = new InputStreamReader(new BufferedInputStream(new FileInputStream(args[0])))) {
            final HashMap<String, Integer> frequency = new HashMap<>();
            StringBuilder sb = new StringBuilder();
            int wordCount = 0; // Count of all words
            int currentChar = 0; //current character

            while (currentChar != -1) {
                currentChar = r.read();
                if (Character.isLetterOrDigit((char) currentChar)) {
                    sb.append((char) currentChar);
                } else {
                    String word = sb.toString(); // word from buffer
                    sb.setLength(0);
                    int val = 1; // first word in hash map
                    if (frequency.containsKey(word) && word.length() > 0) {
                        val += frequency.get(word);
                    }
                    if (word.length() > 0) {
                        frequency.put(word, val);
                    }
                }
            }

            System.out.println(frequency);
            List<String> list = new ArrayList<>(frequency.keySet());
            Collections.sort(list, new Comparator<String>() {
                public int compare(String o1, String o2) {
                    int result;
                    Integer f1 = frequency.get(o1);
                    Integer f2 = frequency.get(o2);
                    result = f2.compareTo(f1);
                    if (result == 0) {
                        result = o1.compareTo(o2);
                    }
                    return result;
                }
            });

            for (int z : frequency.values()) {
                wordCount += z;
            }

            try (Writer out = new OutputStreamWriter(new FileOutputStream("output.csv"))) {
                for (String str : list) {
                    out.write(str + ";" + frequency.get(str) + ";" + (double) frequency.get(str) / wordCount * 100 + "%;\n");
                }
            } catch (IOException e) {
                System.out.println("Не могу сохранить файл");
            }

        } catch (FileNotFoundException e) {
            System.out.println("Не найден файл");
        }

    }
}
