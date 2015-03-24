package contests.siena;

import java.util.Scanner;

public class LetterGradeConverter {
	public static void main (String [] args) {
		Scanner scan = new Scanner (System.in);
		int grade = scan.nextInt();
		
		if (grade >= 95) {
			System.out.println("A+");
		}
		else if (grade >= 90) {
			System.out.println("A");
		}
		else if (grade >= 85) {
			System.out.println("A-");
		}
		else if (grade >= 80) {
			System.out.println("B+");
		}
		else if (grade >= 75) {
			System.out.println("B");
		}
		else if (grade >= 70) {
			System.out.println("B-");
		}
		else if (grade >= 65) {
			System.out.println("C+");
		}
		else if (grade >= 60) {
			System.out.println("C");
		}
		else if (grade >= 55) {
			System.out.println("C-");
		}
		else if (grade >= 50) {
			System.out.println("D+");
		}
		else if (grade >= 45) {
			System.out.println("D");
		}
		else if (grade >= 40) {
			System.out.println("D-");
		} else {
			System.out.println("F");
		}
		scan.close();
	}
}
