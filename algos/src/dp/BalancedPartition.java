package dp;

import java.util.ArrayList;
import java.util.Arrays;

public class BalancedPartition {
	/**
	 * Problem:  Partition an array of integers such that difference between the sums
	 * of each subset is minimized.
	 */
	
	public static void main (String [] args){
		int[] test = {1, 1,  4, 3, 9};
		
		boolean ans = balancePartition (test, 12);
		System.out.println(ans);
	}
	
	public static boolean balancePartition (int[] array, int target) {
		int sum = sumOf(array);
		
		/* In this problem, subsetSums[i][j] true if there IS a subset of integers from 0 to jth index
		 * which add up to sum i.  So the indices themselves aren't just "how many," they act as
		 * data.
		 */
		
		boolean[] subsetSums = new boolean[sum + 1];
		ArrayList<ArrayList<Integer>> subsets = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < subsetSums.length; i++) {
			subsets.add(new ArrayList<Integer>());
		}
		
		Arrays.fill(subsetSums, false);
		subsetSums[0] = true;
		
		for (int i = 0; i < array.length; i++) {
			for (int j = sum - array.length; j >= 0; j--) {
				if (subsetSums[j]) {
					subsetSums[j + array[i]] = true;
					subsets.get(j + array[i]).clear();
					subsets.get(j + array[i]).addAll(subsets.get(j));
					subsets.get(j + array[i]).add(array[i]);
				}
			}
		}
		
		for (int i = 0; i < subsetSums.length; i++) {
			if (subsetSums[i]) {
				System.out.println("To get " + i + " you need " + subsets.get(i));
			}	
		}
		
		// Get subset of sum closest to n/2
		
		int j = -1;
		int n = 1;
		ArrayList<Integer> minSet = new ArrayList<Integer>();
		for (int i = sum/2; i < subsetSums.length && i >= 0; i += j * n, j *= -1, n++) {
			System.out.println("i: " + i);
			System.out.println(subsets.size());
			if (subsetSums[i]) {
				minSet = subsets.get(i);
				break;
			}
		}
		
		// Get compliment set
		ArrayList<Integer> complimentSet = new ArrayList<Integer>();
		for (int i = 0; i < array.length; i++) {
			if (!minSet.contains(array[i])) {
				complimentSet.add(array[i]);
			}
		}
		
		//Abs value diff of sums, is answer.
		System.out.println("Min set 1: " + minSet);
		System.out.println("Min set 2: " + complimentSet);
		System.out.println("Min diff: " + Math.abs(sumOf(minSet) - sumOf(complimentSet)));
		return subsetSums[target];
	}
	
	public static int sumOf (ArrayList<Integer> a) {
		int sum = 0;
		for (Integer i : a) {
			sum += i;
		}
		return sum;
	}
	
	public static int sumOf (int[] a) {
		int sum = 0;
		for (int b : a) {
			sum += b;
		}
		return sum;
	}
}
