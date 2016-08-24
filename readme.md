#Tones and Syllables Distribution
* Get the distribution of the 6 tones in the Vietnamese language.
* Calculate distribution of how many syllable long word are in Vietnamese. (i.e. "em ở_đâu" is 3 syllables 2 words). Here we assume the input data have correctly grouped the syllables into words.

#Example

Input: *"em cực_kỳ dễ_thương"*

Output:
* Number of words belongs to NGA, NGANG, NANG, HUYEN is 1, 2, 1, 1 respectively.
* Number of words has 1 and 2 syllables is 1, 2 respectively
  
##Usage
```java
	String s="em cực_kỳ dễ_thương";
 
	WordDistribution counter = new WordDistribution();

        Map<Diacritic, Integer> countDisTones = counter.countTones(s);

        //print distributed tones result
        for (Diacritic key : countDisTones.keySet()) {
            System.out.println("Number of words belongs to DIACRITIC " + key.name() + " : " + countDisTones.get(key));
        }

        Map<Integer, Integer> countDisSyllable = counter.countSyllables(test, "_");

        //print distributed syllables result
        for (int key : countDisSyllable.keySet()) {
            System.out.println("Number of words has " + key + " syllables: " + countDisSyllable.get(key));
        }
```
##Thanks
Thanks to @Arashrouhani for the comment and inspiring me to do a <b>Tones and Syllables Distribution</b> version
