package search;

import java.util.ArrayList;
import java.util.Stack;

public class SudokuSolver {
	/* Haha! It works! Uses backtracking and a stack */
	
	public static void main (String [] args) {
		/*int[][] puzzle = {{-1, 2, -1, 6, -1, 8, -1, -1, -1},
						  {5, 8, -1, -1, -1, 9, 7, -1, -1},
						  {-1, -1, -1, -1, 4, -1, -1, -1, -1},
						  {3, 7, -1, -1, -1, -1, 5, -1, -1},
						  {6, -1, -1, -1, -1, -1, -1, -1, 4},
						  {-1, -1, 8, -1, -1, -1, -1, 1, 3},
						  {-1, -1, -1, -1, 2, -1, -1, -1, -1},
						  {-1, -1, 9, 8, -1, -1, -1, 3, 6},
						  {-1, -1, -1, 3, -1, 6, -1, 9, -1}};*/
		
		int[][] puzzle = {{-1, -1, -1, 6, 5, -1, -1, -1, 1},
				          {-1, -1, -1, 4, -1, 8, -1, -1, 6},
				          {-1, 3, -1, -1, -1, 7, -1, 8, -1},
				          {9, -1, -1, -1, -1, 1, -1, -1, 3},
				          {-1, 6, -1, -1, 4, -1, -1, 7, -1},
				          {1, -1, -1, 8, -1, -1, -1, -1, 4},
				          {-1, 5, -1, 1, -1, -1, -1, 9, -1},
				          {8, -1, -1, 3, -1, 2, -1, -1, -1},
				          {6, -1, -1, -1, 9, 5, -1, -1, -1}};
		
		
		Stack<Candidate> stack = new Stack<Candidate>();
		Candidate c = null;
		prettyPrint(puzzle);
		while ((c = findNextCandidate(puzzle)) != null) {
			ArrayList<Integer> possible = findPossible(c.r, c.c, puzzle);
			c.setPossible(possible);
			boolean canMove = c.canMove();
			
			if (canMove) {
				puzzle[c.r][c.c] = c.makeMove();
				System.out.println("CHANGED BELOW AT " + c.r + "," + c.c + " to be " + puzzle[c.r][c.c]);
				prettyPrint(puzzle);
				
			}
			else {
				System.out.println("BACKTRACKING");
				backtrack(c, stack, puzzle);
			}
			stack.add(c);
		} while (c != null);
		System.out.println("done");
		System.out.println(verifySoduku(puzzle));
	}
	
	public static Candidate findNextCandidate (int[][] puzzle) {
		for (int i = 0; i < puzzle.length; i++) {
			for (int j = 0; j < puzzle.length; j++) {
				if (puzzle[i][j] == -1) {
					return new Candidate(i, j);
				}
			}
		}
		return null;
	}
	
	public static void backtrack (Candidate c, Stack<Candidate> stack, int[][] puzzle) {
		
		if (c.canMove()) {
			puzzle[c.r][c.c] = c.makeMove();
			return;
		}
		else {
			while (!c.canMove()) {
				Candidate prev = stack.pop();
				prev.removeMove();
				puzzle[prev.r][prev.c] = -1;
				
				backtrack(prev, stack, puzzle);
				
				c.setPossible(findPossible(c.r, c.c, puzzle));
				stack.add(prev);
			}
			
			backtrack(c, stack, puzzle);
		}
	}
	
	public static ArrayList<Integer> findPossible (int r, int c, int[][] puzzle) {
		ArrayList<Integer> alreadyThere = new ArrayList<Integer>();
		for (int i = 0; i < puzzle.length; i++) {
			if (puzzle[i][c] != -1) {
				alreadyThere.add(puzzle[i][c]);
			}
		}
		
		for (int i = 0; i < puzzle[0].length; i++) {
			if (puzzle[r][i] != -1) {
				alreadyThere.add(puzzle[r][i]);
			}
		}
		
		int startR = -1;
		int startC = -1;
		for (int i = 0; i <= 9; i += 3) {
			if (i > r && startR == -1) {
				startR = i - 3;
			}
			if (i > c && startC == -1) {
				startC = i - 3;
			}
		}
		
		for (int i = startR; i < startR + 3; i++) {
			for (int j = startC; j < startC + 3; j++) {
				if (puzzle[i][j] != -1) {
					alreadyThere.add(puzzle[i][j]);
				}
			}
		}
		System.out.println(alreadyThere);
		ArrayList<Integer> ret = new ArrayList<Integer>();
		for (int i = 1; i <= 9; i++) {
			if (!alreadyThere.contains(i)) {
				ret.add(i);
			}
		}
		System.out.println("ret size: " + ret.size());
		return ret;
	}
	
	public static boolean verifySoduku (int[][] puzzle) {
		int rowSum = 0;
		int colSum = 0;
		for (int i = 0; i < puzzle.length; i++) {
			for (int j = 0; j < puzzle.length; j++) {
				rowSum += puzzle[i][j];
				colSum += puzzle[j][i];
			}
			if (rowSum != 45 || colSum != 45) {
				System.out.println("colsum: " + colSum);
				return false;
			}
			rowSum = 0;
			colSum = 0;
		}
		return true;
	}
	
	static class Candidate {
		int r;
		int c;
		ArrayList<Integer> possible;
		
		public Candidate (int r, int c) {
			this.r = r;
			this.c = c;
			possible = new ArrayList<Integer>();
		}
		
		public void addPossible (int p) {
			possible.add(p);
		}
		
		public void setPossible (ArrayList<Integer> p) {
			possible = p;
		}
		
		public boolean canMove () {
			return possible.size() > 0;
		}
		
		public int makeMove () {
			return possible.get(0);
		}
		
		public int removeMove () {
			return possible.remove(0);
		}
	}
	
	public static void prettyPrint (int[][] puzzle) {
		for (int i = 0; i < puzzle.length; i++) {
			for (int j = 0; j < puzzle.length; j++) {
				if (puzzle[i][j] != -1) {
					System.out.print("  " + puzzle[i][j]);
				}
				else {
					System.out.print(" " + puzzle[i][j]);
				}
			}
			System.out.println("");
		}
	}
}
