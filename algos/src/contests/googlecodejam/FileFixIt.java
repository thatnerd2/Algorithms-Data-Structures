package contests.googlecodejam;

import java.util.ArrayList;
import java.util.Arrays;

public class FileFixIt {
	public static void main (String [] args) {
		String[] existing = {"/chicken", "/chicken/egg"};
		String[] toMake = {"/chicken"};
		
		int ans = fileFixIt(existing, toMake);
		System.out.println("Answer: " + ans);
	}
	
	public static int fileFixIt (String[] existing, String[] toMake) {
		Node root = new Node("ROOT", null);
		for (int i = 0; i < existing.length; i++) {
			String[] parts = existing[i].substring(1).split("/");
			System.out.println(Arrays.toString(parts));
			root.addChild(parts, 0);
		}
		
		int sum = 0;
		for (int i = 0; i < toMake.length; i++) {
			String[] parts = toMake[i].substring(1).split("/");
			sum += root.addChild(parts, 0);
		}
		
		return sum;
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
		Node parent;
		ArrayList<Node> children;
		String name;
		
		public Node (String n, Node par) {
			name = n;
			parent = par;
			children = new ArrayList<Node>();
		}
		
		public int addChild (String[] parts, int ix) {
			if (ix == parts.length) return 0;
			
			Node existing = getChild(parts[ix]);
			if (existing != null) {
				return existing.addChild(parts, ix + 1);
			}
			else {
				Node newNode = new Node(parts[ix], this);
				children.add(newNode);
				return 1 + newNode.addChild(parts,  ix + 1);
			}
		}
		
		public Node getChild (String name) {
			for (Node child : children) {
				if (child.name.equals(name)) {
					return child;
				}
			}
			return null;
		}
	}
}
