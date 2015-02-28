package topcoder;

import java.util.ArrayList;

public class SRM_146_Div2_1000 {
	public static void main (String [] args) {
		int[] times = {1, 2, 5, 10};
		
		int ans = minTime (times);
		System.out.println("Answer: " + ans);
	}
	
	public static int minTime (int[] times) {
		
		int min = Integer.MAX_VALUE - 100;
		
		for (int i = 0; i < times.length; i++) {
			for (int j = 0; j < times.length; j++) {
				if (i != j) {
					ArrayList<Integer> left = (new ArrayList<Integer> ());
					for (int k = 0; k < times.length; k++) {
						left.add(times[k]);
					}
					ArrayList<Integer> right = new ArrayList<Integer>();
					
					min = Math.min(min, doit(i, j, left, right));
					System.out.println(min +"---------------");
				}
			}
		}
		
		return min;
	}
	
	public static int doit (int a, int b, ArrayList<Integer> left, ArrayList<Integer> right) {
		if (left.size() == 2) {
			return left.get(a) + left.get(b);
		}
		int sendCost = left.get(a) + left.get(b);
		right.add(left.remove(Math.max(a, b)));
		right.add(left.remove(Math.min(a, b)));
		
		int minIdx = minAt(right);
		
		int sendBackCost = right.get(minIdx);
		left.add(right.remove(minIdx));
		
		int min = Integer.MAX_VALUE - 100;
		for (int i = 0; i < left.size(); i++) {
			for (int j = 0; j < left.size(); j++) {
				if (i != j) {
					ArrayList<Integer> copyLeft = new ArrayList<Integer>();
					ArrayList<Integer> copyRight = new ArrayList<Integer>();
					
					copyLeft.addAll(left);
					copyRight.addAll(right);
					int thisDoit = doit(i, j, copyLeft, copyRight);
					System.out.println("This do it: " + thisDoit);
					min = Math.min(min, thisDoit);
				}
			}
		}
		System.out.println("Result: " + (min + sendCost + sendBackCost));
		return min + sendCost + sendBackCost;
	}
	
	public static int minAt (ArrayList<Integer> a) {
		int min = Integer.MAX_VALUE;
		int idx = -1;
		for (int i = 0; i < a.size(); i++) {
			if (min > a.get(i)) {
				min = a.get(i);
				idx = i;
			}
		}
		return idx;
	}
}
