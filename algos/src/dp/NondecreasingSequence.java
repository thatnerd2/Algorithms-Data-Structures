package dp;

import java.util.Arrays;

public class NondecreasingSequence {
	/** Success!  Although it's still a little fuzzy.
	 * So the problem is: find the longest non decreasing sequence in a set
	 * meaning that for 4, 3, 4, 6, 8, 7, 8;; 3, 4, 6, 7, 8 would work (even though
	 * it's cut in half by that 8 in the middle there.)  Weird problem, but uh, 
	 * if the sequence had to be consecutive you could just solve it in O(n) time
	 * with a loop and a record holder.  Hooray DP!
	 * 
	 * @param args
	 */
	public static void main (String [] args) {
		int[] set = {5, 3, 4, 8, 8, 6, 7};
		int ans = longestSeq (set);
		System.out.println("Answer: " + ans);
	}
	
	public static int longestSeq (int[] set) {
		int[] states = new int[set.length];
		Arrays.fill(states,  1);
		
		for (int i = 0; i < states.length; i++) {
			
			for (int j = 0; j < i; j++) {
				if (set[j] < set[i] && states[j] + 1 > states[i]) {
					states[i] = states[j] + 1;
				}
			}
		}
		
		System.out.println(Arrays.toString(states));
		return maxOf(states);
	}
	
	public static int maxOf (int[] a) {
		int max = 0;
		for (int b : a) {
			max = Math.max(b, max);
		}
		return max;
	}
}
