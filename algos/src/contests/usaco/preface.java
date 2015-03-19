package contests.usaco;
/*
ID: thatner1
LANG: JAVA
TASK: preface
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

/* PROBLEM: CONVERT NUMBERS TO ROMAN NUMERALS */
public class preface {
	static HashMap<Integer, String> memo ;
	
	public static void main (String [] args) throws IOException {
		double time = System.currentTimeMillis();
		BufferedReader reader = new BufferedReader (new FileReader ("preface.in"));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("preface.out")));
		int n = Integer.parseInt(reader.readLine());
		reader.close();
		
		int[] vals = {1, 5, 10, 50, 100, 500, 1000};
		char[] letters = {'I', 'V', 'X', 'L', 'C', 'D', 'M'};
		int[] res = {0, 0, 0, 0, 0, 0, 0};
		
		StringBuilder builder = new StringBuilder();
		memo = new HashMap<Integer, String>();
		
		for (int i = 1; i <= n; i++) {
			//Represent the number i in roman numerals, then append it to builder.
			String rep = getRepresentation(i, vals, letters);
			builder.append(rep);
		}
		
		for (int i = 0; i < builder.length(); i++) {
			for (int j = 0; j < letters.length; j++) {
				if (letters[j] == builder.charAt(i)) {
					res[j]++;
					break;
				}
			}
		}
		
		for (int i = 0; i < res.length; i++) {
			if (res[i] > 0) {
				System.out.println(letters[i] + " " + res[i]);
				writer.write(letters[i] + " " + res[i] + "\n");
			}
		}
		writer.close();
		System.out.println("Time: " + (System.currentTimeMillis() - time));
	}
	
	public static String getRepresentation(int n, int[] vals, char[] letters) {
		if (memo.containsKey(n)) return memo.get(n);
		
		char[] digits = new StringBuilder("" + n).reverse().toString().toCharArray();
		String numeral = "";
		for (int i = 0; i < digits.length; i++) {
			String rep = getRep(Character.getNumericValue(digits[i]), i*2, vals, letters);
			numeral = rep + numeral;
		}
		//System.out.println(n + " -> "  + numeral);
		memo.put(n, numeral);
		return numeral;
	}
	
	public static String getRep (int n, int offset, int[] vals, char[] letters) {
		switch (n) {
		case 0: return "";
		case 1: return "" + letters[offset];
		case 2: return "" + letters[offset] + letters[offset];
		case 3: return "" + letters[offset] + letters[offset] + letters[offset];
		case 4: return "" + letters[offset] + letters[offset + 1];
		case 5: return "" + letters[offset + 1];
		case 6: return "" + letters[offset + 1] + letters[offset];
		case 7: return "" + letters[offset + 1] + letters[offset] + letters[offset];
		case 8: return "" + letters[offset + 1] + letters[offset] + letters[offset] + letters[offset];
		case 9: return "" + letters[offset] + letters[offset + 2];
		default: return "";
		}
	}
}
