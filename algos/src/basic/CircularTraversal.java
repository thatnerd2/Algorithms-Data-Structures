package basic;

public class CircularTraversal {
	public static void main (String [] args) {
		/* People are sitting around a table and you can add whatever number you want and still be at the
		 * circular table
		 */
		
		int[] people = {1, 3, 2, 3, 4};
		int pos = 0;
		
		/* Arbitrarily picked 26 */
		pos += 26 % people.length;
		pos = pos % people.length;
		System.out.println("pos: " + pos + " and people[pos]: " + people[pos]);
	}
}
