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
TASK: fence9
 */

/* Mathy.  Use Pick's theorem and calculate on line lattice points vs off line. */
class fence9 {
	public static void main (String [] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("fence9.in"));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("fence9.out")));
		
		String[] parts = reader.readLine().split(" ");
		int n = Integer.parseInt(parts[0]);
		int m = Integer.parseInt(parts[1]);
		int p = Integer.parseInt(parts[2]);
		
		int area = p * m / 2;
		int b = 0;
		//Left side
		int[] slope = {m, n};
		int[] reduced = reduceFraction(slope);
		System.out.println(Arrays.toString(slope));
		System.out.println(Arrays.toString(reduced));
		if (reduced[0] != slope[0]) {
			if ((reduced[0] == 1) || reduced[1] == 0) {
				System.out.println("here");
				b += m - 1;
			}
			else {
				b += Math.abs(slope[0] / reduced[0]) - 1;
			}
		}
		System.out.println("B after left side: " + b);
		
		//Right side
		slope[0] = m;
		slope[1] = Math.abs(n - p);
		
		reduced = reduceFraction(slope);
		System.out.println(Arrays.toString(slope));
		System.out.println(Arrays.toString(reduced));
		if (reduced[0] != slope[0]) {
			if ((reduced[0] == 1) || reduced[1] == 0) {
				System.out.println("heree");
				b += m - 1;
			}
			else {
				b += Math.abs(slope[0] / reduced[0]) - 1;
			}
			
		}
		System.out.println("B after right isde: " + b);
		b += p - 1;
		b += 3;
		System.out.println("Area: " + area);
		System.out.println("Final b: " + b);
		System.out.println(area - (b / 2) + 1);
		int ans = area - (b / 2) + 1;
		writer.write(ans + "\n");
		
		reader.close();
		
		writer.close();
	}
	
	public static int[] reduceFraction (int[] a) {
		int b = gcd(a[0], a[1]);
		int[] ret = new int[2];
		ret[0] = a[0] / b;
		ret[1] = a[1] / b;
		return ret;
	}
	
	private static int gcd(int a, int b) {
		while (b > 0) {
			int temp = b;
			b = a % b;
			a = temp;
		}
		return a;
	}
	
	public static int gcd(int[] input)
	{
	    int result = input[0];
	    for(int i = 1; i < input.length; i++) 
	    	result = gcd(result, input[i]);
	    return result;
	}
}
