package contests.hackerrank;

import java.math.BigInteger;
import java.util.Scanner;

public class HugeFibonacci {
	public static void main (String [] args) {
		Scanner scan = new Scanner (System.in);
		String[] in = scan.nextLine().split(" ");
		int aa = Integer.parseInt(in[0]);
		int bb = Integer.parseInt(in[1]);
		BigInteger a = BigInteger.valueOf(aa);
		BigInteger b = BigInteger.valueOf(bb);
		int n = Integer.parseInt(in[2]);
		int count = 2;
		while (count < n) {
			BigInteger c = b.multiply(b).add(a);
			a = b;
			b = c;
			count++;
		}
		System.out.println(b.toString());
		scan.close();
	}
}
