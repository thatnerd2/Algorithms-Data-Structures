package dp;

import java.util.Arrays;

public class CoinChange {
	public static void main (String [] args) {
		int[] coinVals1 = {3, 4};
		int sum1 = 2;
		
		int coinsRequired = sol1(coinVals1, sum1);
		System.out.println("Answer: " + coinsRequired);
		System.out.println("My answer: " + mysol(coinVals1, sum1));
	}
	
	public static int mysol (int[] coins, int sum) {
		/**
		 * Solves the problem using DP.  Integer.MAX_VALUE - 1 picked because
		 * a total max value actually overflows when you check it + 1
		 * 
		 * Could be improved by keeping track of exactly what coins were added
		 * (HashMap?) and then sending those back.  Harder problem though.
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
	
	public static int sol1(int[] vals, int sum) {
		/**
		 * Solves the problem (except I don't know how) and even returns
		 * the coins used.  What's going on here?  The array is populated with
		 * the seemingly wrong values, and then somehow the right values are retriieved.
		 * 
		 * The true answer is result.length, and it works for coins even when one coin isn't
		 * 1, like mysol assumes.  However, if the sum is not achievable, this breaks.
		 * 
		 * Anyway, compared to mysol up there, I think this one is inferior, except that
		 * it has the added function of keeping track of what coins were actually used.
		 */
		
		int n = vals.length;
		int[] count = new int [sum + 1];
		int[] from = new int[sum + 1];
		
		
		count[0] = 1; //Why 1?
		System.out.println("START COUNT: " + Arrays.toString(count));
		for (int i = 0; i < sum; i++) {			
			if (count[i] > 0) {
				for (int j = 0; j < n; j++) {
					int p = i + vals[j]; //p is an index!!? BUT THE INDICES ARE VALUES!?
					if (p <= sum) {
						if (count[p] == 0 || count[p] > count[i] + 1) {
							System.out.println("--------ACTION TAKEN----------");
							System.out.println("Assigned a value to count[" + p + "]: " + (count[i] + 1));
							System.out.println("count[" + p + "] before was: " + count[p]);
							System.out.println("count[i (" + i + ")] + 1 was: " + (count[i] + 1));
							
							count[p] = count[i] + 1;
							from[p] = j;
							
							System.out.println("Count is now: " + Arrays.toString(count));
						}
					}
				}
			}
		}
		
		/* NOT REQUIRED */
		int[] result = new int[count[sum] - 1];
        int k = sum;
        while (k > 0)
        {
            result[count[k] - 2] = vals[from[k]];
            k = k - vals[from[k]];
        }
        System.out.println(Arrays.toString(result));
        /* END NOT REQUIRED */
        
        System.out.println("Real answer: " + result.length);
        if (count[sum] == 0) {
			return -1;
		}
		else {
			return count[sum];
		}
	}
	
	public static int minOf (int[] v) {
		int lowest = Integer.MAX_VALUE;
		for (int i = 0; i < v.length; i++) {
			lowest = Math.min(lowest, v[i]);
		}
		return lowest;
	}
}
