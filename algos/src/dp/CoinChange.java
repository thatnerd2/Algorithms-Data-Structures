package dp;

import java.util.Arrays;

public class CoinChange {
	public static void main (String [] args) {
		int[] coinVals1 = {3, 4};
		int sum1 = 2;
		
		int coinsRequired = getNumCoinsChange(coinVals1, sum1);
		System.out.println("Answer: " + coinsRequired);
		System.out.println("My answer: " + getNumCoinsChange(coinVals1, sum1));
	}
	
	public static int getNumCoinsChange (int[] coins, int sum) {
		/**
		 * Solves the problem using DP.  Integer.MAX_VALUE - 1 picked because
		 * a total max value actually overflows when you check it + 1
		 * 
		 * If you need to keep track of what coins are added, I would have an
		 * array of ArrayLists<Integer> for each change sum.  Each time you generate
		 * a result, clear the corresponding list and fill it with the previous list plus the
		 * current coin.  Return the last list (the last one corresponds to the desired sum)
		 * 
		 */
		int[] coinsRequired = new int[sum + 1];
		Arrays.fill(coinsRequired, Integer.MAX_VALUE - 1);
		
		coinsRequired[0] = 0;
		for (int i = 1; i <= sum; i++) {
			for (int j = 0; j < coins.length; j++) {
				if (coins[j] <= i && coinsRequired[i - coins[j]] + 1 < coinsRequired[i]) {
					coinsRequired[i] = coinsRequired[i - coins[j]] + 1;
				}
			}
		}
		
		if (coinsRequired[sum] == Integer.MAX_VALUE - 1) {
			return -1;
		} else {
			return coinsRequired[sum];
		}
	}
}
