package SyllableDistribution;

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

    enum Diacritic {

        SHARP, HANGING, ASKING, TUMBLING, HEAVY, LEVEL;

        public String getDiacriticValue() {
            switch (this) {
                case SHARP:
                    return "á ắ ấ é ế í ó ố ớ ú ứ ý";
                case HANGING:
                    return "à ằ ầ é ề ì ò ồ ờ ù ừ ỳ";
                case ASKING:
                    return "ả ẳ ẩ ẻ ể ỏ ổ ở ủ ử ỷ";
                case TUMBLING:
                    return "ã ẵ ẫ ẽ ễ õ ỗ ỡ ũ ữ ỹ";
                case HEAVY:
                    return "ạ ặ ậ ẹ ệ ọ ộ ợ ụ ự ỵ";
                default://LEVEL
                    return "";
            }
        }

        public static void findDiaDistribution(String s, int[] count) {
            for (Diacritic diacritic : Diacritic.values()) {
                if (diacritic != diacritic.LEVEL) {
                    Pattern p = Pattern.compile("\\w*[" + diacritic.getDiacriticValue() + "]+");
                    Matcher m = p.matcher(s);
                    if (m.find()) {
                        count[diacritic.ordinal()]++;
                        break;
                    }
                }
                else{
                    count[Diacritic.LEVEL.ordinal()]++;
                }
            }
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        String test = readStringFromFile("data/5815000");

        int[] count = countDistributedTones(test);

        //print count distributed tones result
        for (Diacritic diacritic : Diacritic.values()) {
            System.out.println("Number of words belongs to DIACRITIC " + diacritic.name() + " : " + count[diacritic.ordinal()]);
        }

        HashMap<String, Integer> syllableDitribution = countDistributedSyllables(test);

        //print count distributed syllables result
        for (String key : syllableDitribution.keySet()) {
            System.out.println("Number of words has " + key + " syllables: " + syllableDitribution.get(key));
        }
    }

    private static int[] countDistributedTones(String s) {
        int[] count = new int[6];
        for (int i = 0; i < 6; i++) {
            count[i] = 0;
        }
        String[] words = s.replaceAll("_", " ").split(" ");
        for (int i = 0; i < words.length; i++) {
            Diacritic.findDiaDistribution(words[i], count);
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
