/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testdata;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author danhd
 */
public class Main {

    public static final String DIACRITIC1 = "á ắ ấ é ế í ó ố ớ ú ứ ý";
    public static final String DIACRITIC2 = "à ằ ầ é ề ì ò ồ ờ ù ừ ỳ";
    public static final String DIACRITIC3 = "ả ẳ ẩ ẻ ể ỏ ổ ở ủ ử ỷ";
    public static final String DIACRITIC4 = "ã ẵ ẫ ẽ ễ õ ỗ ỡ ũ ữ ỹ";
    public static final String DIACRITIC5 = "ạ ặ ậ ẹ ệ ọ ộ ợ ụ ự ỵ";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        String test = readStringFromFile("data/5815000");

        int[] count = countDistributedTones(test);
        System.out.println("Denote:"
                + "\n DIACRITIC1 represents for sắc"
                + "\n DIACRITIC2 represents for huyền"
                + "\n DIACRITIC3 represents for hỏi"
                + "\n DIACRITIC4 represents for ngã"
                + "\n DIACRITIC5 represents for nặng"
                + "\n DIACRITIC6 represents for thanh ngang");
        //print result
        for (int i = 0; i < 6; i++) {
            System.out.println("Number of words belong to DIACRITIC " + i + 1 + " : " + count[i]);
        }

        HashMap<String, Integer> syllableDitribution = countDistributedSyllables(test);
        for (String key : syllableDitribution.keySet()) {
            System.out.println("Number word has " + key + " syllables: " + syllableDitribution.get(key));
        }
    }

    private static int[] countDistributedTones(String s) {
        int[] count = new int[6];
        for (int i = 0; i < 6; i++) {
            count[i] = 0;
        }
        String[] words = s.replaceAll("_", " ").split(" ");
        for (int i = 0; i < words.length; i++) {
            Pattern p = Pattern.compile("\\w*[" + DIACRITIC1 + "]+");
            Matcher m = p.matcher(words[i]);
            if (m.find()) // match
            {
                count[0]++;
                continue;
            }

            p = Pattern.compile("\\w*[" + DIACRITIC2 + "]+");
            m = p.matcher(words[i]);
            if (m.find()) // match
            {
                count[1]++;
                continue;
            }

            p = Pattern.compile("\\w*[" + DIACRITIC3 + "]+");
            m = p.matcher(words[i]);
            if (m.find()) // match
            {
                count[2]++;
                continue;
            }

            p = Pattern.compile("\\w*[" + DIACRITIC4 + "]+");
            m = p.matcher(words[i]);
            if (m.find()) // match
            {
                count[3]++;
                continue;
            }

            p = Pattern.compile("\\w*[" + DIACRITIC5 + "]+");
            m = p.matcher(words[i]);
            if (m.find()) // match
            {
                count[4]++;
                continue;
            }
            count[5]++;
        }

        return count;
    }

    private static HashMap<String, Integer> countDistributedSyllables(String s) {
        HashMap<String, Integer> result = new HashMap();
        String[] words = s.split(" ");
        for (int i = 0; i < words.length; i++) {
            int matches = 1;
            Matcher matcher = Pattern.compile("_", Pattern.CASE_INSENSITIVE).matcher(words[i]);
            while (matcher.find()) {
                matches++;
            }
            if (result.containsKey(String.valueOf(matches))) {
                int value = result.get(String.valueOf(matches)) + 1;
                result.put(String.valueOf(matches), value);
            } else {
                result.put(String.valueOf(matches), 1);
            }
        }
        return result;
    }

    private static String readStringFromFile(String path) throws FileNotFoundException, IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded);
    }
}
