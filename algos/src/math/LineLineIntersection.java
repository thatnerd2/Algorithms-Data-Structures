package math;

public class LineLineIntersection {
	public static void main (String [] args) {
		double[] coeffs1 = {-3, -1, -2}; //y = -3x + 2, but in form -3x - y = -2
		double[] coeffs2 = {2, -1, 0};   //y = 2x
		
		double[] intersection = intersect(coeffs1, coeffs2);
		System.out.println("Intersection at " + intersection[0] + "," + intersection[1]);
	}
	
	public static double[] intersect (double[] c1, double[] c2) {
		/**
		 * If line segments, you have to check to make sure return values are on both lines
		 * CURRENTLY NOT INCLUDED
		 */
		double det = c1[0] * c2[1] - c1[1] * c2[0];
		if (det == 0) {
			System.out.println("LINES ARE PARALLEL");
			return null;
		}
		else {
			double x = (c2[1] * c1[2] - c1[1] * c2[2]) / det;
			double y = (c1[0] * c2[2] - c2[0] * c1[2]) / det;
			return new double[] {x, y};
		}
		
	}
}
