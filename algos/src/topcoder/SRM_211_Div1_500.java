package topcoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class SRM_211_Div1_500 {
	/* Success! (grafixMask)
	 * Algorithm Details:
	 * 
	 * The grid is 400 by 600 pixels, represented by boolean[][] map.  The rectangles given as input  
	 * turn the grid's boolean values to true.  then a depth first search is performed for each area,
	 * and the number of nodes visited is the area of that sector.  Then, all the sector areas are thrown
	 * into a sorting routine, and that's the answer!
	 */
	
	public static void main (String [] args) {
		@SuppressWarnings("unused")
		String[] input = {"0 292 399 307"};
		@SuppressWarnings("unused")
		String[] input2 = {"48 192 351 207", "48 392 351 407", "120 52 135 547", "260 52 275 547"};
		String[] input3 = {"0 192 399 207", "0 392 399 407", "120 0 135 599", "260 0 275 599"};
		int[] sorted = sortedAreas (input3);
		System.out.println(Arrays.toString(sorted));
	}
	
	public static int[] sortedAreas (String[] rects) {
		boolean[][] map = new boolean[400][600];
		for (boolean[] a : map) {
			Arrays.fill(a, false);
		}
		
		for (int i = 0; i < rects.length; i++) {
			String[] coordString = rects[i].split(" ");
			int[] coords = sToIntArray(coordString);
			for (int j = coords[0]; j <= coords[2]; j++) { //CHECK THIS
				for (int k = coords[1]; k <= coords[3]; k++) {
					map[j][k] = true;
				}
			}
		}
		
		/*System.out.println("MAP:");
		for (boolean[] a : map) {
			int[] b = new int[a.length];
			for (int i = 0; i < a.length; i++) {
				b[i] = (a[i]) ? 1 : 0;
			}
			System.out.println(Arrays.toString(b));
		}*/
		
		ArrayList<Integer> areas = new ArrayList<Integer>();
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] == false) {
					int thisArea = sectorArea(new int[] {i, j}, map);
					areas.add(thisArea);
				}
			}
		}
		
		Integer[] toRes = areas.toArray(new Integer[0]);
		int[] res = new int[toRes.length];
		for (int i= 0; i < toRes.length; i++) {
			res[i] = toRes[i];
		}
		Arrays.sort(res);
		return res;
		
	}
	
	private static int sectorArea (int[] start, boolean[][] map) {
		int area = 0;
		Queue<int[]> toVisit = new LinkedList<int[]>();
		toVisit.add(start);
		
		while (!toVisit.isEmpty()) {
			int[] node = toVisit.poll();
			if (node[0] < map.length && node[0] >= 0 && node[1] < map[0].length && node[1] >= 0) {
				
				if (map[node[0]][node[1]] == false) {
					map[node[0]][node[1]] = true; //visit it
					area++;
					toVisit.add(new int[] {node[0] - 1, node[1]});
					toVisit.add(new int[] {node[0] + 1, node[1]});
					toVisit.add(new int[] {node[0], node[1] - 1});
					toVisit.add(new int[] {node[0], node[1] + 1});
					
				}
				
			}
		}
		return area;
	}
	
	private static int[] sToIntArray (String[] c) {
		int[] res = new int[c.length];
		for (int i = 0; i < c.length; i++) {
			res[i] = Integer.parseInt(c[i]);
		}
		return res;
	}
}
