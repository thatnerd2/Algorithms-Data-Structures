package contests.usaco;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;

/*
ID: thatner1
LANG: JAVA
TASK: runround
 */

/*
 * Runaround numbers are integers with unique digits, none of which is zero (e.g., 81362) 
 * that also have an interesting property, exemplified by this demonstration:

If you start at the left digit (8 in our number) and count that number of digits to the rig
ht (wrapping back to the first digit when no digits on the right are available), you'll end 
up at a new digit (a number which does not end up at a new digit is not a Runaround Number). 
Consider: 8 1 3 6 2 which cycles through eight digits: 1 3 6 2 8 1 3 6 so the next digit is 6.

Repeat this cycle (this time for the six counts designed by the `6') and you should end on a new digit: 2 8 1 3 6 2, 
namely 2.
Repeat again (two digits this time): 8 1
Continue again (one digit this time): 3
One more time: 6 2 8 and you have ended up back where you started, after touching each digit once. 
If you don't end up back where you started after touching each digit once, your number is not a Runaround number.

Given a number M (that has anywhere from 1 through 9 digits), find and print the next runaround number 
higher than M, which will always fit into an unsigned long integer for the given test data.
 */
class runround {
	public static void main (String [] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("runround.in"));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("runround.out")));
		int start = Integer.parseInt(reader.readLine());
		long i = start + 1;
		HashSet<Character> unique = new HashSet<Character>();
		boolean found = false;
		while (!found) {
			unique.clear();
			char[] digits = (i + "").toCharArray();
			boolean[] touched = new boolean[digits.length];
			
			for (char c : digits) {
				unique.add(c);
			}
			if (unique.size() != digits.length || unique.contains('0')) {
				i++;
				continue;
			}
			
			int shift = Character.getNumericValue(digits[0]);
			int pos = 0;
			int times = 0;
			while (times < digits.length) {
				pos += shift % digits.length;
				pos %= digits.length;
				touched[pos] = true;
				shift = Character.getNumericValue(digits[pos]);
				times++;
			}
			if (pos == 0 && allTrue(touched)) {
				System.out.println(i);
				writer.write(i+"\n");
				break;
			}
			i++;
		}
		reader.close();
		
		writer.close();
	}
	
	public static boolean allTrue (boolean[] b) {
		for (boolean c : b) {
			if (!c) return false;
		}
		return true;
	}
}
