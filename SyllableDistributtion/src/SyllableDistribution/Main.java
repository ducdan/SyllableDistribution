package SyllableDistribution;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 *
 * @author danhd
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        String input = "";
        if (null != args[0]) {
            switch (args[0]) {
                case "-path":
                    input = readStringFromFile(args[1]);
                    break;
                case "-text":
                    Scanner sc = new Scanner(System.in);
                    input = sc.nextLine();
                    break;
            }
        }

        WordDistribution counter = new WordDistribution();

        Map<Diacritic, Long> countDisTones = counter.countTones(split(input));

        //print distributed tones resultest
        countDisTones.keySet().stream().forEach((key) -> {
            System.out.println("Number of words belongs to DIACRITIC " + key.name() + " : " + countDisTones.get(key));
        });
        Map<Integer, Long> countDisSyllable = counter.countSyllables(split(input));

        //print distributed syllables result
        countDisSyllable.keySet().stream().forEach((key) -> {
            System.out.println("Number of words has " + key + " syllables: " + countDisSyllable.get(key));
        });
    }

    private static Stream<String> split(String content) {
        return Arrays.stream(content.split(" "));
    }

    private static String readStringFromFile(String path) throws FileNotFoundException, IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded);
    }
}
