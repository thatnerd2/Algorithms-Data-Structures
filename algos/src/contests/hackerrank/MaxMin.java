package contests.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// Given a set "list", choose K integers from the list and determine the max - min value.  Minimize that value.

public class MaxMin {

	public static void main(String[] args) throws NumberFormatException,
			IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		int K = Integer.parseInt(in.readLine());
		int[] list = new int[N];

		for (int i = 0; i < N; i++)
			list[i] = Integer.parseInt(in.readLine());

		int unfairness = Integer.MAX_VALUE;

		Arrays.sort(list);
		System.out.println(Arrays.toString(list));
		for (int i = 0; i < list.length - K; i++) {
			int diff = list[i + K - 1] - list[i];
			unfairness = Math.min(diff,  unfairness);
		}
		
		System.out.println(unfairness);
	}
}
