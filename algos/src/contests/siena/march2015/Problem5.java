package contests.siena.march2015;

import java.util.*;

// Remember to make sure the name of the file & class match!
public class Problem5 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int K = sc.nextInt();
		sc.nextLine();
		for (int trialCount = 0; trialCount < K; trialCount++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			String aStr = a + "";
			String bStr = b + "";

			// Width of the product, which is the biggest number, so the biggest
			// width needed.
			int width = ((a * b) + "").length();

			// If the width happens to be less than 2, then make it 2.
			int minWidth = 2;
			width = Math.max(minWidth, width);

			int[] steps = new int[bStr.length()];

			for (int i = 0; i < steps.length; i++) {
				// This is the multiplication process, multiplying the top by
				// one digit of the bottom.
				// Store the result in an array, steps[i]
				steps[i] = a
						* Character.getNumericValue(bStr.charAt(bStr.length()
								- i - 1));
				String inter = steps[i] + "" + gen(i, '0');
				steps[i] = Integer.parseInt(inter);
			}

			// Output the result in a nicely formatted way.
			// the "gen(int n, char c)" method returns a String of repeated
			// character c, with length n.
			// Example: gen(3, 'a') returns "aaa"
			// We use the gen(int n, char c) method to generate spaces and
			// dashes, usually.

			System.out.println(gen(width - aStr.length(), ' ') + a);
			System.out.println("x" + gen(width - 1 - bStr.length(), ' ') + b);
			System.out.println(gen(width, '-'));
			
			// Stupid special case if the second number is too short.
			if (bStr.length() != 1) {
				
				// Write down all the steps
				for (int i = 0; i < steps.length; i++) {
					String stepStr = steps[i] + "";
					if (steps[i] == 0) {
						System.out.println(gen(width - aStr.length() - i, ' ')
								+ gen(aStr.length() + i, '0'));
					} else {

						System.out.println(gen(width - stepStr.length(), ' ')
								+ steps[i]);
					}
				}
				
				//Write down the dashed lines and then the answer.
				System.out.println(gen(width, '-'));
				System.out.println(a * b);
			}
			else {
				//Write down the answer without any intermediate steps.
				int ans = a * b;
				String ansStr = ans + "";
				if (ansStr.length() == 1) {
					System.out.println(" " + ans);
				} 
				else {
					System.out.println(a * b);
				}
			}
			System.out.println("Done.");

		}
		sc.close();
	}

	public static String gen(int num, char c) {
		String s = "";
		for (int i = 0; i < num; i++) {
			s += c;
		}
		return s;
	}
}
