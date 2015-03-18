package contests.usaco;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

/*
ID: thatner1
LANG: JAVA
TASK: frac1
 */
class frac1 {
	public static void main (String [] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("frac1.in"));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("frac1.out")));
		
		int denom = Integer.parseInt(reader.readLine());
		HashSet<String> unique = new HashSet<String>();
		unique.add("0 1");
		for (int d = 1; d <= denom; d++) {
			for (int i = 1; i < d; i++) {
				int[] frac = new int[] {i, d};
				reduceFraction(frac);
				//System.out.println(Arrays.toString(frac));
				String key = frac[0] + " " + frac[1];
				unique.add(key);
			}
		}
		
		ArrayList<int[]> fracs = new ArrayList<int[]>();
		for (String s : unique) {
			String[] parts = s.split(" ");
			int[] next = {Integer.parseInt(parts[0]), Integer.parseInt(parts[1])};
			fracs.add(next);
		}
		
		Collections.sort(fracs, new Comparator<int[]> () {
			public int compare (int[] a, int[] b) {
				double aVal = (a[0]/(double)a[1]);
				double bVal = (b[0] / (double)b[1]);
				if (aVal < bVal) return -1;
				else return 1;
			}
		});
		
		for (int i = 0; i < fracs.size(); i++) {
			String key = fracs.get(i)[0] + "/" + fracs.get(i)[1];
			//System.out.println(key);
			writer.write(key + "\n");
		}
		//System.out.println("1/1");
		writer.write("1/1\n");
		reader.close();
		writer.close();
	}
	
	public static void reduceFraction (int[] a) {
		int b = gcd(a[0], a[1]);
		a[0] /= b;
		a[1] /= b;
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
