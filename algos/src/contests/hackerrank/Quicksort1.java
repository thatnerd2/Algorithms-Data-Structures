package contests.hackerrank;

import java.util.*;

public class Quicksort1 {

	static void partition(int[] ar) {
		int p = ar[0];
		ArrayList<Integer> smaller = new ArrayList<Integer>();
		ArrayList<Integer> bigger = new ArrayList<Integer>();
		for (int i = 1; i < ar.length; i++) {
			if (ar[i] < p) {
				smaller.add(ar[i]);
			}
			else {
				bigger.add(ar[i]);
			}
		}
		for (int i = 0; i < smaller.size(); i++) {
			ar[i] = smaller.get(i);
		}
		ar[smaller.size()] = p;
		for (int i = 0; i < bigger.size(); i++) {
			ar[i + smaller.size() + 1] = bigger.get(i);
		}
	}

	static void printArray(int[] ar) {
		for (int n : ar) {
			System.out.print(n + " ");
		}
		System.out.println("");
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] ar = new int[n];
		for (int i = 0; i < n; i++) {
			ar[i] = in.nextInt();
		}
		partition(ar);
		printArray(ar);
		in.close();
	}
}
