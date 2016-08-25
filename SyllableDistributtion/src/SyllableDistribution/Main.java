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
     */
    public static void main(String[] args) {
        String input = "";
        System.out.println("1: text, 2: file\nEnter mode: ");
        Scanner sc = new Scanner(System.in);
        int mode = 0;
        String in = sc.nextLine();
        while (mode == 0) {
            if (null != in) {
                switch (in) {
                    case "1":
                        System.err.println("Enter text: ");
                        input = sc.nextLine();
                        mode = 1;
                        break;
                    case "2":
                        System.out.println("Enter path to file: ");
                         {
                            try {
                                input = readStringFromFile(sc.nextLine());
                                mode = 2;
                            } catch (IOException ex) {
                                System.out.println("File not found, please enter again: ");
                            }
                        }
                        break;
                }
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
