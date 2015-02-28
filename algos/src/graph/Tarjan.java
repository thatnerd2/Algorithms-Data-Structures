package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class Tarjan {
	static ArrayList<ArrayList<Node>> components;
	public static void main (String [] args) {
		String[] names = {"A", "B", "C", "D"};
		HashMap<String, String> conns = new HashMap<String, String>();
		conns.put("A", "B C");
		conns.put("B", "C A");
		conns.put("C", "A B D");
		
		
		ArrayList<Node> nodes = new ArrayList<Node>();
		for (int i = 0; i < names.length; i++) {
			Node node = new Node(names[i]);
			nodes.add(node);
		}
		
		for (String key : conns.keySet()) {
			Node from = nodeByName(key, nodes);
			String[] toNames = conns.get(key).split(" ");
			for (int i = 0; i < toNames.length; i++) {
				from.addNeighbor(nodeByName(toNames[i], nodes));
			}
		}
		
		//Now we have graph.  Execute algorithm
		int next = 0;
		components = new ArrayList<ArrayList<Node>>();
		Stack<Node> stack = new Stack<Node>();
		for (int i = 0; i < nodes.size(); i++) {
			if (nodes.get(i).index == -1) {
				strongConnect(nodes.get(i), next, stack);
			}
		}
		System.out.println(components);
	}
	
	public static int strongConnect (Node node, int next, Stack<Node> stack) {
		node.index = next;
		node.lowlink = next;
		next++;
		
		stack.push(node);
		for (Node neighbor : node.neighbors) {
			if (neighbor.index == -1) {
				next = strongConnect(neighbor, next, stack);
				node.lowlink = Math.min(node.lowlink, neighbor.lowlink);
			}
			else if (stack.contains(node)) {
				node.lowlink = Math.min(node.lowlink, neighbor.index);
			}
		}
		
		if (node.lowlink == node.index) {
			/* We are at the root */
			Node offStack = null;
			ArrayList<Node> component = new ArrayList<Node>();
			do {
				offStack = stack.pop();
				component.add(offStack);
			} while (!offStack.equals(node));
			
			components.add(component);
		}
		
		return next;
	}
	
	public static Node nodeByName (String name, ArrayList<Node> nodes) {
		for (Node n : nodes) {
			if (n.name.equals(name)) {
				return n;
			}
		}
		return null;
	}
	
	static class Node {
		ArrayList<Node> neighbors;
		String name;
		int index;
		int lowlink;
		
		public Node (String name) {
			this.name = name;
			neighbors = new ArrayList<Node>();
			index = -1;
		}
		
		public void addNeighbor (Node n) {
			neighbors.add(n);
		}
		
		public String toString () {
			return name;
		}
	}
}
