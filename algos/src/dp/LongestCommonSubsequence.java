package dp;

public class LongestCommonSubsequence {
	public static void main (String [] args) {
		/*String a = "ABCDGH";
		String b = "AEDFHR";
		String c = "AGGTAB";
		String d = "GXTXAYB";*/
		String e = "abcdefghijklmnzyxwvutsrqpo";
		String f = "opqrstuvwxyzabcdefghijklmn";
		
		int len = getLongestCommonSubsequence(e, f);
		System.out.println("Longest: " + len);
	}
	
	public static int getLongestCommonSubsequence (String a, String b) {
		int[][] dp = new int[a.length() + 1][b.length() + 1];
		
		for (int i = 0; i < dp.length; i++) {
			dp[i][0] = 0;
		}
		for (int i = 0; i < dp[0].length; i++) {
			dp[0][i] = 0;
		}
		
		for (int i = 1; i < dp.length; i++) {
			for (int j = 1; j < dp[i].length; j++) {
				if (a.charAt(i - 1) == b.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				}
				else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}
		
		return maxOf(dp);
	}
	
	public static int maxOf (int[][] a) {
		int max= 0;
		for (int[] b : a) {
			for (int c : b) {
				max = Math.max(c, max);
			}
		}
		return max;
	}
}
