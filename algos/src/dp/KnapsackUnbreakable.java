package dp;

public class KnapsackUnbreakable {
	public static void main (String [] args) {
		int[] weights = {10, 1};
		int[] values = {5, 10};
		
		int maxValue = getMaxValue(weights, values, 10);
		System.out.println("Max value: " + maxValue);
	}
	
	public static int getMaxValue (int[] weights, int[] values, int maxWeight) {
		int[][] dp = new int[values.length + 1][maxWeight + 1];
		
		for (int i = 0; i < dp[0].length; i++) {
			dp[0][i] = 0;
		}
		
		for (int i = 1; i < dp.length; i++) {
			dp[i][0] = 0;
			
			for (int j = 1; j < dp[i].length; j++) {
				if (j >= weights[i - 1]) {
					int takeIt = values[i - 1] + dp[i - 1][j - weights[i - 1]];
					int leaveIt = dp[i - 1][j];
					dp[i][j] = Math.max(takeIt, leaveIt);
				}
				else {
					dp[i][j] = dp[i - 1][j];
				}
				
			}
		}
		return dp[values.length][maxWeight];
	}
}
