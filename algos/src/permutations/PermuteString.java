package permutations;

import java.util.ArrayList;

public class PermuteString {
	public static void main (String [] args) {
		String testStr = "hey";
		ArrayList<String> perms2 = permutations(testStr);
		for (String s : perms2) System.out.println(s);
	}
	
	public static ArrayList<String> permutations(String s) {
	    ArrayList<String> ret = new ArrayList<String>();
	    permutation(s.toCharArray(), 0, ret);
	    return ret;
	}

	public static void permutation(char[] arr, int pos, ArrayList<String> list){
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
