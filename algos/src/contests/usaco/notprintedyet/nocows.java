package contests.usaco.notprintedyet;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/*
ID: thatner1
LANG: JAVA
TASK: nocows
 */
/*
 * Farmer John is considering purchasing a new herd of cows. In this new herd, each mother cow 
 * gives birth to two children. The relationships among the cows can easily be represented by 
 * one or more binary trees with a total of N (3 <= N < 200) nodes. The trees have these properties:

The degree of each node is 0 or 2. The degree is the count of the node's immediate children.
The height of the tree is equal to K (1 < K <100). The height is the number of nodes on the 
longest path from the root to any leaf; a leaf is a node with no children.

How many different possible pedigree structures are there? A pedigree is different if its 
tree structure differs from that of another pedigree. Output the remainder when the total number of 
different possible pedigrees is divided by 9901.
 */
class nocows {
	public static void main (String [] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("nocows.in"));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("nocows.out")));
		String[] in = reader.readLine().split(" ");
		int maxH = Integer.parseInt(in[1]);
		int numNodes = Integer.parseInt(in[0]);
		
		int[][] dp = new int[101][202];
		
		for (int i = 1; i <= maxH; i++) {
			dp[i][0] = dp[i][1] = 1;
		}
		
		for (int h = 1; h <= maxH; h++) {
			for (int n = 1; n <= numNodes; n += 2) {
				for (int c = 1; c < n; c += 2) {
					dp[h][n] = (dp[h][n] + dp[h - 1][c] * dp[h - 1][n - c - 1]) % 9901;
				}
			}
		}
		System.out.println((dp[maxH][numNodes] - dp[maxH - 1][numNodes] + 9901) % 9901);
		writer.write((dp[maxH][numNodes] - dp[maxH - 1][numNodes] + 9901) % 9901 + "\n");
		reader.close();
		
		
		writer.close();
	}
	
	static class Node {
		Node[] children;
		Node parent;
		
		public Node (Node parent) {
			children = new Node[2];
			this.parent = parent;
		}
	}
}
