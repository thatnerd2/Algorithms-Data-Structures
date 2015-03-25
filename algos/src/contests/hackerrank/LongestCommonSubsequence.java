package contests.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LongestCommonSubsequence {
	public static void main (String [] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String[] dims = reader.readLine().split(" ");
		int n = Integer.parseInt(dims[0]);
		int m = Integer.parseInt(dims[1]);
		
		int[] a = new int[n];
		int[] b = new int[m];
		
		String[] aParts = reader.readLine().split(" ");
		for (int i = 0; i < aParts.length; i++) {
			a[i] = Integer.parseInt(aParts[i]);
		}
		
		String[] bParts = reader.readLine().split(" ");
		for (int i = 0; i < bParts.length; i++) {
			b[i] = Integer.parseInt(bParts[i]);
		}
		
		int[][] dp = new int[a.length + 1][b.length + 1];
		
		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[i].length; j++) {
				if (i == 0 || j == 0) {
					dp[i][j] = 0;
				}
				else {
					if (a[i - 1] == b[j - 1]) {
						dp[i][j] = dp[i - 1][j - 1] + 1;
					}
					else {
						dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
					}
				}
			}
		}
		
		StringBuilder ans = new StringBuilder(reconstruct(n, m, a, b, dp));
		System.out.println(ans.deleteCharAt(0).toString());
		
	}
	
	public static String reconstruct (int i, int j, int[] a, int[] b, int[][] dp) {
		if (i == 0 || j == 0) {
			return "";
		}
		else if (a[i - 1] == b[j - 1]) {
			return reconstruct(i - 1, j - 1, a, b, dp) + " " + a[i - 1];
		}
		else if (dp[i - 1][j] > dp[i][j - 1]) {
			return reconstruct(i - 1, j, a, b, dp);
		}
		else {
			return reconstruct(i, j - 1, a, b, dp);
		}
	}
}
