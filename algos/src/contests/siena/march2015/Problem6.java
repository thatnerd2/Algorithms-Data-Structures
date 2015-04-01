package contests.siena.march2015;

import java.util.*;

// Remember to make sure the name of the file & class match!
public class Problem6 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int K = sc.nextInt();
		sc.nextLine();
		for (int trialCount = 0; trialCount < K; trialCount++) {
			int[][] costs = new int[10][10];
			Node[][] nodes = new Node[10][10];

			// Create a 2D array of Nodes (a class I made) to conduct Dijkstra's
			// Shortest Path Algo on.
			// Create a 2D cost matrix that shows the cost from Node to Node.
			for (int i = 0; i < 10; i++) {
				String[] parts = sc.nextLine().split(" ");
				for (int j = 0; j < costs.length; j++) {
					Node node = new Node(i + "_" + j);
					nodes[i][j] = node;
					costs[i][j] = Integer.parseInt(parts[j]);
				}
			}

			// Relate the nodes in the 2D array by establishing "neighbor"
			// relationships with costs.
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					// Avoid out of bounds errors with if statements.
					if (j < 9) {
						nodes[i][j].addNeighbor(nodes[i][j + 1],
								costs[i][j + 1]);
					}
					if (i < 9) {
						nodes[i][j].addNeighbor(nodes[i + 1][j],
								costs[i + 1][j]);
					}
					if (i > 0) {
						nodes[i][j].addNeighbor(nodes[i - 1][j],
								costs[i - 1][j]);
					}
					if (j > 0) {
						nodes[i][j].addNeighbor(nodes[i][j - 1],
								costs[i][j - 1]);
					}

				}
			}

			// We always start at 0,0 and end at 9,9
			Node start = nodes[0][0];
			Node dest = nodes[9][9];

			// Compute the answer using Dijkstra's Path Algo
			int ans = dijkstra(start, dest);
			System.out.println(ans);
		}
		sc.close();
	}

	public static int dijkstra(Node start, Node dest) {
		start.score = 0;
		// Use a Heap data structure for efficiency in calculating order of
		// nodes to check.
		// We check new nodes by least cost order for efficiency purposes.
		PriorityQueue<Node> queue = new PriorityQueue<Node>(10,
				new Comparator<Node>() {
					public int compare(Node a, Node b) {
						// Least cost comes first!
						return a.score - b.score;
					}
				});

		// Keep a list of explored nodes already to avoid infinite loops.
		HashSet<Node> explored = new HashSet<Node>();
		queue.add(start);

		// This while loop runs until we find the destination.
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			explored.add(node);
			
			
			// If we find the answer, return the overall cost to get there (which is the solution)
			if (node.equals(dest)) {
				return node.score;
			}
			
			
			// Otherwise, add the node's neighbors to the queue for further checking.
			for (int i = 0; i < node.neighbors.size(); i++) {
				
				Node neighbor = node.neighbors.get(i);
				int cost = node.costs.get(i);
				if (cost + node.score < neighbor.score) {
					// The cost of getting to this neighbor node is the cost it took to get to our current node
					// plus the cost it takes to get from the current node to the neighbor node.
					
					// Search "Dijkstra's Algorithm" to find out more.  I highly recommend Tim Roughgarden's
					// explanation on Coursera's "Algorithm Design & Analysis Part 1" presented by Stanford U
					neighbor.score = cost + node.score;
					queue.add(neighbor);
				}
			}
		}
		// Returns a huge number if impossible.
		return Integer.MAX_VALUE;
	}

	static class Node {
		// Each Node keeps track of a name, a list of neighbors, a list of costs for those neighbors, and
		// a score for special use by the algorithm.  Initially the score is infinity.
		int score;
		String name;
		ArrayList<Node> neighbors;
		ArrayList<Integer> costs;

		public Node(String n) {
			name = n;
			neighbors = new ArrayList<Node>();
			costs = new ArrayList<Integer>();
			score = Integer.MAX_VALUE;
		}

		public void addNeighbor(Node n, int c) {
			neighbors.add(n);
			costs.add(c);
		}

		public String toString() {
			return name + " : " + neighbors.size();
		}
	}

}
