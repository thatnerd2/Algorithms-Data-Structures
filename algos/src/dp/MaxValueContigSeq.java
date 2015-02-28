package dp;

public class MaxValueContigSeq {
	public static void main (String [] args) {
		/*
		 * Problem:  Find subsequence of consecutive numbers that have maximized sum
		 * 
		 * Success!
		 */
		
		int[] in = {1, 2, 3, 5, 6, 7};
		int ans = solve(in);
		System.out.println("Answer: " + ans);
	}
	
	public static int solve (int[] set) {
		int[] states = new int[set.length];
		
		/* Init states to be just the number in set, sequence of 1 */
		for (int i = 0; i < states.length; i++) {
			states[i] = set[i];
		}
		
		for (int i = 0; i < states.length; i++) {
			for (int j = 0; j < i; j++) {
				if (set[i] - set[j] == 1 && states[j] + set[i] > states[i]) {
					states[i] = states[j] + set[i];
					System.out.println("Set states[" + i + "] to " + states[i]);
				}
			}
		}
		
		return maxOf(states);
	}
	
	public static int maxOf (int[] a) {
		int max = 0;
		for (int b : a ) {
			max = Math.max(b, max);
		}
		return max;
	}
}
