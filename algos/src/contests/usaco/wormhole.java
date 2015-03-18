package contests.usaco;
/*
ID: thatner1
LANG: JAVA
TASK: wormhole
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;


class wormhole {
	
	public static void main (String [] args) throws IOException {
		double time = System.currentTimeMillis();
		BufferedReader reader = new BufferedReader(new FileReader("wormhole.in"));
		PrintWriter writer = new PrintWriter(new BufferedWriter (new FileWriter("wormhole.out")));
		
		int nHoles = Integer.parseInt(reader.readLine());
		Wormhole[] holes = new Wormhole[nHoles];
		for (int i = 0; i < nHoles; i++) {
			String[] parts = reader.readLine().split(" ");
			int[] coord = {Integer.parseInt(parts[0]), Integer.parseInt(parts[1])};
			holes[i] = new Wormhole(""+i, coord[0], coord[1]);
		}
		reader.close();
		int[] pairs = new int[holes.length];
		Arrays.fill(pairs, -1);
		
		
		HashSet<String> unique = new HashSet<String>();
		solve(holes, pairs, unique);
		System.out.println("Ans: " + unique.size());
		writer.write(unique.size() + "\n");
		writer.close();
		System.out.println("TIME " + (System.currentTimeMillis() - time));
	}
	
	public static void solve (Wormhole[] holes, int[] pairs, HashSet<String> unique) {

		if (everyoneIsPaired(pairs)) {
			if (cycleExists(holes, pairs)) {
				
				//System.out.println(Arrays.toString(pairs));
				int[] clone = pairs.clone();
				ArrayList<String> pairings = new ArrayList<String>();
				for (int i = 0; i < clone.length; i++) {
					if (clone[i] != -1) {
						int a = clone[i];
						int b = clone[clone[i]];
						String key = Math.min(a, b) + "-" + Math.max(a, b);
						clone[clone[i]] = -1;
						clone[i] = -1;
						pairings.add(key);
					}
				}
				
				Collections.sort(pairings);
				StringBuilder builder = new StringBuilder();
				for (int i = 0; i < pairings.size(); i++) {
					builder.append(pairings.get(i) + " ");
				}
				String sol = builder.toString();
				//System.out.println(sol);
				unique.add(sol);
				
			}
		}
		
		for (int i = 0; i < pairs.length; i++) {
			//System.out.println("loo");
			for (int j = i + 1; j < pairs.length; j++) {
				if (i != j && pairs[i] == -1 && pairs[j] == -1) {
					pairs[i] = j;
					pairs[j] = i;
					solve(holes, pairs, unique);
					pairs[i] = -1;
					pairs[j] = -1;
				}
			}
		}
	}
	
	public static boolean cycleExists (Wormhole[] holes, int[] pairs) {
		HashSet<String> visited = new HashSet<String>();
		for (int i = 0; i < holes.length; i++) {
			int x = holes[pairs[i]].x;
			int y = holes[pairs[i]].y;
			visited.clear();	
			visited.add(x + "_" + y);
			if (loops(x, y, holes, pairs, visited)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean loops (int x, int y, Wormhole[] holes, int[] pairs, HashSet<String> visited) {
		int minDist = Integer.MAX_VALUE;
		int nextHoleIx = -1;
		for (int i = 0; i < holes.length; i++) {
			if (holes[i].x > x && holes[i].y == y) {
				if (holes[i].x < minDist) {
					minDist = holes[i].x;
					nextHoleIx = i;
				}
			}
		}
		if (nextHoleIx == -1) {
			return false;
		}
		else {
			Wormhole nextHole = holes[pairs[nextHoleIx]];
			if (visited.contains(nextHole.x + "_" + nextHole.y)) {
				return true;
			}
			else {
				visited.add(nextHole.x + "_" + nextHole.y);
				return loops(nextHole.x, nextHole.y, holes, pairs, visited);
			}
		}
	}
	
	public static boolean everyoneIsPaired (int[] pairs) {
		for (int i : pairs) {
			if (i == -1) return false;
		}
		return true;
	}
	
	static class Wormhole {
		String name;
		Wormhole pair;
		int x;
		int y;
		
		public Wormhole (String n, int x, int y) {
			name = n;
			this.x = x;
			this.y = y;
		}
		
		public String toString () {
			return name;
		}
	}
}
