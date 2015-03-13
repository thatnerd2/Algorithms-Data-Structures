package contests.siena;

import java.util.Arrays;
import java.util.HashMap;

public class SmokeRings {
	public static void main (String [] args) {
		int n = 6;
		int r = 3;
		int c = 2;
		char ans = getChar(n, r, c);
		System.out.println(ans);
	}
	
	public static char getChar (int n, int ansR, int ansC){
		char[][] board = new char[n][n];
		int r = 0;
		int c = 0;
		String dir = "RIGHT";
		
		for (char[] b : board) {
			Arrays.fill(b, '?');
		}
		
		HashMap<String, int[]> dirs = new HashMap<String, int[]>();
		dirs.put("RIGHT", new int[] {0, 1});
		dirs.put("LEFT", new int[] {0, -1});
		dirs.put("UP", new int[] {-1, 0});
		dirs.put("DOWN", new int[] {1, 0});
		char nextChar = 'A';
		while (ansR - 1 != r || ansC - 1 != c) {
			int[] dydx = dirs.get(dir);
			board[r][c] = nextChar;
			
			r += dydx[0];
			c += dydx[1];
			
			if (r >= board.length || r < 0 || c >= board[0].length || c < 0 || board[r][c] != '?') {
				r -= dydx[0];
				c -= dydx[1];
				dir = changeDir(dir);
				dydx = dirs.get(dir);
				r += dydx[0];
				c += dydx[1];
			}
			
			nextChar += 1;
			if (nextChar == 'Z' + 1) {
				nextChar = 'A';
			}
			/*for (char[] b : board) {
				System.out.println(Arrays.toString(b));
			}*/
		}
		return nextChar;
	}
	
	public static String changeDir (String dir) {
		if (dir.equals("RIGHT")) {
			return "DOWN";
		}
		if (dir.equals("LEFT")) {
			return "UP";
		}
		if (dir.equals("UP")) {
			return "RIGHT";
		}
		if (dir.equals("DOWN")) {
			return "LEFT";
		}
		return "FAILURE";
	}
}
