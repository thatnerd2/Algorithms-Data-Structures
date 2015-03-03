package math;

public class LinePointDistance {
	public static void main (String [] args) {
		int[] a = {0, 0};
		int[] b = {1, 1};
		int[] c = {2, 2};
		
		double d = distanceFromSegToPoint (a, b, c);
		System.out.println(d);
	}
	
	public static double distanceFromLineToPoint (int[] a, int[] b, int[] c) {
		/**
		 * Distance from line (A to B) to C
		 * Doesn't work with line segments.
		 * 
		 * Method:
		 * Find vectors AB, AC
		 * Return AB cross AC / |AB| (|AB| is distance from a to b)
		 */
		
		double cross = crossProd(a, b, c);
		double mag = distance(a, b);
		return cross / mag;
	}
	
	public static double distanceFromSegToPoint (int[] a, int[] b, int[] c) {
		/**
		 * Distance from line segment (A to B) to C
		 * Calculates by checking dot product - if greater than 1, then angle is greater than 90
		 * (or less than -90) so the shortest distance is from the endpoints.
		 * 
		 * Otherwise, return the distance from line to point.
		 */
		
		double dist = distanceFromLineToPoint (a, b, c);
		int dot1 = dotProd(a, b, c);
		int dot2 = dotProd(b, a, c);
		System.out.println(dot1 + " hey " + dot2);
		if (dot1 > 0) {
			return distance (b, c);
		}
		if (dot2 > 0) {
			return distance (a, c);
		}
		return Math.abs(dist);
	}
	
	public static double distance (int[] a, int[] b) {
		return Math.sqrt((b[1] - a[1])*(b[1] - a[1]) + (b[0] - a[0])*(b[0] - a[0]));
	}
	
	public static double crossProd (int[] a, int[] b, int[] c) {
		int[] AB = {b[0] - a[0], a[1] - a[0]};
		int[] AC = {c[0] - a[0], c[1] - a[1]};
		return AB[0] * AC[1] - AB[1] * AC[0];
	}
	
	public static int dotProd (int[] a, int[] b, int[] c) {
		int[] AB = {b[0] - a[0], a[1] - a[0]};
		int[] BC = {c[0] - b[0], c[1] - b[1]};
		return AB[0] * BC[0] + AB[1] * BC[1];
	}
}
