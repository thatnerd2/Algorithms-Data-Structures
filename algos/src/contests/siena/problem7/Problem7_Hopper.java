package contests.siena.problem7;

import java.util.ArrayList;
import java.util.Arrays;

public class Problem7_Hopper {
	public static void main (String [] args) {
		int[] pos = {0, 3, 6};
		int red = 8;
		
		hoppers(pos, red);
	}
	
	public static void hoppers (int[] frogs, int red) {
		ArrayList<Lilypad> pads = new ArrayList<Lilypad>();
		for (int i = 0; i < 13; i++) {
			Lilypad l = new Lilypad(i);
			pads.add(l);
		}
		
		String[] connections = {"0 1 2", "0 3 6", "0 5 10", "1 3 5", "1 4 7", "2 4 6", "2 1 0",
				"3 6 9", "4 6 8", "5 3 1", "5 6 7", "5 8 11", "6 3 0", "6 4 2", "6 9 12", "6 8 10",
				"7 6 5", "7 4 1", "7 9 11", "8 6 4", "9 6 3", "10 5 0", "10 8 6", "10 11 12",
				"11 8 5", "11 9 7", "12 9 6", "12 11 10"};
		
		for (int i = 0; i < connections.length; i++) {
			String[] parts = connections[i].split(" ");
			System.out.println(Arrays.toString(parts));
			Lilypad from = padById(Integer.parseInt(parts[0]), pads);
			Lilypad over = padById(Integer.parseInt(parts[1]), pads);
			Lilypad to = padById(Integer.parseInt(parts[2]), pads); 
			
			Conn c = new Conn (from, over, to);
			System.out.println(from);
			from.addConn(c);
		}
		
		//NOW WE HAVE THE DATA!
		setup(frogs, red, pads);
		
		ArrayList<String> res = solve(frogs, red, pads);
		
	}
	
	public static String solve (int[] state, int red, ArrayList<Lilypad> pads) {
		//Depth first search I guess
		String status = getStatus(state);
		switch (status) {
		case "INVALID":
			return null;
		case "DONE":
			return "";
		}
		
		for (int i = 0; i < pads.size(); i++) {
			Lilypad p = pads.get(i);
			if (p.f != null) {
				Conn valid = p.getNextValid();
				if (valid != null) {
					int[] nextState = state.clone();
					//Perform the jump
					valid.to.putFrog(valid.from.f);
					valid.from.deleteFrog();
					valid.over.deleteFrog();
					
					
					//Make the move and recurse.
					
				}
			}
		}
	}
	
	public static void setup (int[] frogs, int red, ArrayList<Lilypad> pads) {
		for (int i = 0; i < frogs.length; i++) {
			Lilypad thisPad = padById(frogs[i], pads);
			Frog f = new Frog();
			thisPad.putFrog(f);
		}
		
		Lilypad redPad = padById(red, pads);
		Frog redFrog = new Frog();
		redFrog.isRed = true;
		redPad.putFrog(redFrog);
	}
	
	public static Lilypad padById (int id, ArrayList<Lilypad> pads) {
		for (Lilypad p : pads)
			if (p.id == id)
				return p;
		return null;
	}
	
	
}
