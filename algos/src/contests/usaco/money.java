package contests.usaco;
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
TASK: money
 */
class money {
	public static void main (String [] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("money.in"));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("money.out")));
		
		String[] parts = reader.readLine().split(" ");
		int numCoins = Integer.parseInt(parts[0]);
		int target = Integer.parseInt(parts[1]);
		
		long[] coins = new long[numCoins];
		int ix = 0;
		while (numCoins > 0) {
			String[] in = reader.readLine().split(" ");
			for (int i = 0; i < in.length; i++) {
				coins[ix] = Integer.parseInt(in[i]);
				ix++;
				numCoins--;
			}
		}
		
		long[] sums = new long[target + 1];
		sums[0] = 1;
		
		for (int i = 0; i < coins.length; i++) {
			for (int j = 0; j < sums.length; j++) {
				if (coins[i] + j < sums.length) {
					if (sums[j] < 0) System.out.println(sums[j]);
					sums[(int) (coins[i] + j)] += sums[j];
				}
			}
		}
		System.out.println(Arrays.toString(sums));
		System.out.println(sums[sums.length - 1]);
		
		reader.close();
		writer.write(sums[sums.length - 1] + "\n");
		
		writer.close();
	}
}
