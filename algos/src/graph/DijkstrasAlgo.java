package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public class DijkstrasAlgo {
	public static void main (String [] args) {
		ArrayList<Node> nodes = new ArrayList<Node>();
		for (int i = 0; i < 8; i++) {
			nodes.add(new Node("" + (char)(65 + i)));
		}
		
		HashMap<String, String> connections = new HashMap<String, String>();
		connections.put("A", "B.4 C.1");
		connections.put("B", "C.1 A.4 F.3 E.21");
		connections.put("C", "A.1 D.99 B.1");
		connections.put("D", "B.99 H.1");
		connections.put("E", "G.11 H.10 B.21");
		connections.put("F", "B.3 G.7");
		connections.put("G", "F.7 E.11");
		connections.put("H", "D.1 E.10");
		for (String name : connections.keySet()) {
			String connection = connections.get(name);
			String[] edges = connection.split(" ");
			Node source = nodeByName(name, nodes);
			for (String edge : edges) {
				String[] details = edge.split("\\.");
				Node dest = nodeByName(details[0], nodes);
				int cost = Integer.parseInt(details[1]);
				source.addNeighbor(dest, cost);
			}
		}
		
		
		/* BEGIN ALGORITHM */
		
		Node start = nodeByName("A", nodes);
		Node dest = nodeByName("D", nodes);
		/* Start at A, get to D.*/
		ArrayList<Node> shortestPath = getShortestPath(start, dest);
		System.out.println("Shortest path from a to d is: " + shortestPath);
		System.out.println("Shortest distance is: " + shortestPath.get(shortestPath.size() - 1).dijkstraScore);
	}
	
	public static ArrayList<Node> getShortestPath (Node start, Node dest) {
		/**
		 * Two qualities you need to know:
		 * BFS finds shortest paths to all nodes if distances are 1
		 * Add dijkstra score and Priority Queue (heap) to BFS to make it Dijkstra's algo 
		 */
		
		start.setDijkstraScore(0, null);
		PriorityQueue<Node> q = new PriorityQueue<Node>(10, new Comparator<Node> () {
			public int compare(Node o1, Node o2) {
				return o1.dijkstraScore - o2.dijkstraScore;
			}
		});
		HashSet<Node> explored = new HashSet<Node>();
		q.add(start);
		while (!q.isEmpty()) {
			Node n = q.poll();
			if (n.equals(dest)) {
				/* WE GOT THERE - TRACE BACK THE PATH */
				Node prevNode = n;
				ArrayList<Node> path = new ArrayList<Node>();
				while (prevNode != null) {
					path.add(prevNode);
					prevNode = prevNode.previousNode;
				}
				
				Collections.reverse(path);
				return path;
				
			}
			
			for (int i = 0; i < n.neighbors.size(); i++) {
				Node neighbor = n.neighbors.get(i);
				if (!explored.contains(neighbor)) {
					int neighborCost = n.costs.get(i);
					neighbor.setDijkstraScore(neighborCost + n.dijkstraScore, n);
					q.add(neighbor);
				}
			}
			
			explored.add(n);
			
		}
		System.out.println("IMPOSSIBLE");
		return null;
	}
	
	public static Node nodeByName (String name, ArrayList<Node> nodes) {
		for (int i = 0; i < nodes.size(); i++) {
			if (nodes.get(i).name.equals(name)) {
				return nodes.get(i);
			}
		}
		return null;
	}
	static class Node {
		String name;
		ArrayList<Integer> costs;
		ArrayList<Node> neighbors;
		int dijkstraScore;
		Node previousNode;
		
		public Node (String n) {
			name = n;
			neighbors = new ArrayList<Node>();
			costs = new ArrayList<Integer>();
		}
		
		public void addNeighbor (Node n, int cost) {
			neighbors.add(n);
			costs.add(cost);
		}
		
		public void setNeighbors (ArrayList<Node> n, ArrayList<Integer> c) {
			neighbors = n;
			costs = c;
		}
		
		public void setDijkstraScore (int n, Node prevNode) {
			dijkstraScore = n;
			previousNode = prevNode;
		}
		
		public String toString () {
			return name;
		}
	}
}