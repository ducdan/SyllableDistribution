package SyllableDistribution;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

/**
 *
 * @author danhd
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        String test = readStringFromFile("data/5815000");
        
        WordDistribution counter = new WordDistribution();

        Map<Diacritic, Integer> countDisTones = counter.countTones(test);

        //print distributed tones resultest
        for (Diacritic key : countDisTones.keySet()) {
            System.out.println("Number of words belongs to DIACRITIC " + key.name() + " : " + countDisTones.get(key));
        }

        Map<Integer, Integer> countDisSyllable = counter.countSyllables(test, "_");

        //print distributed syllables result
        for (int key : countDisSyllable.keySet()) {
            System.out.println("Number of words has " + key + " syllables: " + countDisSyllable.get(key));
        }
    }

    private static String readStringFromFile(String path) throws FileNotFoundException, IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded);
    }
}
