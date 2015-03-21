package contests.usaco.notprintedyet;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

/*
ID: thatner1
LANG: JAVA
TASK: inflate
 */
/* KNAPSACK 0/1 PROBLEM
 * 
 * Bunch of lengths and points, and a max time, and maximize the number of points gotten by using
 * and unlimited number of the problems to gain points in their time.
 */
class inflate {
	public static void main (String [] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("inflate.in"));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("inflate.out")));
		
		String[] in = reader.readLine().replaceAll("\\s+", " ").split(" ");
		int M = Integer.parseInt(in[0]);
		int numClasses = Integer.parseInt(in[1]);
		
		int[] dp = new int[M + 1];
		for (int i = 0; i < numClasses; i++) {
			String[] parts = reader.readLine().split(" ");
			int len = Integer.parseInt(parts[1]);
			int points = Integer.parseInt(parts[0]);
			for ( int j = len; j <= M; j++) {
				if (dp[j] < dp[j - len] + points) {
					dp[j] = dp[j - len] + points;
				}
			}
		}
		
		System.out.println(Arrays.toString(dp));
		System.out.println(dp[M]);
		writer.write(dp[M] + "\n");
		reader.close();
		
		writer.close();
	}
}
