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
TASK: concom
 */
class concom {
	static int[][] owns = new int[101][101];
	static boolean[][] controls = new boolean[101][101];
	
	public static void main (String [] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("concom.in"));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("concom.out")));
		
		
		int numTriples = Integer.parseInt(reader.readLine());
		
		for (int i = 0; i < controls.length; i++) {
			controls[i][i] = true;
		}
		
		for (int i = 0; i < numTriples; i++) {
			String[] parts = reader.readLine().split(" ");
			int idA = Integer.parseInt(parts[0]);
			int idB = Integer.parseInt(parts[1]);
			int p = Integer.parseInt(parts[2]);
			
			
			for (int j = 0; j < controls.length; j++) {
				if (controls[j][idA]) {
					owns[j][idB] += p;
				}
			}
			
			for (int j = 0; j < owns.length; j++) {
				if (owns[j][idB] > 50) {
					addControl(j, idB);
				}
			}
		}
		
		reader.close();
		
		for (int i = 0; i < controls.length; i++) {
			for (int j = 0; j < controls[i].length; j++) {
				if (controls[i][j] && i != j) {
					System.out.println((i) + " " + (j));
					writer.write(i + " " + j + "\n");
				}
			}
		}
		writer.close();
		System.exit(0);
	}
	

	
	public static void addControl (int idA, int idB) {
		if (controls[idA][idB]) return;
		
		controls[idA][idB] = true;
		for (int i = 0; i < owns[idA].length; i++) {
			owns[idA][i] += owns[idB][i];
			
			if (owns[idA][i] > 50) {
				addControl(idA, i);
				controls[idA][i] = true;
			}
		}
		
		for (int i = 0; i < controls[idA].length; i++) {
			if (controls[i][idA]) {
				addControl(i, idB);
			}
		}
		
		return;
	}
}
