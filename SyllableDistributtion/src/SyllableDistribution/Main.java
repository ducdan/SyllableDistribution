package SyllableDistribution;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author danhd
 */
public class Main {

    enum Diacritic {

        SAC, HUYEN, HOI, NGA, NANG, NGANG;

        public String getDiacriticValue() {
            switch (this) {
                case SAC:
                    return "á ắ ấ é ế í ó ố ớ ú ứ ý";
                case HUYEN:
                    return "à ằ ầ é ề ì ò ồ ờ ù ừ ỳ";
                case HOI:
                    return "ả ẳ ẩ ẻ ể ỏ ổ ở ủ ử ỷ";
                case NGA:
                    return "ã ẵ ẫ ẽ ễ õ ỗ ỡ ũ ữ ỹ";
                case NANG:
                    return "ạ ặ ậ ẹ ệ ọ ộ ợ ụ ự ỵ";
                default://NGANG
                    return "";
            }
        }

        public static Diacritic getTone(String s) {
            Pattern p;
            for (Diacritic diacritic : Diacritic.values()) {
                if (diacritic != NGANG) {
                    p = Pattern.compile("\\w*[" + diacritic.getDiacriticValue() + "]+");
                } else {
                    p = Pattern.compile("");
                }
                Matcher m = p.matcher(s);
                if (m.find()) {
                    return diacritic;
                }
            }
            return NGANG;
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        String test = readStringFromFile("data/5815000");

        Map<Diacritic, Integer> countDisTones = countDistributedTones(test);

        //print count distributed tones result
        for (Diacritic key : countDisTones.keySet()) {
            System.out.println("Number of words belongs to DIACRITIC " + key.name() + " : " + countDisTones.get(key));
        }

        Map<Integer, Integer> syllableDitribution = countDistributedSyllables(test);

        //print count distributed syllables result
        for (int key : syllableDitribution.keySet()) {
            System.out.println("Number of words has " + key + " syllables: " + syllableDitribution.get(key));
        }
    }

    private static Map<Diacritic, Integer> countDistributedTones(String s) {
        Map<Diacritic, Integer> map = new HashMap<>();
        String[] words = s.replaceAll("_", " ").split(" ");
        for (int i = 0; i < words.length; i++) {
            Diacritic diacritic = Diacritic.getTone(words[i]);
            if (map.containsKey(diacritic)) {
                int value = map.get(diacritic) + 1;
                map.put(diacritic, value);
            } else {
                map.put(diacritic, 1);
            }
        }

        return map;
    }

    private static Map<Integer, Integer> countDistributedSyllables(String s) {
        Map<Integer, Integer> result = new HashMap();
        String[] words = s.split(" ");
        for (int i = 0; i < words.length; i++) {
            int matches = 1;
            Matcher matcher = Pattern.compile("_", Pattern.CASE_INSENSITIVE).matcher(words[i]);
            while (matcher.find()) {
                matches++;
            }
            if (result.containsKey(matches)) {
                int value = result.get(matches) + 1;
                result.put(matches, value);
            } else {
                result.put(matches, 1);
            }
        }
        return result;
    }

    private static String readStringFromFile(String path) throws FileNotFoundException, IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded);
    }
}
