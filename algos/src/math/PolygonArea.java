package math;

public class PolygonArea {
	public static void main (String [] args) {
		int[][] pts = {{0, 0}, {2, 0}, {2, 2}, {0, 2}};
	
		double area = polygonArea(pts);
		System.out.println("Area: " + area);
	}
	
	public static double polygonArea (int[][] p) {
		double area = 0;
		
		/* Triangulate the polygon into triangles with points p[0], p[i] and p[i + 1]
		 */
		
		for (int i = 1; i < p.length - 1; i++) {
			double cross = crossProd (p[0], p[i], p[i + 1]);
			area += cross;
		}
		
		return Math.abs(area / 2.0);
	}
	
	public static double crossProd (int[] a, int[] b, int[] c) {
		int[] AB = {b[0] - a[0], a[1] - a[0]};
		int[] AC = {c[0] - a[0], c[1] - a[1]};
		return AB[0] * AC[1] - AB[1] * AC[0];
	}
}
