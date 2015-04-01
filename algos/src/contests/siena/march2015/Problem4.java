package contests.siena.march2015;

import java.util.*;

public class Problem4 {
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		int K = sc.nextInt();
		sc.nextLine();
		for (int trialCount = 0; trialCount < K; trialCount++) {
			String s = sc.nextLine();
			
			// The variable "result" is the number of subtractions that had to be done before a repeat happened.
			int result = 0;
			int prev = Integer.parseInt(s);
			char[] digits = s.toCharArray();
			Arrays.sort(digits);

			while (true) {
				result++;

				String prevAsString = "" + prev;
				char[] previousDigits = prevAsString.toCharArray();
				Arrays.sort(previousDigits);
				
				while (previousDigits.length < 4) {
					// Append leading zeroes, one at a time to the front.
					previousDigits = new char[] { '0', previousDigits[0], previousDigits[1], previousDigits[2] };
				}
				
				// Reassign with the leading zeroes now
				prevAsString = previousDigits[0] + "" + previousDigits[1] + "" + previousDigits[2] + "" + previousDigits[3];

				String subtractMe = (new StringBuilder(prevAsString)).reverse().toString();
				
				// Next iteration
				int n = Math.abs(Integer.parseInt(prevAsString) - Integer.parseInt(subtractMe));

				if (n == prev) {
					//Found a repeat!
					System.out.println(result);
					break;
				}
				prev = n;
			}

		}
		sc.close();
	}

}
