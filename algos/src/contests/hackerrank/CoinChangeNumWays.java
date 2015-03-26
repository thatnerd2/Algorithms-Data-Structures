package contests.hackerrank;

import java.util.Scanner;

public class CoinChangeNumWays {
	public static void main (String [] args) {
		Scanner scan = new Scanner (System.in);
		String[] in = scan.nextLine().split(" ");
		int sum = Integer.parseInt(in[0]);
		int numCoins = Integer.parseInt(in[1]);
		
		//Read in coins
		int[] coins = new int[numCoins];
		String[] parts = scan.nextLine().split(" ");
		for (int i = 0; i < numCoins; i++) {
			coins[i] = Integer.parseInt(parts[i]);
		}
		
		//Init DP
		long[] dp = new long[sum + 1];
		dp[0] = 1;
		
		
		//Compute
		for (int i = 0; i < coins.length; i++) {
			for (int j = 0; j < dp.length; j++) {
				if (dp[j] > 0 && j + coins[i] < dp.length) {
					dp[j + coins[i]] += dp[j];
				}
			}
		}
		
		//System.out.println(Arrays.toString(dp));
		
		System.out.println(dp[sum]);
		scan.close();
	}
}
