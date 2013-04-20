import java.io.*;
import java.util.*;

public class WordsFrequency {
    public static void main(String[] args) throws IOException {
        try (Reader r = new InputStreamReader(new BufferedInputStream(new FileInputStream(args[0])))) {
            final HashMap<String, Integer> frequency = new HashMap<>();
            StringBuilder sb = new StringBuilder();

            int i = 0; //current character

            while (i != -1) {
                try {
                    i = r.read();
                    if (Character.isLetterOrDigit((char) i)) {
                        sb.append((char) i);
                    } else {
                        String key = sb.toString(); // word from buffer
                        sb.setLength(0);
                        int val = 1; // first word in hash map
                        if (frequency.containsKey(key) && key.length() > 0) {
                            val += frequency.get(key);
                        }
                        frequency.put(key, val);
                    }

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

            try (Writer out = new OutputStreamWriter(new FileOutputStream("output.csv"))) {
                for (String str : list) {
                    out.write(str + "," + frequency.get(str) + ";");
                }
            }


        } catch (FileNotFoundException e) {
            System.out.println("Не найден файл");
        }

    }
}
