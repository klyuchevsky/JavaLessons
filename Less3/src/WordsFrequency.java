import java.io.*;
import java.util.*;

public class WordsFrequency {
    public static void main(String[] args) throws IOException {
        try (Reader r = new InputStreamReader(new BufferedInputStream(new FileInputStream(args[0])))) {
            final HashMap<String, Integer> frequency = new HashMap<>();
            StringBuilder sb = new StringBuilder();
            int wordCount = 0; // Count of all words

            // todo лучше сразу назвать переменную currentChar
            int i = 0; //current character

            while (i != -1) {
                try {
                    i = r.read();
                    if (Character.isLetterOrDigit((char) i)) {
                        sb.append((char) i);
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
                    // в этом блоке try/cache нет смысла в данной ситуации
                    // снаружи уже есть один блок, которым можно отловить IOException
                    // предлагается внутренний блок убрать, а IOException отловить внешним
                    // это сделает программу менее запутанной
                } catch (IOException e) {
                    System.out.println("Не могу прочитать символ");
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
