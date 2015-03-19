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
