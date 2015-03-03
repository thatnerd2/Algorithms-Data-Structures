package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class DFS {
	public static void main (String [] args) {
		String[] names = {"S", "A", "B", "C", "D", "E"};
		HashMap<String, String[]> connections = new HashMap<String, String[]>();
		connections.put("S", new String[] {"A", "B"});
		connections.put("A", new String[] {"S", "C"});
		connections.put("B", new String[] {"C", "D", "S"});
		connections.put("C", new String[] {"D", "A", "B"});
		connections.put("D", new String[] {"C", "E", "B"});
		connections.put("E", new String[] {"C", "D"});
		
		ArrayList<Node> graph = buildGraph(names, connections);
		Node s = graph.get(0);
		
		HashSet<Node> explored = new HashSet<Node>();
		dfs(explored, s);
		//Otherwise, use a recursive algorithm
	}
	
	public static void dfs (HashSet<Node> explored, Node v) {
		explored.add(v);
		System.out.println("Explored " + v.name);
		for (Node g : v.neighbors) {
			if (!explored.contains(g)) {
				dfs(explored, g);
			}
		}
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
