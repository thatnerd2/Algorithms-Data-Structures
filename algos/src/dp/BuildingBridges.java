package dp;

import java.util.Arrays;

public class BuildingBridges {
	public static void main (String [] args) {
		int[] northCities = {1, 2, 3, 4};
		int[] southCities = {4, 3, 2, 1};
		
		int nBridges = countBridges(northCities, southCities);
		System.out.println("Max bridges: " + nBridges);
	}
	
	public static int countBridges (int[] north, int[] south) {
		//ASSUME north.length = south.length
		
		int[] states = new int[north.length];
		Arrays.fill(states, 0);
		states[0] = 1;
		for (int i = 0; i < states.length; i++) {
			for (int j = 0; j < i; j++) {
				System.out.println("North[i]: " + north[i]);
				System.out.println("North[j]: " + north[j]);
				System.out.println("--------------------");
				if (north[i] > north[j] && south[i] > south[j] && states[i] < states[j] + 1) {
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
			max = Math.max(b,max);
		}
		return max;
	}
}
