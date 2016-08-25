package SyllableDistribution;

import java.util.Arrays;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author danhd
 */
public class WordDistribution {

    public Map<Diacritic, Long> countTones(Stream<String> words) {
        return words.parallel()
                .flatMap(w -> Arrays.stream(w.replaceAll("_", " ").split(" ")))
                .collect(Collectors.groupingBy(w -> Diacritic.getTone(w), Collectors.counting()));
    }

    public Map<Integer, Long> countSyllables(Stream<String> words) {
        return words.parallel()
                .collect(Collectors.groupingBy(w -> w.length() - w.replace("_", "").length(), Collectors.counting()));
    }

}
