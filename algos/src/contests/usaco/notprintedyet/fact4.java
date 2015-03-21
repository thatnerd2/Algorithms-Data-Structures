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
TASK: fact4
 */
/* Find the rightmost nonzero digit in a huge factorial */
class fact4 {
	public static void main (String [] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("fact4.in"));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("fact4.out")));
		
		int n = Integer.parseInt(reader.readLine());
		
		int prod = 1;
		for (int i = 1; i <= n; i++) {
			
			prod = (prod * i) % 1000000;
			while (prod % 10 == 0) prod /= 10;
			//System.out.println(prod);
			
		}
		String ans = ("" + prod);;
		System.out.println(ans.charAt(ans.length() - 1));
		writer.write(ans.charAt(ans.length() - 1) + "\n");
		reader.close();
		
		writer.close();
		
	}
}
