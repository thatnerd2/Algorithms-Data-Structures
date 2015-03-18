package contests.usaco;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;

/*
ID: thatner1
LANG: JAVA
TASK: castle
 */
class castle {
	/*BROKEN BUT NOT SURE WHY*/
	public static void main (String [] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("castle.in"));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("castle.out")));
		
		String[] parts = reader.readLine().split(" ");
		int numCols = Integer.parseInt(parts[0]);
		int numRows = Integer.parseInt(parts[1]);
		Square[][] map = new Square[numRows][numCols];
		for (int i = 0; i < numRows; i++) {
			String[] in = reader.readLine().split(" ");
			for (int j = 0; j < in.length; j++) {
				System.out.print(i + "," + j + ": ");
				map[i][j] = new Square(Integer.parseInt(in[j]));
			}
		}
		
		boolean[][] explored = new boolean[numRows][numCols];
		for (int i = 0; i < explored.length; i++) {
			Arrays.fill(explored[i], false);
		}
		
		int maxSize = 0;
		int numRooms = 0;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (!explored[i][j]) {
					maxSize = Math.max(maxSize, getSize(i, j, explored, map));
					numRooms++;
					printExplored(explored);
					System.out.println();
					System.out.println("SIZE " + maxSize);
				}
			}
		}
		System.out.println("largest: " + maxSize);
		System.out.println("Num rooms: " + numRooms);
		reader.close();
		writer.close();
	}
	
	public static void printExplored (boolean[][] explored) {
		for (int i = 0; i < explored.length; i++) {
			for (int j = 0; j < explored[i].length; j++) {
				if (explored[i][j]) System.out.print(1 + " ");
				else System.out.print(0 + " ");
			}
			System.out.println();
		}
	}
	
	public static int getSize (int r, int c, boolean[][] explored, Square[][] map) {
		LinkedList<int[]> toVisit = new LinkedList<int[]>();
		toVisit.add(new int[] {r, c});
		int size = 0;
		while (!toVisit.isEmpty()) {
			int[] pos = toVisit.poll();
			explored[pos[0]][pos[1]] = true;
			if (!map[pos[0]][pos[1]].walls[0] && !explored[r + 1][c]) {
				System.out.println("Added south");
				toVisit.add(new int[] {r + 1, c});
			}
			if (!map[pos[0]][pos[1]].walls[1] && !explored[r][c + 1]) {
				System.out.println("Added east");
				toVisit.add(new int[] {r, c + 1});
			}
			if (!map[pos[0]][pos[1]].walls[2] && r > 0 && !explored[r - 1][c]) {
				System.out.println("Added north");
				toVisit.add(new int[] {r - 1, c});
			}
			if (!map[pos[0]][pos[1]].walls[3] && c > 0 && !explored[r][c - 1]) {
				System.out.println("Added west");
				toVisit.add(new int[] {r, c - 1});
			}
			size++;
		}
		return size;
	}
	
	static class Square {
		boolean[] walls;
		//S, E, N, W
		
		public Square (int d) {
			int[] vals = {8, 4, 2, 1};
			walls = new boolean[4];
			for (int i = 0; i < vals.length; i++) {
				if (d >= vals[i]) {
					walls[i] = true;
					d -= vals[i];
				}
			}
			System.out.println(Arrays.toString(walls));
		}
		
	}
}
