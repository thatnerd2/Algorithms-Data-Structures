package contests.usaco.notprintedyet;
/*
ID: thatner1
LANG: JAVA
TASK: maze1
 */


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

class maze1 {
	public static void main (String [] args) throws IOException {
		
		BufferedReader reader = new BufferedReader(new FileReader("maze1.in"));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("maze1.out")));
		String[] parts = reader.readLine().split(" ");
		int w = Integer.parseInt(parts[0]);
		int h = Integer.parseInt(parts[1]);
		
		int[][] maze = new int[2*h + 1][2*w + 1];
		for (int i = 0; i < maze.length; i++) {
			String line = reader.readLine();
			//System.out.println(line);
			char[] in = line.toCharArray();
			for (int j = 0; j < in.length; j++) {
				maze[i][j] = (in[j] == ' ') ? 0 : 1;
			}
		}
		
		int maxDist = 0;
		for (int i = 1; i < maze.length - 1; i++) {
			for (int j = 1; j < maze[i].length - 1; j++) {
				if (maze[i][j] == 0) {
					int path = BFS(i, j, maze);
					//System.out.println("At (" + i + "," + j + ") I got " + path);
					maxDist = Math.max(path, maxDist);
				}
			}
		}
		System.out.println(maxDist / 2);
		writer.write((maxDist/2) + "\n");
		
		reader.close();
		
		writer.close();
	}
	
	public static int BFS (int startR, int startC, int[][] maze) {
		LinkedList<Integer> nextR = new LinkedList<Integer>();
		LinkedList<Integer> nextC = new LinkedList<Integer>();
		LinkedList<Integer> nextSteps = new LinkedList<Integer>();
		boolean[][] visited = new boolean[maze.length][maze[0].length];
		
		nextSteps.add(1);
		int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
		nextR.add(startR);
		nextC.add(startC);
		while (!nextR.isEmpty()) {
			int r = nextR.poll();
			int c = nextC.poll();
			
			//System.out.println("At " + r + "," + c);
			int steps = nextSteps.poll();
			//visited[r][c] = true;
			for (int i = 0; i < dirs.length; i++) {
				int rr = r + dirs[i][0];
				int cc = c + dirs[i][1];
				if (rr > maze.length - 1 || rr < 0 || cc > maze[0].length - 1 || cc < 0) {
					return steps;
				}
				if (maze[rr][cc] == 0 && !visited[rr][cc]) {
					//System.out.println("Adding " + rr + "," + cc);
					nextR.add(rr);
					nextC.add(cc);
					nextSteps.add(steps + 1);
					visited[rr][cc] = true;
				}
			}
			
		}
		return -1;
	}
}
