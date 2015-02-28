package topcoder;

import java.util.Arrays;

public class TTC_R3_Div1_200 {
	/*
	 * Success!
	 * 
	 */
	
	public static void main (String [] args) {
		int[] in = {1, 7, 4, 9, 2, 5};
		int[] in2 = { 1, 17, 5, 10, 13, 15, 10, 5, 16, 8 };
		int[] in3 = { 44 };
		int[] in4 = { 374, 40, 854, 203, 203, 156, 362, 279, 812, 955, 
				600, 947, 978, 46, 100, 953, 670, 862, 568, 188, 
				67, 669, 810, 704, 52, 861, 49, 640, 370, 908, 
				477, 245, 413, 109, 659, 401, 483, 308, 609, 120, 
				249, 22, 176, 279, 23, 22, 617, 462, 459, 244 };
		int[] in5 = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		
		assert longestZigZag(in) == 6 : "Case 1 failed, got " + longestZigZag(in);
		assert longestZigZag(in2) == 7: "Case 2 failed, got " + longestZigZag(in2);
		assert longestZigZag(in3) == 1: "Case 3 failed, got " + longestZigZag(in3);
		assert longestZigZag(in4) == 36: "Case 4 failed, got " + longestZigZag(in4);
		assert longestZigZag(in5) == 2: "Case 5 failed, got " + longestZigZag(in5);
	}
	
	public static int longestZigZag (int[] sequence) {
		int[][] states = new int[sequence.length][2];
		//Let state i be the length of the sequence that ends with the number sequence[i]
		for (int i = 0; i < states.length; i++) {
			states[i][1] = 0; //argh, answer depends on whether this is 0 or one.
			states[i][0] = 1;
		}
		if (sequence.length > 1) {
			states[0][1] = (sequence[1] - sequence[0] > 0) ? 0 : 1;
		}
		
		
		for (int i = 0; i < states.length; i++) {
			for (int j = 0; j < i; j++) {
				
				if (((states[j][1] == 0 && sequence[i] - sequence[j] > 0) ||
					(states[j][1] == 1 && sequence[i] - sequence[j] < 0)) &&
					states[j][0] + 1 > states[i][0]) {
					
					
					System.out.println("state[" + i + "] updated from state[" + j + "]");
					
					states[i][0] = states[j][0] + 1;
					states[i][1] = (states[j][1] == 0) ? 1 : 0;
				}
			}
		}
		for (int[] s : states) {
			System.out.println(Arrays.toString(s));
		}
		return maxAcross0(states);
	}
	
	public static boolean isOppSigns (int a, int b) {
		if (a < 0) {
			return b > 0;
		}
		else if (a > 0) {
			return b < 0;
		}
		else {
			return false;
		}
	}
	
	public static int maxAcross0 (int[][] a) {
		int max = 0;
		for (int[] b : a ) {
			max = Math.max(b[0], max);
		}
		return max;
	}
}
