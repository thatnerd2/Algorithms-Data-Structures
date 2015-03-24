package contests.siena.problem7;

import java.util.Scanner;

public class InfinitePrimes {
	public static void main (String [] args) {
		Scanner scan = new Scanner (System.in);
		int n = scan.nextInt();
		int[] given = new int[n];
		for (int i = 0; i < n; i++) {
			given[i] = scan.nextInt();
		}
		
		int prod = 1;
		for (int b : given) prod *= b;
		prod += 1;
		
		boolean isPrime = true;
		for (int i = 3; i < Math.sqrt(prod) + 1; i += 2) {
			if (prod % i == 0) {
				System.out.println(prod + " " + i);
				isPrime = false;
				break;
			}
		}
		
		if (isPrime) {
			System.out.println(prod + " Prime");
		}
		scan.close();
	}
}
