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
TASK: ratios
 */
/* BRUTE FORCEY but whatever :-) */
class ratios {
	public static void main (String [] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("ratios.in"));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("ratios.out")));
		
		String[] parts = reader.readLine().split(" ");
		int[] wanted = {Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2])};
		
		int[][] mixtures = new int[3][3];
		for (int i = 0; i < mixtures.length; i++) {
			parts = reader.readLine().split(" ");
			mixtures[i][0] = Integer.parseInt(parts[0]);
			mixtures[i][1] = Integer.parseInt(parts[1]);
			mixtures[i][2] = Integer.parseInt(parts[2]);
		}
		
		boolean found = false;
		outer: for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				for (int k = 0; k < 100; k++) {
					if (i == 0 && j == 0 && k == 0) continue;
					
					int first = mixtures[0][0] * i + mixtures[1][0] * j + mixtures[2][0] * k;
					int second = mixtures[0][1] * i + mixtures[1][1] * j + mixtures[2][1] * k;
					int third = mixtures[0][2] * i + mixtures[1][2] * j + mixtures[2][2] * k;
					if ((wanted[0] == 0 || first % wanted[0] == 0) &&
							(wanted[1] == 0 || second % wanted[1] == 0) &&
							(wanted[2] == 0 || third % wanted[2] == 0)) {
						int[] divs = {-1, -1, -1};
						if (wanted[0] != 0) divs[0] = first / wanted[0];
						if (wanted[1] != 0) divs[1] = second / wanted[1];
						if (wanted[2] != 0) divs[2] = third / wanted[2];
						//System.out.println("IN");
						//System.out.println(Arrays.toString(divs));
						if (divs[0] == divs[1] || divs[0] == -1 || divs[1] == -1) {
							if (divs[1] == divs[2] || divs[1] == -1 || divs[2] == -1) {
								if (divs[2] == divs[0] || divs[0] == -1 || divs[2] == -1) {
									int multiplier = first / wanted[0];
									writer.write(i + " " + j + " " + k + " " + multiplier+"\n");
									System.out.println("FOUND HERE: " + i + " " + j + " " + k + " " + multiplier);
									found = true;
									break outer;
								}
							}
						}
					}
				}
			}
		}
		if (!found) {
			writer.write("NONE\n");
		}
		
		reader.close();
		
		writer.close();
	}
}
