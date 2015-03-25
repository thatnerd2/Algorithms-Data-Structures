package contests.usaco.notprintedyet;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/*
ID: thatner1
LANG: JAVA
TASK: rockers
 */
/* Super 3D DP, but I have no idea what's going on... */
class rockers {
	public static void main (String [] args) throws IOException {
		BufferedReader reader = new BufferedReader (new FileReader("rockers.in"));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("rockers.out")));
		
		String[] in = reader.readLine().split(" ");
		int n = Integer.parseInt(in[0]);
		int t = Integer.parseInt(in[1]);
		int m = Integer.parseInt(in[2]);
		
		int[] songs = new int[25];
		String[] parts = reader.readLine().split(" ");
		for (int i = 0; i < parts.length; i++) {
			songs[i + 1] = Integer.parseInt(parts[i]);
		}
		
		//Knapsack...
		int best = 0;
		int[][][] dp = new int[25][25][25];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j <= t; j++) {
				for (int k = 0; k <= n; k++) {
					for (int l = k + 1; l <= n; l++) {
						if (songs[l] + j <= t) {
							if (dp[i][j][k] + 1 > dp[i][j + songs[l]][l]) {
								dp[i][j + songs[l]][l] = dp[i][j][k] + 1;
							}
						}
						else {
							if (dp[i][j][k] + 1 > dp[i + 1][songs[l]][l]) {
								dp[i + 1][songs[l]][l] = dp[i][j][k] + 1;
							}
						}
						
					}
					best = Math.max(dp[i][j][k], best);
				}
			}
		}
		System.out.println(best);
		writer.write(best + "\n");
		reader.close();
		
		writer.close();
	}
}
