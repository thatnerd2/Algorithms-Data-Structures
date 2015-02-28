package topcoder;

import java.util.ArrayList;

public class SRM_150_Div1_1000 {
	/**
	 * Hmm, I have no idea.
	 * @param args
	 */
	
	public static void main (String [] args) {
		String[] in1 = { "FRRFLLFLLFRRFLF" };
		
		int shortest = timeToDeliver(in1);
		System.out.println("Answer: " + shortest);
	}
	
	public static int timeToDeliver (String[] path) {
		/* Construct a graph using path.  Do not give it weights.
		 * 
		 * Use the theory of the courier to create the weights of the graph?  hmm but it's different
		 * depending on how you do it.
		 * 
		 * Wait, no it's not, since you can assume a starting point.
		 * Apply Dijkstra's algorithm with heap to obtain shortest distance.
		 */
		return 0;
	}
	
	static class Node {
		String name;
		ArrayList<Node> neighbors;
		
		public Node (String nam) {
			name = nam;
			neighbors = new ArrayList<Node>();
		}
	}
}
