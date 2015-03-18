package contests.usaco;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/*
ID: thatner1
LANG: JAVA
TASK: pprime
 */
class pprime {
	public static void main (String [] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("pprime.in"));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("pprime.out")));
		
		String[] parts = reader.readLine().split(" ");
		char[] min = parts[0].toCharArray();
		char[] max = parts[1].toCharArray();
		int minDigits = min.length;
		int maxDigits = max.length;
		int minN = Integer.parseInt(parts[0]);
		int maxN = Integer.parseInt(parts[1]);
		
		
		ArrayList<Integer> res = new ArrayList<Integer>();
		outer: for (int numDigits = minDigits; numDigits <= maxDigits; numDigits++) {
			int start = (numDigits == minDigits) ? Character.getNumericValue(min[0]) : 1;
			int end = (numDigits == maxDigits) ? Character.getNumericValue(max[0]) : 9;
			for (int i = start; i <= end; i += 2) {
				if (numDigits % 2 == 0) {
					genPrimePalindromes(i + "", numDigits/2 - 1, res, false);
				}
				else {
					genPrimePalindromes(i + "", numDigits/2, res, true);
				}
				//System.out.println(res.get(res.size() - 1));
				if (res.size() > 0 && res.get(res.size() - 1) > maxN) break outer;
				
			}
		}
		
		for (int i = 0; i < res.size(); i++) {
			if (res.get(i) > maxN) break;
			
			if (res.get(i) >= minN) {
				//System.out.println(res.get(i));
				writer.write(res.get(i) + "\n");
			}
			
		}
		reader.close();
		writer.close();
	}
	
	public static void genPrimePalindromes (String s, int loops, ArrayList<Integer> res, 
											boolean odd) {
		if (loops <= 0) {
			String otherHalf;
			if (odd) {
				otherHalf = (new StringBuilder(s.substring(0, s.length() - 1))).reverse().toString();
			} else {
				otherHalf = (new StringBuilder(s)).reverse().toString();
			}
			int candidate = Integer.parseInt(s + otherHalf);
			if (isPrime(candidate)) {
				res.add(candidate);
			}
			return;
		}
		else {
			for (int i = 0; i <= 9; i++) {
				genPrimePalindromes(s + i, loops - 1, res, odd);
			}
		}
	}
	
	public static boolean isPrime (int check) {
		if (check % 2 == 0) return false;
		double cap = Math.sqrt(check) + 1;
		for (int i = 3; i < cap; i+=2) {
			if (check % i == 0) return false;
		}
		return true;
	}
}
