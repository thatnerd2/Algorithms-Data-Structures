package contests.usaco.notprintedyet;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/*
ID: thatner1
LANG: JAVA
TASK: fracdec
 */

/*Write a program that will accept a fraction of the form N/D, where N is the numerator and D is the denominator and print the decimal representation. If the decimal representation has a repeating sequence of digits, indicate the sequence by enclosing it in brackets. For example, 1/3 = .33333333...is denoted as 0.(3), and 41/333 = 0.123123123...is denoted as 0.(123). Use xxx.0 to denote an integer. Typical conversions are:

1/3     =  0.(3)
22/5    =  4.4
1/7     =  0.(142857)
2/2     =  1.0
3/8     =  0.375
45/56   =  0.803(571428)*/

/* BROKEN, FAILS IN SMALL PATTERNS LIKE 1.459494 459494 SHOULD BE 1.(459494) BUT THIS RETURNS 1.45(94)...
 * NEEDS TO LOOK AHEAD SOMEHOW.
 *  */
class fracdec {
	public static void main (String [] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("fracdec.in"));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("fracdec.out")));
		
		String[] parts = reader.readLine().split(" ");
		int n = Integer.parseInt(parts[0]);
		int d = Integer.parseInt(parts[1]);
		
		int whole = n / d;
		n -= d * whole;
		
		String digits = "";
		while (n != 0) {
			n *= 10;
			int div = n / d;
			n -= div * d;
			digits += div;
			
			for (int i = 2; i*2 < digits.length() + 1; i+= 2) {
				//System.out.println(i);
				String left = digits.substring(digits.length() - (i * 2), digits.length() - i);
				String right = digits.substring(digits.length() - i);
				//System.out.println(left + " _ " + right);
				if (left.equals(right) && !left.replaceAll("0", "").isEmpty()) {
					//System.out.println(whole + "." + digits.substring(0, digits.length() - (i * 2)) + "(" + left + ")");
					writer.write(whole + "." + digits.substring(0, digits.length() - i * 2) + "(" + left + ")\n");
					reader.close();
					writer.close();
					System.exit(0);
				}
			}
		}
		
		if (digits.isEmpty()) {
			digits = "0";
		}
		System.out.println(whole + "." + digits + "\n");
		writer.write(whole + "." + digits + "\n");
		reader.close();
		
		writer.close();
	}
}
