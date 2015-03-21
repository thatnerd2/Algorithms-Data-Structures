package contests.usaco.notprintedyet;
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
TASK: agrinet
 */
/* PRIM MINIMUM SPANNING TREE WITH ADJ MATRIX AND DIST MATRIX */
/*
 * Farmer John has been elected mayor of his town! One of his campaign promises 
 * was to bring internet connectivity to all farms in the area. He needs your help, of course.

Farmer John ordered a high speed connection for his farm and is going to share his 
connectivity with the other farmers. To minimize cost, he wants to lay the minimum 
amount of optical fiber to connect his farm to all the other farms.


Given a list of how much fiber it takes to connect each pair of farms, you must find the 
minimum amount of fiber needed to connect them all together. Each farm must connect to 
some other farm such that a packet can flow from any one farm to any other farm.

The distance between any two farms will not exceed 100,000.
 */
class agrinet {
	public static void main (String [] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("agrinet.in"));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("agrinet.out")));
		
		int numFarms = Integer.parseInt(reader.readLine());
		int[][] dist = new int[numFarms][numFarms];
		for (int i = 0; i < numFarms; i++) {
			int len = 0;
			while (len < numFarms) {
				String line = reader.readLine();
				String[] in = line.split(" ");
				for (int j = 0; j < in.length; j++) {
					dist[i][len] = Integer.parseInt(in[j]);
					len++;
				}
			}
			//System.out.println(Arrays.toString(dist[i]));
		}
		
		for (int[] b : dist) {
			System.out.println(Arrays.toString(b));
		}
		
		boolean[] conn = new boolean[numFarms]; 
		for (int i = 0; i < conn.length; i++) {
			conn[i] = false;
		}
		
		conn[0] = true;
		int totalDist = 0;
		while (hasFalse(conn)) {
			int minDistToNew = Integer.MAX_VALUE;
			int connectTo = -1;
			for (int i = 0; i < conn.length; i++) {
				for (int j = 0; j < dist.length; j++) {
					if (conn[i] && !conn[j] && dist[i][j] < minDistToNew) {
						connectTo = j;
						minDistToNew = dist[i][j];
					}
				}
			}
			conn[connectTo] = true;
			totalDist += minDistToNew;
		}
		System.out.println(totalDist);
		writer.write(totalDist+"\n");
		reader.close();
		
		
		writer.close();
	}
	
	public static boolean hasFalse (boolean[] b) {
		for (boolean c : b) 
			if (!c) return true;
		return false;
	}
}
