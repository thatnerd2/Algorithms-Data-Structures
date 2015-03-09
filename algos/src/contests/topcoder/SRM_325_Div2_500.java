package contests.topcoder;

import java.util.Arrays;

public class SRM_325_Div2_500 {
	public static void main (String [] args) {
		String[] in = {"1 100 100", "100 1 100", "100 100 1"};
		
		int minCost = rgbNeighbors(in);
		System.out.println("Min cost: " + minCost);
	}
	
	public static int rgbNeighbors (String[] in) {
		int n = in.length;
		int[] redCosts = new int[n];
		int[] blueCosts = new int[n];
		int[] greenCosts = new int[n];
		
		for (int i = 0; i < in.length; i++) {
			String[] costs = in[i].split(" ");
			redCosts[i] = Integer.parseInt(costs[0]);
			blueCosts[i] = Integer.parseInt(costs[1]);
			greenCosts[i] = Integer.parseInt(costs[2]);
		}
		
		int[][] dp = new int[3][n];
		char[] previous = new char[3];
		for (int b[] : dp) {
			Arrays.fill(b, Integer.MAX_VALUE);
		}
		
		dp[0][0] = redCosts[0];
		previous[0] = 'R';
		dp[1][0] = blueCosts[0];
		previous[1] = 'B';
		dp[2][0] = greenCosts[0];
		previous[2] = 'G';

		for (int i = 0; i < dp[0].length; i++) {
			for (int j = 0; j < dp.length; j++) {
				if (previous[j] != 'R') {
					previous[j] = (blueCosts[i] <= greenCosts[i]) ? 'B' : 'G';
					dp[j][i] = Math.min(blueCosts[i], greenCosts[i]);
				}
				if (previous[j] != 'G') {
					previous[j] = (blueCosts[i] <= redCosts[i]) ? 'B' : 'R';
					dp[j][i] = Math.min(blueCosts[i], redCosts[i]);
				}
				if (previous[j] != 'B') {
					previous[j] = (greenCosts[i] <= redCosts[i]) ? 'G' : 'R';
					dp[j][i] = Math.min(greenCosts[i], blueCosts[i]);
				}
			}
		}
		return Math.min(Math.min(dp[0][dp.length - 1], dp[1][dp.length - 1]), dp[2][dp.length - 1]);
	}
}
