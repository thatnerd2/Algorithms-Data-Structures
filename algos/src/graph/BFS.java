package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;


public class BFS {
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
		
		Queue<Node> q = new LinkedList<Node>();
		HashSet<Node> explored = new HashSet<Node>();
		HashMap<String, Integer> distances = new HashMap<String, Integer>();
		distances.put(s.name, 0);
		explored.add(s);
		q.add(s); //Start at s
		while (!q.isEmpty()) {
			Node g = q.poll();
			/* Do whatever you need to do */
			System.out.println("EXPLORED " + g.name);
			
			/* Look for next nodes */
			ArrayList<Node> neighbors = g.neighbors;
			for (Node neighbor : neighbors) {
				if (!explored.contains(neighbor)) {
					explored.add(neighbor);
					distances.put(neighbor.name, distances.get(g.name) + 1);
					q.add(neighbor);
				}
			}
		}
		
		System.out.println("Distance from s to E: " + distances.get("E"));
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
