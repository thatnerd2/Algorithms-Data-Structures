package dp;

import java.util.Arrays;

public class KnapsackBreakable {
	public static void main (String [] args) {
		int[] weights = {20, 5};
		int[] values = {5, 10};
		
		double maxValue = getMaxValue(weights, values, 10);
		System.out.println("Max value: " + maxValue);
	}
	
	public static double getMaxValue (int[] weights, int[] values, int maxWeight) {
		boolean[] used = new boolean[weights.length];
		Arrays.fill(used, false);
		
		int weight = 0;
		double value = 0;
		while (weight < maxWeight) {
			int ix = getBestOf(weights, values, used);
			if (weights[ix] + weight <= maxWeight) {
				used[ix] = true;
				weight += weights[ix];
				value += values[ix];
			}
			else {
				int weightLeft = maxWeight - weight;
				double portion = weightLeft / (double) weights[ix];
				value += portion * values[ix];
				weight = maxWeight;
			}
		}
		return value;
	}
	
	public static int getBestOf (int[] weights, int[] values, boolean[] used) {
		//Maximize values[i]/weights[i];
		double best = 0;
		int ix = 0;
		for (int i = 0; i < weights.length; i++) {
			double rat = values[i] / (double)weights[i];
			if (rat > best && !used[i]) {
				best = rat;
				ix = i;
			}
		}
		return ix;
	}
}
