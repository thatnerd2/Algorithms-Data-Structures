package contests.usaco.notprintedyet;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

/*
ID: thatner1
LANG: JAVA
TASK: stamps
 */


/*
Given a set of N stamp values (e.g., {1 cent, 3 cents}) and an 
upper limit K to the number of stamps that can fit on an envelope, 
calculate the largest unbroken list of postages from 1 cent to M cents that can be created.

For example, consider stamps whose values are limited to 1 cent and 3 cents;
 you can use at most 5 stamps. It's easy to see how to assemble postage of 1 through 5 cents
  (just use that many 1 cent stamps), and successive values aren't much harder:

6 = 3 + 3
7 = 3 + 3 + 1
8 = 3 + 3 + 1 + 1
9 = 3 + 3 + 3
10 = 3 + 3 + 3 + 1
11 = 3 + 3 + 3 + 1 + 1
12 = 3 + 3 + 3 + 3
13 = 3 + 3 + 3 + 3 + 1.
However, there is no way to make 14 cents of postage with 5 or fewer stamps of value 1 and 3 cents. 
Thus, for this set of two stamp values and a limit of K=5, the answer is M=13.

The most difficult test case for this problem has a time limit of 3 seconds.

TL;DR
How to make "change" for something using under a certain amount of coins
 */
class stamps {
	public static void main (String [] args)  throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("stamps.in"));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("stamps.out")));
		
		String[] in = reader.readLine().split(" ");
		int numStamps = Integer.parseInt(in[1]);
		int maxStamps = Integer.parseInt(in[0]);
		int[] stamps = new int[numStamps];
		int count = 0;
		while (count < numStamps) {
			String[] parts = reader.readLine().split(" ");
			for (int i = 0; i < parts.length; i++) {
				stamps[count] = Integer.parseInt(parts[i]);
				count++;
			}
		}
		
		Arrays.sort(stamps);
		
		boolean[] valid = new boolean[stamps[stamps.length - 1] * maxStamps + 1];
		valid[0] = true;
		int[] dp = new int[stamps[stamps.length - 1] * maxStamps + 1];
		
		dp[0] = 0;
		for (int i = 0; i < stamps.length; i++) {
			for (int j = 0; j < dp.length; j++) {
				if (j + stamps[i] < dp.length && valid[j] && 
						(dp[j] + 1 < dp[j + stamps[i]] || valid[j + stamps[i]] != true)) {
					dp[j + stamps[i]] = dp[j] + 1;
					valid[j + stamps[i]] = true;
				}
			}
		}
		
		boolean found = false;
		for (int i = 0; i < dp.length; i++) {
			if (valid[i] && dp[i] > maxStamps) {
				System.out.println(i - 1);
				writer.write((i - 1) + "\n");
				found = true;
				break;
				
			}
		}
		
		if (!found) {
			System.out.println(dp.length - 1);
			writer.write((dp.length - 1) + "\n");
		}
		
		reader.close();
		
		writer.close();
	}
}
