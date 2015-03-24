package contests.siena;

import java.util.Scanner;

public class BeThereBeSquare {
	public static void main (String [] args) {
		Scanner scan = new Scanner (System.in);
		int size = scan.nextInt();
		
		System.out.println(size * (size + 1) * (2*size + 1) / 6);
		scan.close();
	}
}
