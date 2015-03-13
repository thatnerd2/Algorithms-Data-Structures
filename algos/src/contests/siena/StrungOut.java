package contests.siena;

import java.util.HashSet;

public class StrungOut {
	static HashSet<Character> vowels;
	
	public static void main (String [] args) {
		String[] input = {"IN THE THEORY OF THEATRE THE PLAY IS THE THING",
							"ANTIDISESTABLISHMENTARIANISM IS THE LONGEST WORD",
							"ZZZ"};
		
		vowels = new HashSet<Character>();
		vowels.add('A');
		vowels.add('E');
		vowels.add('I');
		vowels.add('O');
		vowels.add('U');
		
		strungOut(input);
	}
	
	public static void strungOut (String[] input) {
		for (int i = 0; i < input.length; i++) {
			process(input[i]);
		}
	}
	
	public static void process (String s) {
		s = s.trim();
		String[] parts = s.split(" ");
		int numThe = 0;
		int numVowels = 0;
		int sumLengths = 0;

		for (int i = 0; i < parts.length; i++) {
			if (parts[i].equals("THE")) {
				numThe++;
			}
			sumLengths += parts[i].length();
		}
		
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (vowels.contains(c)) {
				numVowels++;
			}
		}
		
		double avgLength = sumLengths / (double) parts.length;
		System.out.println("THE OCCURS " + numThe + " TIME(S)");
		System.out.println("NUMBER OF VOWELS: " + numVowels);
		System.out.println("THE AVERAGE WORD LENGTH IS " + avgLength);
	}
}
