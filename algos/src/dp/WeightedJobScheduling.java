package dp;

import java.util.HashMap;

public class WeightedJobScheduling {
	static int timesCalled = 0;
	static HashMap<String, Integer> memo;
	
	public static void main (String [] args) {
		memo = new HashMap<String, Integer>();
		int[][] jobs = {{1, 2, 50},
						{3, 5, 20},
						{6, 19, 100},
						{2, 100, 200},
						{4, 10, 20},
						{5, 100, 100}};
		
		int maxProfit = solve(jobs, 0, 0);
		System.out.println("Max profit: " + maxProfit);
		System.out.println("CALLED " + timesCalled);
	}
	
	public static int solve (int[][] jobs, int considering, int prevFin) {
		
		if (memo.containsKey(considering + " " + prevFin)) {
			return memo.get(considering + " " + prevFin);
		}
		timesCalled++;
		System.out.println("CALL");
		if (considering == jobs.length) return 0;
		
		if (jobs[considering][0] < prevFin) {
			return solve(jobs, considering + 1, prevFin);
		}
		
		//Available? Can take it or leave it
		int takeIt = jobs[considering][2] + solve(jobs, considering + 1, jobs[considering][1]);
		int leaveIt = solve(jobs, considering + 1, prevFin);
		
		int ans = Math.max(takeIt, leaveIt);
		memo.put(considering + " " + prevFin, ans);
		return ans;
	}
}
