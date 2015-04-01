package contests.siena.march2015;

import java.util.*;

// Remember to make sure the name of the file & class match!
public class Problem3 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int K = sc.nextInt();
		sc.nextLine();
		for (int trialCount = 0; trialCount < K; trialCount++) {
			int n = sc.nextInt();
			sc.nextLine();
			String a = sc.nextLine();
			String b = sc.nextLine();

			boolean isCycle = false;
			for (int i = 0; i < n; i++) {
				// Suppose we have a string S as "ABC", then this would take the
				// last char "C" and move it to the front, then substring up to right before C
				// Thus, the iterations would be "CAB", "BCA", etc.
				
				// We only need to loop n times where n is the length of the string.

				a = a.charAt(a.length() - 1) + a.substring(0, a.length() - 1);
				if (a.equals(b)) {
					isCycle = true;
					System.out.println("CYCLE");
					break;
				}
			}
			if (!isCycle) {
				System.out.println("NO CYCLE");
			}

		}
		sc.close();
	}

}
