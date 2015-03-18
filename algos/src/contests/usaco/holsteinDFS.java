package contests.usaco;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

/*
ID: thatner1
LANG: JAVA
TASK: holstein
 */
class holsteinDFS {
	public static void main (String [] args) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader("holstein.in"));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("holstein.out")));
		
		int numRequirements = Integer.parseInt(reader.readLine());
		int[] require = new int[numRequirements];
		String[] in = reader.readLine().split(" ");
		for (int i = 0; i < require.length; i++) {
			require[i] = Integer.parseInt(in[i]);
		}
		
		int numTypes = Integer.parseInt(reader.readLine());
		int[][] types = new int[numTypes][numRequirements];
		for (int i = 0; i < types.length; i++) {
			//String whole = reader.readLine().replaceAll("\\s+", " ");
			//String[] parts = whole.split(" ");
			String[] parts = reader.readLine().split(" ");
			
			for (int j = 0; j < parts.length; j++) {
				types[i][j] = Integer.parseInt(parts[j]);
			}
		}
		reader.close();
		
		boolean[] visited = new boolean[types.length];
		int absMin = Integer.MAX_VALUE;
		boolean[] absMinScoops = new boolean[visited.length];
		
		for (int i = 0; i < types.length; i++) {
			Arrays.fill(visited, false);
			int[] state = types[i].clone();
			visited[i] = true;
			boolean[] minScoops = solve(state, require, types, visited);
			int minCount = countTrue(minScoops);
			
			//System.out.println(Arrays.toString(visited));
			if (minCount < absMin) {
				absMin = minCount;
				absMinScoops = minScoops.clone();
				//Collect boolean list
			}
		}
		
		System.out.println("Absolute min: " + absMin);
		System.out.println("List: " + Arrays.toString(absMinScoops));
		writer.write(absMin + "");
		for (int i = 0; i < absMinScoops.length; i++) {
			if (absMinScoops[i]) writer.write(" " + (i+1));
		}
		writer.write("\n");
		
		writer.close();
	}
	
	public static boolean[] solve (int[] state, int[] require, int[][] t, boolean[] v) {
		if (requirementsMet(state, require)) return v;
		
		int absMin = Integer.MAX_VALUE;
		boolean[] best = new boolean[v.length];
		for (int i = 0; i < t.length; i++) {
			if (!v[i]) {
				for (int j = 0; j < state.length; j++) state[j] += t[i][j];
				v[i] = true;
				boolean[] min = solve(state, require, t, v);
				int minN = countTrue(min);
				if (absMin > minN) {
					absMin = minN;
					best = min.clone();
				}
				v[i] = false;
				for (int j = 0; j < state.length; j++) state[j] -= t[i][j];
			}
		}
		//System.out.println("BEst: " + Arrays.toString(best));
		return best;
	}
	
	public static int countTrue (boolean[] b) {
		int count = 0;
		for (boolean a : b) {
			if (a) count++;
		}
		return count;
	}
	
	public static boolean requirementsMet (int[] state, int[] require) {
		for (int i = 0; i < state.length; i++) {
			if (state[i] < require[i]) return false;
		}
		return true;
	}
}
