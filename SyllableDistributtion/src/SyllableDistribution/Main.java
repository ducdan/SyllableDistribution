package SyllableDistribution;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

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
        Integer[] da = {1, 2, 3, 4, 5};
        String test = readStringFromFile("data/5815000");

        Map<Diacritic, Integer> countDisTones = countDistributedTones(test);

        //print count distributed tones result
        for (Diacritic key : countDisTones.keySet()) {
            System.out.println("Number of words belongs to DIACRITIC " + key.name() + " : " + countDisTones.get(key));
        }

        Map<Integer, Integer> syllableDitribution = countDistributedSyllables(test, "_");

        //print count distributed syllables result
        for (int key : syllableDitribution.keySet()) {
            System.out.println("Number of words has " + key + " syllables: " + syllableDitribution.get(key));
        }
    }

    private static void putMap(Object key, Map map) {
        if (map.containsKey(key)) {
            int value = (int) map.get(key) + 1;
            map.put(key, value);
        } else {
            map.put(key, 1);
        }
    }

    private static Map<Diacritic, Integer> countDistributedTones(String s) {
        Map<Diacritic, Integer> map = new HashMap<>();
        String[] words = s.replaceAll("_", " ").split(" ");
        Stream.of(words).parallel()
                .map(w -> Diacritic.getTone(w))
                .forEach(t -> putMap(t, map));
        return map;
    }

    private static int countMatch(String s, String regex) {
        int matches = 1;
        Matcher matcher = Pattern.compile(regex, Pattern.CASE_INSENSITIVE).matcher(s);
        while (matcher.find()) {
            matches++;
        }
        return matches;
    }

    private static Map<Integer, Integer> countDistributedSyllables(String s, String regex) {
        Map<Integer, Integer> result = new HashMap();
        String[] words = s.split(" ");
        Stream.of(words).parallel()
                .map(w -> countMatch(w, regex))
                .forEach(t -> putMap(t, result));
        return result;
    }

    private static String readStringFromFile(String path) throws FileNotFoundException, IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded);
    }
}
