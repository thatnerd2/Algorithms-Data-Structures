package contests.topcoder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class TTC_R4_Div1_275 {
	/* Success!
	 * This one was a bit of a doozy.  I created a big ole graph from the input data, and then
	 * BFSed over and over from each node.  BFS can give distances based on UNWEIGHTED graphs, but
	 * with weighted graphs, it's a problem.  I guess the advantage that Dijkstra's algo has over BFS
	 * is that it doesn't need to compute from every single node.  It's a little bit more optimized.
	 * 
	 * So anyway, since BFS can give distances at least (even if they turn out wrong because of costs),
	 * I Math.max to find the biggest distance every time that I record a new distance from the source
	 * node to the new node.  That way, the distances that are in the Hash Map are all the biggest distances from
	 * the source node to each of the other nodes in the graph.  Then, I max the distances of every node to
	 * every other node, and max those maxed distances, and there's the answer!
	 * 
	 * I picked repetitive BFS because the constraints were low enough that speed wasn't really an issue.
	 * 
	 * Further research - can Dijkstra's algorithm be applied to find longest path?
	 */
	
	public static void main (String[] args) {
		/*String[] inputConnect = { "1 2", "2", "" };
		String[] inputCost = { "5 3", "7", "" };
		
		String[] inputConn2 = {"1 2 3 4 5","2 3 4 5","3 4 5","4 5","5",""};
		String[] inputCost2 = {"2 2 2 2 2","2 2 2 2","2 2 2","2 2","2",""};

		String[] inCon3 = {"1","2","3","","5","6","7",""};
		String[] inCos3 = {"2","2","2","","3","3","3",""};
		
		String[] inCon4 = {"","2 3 5","4 5","5 6","7","7 8","8 9","10","10 11 12","11","12","12",""};
		String[] inCos4 = {"","3 2 9","2 4","6 9","3","1 2","1 2","5","5 6 9","2","5","3",""};*/
		
		String[] inCon5 = {"","2 3","3 4 5","4 6","5 6","7","5 7",""};
		String[] inCos5 = {"","30 50","19 6 40","12 10","35 23","8","11 20",""};
		
		int longestPath = howLong (inCon5, inCos5);
		System.out.println("Answer: " + longestPath);
	}
	
	public static int howLong (String [] connects, String[] costs) {
		//I'll want a recursive method here.
		ArrayList<Node> nodes = new ArrayList<Node>();
		for (int i  = 0; i < connects.length; i++) {
			Node n = new Node("" + i);
			nodes.add(n);
		}
		
		for (int i = 0; i < connects.length; i++) {
			String[] connections = connects[i].split(" ");
			int[] connCosts = sArrToIntArr(costs[i].split(" "));
			Node thisNode = nodes.get(i);
			
			if (!connections[0].equals("")) {
				for (int j = 0; j < connections.length; j++) {
					int ix = ixByName(connections[j], nodes);
					thisNode.addNeighbor(nodes.get(ix), connCosts[j]);
				}
			}
			
		}
		
		/* DEBUGGING */
		for (int i = 0; i < nodes.size(); i++) {
			Node n = nodes.get(i);
			for (int j = 0; j < n.neighbors.size(); j++) {
				Node neighbor = n.neighbors.get(j);
				System.out.println(n.name + " maps to " + neighbor.name + " with cost: " + n.costs.get(j));
			}
		}
		
		/* END DEBUGGING */
		
		
		ArrayList<Integer> maxDistances = new ArrayList<Integer>();
		for (int i = 0; i < nodes.size(); i++) {
			//how to keep track of distances?  Answer: Hash map!
			HashMap<String, Integer> distances = new HashMap<String, Integer>();
			distances.put(nodes.get(i).name, 0);
			LinkedList<Node> toVisit = new LinkedList<Node>();
			toVisit.add(nodes.get(i));
			while (!toVisit.isEmpty()) {
				Node n = toVisit.poll();
				
				/**
				 * I'm exploiting BFS's distance finding, but since the distance to each node will be found
				 * many different ways, I'm only keeping track of the max value recorded.
				 */
				
				for (Node next : n.neighbors){
					if (distances.get(next.name) != null) {
						distances.put(next.name, Math.max(distances.get(n.name) + n.getCostTo(next), distances.get(next.name)));
						//System.out.println("\t " + Math.max(distances.get(n.name) + n.getCostTo(next), distances.get(next.name)) + " wins!");
					}
					else {
						distances.put(next.name, distances.get(n.name) + n.getCostTo(next));
					}
					
					
					toVisit.add(next);
				}
			}
			
			int thisMax = maxOf(distances);
			System.out.println("Starting from node: " + i + " I found a max of " + thisMax);
			maxDistances.add(thisMax);
		}
		
		return maxOf(maxDistances);
		
	}
	
	public static int maxOf(ArrayList<Integer> a) {
		int record = 0;
		for (Integer i : a) {
			record = Math.max(i, record);
		}
		return record;
	}
	
	public static int maxOf (HashMap<String, Integer> map) {
		int record = 0;
		for (String key : map.keySet()) {
			record = Math.max(record, map.get(key));
		}
		return record;
	}
	
	public static int[] sArrToIntArr (String[] a) {
		int[] res = new int[a.length];
		for (int i = 0; i < a.length; i++) {
			if (!a[i].equals("")) {
				res[i] = Integer.parseInt(a[i]);
			}
			
		}
		return res;
	}
	
	public static int ixByName (String name, ArrayList<Node> nodes) {
		for (int i = 0; i < nodes.size(); i++) {
			if (nodes.get(i).name.equals(name)) {
				return i;
			}
		}
		System.out.println("Didn't find it");
		return -1;
	}
	
	static class Node {
		String name;
		ArrayList<Node> neighbors;
		ArrayList<Integer> costs;
		
		public Node (String name) {
			this.name = name;
			costs = new ArrayList<Integer>();
			neighbors = new ArrayList<Node>();
		}
		
		public void setNeighbors (ArrayList<Node> neighbors) {
			this.neighbors = neighbors;
		}
		
		public void addNeighbor (Node n, int cost) {
			neighbors.add(n);
			costs.add(cost);
		}
		
		public int getCostTo (Node n) {
			for (int i = 0; i < neighbors.size(); i++) {
				if (neighbors.get(i).equals(n)) {
					return costs.get(i);
				}
			}
			return -1;
		}
	}
}
