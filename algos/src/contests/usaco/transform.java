package contests.usaco;
/*
ID: thatner1
LANG: JAVA
TASK: transform
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;


class transform {
	public static void main (String [] args) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader("transform.in"));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("transform.out")));
		
		int size = Integer.parseInt(reader.readLine());
		String[] a = new String[size];
		String[] b = new String[size];
		for (int i = 0; i < size; i++) {
			a[i] = reader.readLine();
		}
		
		for (int i = 0; i < size; i++) {
			b[i] = reader.readLine();
		}
		
		System.out.println(Arrays.toString(a));
		
		int[] possible = {1, 2, 3, 4, /**/5, 6, 7, /**/8};
		boolean gotIt = false;
		for (int i = 0; i < possible.length; i++) {
			
			String[] temp = deepCopy(a);
			char[][] chars = new char[temp.length][];
			for (int j = 0; j < temp.length; j++) {
				chars[j] = temp[j].toCharArray();
			}
			
			System.out.println(i + " data: ");
			for (char[] s : chars) {
				System.out.println(new String(s));
			}
			
			char[][] newChars = tryTransform(chars, possible[i]);
			String[] newBoard = charArrToStringArr(newChars);
			System.out.println(i + " result: ");
			for (String s : newBoard) {
				System.out.println(s);
			}
			System.out.println("\n\n\n");
			if (equals(newBoard, b)) {
				gotIt = true;
				System.out.println("OK!");
				if (i >= 4 && i <= 6) {
					i = 5;
					writer.write(i + "\n");
				}
				else if (i == possible.length - 1) {
					writer.write("6\n");
				}
				else {
					writer.write((i + 1) + "\n");
				}
				break;
			}
		}
		if (!gotIt) {
			writer.write("7\n");
		}
		reader.close();
		writer.close();
		System.exit(0);
	}
	
	public static char[][] tryTransform (char[][] board, int method) {
		/**
		 * @TODO
		 * Fix rotation and reflection
		 */
		int len = board.length;
		//System.out.println("dims: " + newBoard.length + " by " + newBoard[0].length);
		switch (method) {
		case 1:
			for (int r = 0; r < len/2; r++) {
				for (int c = 0; c < len - r - 1; c++) {
					System.out.println("run");
					char temp = board[r][c];
					board[r][c] = board[len - c - 1][r];
					board[len - c - 1][r] = board[len - r - 1][len - c - 1];
					board[len - r - 1][len - c - 1] = board[c][len - r - 1];
					board[c][len - r - 1] = temp;
				}
			}
			return board;
		case 2:
			return tryTransform(tryTransform(board, 1), 1);
		case 3:
			return tryTransform(tryTransform(tryTransform(board, 1), 1), 1);
		case 4:
			System.out.println("Flipping");
			for (int i = 0; i < len; i++) {
				for (int j = 0; j < len/2; j++) {
					char temp = board[i][len - j - 1];
					board[i][len - j - 1] = board[i][j];
					board[i][j] = temp;
				}
			}
			return board;
		case 5:
			return tryTransform(tryTransform(board, 4), 1);
		case 6:
			return tryTransform(tryTransform(board, 4), 2);
		case 7:
			return tryTransform(tryTransform(board, 4), 3);
		case 8:
			return board;
		default:
			return board;
		}
	}
	
	public static String[] charArrToStringArr (char[][] arr) {
		String[] res = new String[arr.length];
		for (int i = 0; i < arr.length; i++) {
			res[i] = new String(arr[i]);
		}
		return res;
	}
	
	public static boolean equals (String[] a, String[] b) {
		for (int i = 0; i < a.length; i++) {
			if (!a[i].equals(b[i])) {
				return false;
			}
		}
		return true;
	}
	
	public static String[] deepCopy (String[] a) {
		String[] res = new String[a.length];
		for (int i = 0; i < a.length; i++) {
			res[i] = a[i];
		}
		return res;
	}
}
