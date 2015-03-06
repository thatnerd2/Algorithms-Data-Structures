package permutations;

import java.util.ArrayList;
import java.util.Arrays;

public class PermuteArray {
	public static void main (String [] args) {
		/* TEST CODE, don't copy! */
		int[] testInts = {1, 2, 3};
		ArrayList<int[]> perms1 = permutations(testInts);
		for (int[] b : perms1) System.out.println(Arrays.toString(b));
		System.out.println(perms1.size());
	}
	
	static ArrayList<int[]> permutations(int[] a) {
	    ArrayList<int[]> res = new ArrayList<int[]>();
	    permutation(a, 0, res);
	    return res;
	}

	public static void permutation(int[] arr, int pos, ArrayList<int[]> list){
	    if(arr.length - pos == 1)
	        list.add(arr.clone());
	    else
	        for(int i = pos; i < arr.length; i++){
	            swap(arr, pos, i);
	            permutation(arr, pos+1, list);
	            swap(arr, pos, i);
	        }
	}

	public static void swap(int[] arr, int pos1, int pos2){
	    int h = arr[pos1];
	    arr[pos1] = arr[pos2];
	    arr[pos2] = h;
	}
}
