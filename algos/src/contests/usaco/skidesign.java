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
TASK: skidesign
 */
class skidesign {
	public static void main (String [] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("skidesign.in"));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("skidesign.out")));
		
		int numHills = Integer.parseInt(reader.readLine());
		int[] hills = new int[numHills];
		int[] mov = new int[numHills];
		for (int i = 0; i < numHills; i++) {
			hills[i] = Integer.parseInt(reader.readLine());
		}
		Arrays.sort(hills);
		Arrays.fill(mov, 0);
		reader.close();
		
		while (getHeightDiff(hills) > 17) {
			//System.out.println(getHeightDiff(hills));
			int maxMovLeft = mov[0];
			for (int i = 1; i < hills.length; i++) {
				if (hills[i] == hills[i - 1]) maxMovLeft += mov[i];
				else break;
			}
			
			int maxMovRight = mov[mov.length - 1];
			for (int i = hills.length - 2; i >= 0; i--) {
				if (hills[i] == hills[i + 1]) maxMovRight += mov[i];
				else break;
			}
			
			if (maxMovLeft <= maxMovRight) {
				hills[0] += 1;
				mov[0] += 1;
				for (int i = 1; i < hills.length; i++) {
					if (hills[i] < hills[i - 1]) {
						hills[i] += 1;
						mov[i] += 1;
					}
					else break;
				}
			}
			else {
				hills[hills.length - 1] -= 1;
				mov[hills.length - 1] += 1;
				for (int i = hills.length - 2; i >= 0; i--) {
					if (hills[i] > hills[i + 1]) {
						hills[i] -= 1;
						mov[i] += 1;
					}
					else break;
				}
			}
		}
		
		int spent = 0;
		for (int i = 0; i < mov.length; i++) {
			spent += mov[i] * mov[i];
		}
		
		System.out.println(spent);
		writer.write(spent+ "\n");
		writer.close();
	}
	
	public static int getHeightDiff (int[] hills) {
		int max = 0;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < hills.length; i++) {
			max = Math.max(hills[i], max);
			min = Math.min(hills[i], min);
		}
		return max - min;
	}
}
