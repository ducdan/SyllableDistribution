package SyllableDistribution;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 *
 * @author danhd
 */
public class WordDistribution {

    private void putMapUsingDefault(Object key, Map map) {
        if (map.containsKey(key)) {
            int value = (int) map.get(key) + 1;
            map.put(key, value);
        } else {
            map.put(key, 1);
        }
    }

    public Map<Diacritic, Integer> countTones(String s) {
        Map<Diacritic, Integer> map = new HashMap<>();
        String[] words = s.replaceAll("_", " ").split(" ");
        Stream.of(words)
                .parallel()
                .map(w -> Diacritic.getTone(w))
                .forEach(t -> putMapUsingDefault(t, map));
        return map;
    }

    private int countMatch(String s, String regex) {
        int matches = 1;
        Matcher matcher = Pattern.compile(regex, Pattern.CASE_INSENSITIVE).matcher(s);
        while (matcher.find()) {
            matches++;
        }
        return matches;
    }

    public Map<Integer, Integer> countSyllables(String s, String regex) {
        Map<Integer, Integer> result = new HashMap();
        String[] words = s.split(" ");
        Stream.of(words)
                .parallel()
                .map(w -> countMatch(w, regex))
                .forEach(t -> putMapUsingDefault(t, result));
        return result;
    }

}
