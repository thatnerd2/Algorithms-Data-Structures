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
TASK: game1
 */
/* Simulate game where 2 players pick numbers from the edge of a board of integers.  Returns optimal for player 1
 * and optimal for player 2.
 */
class game1 {
	public static void main (String [] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("game1.in"));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("game1.out")));
		
		int n = Integer.parseInt(reader.readLine());
		int[] board = new int[n];
		int count = 0;
		while (count < n) {
			String[] parts = reader.readLine().split(" ");
			for (int i = 0; i < parts.length; i++) {
				board[count] = Integer.parseInt(parts[i]);
				count++;
			}
		}
		
		
		int[][] dp = new int[n + 1][n + 1];
		int[][] sum = new int[n + 1][n + 1];
		
		for (int i = 0; i < board.length; i++) {
			dp[i + 1][i + 1] = board[i];
			sum[i + 1][i + 1] = board[i];
		}
		
		for (int i = 1; i <= n; i++) {
			for (int j = i + 1; j <= n; j++) {
				sum[i][j] = sum[i][j - 1] + dp[j][j];
			}
		}
		
		/*for (int[] b : dp) {
			System.out.println(Arrays.toString(b));
		}
		System.out.println("AND SUMS:");
		for (int[] b : sum) {
			System.out.println(Arrays.toString(b));
		}*/
		
		for (int length = 1; length < n; length++) {
			for (int j = 1; j + length <= n; j++) {
				dp[j][j + length] = sum[j][j + length] - Math.min(dp[j][j + length - 1], dp[j + 1][j + length]);
				/*for (int[] b : dp) {
					System.out.println(Arrays.toString(b));
				}
				System.out.println("----------------------");*/
				
			}
		}
		/*System.out.println("--------FINAL----------------");
		for (int[] b : dp) {
			System.out.println(Arrays.toString(b));
		}
		System.out.println("AND SUMS:");
		for (int[] b : sum) {
			System.out.println(Arrays.toString(b));
		}*/
		//System.out.println(dp[1][n] + " " + (sum[1][n] - dp[1][n]));
		//System.out.println(Arrays.toString(board));
		writer.write(dp[1][n] + " " + (sum[1][n] - dp[1][n]) + "\n");
		reader.close();
		
		writer.close();
	}
}
