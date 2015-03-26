package contests.hackerrank;

import java.util.Scanner;

public class SnakesAndLadders {
	public static void main (String [] args) {
		Scanner scan = new Scanner (System.in);
		int numTests = scan.nextInt();
		scan.nextLine();
		
		for (int t = 0; t < numTests; t++) {
			String[] in = scan.nextLine().split(", ");
			int numSnakes = Integer.parseInt(in[0]);
			int numLadders = Integer.parseInt(in[1]);
			
			String[] snakeCoords = scan.nextLine().split(" ");
			Snake[] snakes = new Snake[numSnakes];
			for (int i = 0; i < snakeCoords.length; i++) {
				String[] rawSnake = snakeCoords[i].split(",");
				snakes[i] = new Snake(Integer.parseInt(rawSnake[1]), Integer.parseInt(rawSnake[0]));
			}
			
			String[] ladderCoords = scan.nextLine().split(" ");
			Ladder[] ladders = new Ladder[numLadders];
			for (int i = 0; i < ladders.length; i++) {
				String[] rawLadder = ladderCoords[i].split(",");
				ladders[i] = new Ladder(Integer.parseInt(rawLadder[0]), Integer.parseInt(rawLadder[1]));
			}
			
			//Now what!?
		}
		
		
		scan.close();
	}
	
	static class Snake {
		int tail;
		int head;
		
		public Snake (int t, int m) {
			tail = t;
			head = m;
		}
	}
	
	static class Ladder {
		int tail;
		int head;
		
		public Ladder (int t, int h) {
			tail = t;
			head = h;
		}
	}
}
