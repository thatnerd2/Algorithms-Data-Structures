package contests.siena.problem7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class TakingToTheMax {
	public static void main (String [] args) {
		Scanner scan = new Scanner (System.in);
		int n = scan.nextInt();
		scan.nextLine();
		Pt[] pts = new Pt[n];
		for (int i = 0; i < n; i++) {
			String[] parts = scan.nextLine().split(" ");
			pts[i] = new Pt(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
		}
		
		ArrayList<Pt> res = new ArrayList<Pt>();
		for (int i = 0; i < pts.length; i++) {
			Pt pi = pts[i];
			boolean good = true;
			for (int j = 0; j < pts.length; j++) {
				if (i != j) {
					Pt pj = pts[j];
					if (pj.x >= pi.x && pj.y >= pi.y) {
						good = false;
						break;
					}
				}
			}
			if (good) {
				res.add(pi);
			}
		}
		
		Collections.sort(res, new Comparator<Pt>() {
			public int compare (Pt a, Pt b) {
				return a.x - b.x;
			}
		});
		
		System.out.println(res.size());
		for (Pt p : res) {
			System.out.println(p.x + " " + p.y);
		}
		scan.close();
	}
	
	static class Pt {
		int x;
		int y;
		public Pt (int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
