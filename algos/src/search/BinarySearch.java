package search;

public class BinarySearch {
	public static void main (String [] args) {
		int[] in = {1, 3, 4, 6, 45, 46, 67, 78, 976, 6453, 3464, 3553, 23400, 40000};
		
		int idx = binarySearch(in, -1);
		System.out.println("Found at " + idx);
	}
	
	public static int binarySearch (int[] a, int n) {
		int right = a.length - 1;
		int left = 0;
		int middle = (right + left) / 2;
		while (a[middle] != n) {
			if (right <= left) {
				return -1;
			}
			
			if (a[middle] > n) {
				right = middle;
			}
			else {
				left = middle;
			}
			middle = (right + left) / 2;
			System.out.println(right + " and " + left);
		}
		return middle;
	}
	
	public static int binarySearch (int[] a, int n, int low, int high) {
		System.out.println("high: " + high + " and low: " + low);
		if (high < low) {return -1;}
		
		int avg = (high + low) / 2;
		if (a[avg] == n) {
			return avg;
		}
		else if (a[avg] > n) {
			System.out.println("too high");
			return binarySearch(a, n, low, avg);
		}
		else {
			System.out.println("too low");
			return binarySearch(a, n, avg, high);
		}
	}
}
