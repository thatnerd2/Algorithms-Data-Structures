package contests.siena.problem7;

import java.util.ArrayList;
import java.util.Arrays;

public class Problem7_Hopper {
	public static void main (String [] args) {
		int[] pos = {4, 9, 2};
		hoppers(pos);
	}
	
	public static void hoppers (int[] pos) {
		int redPos = pos[pos.length - 1];
		boolean[] frogs = new boolean[13];
		
		Arrays.fill(frogs, false);
		for (int i = 0; i < pos.length; i++) {
			frogs[pos[i]] = true;
		}
		
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
			//System.out.println(Arrays.toString(parts));
			Lilypad from = padById(Integer.parseInt(parts[0]), pads);
			Lilypad over = padById(Integer.parseInt(parts[1]), pads);
			Lilypad to = padById(Integer.parseInt(parts[2]), pads); 
			
			Conn c = new Conn (from, over, to);
			//System.out.println(from);
			from.addConn(c);
		}
		
		//NOW WE HAVE THE DATA!
		
		setup(frogs, redPos, pads);
		
		String res = solve(frogs, redPos, pads);
		System.out.println(res);
	}
	
	public static String solve (boolean[] state, int redPos, ArrayList<Lilypad> pads) {
		//Depth first search I guess
		System.out.println("Status: " + Arrays.toString(state));
		String status = getStatus(state, redPos);
		switch (status) {
		case "INVALID":
			return "INVALID";
		case "DONE":
			return ""+redPos;
		}
		
		for (int i = 0; i < pads.size(); i++) {
			Lilypad p = pads.get(i);
			
			if (p.f != null) {
				//System.out.println(p);
				Conn valid = p.getNextValid();
				//System.out.println(valid);
				if (valid != null) {
					boolean[] nextState = state.clone();
					int nextRed = redPos;
					//Perform the jump
					valid.to.putFrog(valid.from.f);
					valid.from.deleteFrog();
					valid.over.deleteFrog();
					
					nextState[valid.from.id] = false;
					nextState[valid.to.id] = true;
					nextState[valid.over.id] = false;
					
					if (valid.from.id == redPos) {
						valid.to.f.isRed = true;
						nextRed = valid.to.id;
					}
					System.out.println("MOVE FROM " + valid.from.id + " TO " + valid.to.id);
					String res = valid.from.id + " " + solve(nextState, nextRed, pads);
					
					if (isValidSolution(res)) {
						return res;
					}
					
					//Reset
					setup(state, redPos, pads);
					
				}
			}
		}
		
		return "FAILURE";
	}
	
	private static boolean isValidSolution (String s) {
		String[] parts = s.split(" ");
		for (int i = 0; i < parts.length; i++) {
			if (parts[i].length() > 2) {
				return false;
			}
		}
		return true;
	}
	
	public static void setup (boolean[] frogs, int redPos, ArrayList<Lilypad> pads) {
		for (int i = 0; i < pads.size(); i++) pads.get(i).deleteFrog();
		
		for (int i = 0; i < frogs.length - 1; i++) {
			if (frogs[i]) {
				Lilypad thisPad = padById(i, pads);
				Frog f = new Frog();
				thisPad.putFrog(f);
			}
			
		}
		
		Lilypad redPad = padById(redPos, pads);
		redPad.f.isRed = true;
	}
	
	public static String getStatus (boolean[] state, int redPos) {
		for (int i = 0; i < state.length; i++) {
			if (state[i] && i != redPos) {
				return "NOT DONE";
			}
		}
		return (state[redPos]) ? "DONE" : "INVALID";
	}
	
	public static Lilypad padById (int id, ArrayList<Lilypad> pads) {
		for (Lilypad p : pads)
			if (p.id == id)
				return p;
		return null;
	}
}