package topcoder;

import java.util.Arrays;

public class TCCC_R4_Div1_250 {
	/* Success!
	 * 
	 * Since we couldn't loop around, had to break into two possiblities, find maxes per each
	 * and then max between those two.  Pretty cool.
	 * 
	 * 
	 */
	public static void main (String [] args) {
		/*int[] donations = { 10, 3, 2, 5, 7, 8 };
		int[] d2 = {11, 15};
		int[] d3 = { 7, 7, 7, 7, 7, 7, 7 };*/
		int[] d4 = 	
			{ 94, 40, 49, 65, 21, 21, 106, 80, 92, 81, 679, 4, 61,  
				  6, 237, 12, 72, 74, 29, 95, 265, 35, 47, 1, 61, 397,
				  52, 72, 37, 51, 1, 81, 45, 435, 7, 36, 57, 86, 81, 72 };
		int max = maxDonations(d4);
		
		System.out.println("Max: " + max);
	}
	
	public static int maxDonations (int[] donations) {
		
		//Either we pick the first or we don't.  Compute separately.
		int[] d1 = new int[donations.length - 1];
		int[] d2 = new int[donations.length - 1];
		for (int i = 0; i < d1.length; i++) {
			d1[i] = donations[i];
			d2[i] = donations[i + 1];
		}
		
		//Find max set of d1 and d2 and return max between those
		int[] s1 = new int[d1.length];
		int[] s2 = new int[d2.length];
		
		s1[0] = d1[0];
		s2[0] = d2[0];
		if (donations.length > 2) {
			s1[1] = Math.max(d1[0], d1[1]);
			s2[1] = Math.max(d2[0], d2[1]);
			for (int i = 2; i < s1.length; i++) {
				s1[i] = Math.max(s1[i - 2] + d1[i], s1[i - 1]);
			}
			
			for (int i = 2; i < s2.length; i++) {
				s2[i] = Math.max(s2[i - 2] + d2[i], s2[i - 1]);
			}
			
		}
		
		System.out.println("s1: " + Arrays.toString(s1));
		System.out.println("s2: " + Arrays.toString(s2));
		return Math.max(maxOf(s1), maxOf(s2));
	}
	
	public static int maxOf (int[] a) {
		int max = 0;
		for (int b : a) {
			max = Math.max(b, max);
		}
		return max;
	}
}
