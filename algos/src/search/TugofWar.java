package search;

import java.util.HashSet;

/* Problem statement:
 * Given a set of n integers, divide the set in two subsets of n/2 sizes each such 
 * that the difference of the sum of two subsets is as minimum as possible. 
 * 
 * If n is even, then sizes of two subsets must be strictly n/2 and if n is odd, 
 * then size of one subset must be (n-1)/2 and size of other subset must be (n+1)/2.
 */

public class TugofWar {
	static HashSet<Integer> best;
	static int minDiff;
	
	public static void main (String [] args) {
		int[] set = {3, 4, 5, -3, 100, 1, 89, 54, 23, 20};
		
		int sum = getSum(set);
		minDiff = Integer.MAX_VALUE;
		best = new HashSet<Integer>();
		solve(set, 0, "", sum);
		System.out.println(best);
		System.out.println("total : " + sum);
		System.out.println("Min diff: " + minDiff);
	}
	
	public static void solve (int[] nums, int n, String soFar, int total) {
		
		
		String[] list = soFar.split(" ");
		//System.out.println(Arrays.toString(list));
		if (list.length - 1 == nums.length / 2) {
			int sum = 0;
			for (int i = 1; i < list.length; i++) {
				int m = Integer.parseInt(list[i]);
				sum += m;
			}
			
			int diff = Math.abs(sum - total/2);
			
			if (minDiff > diff) {
				minDiff = diff;
				best.clear();
				if (diff == 0) {
					System.out.println("sum: " + sum);
					System.out.println("diff: " + diff);
				}
				for (int i = 1; i < list.length; i++) {
					best.add(Integer.parseInt(list[i]));
				}
			}
		}
		
		if (n == nums.length) return;
		
		solve(nums, n + 1, soFar + " " + nums[n], total);
		solve(nums, n + 1, soFar, total);
	}
	
	public static int getSum (int[] s) {
		int sum = 0;
		for (int b : s) {
			sum += b;
		}
		return sum;
	}
	
}
