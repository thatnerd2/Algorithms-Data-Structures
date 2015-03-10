package contests.siena;

import java.util.ArrayList;
import java.util.Scanner;

public class Problem_6_Word_Search {
	/*public static void main (String [] args) {
		Scanner scan = new Scanner (System.in);
		
		int rows = scan.nextInt();
		scan.nextLine();
		String[] board = new String[rows];
		for (int i = 0; i < rows; i++) {
			board[i] = scan.nextLine();
		}
		
		int numFind = scan.nextInt();
		scan.nextLine();
		String[] find = new String[numFind];
		for (int i = 0; i < numFind; i++) {
			find[i] = scan.nextLine();
		}
		
		String[] board = {"BOB","AAA", "BOB"};
		String[] find = {"BOB", "BAB"};
		
		Node root = new Node(null, null);
		
		for (int i = 0; i < board.length; i++) {
			root.addWord(board[i], 0);
			root.addWord(reverse(board[i]), 0);
			StringBuilder vertical = new StringBuilder ();
			
			for (int j = 0; j < board.length; j++) {
				vertical.append(board[j].charAt(i));
			}
			root.addWord(vertical.toString(), 0);
			root.addWord(reverse(vertical.toString()), 0);
		}
		
		
		//Diagonals now
		for (int i = 0; i < )
		
		
		
		for (int i = 0; i < find.length; i++) {
			System.out.println(find[i] + " : " + root.countWord(find[i], 0));
		}
		
		//scan.close();
	}
	
	private static String reverse (String s) {
		return (new StringBuilder(s)).reverse().toString();
	}
	
	static class Node {
		Character c;
		Node parent;
		ArrayList<Node> children;
		int count;
		
		public Node (Character c, Node parent) {
			this.c = c;
			this.parent = parent;
			children = new ArrayList<Node>();
			count = 1;
		}
		
		public void addChild (Node n) {
			children.add(n);
		}
		
		public void addWord (String w, int ix) {
			count++;
			if (ix == w.length()) return;
			
			Node n = indexOfChar(w.charAt(ix));
			if (n == null) {
				n = new Node (w.charAt(ix), this);
				addChild(n);
			}
			
			
			//System.out.println(getUpperParent() + ": " + count);
			n.addWord(w,  ix + 1);
			
			
		}
		
		public int countWord (String w, int ix) {
			if (ix == w.length()) {
				return count - 1; //not sure why I need the - 1 but I need it
			}
			else {
				Node next = indexOfChar(w.charAt(ix));
				if (next == null) {
					return 0;
				} else {
					return next.countWord(w, ix + 1);
				}
			}
		}
		
		private String getUpperParent () {
			if (c == null) {
				return "";
			}
			else {
				return c + parent.getUpperParent();
			}
		}
		
		private Node indexOfChar (Character c) {
			for (int i = 0; i < children.size(); i++) {
				if (children.get(i).c.equals(c)) {
					return children.get(i);
				}
			}
			return null;
		}
	}*/
}