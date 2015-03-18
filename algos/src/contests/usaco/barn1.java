package contests.usaco;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/*
ID: thatner1
LANG: JAVA
TASK: barn1
 */
public class barn1 {
	public static void main (String [] args) throws IOException {
		BufferedReader reader = new BufferedReader (new FileReader("barn1.in"));
		PrintWriter writer = new PrintWriter (new BufferedWriter (new FileWriter("barn1.out")));
		
		String[] params = reader.readLine().split(" ");
		int maxBoards = Integer.parseInt(params[0]);
		int numStalls = Integer.parseInt(params[1]);
		int numCows = Integer.parseInt(params[2]);
		boolean[] stalls = new boolean[numStalls];

		for (int i = 0; i < numCows; i++) {
			stalls[Integer.parseInt(reader.readLine().trim()) - 1] = true;
		}
		
		
		int cover = numCows;
		int boardsUsed = 0;
		
		ArrayList<Integer[]> sequences = new ArrayList<Integer[]>();
		
		//Find gaps first
		int start = 0;
		for (int i = 1; i < stalls.length; i++) {
			if (stalls[i]) {
				if (!stalls[i - 1]) {
					//Changed from stall without a cow to stall with a cow
					boardsUsed++;
					sequences.add(new Integer[] {start, i});
				}
			}
			else if (!stalls[i]){
				if (stalls[i - 1]) {
					//Changed from stall with cow in it to one without a cow
					start = i;
				}
			}
		}
		if (stalls[0] && !stalls[1]) {
			boardsUsed++;
			boolean stillOnFirstSequence = true;
			for (int i = 0; i < stalls.length; i++) {
				if (!stalls[i]) {
					stillOnFirstSequence = false;
				}
				if (stalls[i] && !stillOnFirstSequence) {
					sequences.add(new Integer[] {1, i});
					break;
				}
			}
			
		}
		
		sequences.remove(0);
		
		while (boardsUsed > maxBoards) {
			int min = Integer.MAX_VALUE;
			int recordIdx = -1;
			for (int i = 0; i < sequences.size(); i++) {
				int gap = sequences.get(i)[1] - sequences.get(i)[0];
				if (gap < min) {
					min = gap;
					recordIdx = i;
				}
			}
			
			cover += min;
			sequences.remove(recordIdx);
			boardsUsed -= 1;
		}

		System.out.println("Answer: " + cover);
		writer.write(cover+ "\n");
		
		reader.close();
		writer.close();
	}
}
