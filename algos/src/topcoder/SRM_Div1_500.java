package topcoder;

import java.util.ArrayList;
import java.util.HashSet;

public class SRM_Div1_500 {
	/* Incomplete */
	
	public static void main (String [] args) {
		/*int[] in1 = {7, 5, 9, 6, 8, 1, 2};
		int[] in2 = {4, 8, 6, 5, 2};
		int[] in3 = {2, 4, 7, 6, 9, 8, 5};*/
		int[] in4 = {9, 2, 1, 6, 3, 4, 8};
		
		String ans = outcome(in4);
		System.out.println("Answer: " + ans);
	}
	
	public static String outcome (int[] moves) {
		int[] dealerMoves = new int[moves.length/2 + 1];
		int[] patronMoves = new int[moves.length/2];
		boolean dealerTurn = true;
		int c1 = 0, c2 = 0;
		for (int i = 0; i < moves.length; i++) {
			if (dealerTurn) {
				dealerMoves[c1] = moves[i];
				c1++;
			}
			else {
				patronMoves[c2] = moves[i];
				c2++;
			}
			dealerTurn = !dealerTurn;
		}
		
		int dealerTotal = sumOf(dealerMoves);
		int patronTotal = sumOf(patronMoves);
		ArrayList<Integer> movesLeft = getMovesLeftAfter(moves);
		
		//It's patron's turn.
		dealerTurn = false;
		String res = "";
		boolean wins = solve(dealerTotal, patronTotal, movesLeft, dealerTurn);
		res += (wins) ? "WINS" : "LOSES";
		return res;
	}
	
	public static boolean solve (int d, int p, ArrayList<Integer> moves, boolean isDealer) {
		if (isDealer) {
			if (existsMoveThatWins(moves, d)) {
				return false;
			}
			else {
				ArrayList<Boolean> allSolutions = new ArrayList<Boolean>();
				for (int i = 0; i < moves.size(); i++) {
					ArrayList<Integer> newMoves = deepCopy(moves);
					newMoves.remove(i);
					allSolutions.add(solve(d + moves.get(i), p, newMoves, false));
				}
				
				if (allSolutions.contains(true)) {
					return true;
				}
				else {
					return false;
				}
			}
		}
		else {
			if (existsMoveThatWins(moves, p)) {
				return true;
			}
			else {
				ArrayList<Boolean> allSolutions = new ArrayList<Boolean>();
				for (int i = 0; i < moves.size(); i++) {
					ArrayList<Integer> newMoves = deepCopy(moves);
					newMoves.remove(i);
					allSolutions.add(solve(d, p + moves.get(i), newMoves, true));
				}
				
				if (allSolutions.contains(true)) {
					return true;
				}
				else {
					return false;
				}
			}
		}
	}
	
	public static ArrayList<Integer> deepCopy (ArrayList<Integer> a) {
		ArrayList<Integer> res = new ArrayList<Integer>(a.size());
		for (Integer i : a) {
			res.add(i);
		}
		return res;
	}
	
	public static boolean existsMoveThatWins (ArrayList<Integer> moves, int total) {
		for (int i =0; i < moves.size(); i++) {
			if (moves.get(i) + total == 15) {
				return true;
			}
		}
		return false;
	}
	
	public static ArrayList<Integer> getMovesLeftAfter (int[] moves) {
		HashSet<Integer> movesContained = new HashSet<Integer>();
		ArrayList<Integer> movesLeft = new ArrayList<Integer>();
		for (int a : moves) {
			movesContained.add(a);
		}
		for (int i = 1; i <= 9; i++) {
			if (!movesContained.contains(i)) {
				movesLeft.add(i);
			}
		}
		
		return movesLeft;
	}
	
	public static int sumOf (int[] a) {
		int sum = 0;
		for (int b : a) {
			sum += b;
		}
		return sum;
	}
}
