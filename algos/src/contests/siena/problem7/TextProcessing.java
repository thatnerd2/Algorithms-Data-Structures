package contests.siena.problem7;

import java.util.HashSet;
import java.util.Scanner;

public class TextProcessing {
	public static void main (String [] args) {
		Scanner scan = new Scanner (System.in);
		HashSet<Character> vowels = new HashSet<Character>();
		vowels.add('A');
		vowels.add('E');
		vowels.add('I');
		vowels.add('O');
		vowels.add('U');
		vowels.add('a');
		vowels.add('e');
		vowels.add('i');
		vowels.add('o');
		vowels.add('u');
		String s = scan.nextLine();
		int numCons = 0;
		int numVow = 0;
		int numY = 0;
		int numW = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (vowels.contains(c)) {
				numVow++;
			}
			else if (c == 'Y' || c == 'y') {
				numY++;
			}
			else if (c == 'W' || c == 'w') {
				numW++;
			}
			else {
				for (char j = 'A'; j <= 'Z'; j++) {
					if (c == j) {
						
						numCons++;
						break;
					}
				}
				for (char j = 'a'; j <='z'; j++) {
					if (c == j) {
						System.out.println("yes " + c);
						numCons++;
						break;
					}
				}
			}
		}
		System.out.println(numCons + " consonants");
		System.out.println(numVow + " vowels");
		System.out.println(numY + " Ys");
		System.out.println(numW + " Ws");
		scan.close();
	}
}
