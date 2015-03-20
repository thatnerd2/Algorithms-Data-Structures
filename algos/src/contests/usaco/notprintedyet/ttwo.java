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
TASK: ttwo
 */

/*
 * A pair of cows is loose somewhere in the forest. Farmer John is lending his 
 * expertise to their capture. Your task is to model their behavior.

The chase takes place on a 10 by 10 planar grid. Squares can be empty or they can contain:

an obstacle,
the cows (who always travel together), or
Farmer John.

The cows and Farmer John can occupy the same square (when they `meet') but neither the cows nor Farmer John can share a square with an obstacle.
Each square is represented as follows:

. Empty square
* Obstacle
C Cows
F Farmer
Here is a sample grid:
*...*.....
......*...
...*...*..
..........
...*.F....
*.....*...
...*......
..C......*
...*.*....
.*.*......
The cows wander around the grid in a fixed way. Each minute, they either move forward or rotate. 
Normally, they move one square in the direction they are facing. If there is an obstacle in the way 
or they would leave the board by walking `forward', then they spend the entire minute rotating 
90 degrees clockwise.

Farmer John, wise in the ways of cows, moves in exactly the same way.
The farmer and the cows can be considered to move simultaneously during each minute. 
If the farmer and the cows pass each other while moving, they are not considered to have met. 
The chase ends when Farmer John and the cows occupy the same square at the end of a minute.

Read a ten-line grid that represents the initial state of the cows, Farmer John, and obstacles. 
Each of the ten lines contains exactly ten characters using the coding above. There is guaranteed 
to be only one farmer and one pair of cows. The cows and Farmer John will not initially be on the same square.

Calculate the number of minutes until the cows and Farmer John meet. Assume both the cows and 
farmer begin the simulation facing in the `north' direction. Print 0 if they will never meet.
 */
class ttwo {
	public static void main (String [] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("ttwo.in"));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("ttwo.out")));
		
		char[][] map = new char[10][10];
		Agent[] agents = new Agent[2];
		for (int i = 0; i < map.length; i++) {
			map[i] = reader.readLine().toCharArray();
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] == 'F') {
					agents[0] = new Agent(i, j, 0);
				}
				else if (map[i][j] == 'C') {
					agents[1] = new Agent(i, j, 0);
				}
			}
		}
		
		int i;
		for (i = 0; i < 10000; i++) {
			moveAgents(agents, map);
			if (agents[0].touching(agents[1])) {
				break;
			}
		}
		i += 1;
		if (i >= 10000) i = 0;
		
		writer.write(i + "\n");
		System.out.println("i: " + i);
		System.out.println("F: " + agents[0]);
		System.out.println("C: " + agents[1]);
		reader.close();
		
		writer.close();
	}
	
	public static void moveAgents (Agent[] agents, char[][] map) {
		for (int i = 0; i < agents.length; i++) {
			if (agents[i].canMove(map)) {
				agents[i].r = agents[i].nextR();
				agents[i].c = agents[i].nextC();
			}
			else {
				agents[i].rotate();
			}
		}
	}
	
	static class Agent {
		int r;
		int c;
		int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
		int dir;
		
		public Agent (int r, int c, int dirIx) {
			this.r = r;
			this.c = c;
			this.dir = dirIx;
		}
		
		public boolean canMove (char[][] map) {
			int nextR = nextR();
			int nextC = nextC();
			if (nextR >= map.length || nextR < 0) return false;
			if (nextC >= map[0].length || nextC < 0) return false;
			return (map[nextR][nextC] != '*');
		}
		
		public int nextR () {
			return r + dirs[dir][0];
		}
		
		public int nextC () {
			return c + dirs[dir][1];
		}
		
		public boolean touching (Agent b) {
			return (r == b.r && c == b.c);
		}
		
		public void rotate () {
			dir += 1;
			dir %= dirs.length;
		}
		
		public String toString () {
			return r + " " + c;
		}
	}
}
