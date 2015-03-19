package contests.usaco;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

/*
ID: thatner1
LANG: JAVA
TASK: zerosum
 */
class zerosum {
	static int[] nums;
	static ArrayList<String> zeroes;
	
	public static void main (String [] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("zerosum.in"));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("zerosum.out")));
		int n = Integer.parseInt(reader.readLine());
		nums = new int[n];
		for (int i = 0; i < nums.length; i++) nums[i] = i + 1;
		zeroes = new ArrayList<String>();
		reader.close();
		
		String ops = "";
		solve(ops, n - 1);
		
		Collections.sort(zeroes);
		for (String s : zeroes) {
			//System.out.println(s);
			writer.write(s+"\n");
		}
		
		writer.close();
	}
	
	
	public static void solve (String ops, int maxLen) {
		if (ops.length() == maxLen) {
			eval(nums, ops);
			
			return;
		}
		
		solve(ops + "+", maxLen);
		solve(ops + "-", maxLen);
		solve(ops + " ", maxLen);
	}
	
	public static int eval (int[] nums, String ops) {
		StringBuilder x = new StringBuilder("" + nums[0]);
		StringBuilder clear = new StringBuilder("" + nums[0]);
		for (int i = 1; i < nums.length; i++) {
			if (ops.charAt(i - 1) != ' ') {
				x.append(ops.charAt(i - 1));
			}
			clear.append(ops.charAt(i - 1));
			clear.append(nums[i]);
			x.append(nums[i]);
		}
		
		String expr = x.toString();
		String[] byPlus = expr.split("\\+");
		int sum = 0;
		for (int i = 0; i < byPlus.length; i++) {
			String[] byMinus = byPlus[i].split("\\-");
			int minusSum = Integer.parseInt(byMinus[0]);
			for (int j = 1; j < byMinus.length; j++) {
				int toMinus = Integer.parseInt(byMinus[j]);
				minusSum -= toMinus;
			}
			byPlus[i] = "" + minusSum;
		}
		
		for (int i = 0; i < byPlus.length; i++) {
			sum += Integer.parseInt(byPlus[i]);
		}
		
		if (sum == 0) {
			//System.out.println("Added");
			zeroes.add(clear.toString());
		}
		return sum;
	}
	
}
