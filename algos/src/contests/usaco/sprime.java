package contests.usaco;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

/*
ID: thatner1
LANG: JAVA
TASK: sprime
 */
class sprime {
	static HashMap<Integer, Boolean> memo;
	
	public static void main (String [] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("sprime.in"));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("sprime.out")));
		int n = Integer.parseInt(reader.readLine());
		reader.close();
		
	
		memo = new HashMap<Integer, Boolean>();
		
		solve("2", n, writer);
		for (int i = 3; i <= 7; i += 2) {
			solve(""+i, n, writer);
		}
		
		writer.close();
	}
	
	public static void solve (String s, int loops, PrintWriter writer) {
		if (loops == 1) {
			//System.out.println("Got one: " + s);
			writer.write(s + "\n");
		}
		
		
		for (int i = 1; i <= 9; i += 2) {
			int rib = Integer.parseInt(s + i);
			if (memo.containsKey(rib) || isPrime(rib)) {
				solve(s + i, loops - 1, writer);
			}
		}
	}
	
	public static boolean isPrime (int check) {
		if (check % 2 == 0) return false;
		double cap = Math.sqrt(check) + 1;
		for (int i = 3; i < cap; i+=2) {
			if (check % i == 0) return false;
		}
		memo.put(check, true);
		return true;
	}
}
