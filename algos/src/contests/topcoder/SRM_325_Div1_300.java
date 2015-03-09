package contests.topcoder;

import java.util.Arrays;

public class SRM_325_Div1_300 {
	/**
	 * BROKEN
	 * 
	 * @param args
	 */
	public static void main (String [] args) {
		String[] in = {"X.X...X.X"};
		//String[] in2 = {"X.X.....X"};
		
		double minCost = calculateCost(in);
		System.out.println("Min cost: " + minCost);
	}
	
	public static double calculateCost (String[] boards) {
		String fence = assemble(boards);
		
		System.out.println(fence);
		
		int len = fence.length();
		double[] dp = new double[len];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = (fence.charAt(0) == 'X') ? 1 : 0;
		for (int i = 1; i < dp.length; i++) {
			char state = fence.charAt(i);
			if (state == '.') {
				dp[i] = dp[i - 1];
			}
			for (int j = 0; j < i - 1; j++) {
				dp[i] = Math.min(dp[i], dp[j] + Math.sqrt(i - j));
			}
		}
		System.out.println(Arrays.toString(dp));
		return dp[len - 1];
	}
	
	public static String assemble (String[] b) {
		StringBuilder builder = new StringBuilder(b[0]);
		for (int i = 1; i < b.length; i++) {
			builder.append(b[i]);
		}
		return builder.toString();
	}
}
