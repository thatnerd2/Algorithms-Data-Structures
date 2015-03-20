package contests.usaco.notprintedyet;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

/*
ID: thatner1
LANG: JAVA
TASK: comehome
 */

/* DIJKSTRA'S ALGO
 * Between milkings, each cow is located in her own pasture, though some pastures have no cows in them.
 *  Each pasture is connected by a path to one or more other pastures (potentially including itself). 
 *  Sometimes, two (potentially self-same) pastures are connected by more than one path. One or more 
 *  of the pastures has a path to the barn. Thus, all cows have a path to the barn and they always know 
 *  the shortest path. Of course, cows can go either direction on a path and they all walk at the same speed.

The pastures are labeled `a'..`z' and `A'..`Y'. One cow is in each pasture labeled with a capital letter.
 No cow is in a pasture labeled with a lower case letter. The barn's label is `Z'; no cows are in the barn, though.

PROGRAM NAME: comehome

INPUT FORMAT

Line 1:	Integer P (1 <= P <= 10000) the number of paths that interconnect the pastures (and the barn)
Line 2..P+1:	Space separated, two letters and an integer: the names of the interconnected pastures/barn 
and the distance between them (1 <= distance <= 1000)
SAMPLE INPUT (file comehome.in)

5
A d 6
B d 3
C e 9
d Z 8
e Z 3
 */
class comehome {
	public static void main (String [] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("comehome.in"));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("comehome.out")));
		
		ArrayList<Node> nodes = new ArrayList<Node>();
		ArrayList<Node> starts = new ArrayList<Node>();
		int conns = Integer.parseInt(reader.readLine());
		for (int i = 0; i < conns; i++) {
			String[] parts = reader.readLine().split(" ");
			Node A = nodeByName(parts[0], nodes);
			Node B = nodeByName(parts[1], nodes);
			if (A == null) {
				A = new Node (parts[0]);
				nodes.add(A);
			}
			
			if (parts[0].charAt(0) < 'Z') {
				starts.add(A);
			}
			
			if (B == null) {
				B = new Node (parts[1]);
				nodes.add(B);
			}
			
			int distance = Integer.parseInt(parts[2]);
			A.addNeighbor(B, distance);
			B.addNeighbor(A, distance);
		}
		
		int minDist = Integer.MAX_VALUE - 10000;
		String res = "";
		Node end = nodeByName("Z", nodes);
		for (int i = 0; i < starts.size(); i++) {
			int dist = shortestPath(starts.get(i), end);
			if (dist < minDist) {
				minDist = dist;
				res = starts.get(i).name;
			}
		}
		writer.write(res + " " + minDist + "\n");
		reader.close();
		
		writer.close();
	}
	
	public static int shortestPath (Node A, Node B) {
		A.dScore = 0;
		PriorityQueue<Node> queue = new PriorityQueue<Node>(10, new Comparator<Node> () {
			public int compare (Node a, Node b) {
				return a.dScore - b.dScore;
			}
		});
		
		HashSet<Node> explored = new HashSet<Node>();
		queue.add(A);
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			explored.add(node);
			
			if (node.equals(B)) {
				return node.dScore;
			}
			
			for (int i = 0; i < node.neighbors.size(); i++) {
				Node neighbor = node.neighbors.get(i);
				int cost = node.costs.get(i);
				if (cost + node.dScore < neighbor.dScore) {
					neighbor.dScore = cost + node.dScore;
					queue.add(neighbor);
				}
			}
		}
		return Integer.MAX_VALUE;
	}
	
	public static Node nodeByName (String name, ArrayList<Node> nodes) {
		for (Node n : nodes) {
			if (n.name.equals(name)) return n;
		}
		return null;
	}
	
	static class Node {
		int dScore;
		String name;
		ArrayList<Node> neighbors;
		ArrayList<Integer> costs;
	
		public Node (String n){
			name = n;
			neighbors = new ArrayList<Node>();
			costs = new ArrayList<Integer>();
			dScore = Integer.MAX_VALUE;
		}
		
		public void addNeighbor (Node n, int c) {
			neighbors.add(n);
			costs.add(c);
		}
		
		public String toString () {
			return name + ": " +  neighbors.size();
		}
	}
}
