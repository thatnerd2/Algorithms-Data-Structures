package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class TopologicalOrdering {
	static int ordering = 0;
	
	/* The greatest assigned number is the sinkhole */
	public static void main (String [] args) {
		String[] names = {"A", "B", "C", "D"};
		HashMap<String, String[]> connections = new HashMap<String, String[]>();
		connections.put("A", new String[] {"B", "C"});
		connections.put("B", new String[] {"D"});
		connections.put("C", new String[] {"D"});
		
		ArrayList<Node> graph = buildGraph(names, connections);
		ordering = graph.size();
		HashSet<Node> explored = new HashSet<Node>();
		for (int i = 0; i < graph.size(); i++) {
			if (!explored.contains(graph.get(i))) {
				DFS(graph.get(i), explored);
			}
		}
	}
	
	public static void DFS (Node n, HashSet<Node> explored) {
		
		explored.add(n);
		
		for (int j = 0; j < n.neighbors.size(); j++) {
			if (!explored.contains(n.neighbors.get(j))) {
				DFS(n.neighbors.get(j), explored);
			}
		}
		System.out.println("I, " + n.name +", was assigned " + ordering);
		ordering -= 1;
	}
	
	public static ArrayList<Node> buildGraph (String[] names, HashMap<String, String[]> conns) {
		ArrayList<Node> res = new ArrayList<Node>();
		for (int i = 0; i < names.length; i++) {
			Node g = new Node(names[i]);
			res.add(g);
		}
		
		for (String key : conns.keySet()) {
			String[] neighbors = conns.get(key);
			Node source = nodeByName(key, res);
			for (int i = 0; i < neighbors.length; i++) {
				Node g = nodeByName(neighbors[i], res);
				source.addNeighbor(g);
			}
		}
		
		return res;
	}
	
	public static Node nodeByName (String name, ArrayList<Node> nodes) {
		for (Node g : nodes) {
			if (name.equals(g.name)){
				return g;
			}
		}
		return null;
	}
	
	static class Node {
		String name;
		ArrayList<Node> neighbors;
		
		public Node (String n) {
			name = n;
			neighbors = new ArrayList<Node>();
		}
		
		public void addNeighbor (Node n) {
			neighbors.add(n);
		}
		
		public String toString () {
			return name;
		}
	}
}
