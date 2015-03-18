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
TASK: subset
 */
public class subset {
	static HashSet<String> sols;
	
	public static void main (String [] args) throws IOException {
		BufferedReader reader = new BufferedReader (new FileReader ("subset.in"));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("subset.out")));
		int n = Integer.parseInt(reader.readLine());
		int[] set = new int[n];
		for (int i = 0; i < n; i++) {
			set[i] = i + 1;
		}
		int total = sumOf(set);
		System.out.println("total: " + total);
		reader.close();
		
		int[] subsetSums = new int[total + 1];
		subsetSums[0] = 1;
		int res = 0;
		for (int i = 1; i < n; i++) {
			for (int j = total - i; j >= 0; j--) {
				subsetSums[j + i] += subsetSums[j];
					
			}
		}
		
		res = subsetSums[total / 2];
		if (total % 2 != 0) res = 0;
		
		System.out.println(res);
		writer.write(res + "\n");
		writer.close();
	}
	
	public static int sumOf (int[] a) {
		int sum = 0;
		for (int i : a) sum += i;
		return sum;
	}
}
