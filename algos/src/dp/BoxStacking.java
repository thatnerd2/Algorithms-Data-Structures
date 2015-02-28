package dp;

import java.util.Arrays;
import java.util.Comparator;

public class BoxStacking {
	public static void main (String [] args) {
		Box[] boxes = new Box[4];
		boxes[0] = new Box(3, 3, 3);
		boxes[1] = new Box(4, 4, 4);
		boxes[2] = new Box(5, 5, 5);
		boxes[3] = new Box(2, 3, 4);
		
		int tallest = stackBoxes (boxes);
		System.out.println("Tallest height: " + tallest);
	}
	
	public static int stackBoxes (Box[] boxes) {
		int[] states = new int[boxes.length * 3];
		Box[] allBoxes = new Box[boxes.length * 3];
		
		for (int i = 0; i < boxes.length; i++) {
			allBoxes[i*3] = boxes[i];
			allBoxes[i*3 + 1] = new Box(boxes[i].height, boxes[i].depth, boxes[i].width);
			allBoxes[i*3 + 2] = new Box(boxes[i].depth, boxes[i].width, boxes[i].height);
		}
		
		Arrays.sort(allBoxes, new Comparator<Box>() {
			public int compare (Box a, Box b) {
				return a.width * a.depth - b.width * b.depth;
			}
		});
		
		Arrays.fill(states, allBoxes[0].height);
		for (int i = 0; i < states.length; i++) {
			for (int j = 0; j < i; j++) {
				//System.out.println("Allboxes[i] " + allBoxes[i]);
				//System.out.println("Allboxes[j]: " + allBoxes[j]);
				if (allBoxes[i].width > allBoxes[j].width &&
					allBoxes[i].depth > allBoxes[j].depth &&
					states[i] < states[j] + allBoxes[i].height) {
					
					states[i] = states[j] + allBoxes[i].height;
				}
			}
		}
		return maxOf(states);
	}
	
	public static int maxOf (int[] a) {
		int max = 0;
		for (int b : a ) {
			max = Math.max(b, max);
		}
		return max;
	}
	
	static class Box {
		int width;
		int height;
		int depth;
		
		public Box (int w, int h, int d) {
			width = w;
			height = h;
			depth = d;
		}
		
		public String toString () {
			return width+","+depth+","+height;
		}
	}
}
