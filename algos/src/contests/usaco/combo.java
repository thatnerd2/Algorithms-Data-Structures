package contests.usaco;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;

/*
ID: thatner1
LANG: JAVA
TASK: combo
 */
class combo {
	public static void main (String [] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("combo.in"));
		PrintWriter writer = new PrintWriter (new BufferedWriter (new FileWriter("combo.out")));
		
		int n = Integer.parseInt(reader.readLine());
		
		if (n == 1) {
			writer.write("1\n");
			reader.close();
			writer.close();
			System.exit(0);
		}
		else if (n == 2) {
			writer.write("4\n");
			reader.close();
			writer.close();
			System.exit(0);
		}
		
		int[][] valid = new int[2][3];
		String[] farmerKey = reader.readLine().split(" ");
		String[] locksmithKey = reader.readLine().split(" ");
		for (int i = 0; i < valid[0].length; i++) valid[0][i] = Integer.parseInt(farmerKey[i]);
		for (int i = 0; i < valid[1].length; i++) valid[1][i] = Integer.parseInt(locksmithKey[i]);
		
		HashSet<String> works = new HashSet<String>();
		for (int i = 0; i < valid.length; i++) { //FOR EACH COMBO
			int[] start = valid[i];
			//System.out.println("Start " + Arrays.toString(start));
			int[] combo = new int[3];
			for (int j = start[0] - 2; j <= start[0] + 2; j++) {
				combo[0] = (j > 0) ? j : j + n;
				combo[0] = (j <= n) ? combo[0] : j - n;
				for (int k = start[1] - 2; k <= start[1] + 2; k++) {
					combo[1] = (k > 0) ? k : k + n;
					combo[1] = (k <= n) ? combo[1] : k - n;
					for (int l = start[2] - 2; l <= start[2] + 2; l++) {
						combo[2] = (l > 0) ? l : l + n;
						combo[2] = (l <= n) ? combo[2] : l - n;
						
						works.add(combo[0] + "," + combo[1] + "," + combo[2]);
					}
				}
			}
			
		}
		for (String i : works) {
			System.out.println(i);
		}
		
		System.out.println("Answer: " + works.size());
		writer.write(works.size() + "\n");
		reader.close();
		writer.close();
		System.exit(0);
	}
}
