package math;

public class CircleFromThreePoints {
	public static void main (String [] args) {
		double[] a = {2, 0};
		double[] b = {0, 2};
		double[] c = {-2, 0};
	
		double[] circle = getCircle(a, b, c); //0: x, 1: y, 2: radius
		System.out.println("Circle at : (" + circle[0] + " ," + circle[1] + " ) and radius: " + circle[2]);
	}
	
	public static double[] getCircle (double[] a, double[] b, double c[]) {
		/* Come up with perpendicular bisectors
		 * find intersection of perpendicular bisectors
		 */
		return null;
	}
}
