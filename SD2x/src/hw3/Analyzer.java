package hw3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

/*
 * SD2x Homework #3
 * Implement the methods below according to the specification in the assignment description.
 * Please be sure not to change the method signatures!
 */
public class Analyzer {
	
	/*
	 * Implement this method in Part 1
	 */
	public static List<Sentence> readFile(String filename) {

		/* IMPLEMENT THIS METHOD! 
		
		This method should take the name of the file to read and read it one line at a time, 
		creating Sentence objects and putting them into the List. 
		Note that the method returns a List containing Sentence objects.

		For a valid sentence such as:

		2 I am learning a lot .”

		then the score field of the Sentence object should be set to 2, 
		and the text field should be “I am learning a lot .”

		Your code should ignore (and not create a Sentence object for) any line that is not well-formatted, 
		which we assume to mean “starts with an int between -2 and 2 (inclusive), 
		has a single whitespace, and then is followed by more text.”

		However, if the file cannot be opened for reading or if the input filename is null, 
		this method should return an empty List.

		Note that it is up to you to determine which List implementation to return; 
		any decision is fine, as long as it implements the List interface and you do not change the signature of this method.
		
		*/
		List<Sentence> sentences = new ArrayList<>();
		List<String> lines = new ArrayList<>();
		
		/*
		 * https://stackoverflow.com/questions/4716503/reading-a-plain-text-file-in-java
		 */
		try {
			lines = Files.lines(Paths.get(filename)).collect(Collectors.toList());
		} catch (Exception e) {
			return new ArrayList<Sentence>(); // if the file cannot be opened for reading or if the input filename is null, this method should return an empty List.
		}
		
		for (String line : lines) {
			int spaceIndex = line.indexOf(" ");
			 
			try { 
				int score = Integer.parseInt(line.substring(0, spaceIndex));
				String text = line.substring(spaceIndex + 1); 
				
				if (score <= 2 && score >= -2 && !text.isEmpty()) { // well formatted
					sentences.add(new Sentence(score, text));
				}
			} catch(Exception e) {
				continue;
			}
			
		}
		
		return sentences; 
	}

	
	
	/*
	 * Implement this method in Part 2
	 */

	public static String parseWord(String s) {
		
		// Make all lower case
		s = s.toLowerCase();
		
		// Remove anything after a non-letter character
		

		return s;
		
	}

	public static Set<Word> allWords(List<Sentence> sentences) {

		/* IMPLEMENT THIS METHOD!
		
		Implement the allWords method in Analyzer.java (the same file as Part 1).

		This method should find all of the individual tokens/words in the text field of each Sentence in the List 
		and create Word objects for each distinct word. 
		The Word objects should keep track of the number of occurrences of that word in all Sentences, 
		and the total cumulative score of all Sentences in which it appears. 
		This method should then return a Set of those Word objects.

		If the input List of Sentences is null or is empty, the allWords method should return an empty Set.

		If a Sentence object in the input List is null, this method should ignore it and process the non-null Sentences.

		As you can see, allWords needs to tokenize/split the text of each Sentence to get the individual words.

		Keep in mind that when you tokenize the text of each Sentence, 
		you will be getting Strings, but the Set that this method returns needs to include Word objects. 
		However, if two Strings are equal, they should be combined into the same Word object, 
		which should track the cumulative score of all Sentences containing that String.

		As you may have noticed in the data file we provided, 
		some tokens start with punctuation and the first word of each sentence starts with a capital letter. 
		In producing the Set of Words, your program should ignore any token that does not start with a letter. 
		Also, this method should convert all strings to lowercase so that it is case-insensitive. 
		Do not assume that the strings in the Sentence objects have already been converted to lowercase.

		As an example, consider this text:

		It 's a lot of fun to learn about data structures.

		Your program should convert " It " to " it " (to make it lowercase) and ignore " 's " 
		and the period at the end of the sentence since those tokens do not start with a letter.

		*/

		// Initiate empty set (use a hashset)
		List<Word> words = new ArrayList<>();
		if (sentences.isEmpty() || !(sentences==null)) {
			// Loop through sentence
			for (int i = 0; i < sentences.size(); i++) {
				Sentence sentence = sentences.get(i);
				if (sentence != null) {
					// split into individual words
					String[] strings = sentence.getText().split(" ");
					// Loop through each word
					for (int j = 0; j < strings.length; j++) {
						String s = parseWord(strings[j]);
						if (Character.isLetter(s.charAt(0))) {
							Word newWord = new Word(s);
							// If word in list already
							if (words.contains(newWord)) {
								// Increment number of appearances
								// Increment the running total score of the string
								words.get(words.indexOf(newWord)).increaseTotal(sentence.getScore());
							}
							else {
								// Create new word object with total = score and count = 1
								words.add(newWord);
							}
						}

					}
				}
			}
		}
		
		return new HashSet<>(words);

	}
	
