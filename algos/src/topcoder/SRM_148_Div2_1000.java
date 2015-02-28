package topcoder;

import java.util.HashSet;

public class SRM_148_Div2_1000 {
	public static void main (String [] args) {
		/*int[] in = {1, 2, 3, 3, 2, 1, 2, 2, 2};
		int[] in2 = {4,4,4,4,4,4,4,4,4};*/
		int[] in3 = {1,5,1,2,5,6,2,3,2};
		int ans = combos(in3);
		System.out.println("Answer: " + ans);
	}
	
	public static int combos (int[] n) {
		StringBuilder builder = new StringBuilder();
		for (int b : n) {
			builder.append(Integer.toString(b));
		}
		String toPermute = builder.toString();
		HashSet<String> perms = permutations(toPermute);
		int ret = 0;
		System.out.println(perms.size());
		outer: for (String permString : perms) {
			char[] nums = permString.toCharArray();
			int[] perm = new int[nums.length];
			
			for (int i = 0; i < nums.length; i++) {
				perm[i] = Character.getNumericValue(nums[i]);
			}
			
			//System.out.println(perm.length);
			
			int[][] square = {{perm[0], perm[1], perm[2]}, {perm[3], perm[4], perm[5]}, {perm[6], perm[7], perm[8]}};
			
			int sum = square[0][0] + square[0][1] + square[0][2];
			for (int j = 0; j < square.length; j++) {
				int s = 0;
				for (int k = 0; k <= 2; k++) {
					s += square[j][k];
				}
				//System.out.println(s + " vs. " + sum);
				if (s != sum) {continue outer;}
			}
			//System.out.println("here");
			for (int j = 0; j < square[0].length; j++) {
				int s = 0;
				for (int k = 0; k <= 2; k++) {
					s += square[k][j];
				}
				if (s != sum) {continue outer;}
			}
			//if (square[0][0] + square[1][1] + square[2][2] == sum &&
			//	square[0][2] + square[1][1] + square[2][0] == sum) {
				ret++;
			//}
		}
		
		return ret;
	}
	
	static HashSet<String> permutations(String s) {
	    HashSet<String> ret = new HashSet<String>();
	    permutation(s.toCharArray(), 0, ret);
	    return ret;
	}

	public static void permutation(char[] arr, int pos, HashSet<String> list){
	    if(arr.length - pos == 1)
	        list.add(new String(arr));
	    else
	        for(int i = pos; i < arr.length; i++){
	            swap(arr, pos, i);
	            permutation(arr, pos+1, list);
	            swap(arr, pos, i);
	        }
	}

	public static void swap(char[] arr, int pos1, int pos2){
	    char h = arr[pos1];
	    arr[pos1] = arr[pos2];
	    arr[pos2] = h;
	}
}
