package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class PrimsAlgoMST {
	public static void main (String [] args) {
		HashMap<String, String> conns = new HashMap<String, String>();
		ArrayList<Node> nodes = new ArrayList<Node>();
		String[] nodeNames = {"A", "B", "C", "D", "E", "F"};
		
		/* Space efficient way to specify connections */
		conns.put("A", "10_E 3_F 10_B");
		conns.put("B", "10_A 3_F 10_C");
		conns.put("C", "10_B 3_F 10_D");
		conns.put("D", "10_E 3_F 10_C");
		conns.put("E", "10_A 3_F 10_D");
		conns.put("F", "3_A 3_B 3_C 3_D 3_E");
		
		for (int i = 0; i < nodeNames.length; i++) {
			nodes.add(new Node(nodeNames[i]));
		}
		
		for (String key : conns.keySet()) {
			Node from = nodeByName(key, nodes);
			String[] tos = conns.get(key).split(" ");
			for (int j = 0; j < tos.length; j++) {
				int cost = Integer.parseInt(tos[j].split("_")[0]);
				String nodeName = tos[j].split("_")[1];
				from.addNeighbor(nodeByName(nodeName, nodes), cost);
			}
		}
		
		ArrayList<Node> mst = prim(nodes);
		System.out.println(mst);
	}
	
	public static ArrayList<Node> prim (ArrayList<Node> nodes) {
		ArrayList<Node> checking = new ArrayList<Node>();
		HashMap<String, String> conns = new HashMap<String, String>();
		HashSet<Node> explored = new HashSet<Node>();
		
		
		Node root = nodes.get(0);
		explored.add(root);
		checking.add(root);
		Node minNode = null;
		do {
			int minCost = Integer.MAX_VALUE;
			Node baseToMin = null;
			minNode = null;
			for (int i = 0; i < checking.size(); i++) {
				//Find lowest cost new node we haven't seen before
				Node base = checking.get(i);
				for (int j = 0; j < base.neighbors.size(); j++) {
					if (base.costs.get(j) < minCost && !explored.contains(base.neighbors.get(j))) {
						minCost = base.costs.get(j);
						minNode = base.neighbors.get(j);
						baseToMin = base;
						
					}
				}
			}
			
			/* Use new minimums info to create connection in tree */
			if (minNode != null) {
				System.out.println("MIN NODE: " + minNode + " FROM BASE " + baseToMin);
				if (conns.containsKey(baseToMin.name)) {
					conns.put(baseToMin.name, conns.get(baseToMin.name) + " " + minCost + "_" + minNode.name);
				}
				else {
					conns.put(baseToMin.name, minCost + "_" + minNode.name);
				}
				checking.add(minNode);
				explored.add(minNode);
			}
			
			
		} while (minNode != null);
		
		/* Create tree from new nodes without the extra edges */
		ArrayList<Node> ret = new ArrayList<Node>();
		for (Node node : nodes) {
			ret.add(new Node(node.name));
		}
		
		for (String key : conns.keySet()) {
			System.out.println(key + " SET TO " + conns.get(key));
			Node from = nodeByName(key, ret);
			String[] tos = conns.get(key).split(" ");
			for (String conn : tos) {
				Node to = nodeByName(conn.split("_")[1], ret);
				int cost = Integer.parseInt(conn.split("_")[0]);
				from.addNeighbor(to, cost);
				to.addNeighbor(from, cost);
			}
		}
		
		return ret;
	}
	
	public static Node nodeByName (String n, ArrayList<Node> nodes) {
		for (Node node : nodes) {
			if (node.name.equals(n)) {
				return node;
			}
		}
		return null;
	}
	
	static class Node {
		String name;
		ArrayList<Node> neighbors;
		ArrayList<Integer> costs;
		
		public Node (String n) {
			name = n;
			neighbors = new ArrayList<Node>();
			costs = new ArrayList<Integer>();
		}
		
		public Node clone () {
			Node ret = new Node(name);
			for (int i = 0; i < neighbors.size(); i++) {
				ret.addNeighbor(neighbors.get(i), costs.get(i));
			}
			return ret;
		}
		
		public void addNeighbor (Node n, int cost) {
			neighbors.add(n);
			costs.add(cost);
		}
		
		public String toString () {
			StringBuilder neighborNames = new StringBuilder();
			for (Node neighbor : neighbors) {
				neighborNames.append(neighbor.name + " ");
			}
			
			return name + ":" + neighborNames.toString() +"v " + costs;
		}
	}
}
