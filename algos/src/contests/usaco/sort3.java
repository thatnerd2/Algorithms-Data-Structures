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
TASK: sort3
 */
class sort3 {
	public static void main (String [] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("sort3.in"));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("sort3.out")));
		
		int total = Integer.parseInt(reader.readLine());
		int[] ranks = new int[total];
		int[] regions = {0, 0, 0, 0};
		for (int i = 0; i < total; i++) {
			ranks[i] = Integer.parseInt(reader.readLine());
			regions[ranks[i]]++;
		}
		reader.close();
		
		regions[2] += regions[1];
		regions[3] += regions[2];
		
		int[] ideal = ranks.clone();
		Arrays.sort(ideal);
		System.out.println(Arrays.toString(ideal));
		int res = 0;
		System.out.println(Arrays.toString(regions));
		
		boolean useOptimal = true;
		while (!isSorted(ranks)) {
			if (useOptimal) {
				res += makeOptimalSwaps(ranks, regions);
			} else {
				oneSelectionSort(ranks, regions);
				
				//System.out.println("MADE SELECTION SWAP");
				res++;
			}
			//System.out.println("swaps count now: " + res);
			//System.out.println(Arrays.toString(ranks));
			//System.out.println("----LOOP------");
			
			useOptimal = !useOptimal;
		}
		
		System.out.println("Ans: " + res);
		
		writer.write(res + "\n");
		
		writer.close();
	
	}
	
	public static int makeOptimalSwaps (int[] ranks, int[] regions) {
		int wantsToGoTo = -1;
		int needsReplacementAs = -1;
		int numSwapsMade = 0;
		for (int i = 0; i < ranks.length; i++) {
			if (i >= regions[ranks[i]] || i < regions[ranks[i] - 1]) {
				//So if it's a 3 in the 1s place, then it will want to switch with 1 in the 3s place.
				needsReplacementAs = getNumByRegion(regions, i);
				wantsToGoTo = regions[ranks[i] - 1];
				//System.out.println("Needs replacement as " + needsReplacementAs);
				//System.out.println("Wants to go to region starting at " + wantsToGoTo);
				for (int j = wantsToGoTo; j < regions[ranks[i]]; j++) {
					//System.out.println("Ranks[j]: " + ranks[j] + " vs. needs: " + needsReplacementAs);
					if (ranks[j] == needsReplacementAs) {
						swap(ranks, j, i);
						numSwapsMade++;
					}
				}
			}
		}
		//System.out.println("Made " + numSwapsMade + " swaps");
		return numSwapsMade;
	}
	
	public static void oneSelectionSort (int[] ranks, int[] regions) {
		int needType = -1;
		int ix = -1;
		for (int i = 0; i < ranks.length; i++) {
			if (i >= regions[ranks[i]] || i < regions[ranks[i] - 1]) {
				if (needType == -1) {
					needType = getNumByRegion(regions, i);
					ix = i;
				}
				else if (ranks[i] == needType) {
					swap(ranks, i, ix);
					return;
				}
			}
		}
	}
	
	public static int getNumByRegion (int[] regions, int n) {
		for (int i = 1; i < regions.length; i++) {
			if (n < regions[i]) return i;
		}
		return -1;
	}
	
	public static void swap (int[] ranks, int a, int b) {
		int temp = ranks[a];
		ranks[a] = ranks[b];
		ranks[b] = temp;
	}
	
	public static boolean isSorted (int[] ranks) {
		for (int i = 1; i < ranks.length; i++) {
			if (ranks[i] < ranks[i - 1]) return false;
		}
		return true;
	}
}
