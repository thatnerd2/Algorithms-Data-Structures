package contests.topcoder;

import java.util.Arrays;

/*
 * Problem Statement
    
You will be given 2 numbers start and finish each with the same number of digits. Both may have leading zeros. You are going to transform start into finish using the following kinds of transformation steps:
1) Increment a digit less than 9 by 1. For example, changing 354 to 364.
2) Decrement a digit greater than 0 by 1. For example, changing 354 to 254.
3) Swapping two digits. For example, changing 354 to 453.
Return the fewest number of transformation steps required to change start into finish.
Definition
    
Class:
NumberChanger
Method:
transform
Parameters:
String, String
Returns:
int
Method signature:
int transform(String start, String finish)
(be sure your method is public)
Limits
    
Time limit (s):
2.000
Memory limit (MB):
64

Constraints
-
start will contain between 1 and 8 characters inclusive.
-
Each character in start will be a digit ('0'-'9').
-
finish will contain the same number of characters as start.
 */
public class SRM_221_Div2_1100 {
	/* Incomplete since 12/30/2014
	 * - Aaron Chan
	 */
	
	public static int transform (String _start, String _finish) {
		int[] start = turnIntoIntArray(_start);
		int[] finish = turnIntoIntArray(_finish);
		int steps = 0;
		int idx = 0;
		int oo = Integer.MAX_VALUE;
		
		while (!same(start, finish)) {
			System.out.println(Arrays.toString(start));
			//So algorithm:
			//1.  Swap everyone without changing anything.
			//2.  Look at one number in finish at a time.  Find the closest number in start that's not already set
			//, increment/decrement a step closer, #1!! and go again, #1!!!, etc.  Basically take advantage
			// of swaps whenever you can.
			
			/* Locate and exploit potential swaps */
			for (int i = 0; i < finish.length; i++) {
				if (finish[i] != start[i]) {
					/* Start and Finish don't match here, so go through start to find swap. */
					for (int j = 0; j < start.length; j++) {
						/* Swap IF:
						 *     You're swapping with something that matches
						 *     The thing you're swapping with isn't already matching something else.
						 */
						if (i != j && finish[i] == start[j] && start[j] != finish[j]) {
							int temp = start[j];
							start[j] = start[i];
							start[i] = temp;
							steps++;
							break;
						}
					}
				}
			}
			
			/* Look at idx number and find closest in start in numberline distance, then increment/decrement */
			int recordDistance = oo;
			int recordIx = -1;
			for (int i = 0; i < start.length; i++) {
				if (Math.abs(start[i] - finish[idx]) < recordDistance && start[i] != finish[i]) {
					recordIx = i;
					recordDistance = Math.abs(start[i] - finish[idx]);
				}
			}

			if (recordDistance == 0) {
				idx++;
				continue;
			}
			
			if (start[recordIx] > finish[idx]) {
				start[recordIx] -= 1;
				steps++;
			}
			else if (start[recordIx] < finish[idx]) {
				start[recordIx] += 1;
				steps++;
			}
			else {
				System.out.println("WHAAT");
			}
		}
		return steps;
	}
	
	
	public static boolean same (int[] a, int[] b) {
		for (int i = 0; i < a.length; i++) {
			if (a[i] != b[i]) {
				return false;
			}
		}
		return true;
	}
	
	public static int[] turnIntoIntArray (String s) {
		char[] c = s.toCharArray();
		int[] res = new int[c.length];
		for (int i = 0; i < c.length; i++) {
			res[i] = Character.getNumericValue(c[i]);
		}
		return res;
	}
}
