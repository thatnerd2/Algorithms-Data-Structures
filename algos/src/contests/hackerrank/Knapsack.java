package contests.hackerrank;

import java.util.Scanner;

public class Knapsack {
	public static void main (String [] args) {
		Scanner scan = new Scanner (System.in);
		int numTests = scan.nextInt();
		scan.nextLine();
		
		for (int t = 0; t < numTests; t++) {
			String[] in = scan.nextLine().split(" ");
			int n = Integer.parseInt(in[0]);
			int sum = Integer.parseInt(in[1]);
			int[] A = new int[n];
			String[] parts = scan.nextLine().split(" ");
			for (int j =0; j < A.length; j++) {
				A[j] = Integer.parseInt(parts[j]);
			}
			
			//Find subset sums, then see if sum is divisible by any, otherwise pick the closest.
			
			boolean[] subsetSums = new boolean[sum + 1];
			subsetSums[0] = true;
			for (int i = 0; i < A.length; i++) {
				for (int j = subsetSums.length - A[i] - 1; j >= 0; j--) {
					if (subsetSums[j]) {
						subsetSums[j + A[i]] = true;
					}
				}
			}
			
			boolean found = false;
			for (int i = 1; i < subsetSums.length; i++) {
				if (subsetSums[i] && sum % i == 0) {
					System.out.println(sum);
					found = true;
					break;
				}
			}
			
			if (!found) {
				for (int i = sum; i >= 1; i--) {
					if (subsetSums[i]) {
						System.out.println(i);
						found = true;
						break;
					}
				}
			}
			
			if (!found) {
				System.out.println("0");
			}
		}
		
		scan.close();
	}
}
