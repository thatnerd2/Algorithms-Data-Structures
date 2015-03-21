package contests.usaco.notprintedyet;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

/*
ID: thatner1
LANG: JAVA
TASK: butter
 */

/* Nuts multi level Dijkstra with Heap.  Needed to be ultra memoized where memoization happens
 * in the dijkstra method.
 */
class butter {
	static HashMap<String, Integer> memo = new HashMap<String, Integer>();
	
	public static void main (String [] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("butter.in"));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("butter.out")));
		
		String[] vars = reader.readLine().split(" ");
		int numPastures = Integer.parseInt(vars[1]);
		int numCows = Integer.parseInt(vars[0]);
		int numConns = Integer.parseInt(vars[2]);
		
		ArrayList<Node> nodes = new ArrayList<Node>();
		for (int i = 0; i < numPastures; i++) {
			nodes.add(new Node(i + 1));
		}
		
		ArrayList<Node> starts = new ArrayList<Node>();
		for (int i = 0; i < numCows; i++) {
			int id = Integer.parseInt(reader.readLine());
			starts.add(nodeByName(id, nodes));
		}
		
		
		for (int i = 0; i < numConns; i++) {
			String[] parts = reader.readLine().split(" ");
			Node a = nodeByName(Integer.parseInt(parts[0]), nodes);
			Node b = nodeByName(Integer.parseInt(parts[1]), nodes);
			int cost = Integer.parseInt(parts[2]);
			a.addNeighbor(b, cost);
			b.addNeighbor(a, cost);
		}
		
		int minDist = Integer.MAX_VALUE;
		for (int i = 0; i < nodes.size(); i++) {
			int dist = 0;
			Node candidate = nodes.get(i);
			//System.out.println("WHAT HAPPENS IF WE PUT THE SUGAR AT " + nodes.get(i).id);
			for (int j = 0; j < starts.size(); j++) {
				Node start = starts.get(j);
				if (candidate.id != start.id) {
					String key = (candidate.id < start.id) ? candidate.id + "_" + start.id : start.id + "_" + candidate.id;
					if (memo.containsKey(key)) {
						dist += memo.get(key);
						continue;
					}
					
					//System.out.println("DIST FROM " + candidate.id + " TO " + start.id);
					
					
					int shortest = shortestPath(start, nodes.get(i));
					dist += shortest;
					
					for (Node n : nodes) n.score = Integer.MAX_VALUE;
				}
			}
			
			//System.out.println("--------THIS DIST: " + dist);
			if (dist < minDist) {
				minDist = dist;
			}
		}
		
		System.out.println("BEST: " + minDist);
		writer.write(minDist + "\n");
		reader.close();
		
		writer.close();
		
	}
	
	public static int shortestPath (Node start, Node dest) {
		PriorityQueue<Node> q = new PriorityQueue<Node>(10, new Comparator <Node>() {
			public int compare (Node a, Node b) {
				return a.score - b.score;
			}
		});
		
		
		start.score = 0;
		//System.out.println("STARTING AT " + start.id + " TRYING TO GET TO " + dest.id);
		q.add(start);
		
		while (!q.isEmpty()) {
			Node thisNode = q.poll();
			String key = (thisNode.id < start.id) ? thisNode.id + "_" + start.id : start.id + "_" + thisNode.id;
			memo.put(key, thisNode.score);
			
			if (thisNode.equals(dest)) {
				int res = thisNode.score;
				//System.out.println("returning " + res);
				return res;
			}
			
			for (int i = 0; i < thisNode.neighbors.size(); i++) {
				int cost = thisNode.costs.get(i);
				Node neighbor = thisNode.neighbors.get(i);
				if (cost + thisNode.score < neighbor.score) {
					//System.out.println("Set " + neighbor.id + "'s score: " + (cost + thisNode.score));
					neighbor.score = cost + thisNode.score;
					q.add(neighbor);
				}
			}
		}
		
		System.out.println("IMPOSSIBLE from " + start.id + " to " + dest.id);
		return Integer.MAX_VALUE;
	}
	
	public static Node nodeByName (int name, ArrayList<Node> nodes) {
		for (Node n : nodes) {
			if (n.id == name) return n;
		}
		return null;
	}
	
	static class Node {
		int id;
		ArrayList<Node> neighbors;
		ArrayList<Integer> costs;
		int score;
		
		public Node (int i) {
			id = i;
			neighbors = new ArrayList<Node>();
			costs = new ArrayList<Integer>();
			score = Integer.MAX_VALUE;
		}
		
		public void addNeighbor (Node n, int cost) {
			neighbors.add(n);
			costs.add(cost);
		}
	}
}
