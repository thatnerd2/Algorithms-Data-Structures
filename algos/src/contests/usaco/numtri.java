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
TASK: numtri
 */
class numtri {
	public static void main (String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("numtri.in"));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("numtri.out")));
		
		int numRows = Integer.parseInt(reader.readLine());
		int[][] triangle = new int[numRows][];
		for (int i = 0; i < numRows; i++) {
			int[] in = new int[i + 1];
			String[] parts = reader.readLine().split(" ");
			for (int j = 0; j < parts.length; j++) {
				in[j] = Integer.parseInt(parts[j]);
			}
			triangle[i] = in;
		}
		reader.close();
		
		for (int i = triangle.length - 2; i >= 0; i--) {
			for (int j = 0; j < triangle[i].length; j++) {
				int goLeft = triangle[i][j] + triangle[i + 1][j];
				int goRight = triangle[i][j] + triangle[i + 1][j + 1];
				triangle[i][j] = Math.max(goLeft, goRight);
			}
		}
		
		System.out.println(triangle[0][0]);
		writer.write(triangle[0][0] + "\n");
		writer.close();
	}
	
	public static void printTriangle (int[][] t) {
		for (int[] a : t) {
			System.out.println(Arrays.toString(a));
		}
	}
}
