package topcoder;

import java.util.ArrayList;

public class SRM_459_Div2_1000 {
	/* TODO */
	public static void main (String [] args) {
		String[] in = {"E000",
						"1000",
						"1000",
						"1000"};
		int startLanding = 1;
		int K = 1;
		System.out.println("ans: " + getProbability(in, startLanding, K));
	}
	
	public static double getProbability (String[] arr, int startLanding, int K) {
		ArrayList<Landing> landings = new ArrayList<Landing>();
		for (int i = 0; i < arr.length; i++) {
			landings.add(new Landing("" + i));
		}
		
		
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length(); j++) {
				char conn = arr[i].charAt(j);
				if (conn != '0' && Character.isDigit(conn)) {
					Landing from = landingByName("" + i, landings);
					from.addPath(landingByName((Character.getNumericValue(conn) - 1) + "", landings));
				}
				else if (conn == 'E') {
					landingByName(i + "", landings).setType("SAFE");
					break;
				}
				else if (conn == 'P') {
					landingByName(i + "", landings).setType("UNSAFE");
					break;
				}
			}
		}
		
		//Landings should be all set up now.  Calc the probability via crazy DFSes?  recursively dfs freaking everywhere?
		int denom = 0;
		int num = 0;
		for (int i = 0; i < landings.size(); i++) {
			Landing start = landings.get(i);
			System.out.println(start.paths);
			String possiblePaths = dfs(start, 0, K); 
			System.out.println(possiblePaths);
			num += Integer.parseInt(possiblePaths.split(" ")[0]);
			denom += Integer.parseInt(possiblePaths.split(" ")[1]);
		}
		
		String fromDesired = dfs(landingByName(startLanding + "", landings), 0, K);
		double possibleFromStart = Double.parseDouble(fromDesired.split(" ")[0]);
		System.out.println(possibleFromStart + " " + num + " " + denom);
		
		return Double.parseDouble(fromDesired.split(" ")[0]) / ((double)num / (double)denom);
	}
	
	public static String dfs (Landing landing, int depth, int K) {
		if (depth > K) {
			return "0 0";
		}
		int num = 0;
		int denom = 0;
		if (landing.type.equals("SAFE") && depth == K) {
		System.out.println("Hello");
			return "1 1";
		}
		else if (landing.type.equals("UNSAFE") && depth == K) {
			return "0 1";
		}
		else {
			for (Landing neighbor : landing.paths) {
				String travel = dfs(neighbor, depth + 1, K);
				num += Integer.parseInt(travel.split(" ")[0]);
				denom += Integer.parseInt(travel.split(" ")[1]);
			}
			return num + " " + denom;
		}
	}
		
	
	public static Landing landingByName (String name, ArrayList<Landing> landings) {
		for (Landing l : landings) {
			if (l.name.equals(name)) {
				return l;
			}
		}
		return null;
	}
	
	static class Landing {
		ArrayList<Landing> paths;
		String name;
		String type;
		
		public Landing (String name) {
			this.name = name;
			type = "DEFAULT";
			paths = new ArrayList<Landing>();
		}
		
		public void setType (String type) {
			this.type = type;
		}
		
		public void addPath (Landing l) {
			paths.add(l);
		}
		
		public String toString () {
			return name;
		}
	} 
}	
