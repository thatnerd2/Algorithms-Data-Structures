package dp;

import java.util.Arrays;

public class EditDistance {
	public static void main (String [] args) {
		String from = "cdd333";
		String to = "323333";
		
		int minSteps = getEditDistance (from, to);
		System.out.println("Distance: " + minSteps);
	}
	
	public static int getEditDistance (String from, String to) {

		
		//Three operations:
		//1.  Delete 1 character.
		//2.  Insert 1 character
		//3.  Change some character to a new character
		int[][] costs = new int[from.length() + 1][to.length() + 1];
		
		for (int i = 0; i <= from.length(); i++) {
			costs[i][0] = i;
		}
		
		for (int j = 0; j <= to.length(); j++) {
			costs[0][j] = j;
		}
		
		for (int i = 0; i < from.length(); i++) {
			for (int j = 0; j < to.length(); j++) {
				
				
				
				if (from.charAt(i) == to.charAt(j)) {
					System.out.println(from.charAt(i) + "= " + to.charAt(j));
					costs[i + 1][j + 1] = costs[i][j];
				}
				else {
					System.out.println(from.charAt(i) + " != " + to.charAt(j));
					int replace = costs[i][j] + 1;
					int insert = costs[i][j + 1] + 1;
					int delete = costs[i + 1][j] + 1;
					costs[i + 1][j + 1] = Math.min(replace, Math.min(insert, delete));
				}
			}
		}
		for (int[] b : costs) {
			System.out.println(Arrays.toString(b));
		}
		return costs[from.length()][to.length()];
	}
}
