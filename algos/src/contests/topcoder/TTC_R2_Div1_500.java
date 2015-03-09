package contests.topcoder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class TTC_R2_Div1_500 {
	/* Success!
	 * 
	 * Each input constructs a bunch of graphs, unconnected and connected alike.  First, I parse out
	 * all the nodes.  Then, since constrainst limit compete to have 0 and 50 characters, 1 and 30 elems,
	 * I use a BFS to parse out all the connected graphs, storing them into ArrayList<ArrayList<Product>> graphs.
	 * 
	 * After that, I use a DFS (so that I can keep track of the previous audience and make sure they don't match)
	 * to assign audience types to each node.  Since if the configuration is possible at all, there are 2 configurations,
	 * I just check to see if a configuration is possible, and if so, multiply res by 2.  If res ends up at 1 at the
	 * end, then I know that no configurations are possible so I return -1.
	 * 
	 */
	
	public static void main (String [] args) {
		/* String[] input = {"1 4","2","3","0",""};
		String[] input2 = {"1","2","0"};
		String[] input3 = {"1","2","3","0","0 5","1"};
		String[] input4 = {"","","","","","","","","","",
				 "","","","","","","","","","",
				 "","","","","","","","","",""}; */
		String[] input5 = {"1","2","3","0","5","6","4"};
		
		long ways = howMany (input5);
		System.out.println("answer: " + ways);
	}
	
	public static long howMany (String[] compete) {
		ArrayList<Product> products = new ArrayList<Product>();
		for (int i = 0; i < compete.length; i++) {
			products.add(new Product("" + i));
		}
		
		for (int i = 0; i < compete.length; i++) {
			String[] competingWith = compete[i].split(" ");
			for (int j = 0; j < competingWith.length; j++) {
				if (competingWith[j].equals("")) {
					continue;
				}
				int ix = ixByName(products, competingWith[j]);
				products.get(i).addNeighbor(products.get(ix));
				products.get(ix).addNeighbor(products.get(i));
			}
		}
		
		ArrayList<ArrayList<Product>> graphs = new ArrayList<ArrayList<Product>>();
		
		while (products.size() > 0) {
			ArrayList<Product> foundGraph = pullOutConnectedGraph(products);
			graphs.add(foundGraph);
		}
		
		System.out.println("Number of connected graphs: " + graphs.size());
		
		int res = 1;
		for (int i = 0; i < graphs.size(); i++) {
			//Do a DFS to figure out how many configs there are.
			LinkedList<Product> toVisit = new LinkedList<Product>();
			ArrayList<Product> graph = graphs.get(i);
			String lastAudience = "ADULT"; //lastAudience and thisAudience must NOT match.
			toVisit.add(graph.get(0));
			
			boolean isPossible = true;
			while (!toVisit.isEmpty()) {
				Product p = toVisit.poll();
				if (!p.audience.equals("UNKNOWN")) {
					for (int j = 0; j < p.neighbors.size(); j++) {
						if (p.audience.equals(p.neighbors.get(j).audience)) {
							isPossible = false;
							
						}
					}
					if (!isPossible) {
						break;
					}
				}
				else {
					p.audience = (lastAudience.equals("ADULT")) ? "TEENAGER" : "ADULT";
					lastAudience = p.audience;
					for (int j = 0; j < p.neighbors.size(); j++) {
						toVisit.offerFirst(p.neighbors.get(j));
					}
				}
			}
			
			if (isPossible) {
				res *= 2;
			}
		}
		
		return (res == 1) ? -1 : res;
	}
	
	private static ArrayList<Product> pullOutConnectedGraph (ArrayList<Product> products) {
		//Implemented with BFS, faster with union find but I don't know how to do union find.
		Queue<Product> toVisit = new LinkedList<Product>();
		HashSet<Product> visited = new HashSet<Product>();
		toVisit.add(products.get(0));
		while (!toVisit.isEmpty()) {
			Product p = toVisit.poll();
			if (!visited.contains(p)) {
				visited.add(p);
				for (int i = 0; i < p.neighbors.size(); i++) {
					toVisit.add(p.neighbors.get(i));
				}
			}
		}
		
		ArrayList<Product> connectedGraph = new ArrayList<Product>();
		
		for (Product p : visited) {
			connectedGraph.add(p);
			products.remove(p);
		}
		return connectedGraph;
	}
	
	private static int ixByName (ArrayList<Product> products, String name) {
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).name.equals(name)) {
				return i;
			}
		}
		System.out.println("Doesn't exist");
		return -1;
	}
	
	static class Product {
		String name;
		String audience;
		ArrayList<Product> neighbors;
		
		public Product (String name) {
			this.name = name;
			audience = "UNKNOWN";
			neighbors = new ArrayList<Product>();
		}
		
		public void addNeighbor (Product p) {
			neighbors.add(p);
		}
		
		public void removeProduct (Product p) {
			neighbors.remove(p);
		}
		
		public void setNeighbors (ArrayList<Product> list) {
			neighbors = list;
		}
		
		public void setAudience (String newAudience) {
			audience = newAudience;
		}
	}
}
