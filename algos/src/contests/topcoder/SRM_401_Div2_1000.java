package contests.topcoder;

import java.util.Arrays;

/*
 * There is an electronic sign above the entrance to FIELD-Tech headquarters. 
 * The sign displays a scrolling message that is repeated over and over again. 
 * The letters show up on one side of the sign, scroll to the other side, and then disappear. 
 * Polly, who works in the office, is curious about the length of the message. 
 * She has observed the sign for some period of time and written down the letters she has seen, in order. 
 * Now, you must help her determine the minimal possible length of the message. 
 * For example, if she saw the letters "abcabcabcab", two possible messages would be "bca" and "abcabc". 
 * The shortest possible length would be 3.
 * 
 * You will be given a String[] running. 
 * Concatenate the elements of running to get a space separated list of sections, 
 * each formatted "N S" (quotes for clarity), representing the concatenation of N instances of S.
 * Expand out all the sections to get the entire text. For example, "3 abc 1 ab" expands out to 
 * "abcabcabcab" (3 instances of "abc" followed by 1 instance of "ab"). 
 *  
 *  Return the minimal possible message length that could have produced the given text.
 */

public class SRM_401_Div2_1000 {
	public static void main (String [] args) {
		//String[] in = {"3 abc 1 ab"};
		//String[] in2 = {"1 b ", "1 a ", "1 b ", "1 a", " 1 b"};
		String[] in3 = {"42 runningletters 42 runningletters 1 running"};
		
		int len = newsLength(in3);
		System.out.println("Length: " + len);
	}
	
	public static int newsLength (String[] in) {
		StringBuilder whole = new StringBuilder();
		for (int i = 0; i < in.length; i++) {
			whole.append(in[i]);
		}
		
		StringBuilder text = new StringBuilder();
		String[] parts = whole.toString().split(" ");
		for (int i = 0; i < parts.length; i += 2) {
			int n = Integer.parseInt(parts[i]);
			for (int j = 0; j < n; j++) {
				text.append(parts[i + 1]);
			}
		}
		
		System.out.println(text.toString());
		
		int textLength = text.length();
		int[] prefixFunction = new int[textLength];
		prefixFunction[0] = 0;
		int cur = 0;
		for (int i = 1; i < prefixFunction.length; i++) {
		    while (cur > 0 && text.charAt(cur) != text.charAt(i)) {
		    	System.out.println("FIRE OFF");
		        cur = prefixFunction[cur - 1];
		    }
		    if (text.charAt(cur) == text.charAt(i)) {
		    	System.out.println("HEELOO");
		        cur++;
		    }
		    prefixFunction[i] = cur;
		}
		System.out.println(Arrays.toString(prefixFunction));
		return textLength - prefixFunction[textLength - 1];
	}
}