	/*
	 * Implement this method in Part 3
	 */
	public static Map<String, Double> calculateScores(Set<Word> words) {

		/* IMPLEMENT THIS METHOD!
		
		Implement the calculateScores method in Analyzer.java (the same file as Parts 1 and 2).

		This method should iterate over each Word in the input Set, 
		use the Word’s calculateScore method to get the average sentiment score for that Word, 
		and then place the text of the Word (as key) and calculated score (as value) in a Map.

		If the input Set of Words is null or is empty, the calculateScores method should return an empty Map.

		If a Word object in the input Set is null, this method should ignore it and process the non-null Words.

		As above, it is up to you to determine which Map implementation to return; 
		any decision is fine, as long as it implements the Map interface and you do not change the signature of this method.

		Please do not change the signature of the calculateScores method and do not modify Sentence.java or Word.java. 
		Also, please do not create new .java files. If you need to create new classes, add them to Analyzer.java.  
		Analyzer class is in the default package, i.e. there is no “package” declaration at the top of the source code.

		*/

		Map<String, Double> wordMap = new HashMap<>();

		if (words.isEmpty() ||  words != null) {
			for (Word word: words) {
				if (word != null) {
					wordMap.put(word.getText(), word.calculateScore());
				}
			}
		}
		
		return wordMap; 

	}
	
	/*
	 * Implement this method in Part 4
	 */
	public static double calculateSentenceScore(Map<String, Double> wordScores, String sentence) {

		/* IMPLEMENT THIS METHOD!
		
		Finally, implement the calculateSentenceScore method in Analyzer.java.

		This method should use the Map to calculate and return the average score of all the words in the input sentence.

		Note that you will need to tokenize/split the sentence, as you did in Part 2.

		If a word in the sentence does not start with a letter, then you can ignore it, 
		but if it starts with a letter and is not present in the Map, assign it a score of 0.

		You may assume that all words in the Map consist only of lowercase letters 
		(since this would have been done in previous steps) 
		but do not assume that all words in the input sentence consist only of lowercase letters; 
		you should convert them to lowercase before checking whether they’re contained in the Map.

		If the input Map is null or empty, 
		or if the input sentence is null or empty or does not contain any valid words, this method should return 0.

		Although you can (should!) test each method individually, 
		you can test the entire program using the main method in Analyzer.java. 
		Be sure to specify the name of the input file as the argument to main.

		*/

		double total = 0.0;
		int count = 0;

		if (wordScores != null && sentence != null) {
			// Tokenise the sentence
			String[] tokens = sentence.toLowerCase().split(" ");

			for (String token: tokens) {
				if (Character.isLetter(token.charAt(0))) {
					if (wordScores.containsKey(token)) {
						double avg = wordScores.get(token);
						total += avg;
						count++;
					}
				}
			}
		}

		if (count == 0) {
			return 0;
		}
		else {
			return total / count;
		}
	}
	
	/*
	 * This method is here to help you run your program.
	 * You may modify it as needed.
	 */
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Please specify the name of the input file");
			System.exit(0);
		}
		String filename = args[0];
		System.out.print("Please enter a sentence: ");
		Scanner in = new Scanner(System.in);
		String sentence = in.nextLine();
		in.close();
		List<Sentence> sentences = Analyzer.readFile(filename);
		Set<Word> words = Analyzer.allWords(sentences);
		Map<String, Double> wordScores = Analyzer.calculateScores(words);
		double score = Analyzer.calculateSentenceScore(wordScores, sentence);
		System.out.println("The sentiment score is " + score);
	}
}
