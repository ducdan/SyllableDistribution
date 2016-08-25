#Tones and Syllables Distribution
* Get the distribution of the 6 tones in the Vietnamese language.
* Calculate distribution of how many syllable long word are in Vietnamese. (i.e. "em ở_đâu" is 3 syllables 2 words). Here we assume the input data have correctly grouped the syllables into words.

#Example

Input: *"em cực_kỳ dễ_thương"*

Output:
* Number of words belongs to NGA, NGANG, NANG, HUYEN is 1, 2, 1, 1 respectively.
* Number of words has 1 and 2 syllables is 1, 2 respectively
  
##Usage
### Using the compile file: SyllableDistributtion.jar
Run the command: java -jar "path to SyllableDistributtion.jar " -option [argument]
There are 2 options: <italic>-path</italic> and <italic>-text</italic>
<b> Example:</b>
* If you want to input by a file: ` java -jar dist/SyllableDistributtion.jar -path data/5815000`
* If you want to input by hand: ` java -jar dist/SyllableDistributtion.jar -text` Enter and input the text you want to count tones and sybllables

### Using the code:

```java
	String s="em cực_kỳ dễ_thương";
	
	WordDistribution counter = new WordDistribution();
	
	Map<Diacritic, Long> countDisTones = counter.countTones(s);
	
	Map<Integer, Long> countDisSyllable = counter.countSyllables(s, "_");
```
##Thanks
Thanks to @Tarrasch for the comment and inspiring me to do a <b>Tones and Syllables Distribution</b> version
