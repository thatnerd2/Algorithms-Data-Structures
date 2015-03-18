package contests.usaco;
/* 
ID: thatner1
LANG: JAVA
TASK: milk
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


class milk {
	public static void main (String [] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("milk.in"));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("milk.out")));
	
		String nm = reader.readLine();
		int amt = Integer.parseInt(nm.split(" ")[0]);
		int nFarmers = Integer.parseInt(nm.split(" ")[1]);
		
		ArrayList<Farmer> farmers = new ArrayList<Farmer>();
		for (int i = 0; i < nFarmers; i++) {
			String line = reader.readLine();
			int cost = Integer.parseInt(line.split(" ")[0]);
			int canSell = Integer.parseInt(line.split(" ")[1]);
			farmers.add(new Farmer(cost, canSell));
			
		}
		
		Collections.sort(farmers, new Comparator<Farmer> () {
			@Override
			public int compare (Farmer a, Farmer b) {
				return a.cost - b.cost;
			}
		});
		
		//Cheapest first!
		int count = 0;
		int cost = 0;
		while (amt > 0) {
			Farmer f = farmers.get(count);
			if (amt >= f.canSell) {
				System.out.println("Bought " + f.canSell + " at cost " + f.cost);
				cost += f.cost * f.canSell;
				amt -= f.canSell;
				count++;
			}
			else {
				cost += f.cost * amt;
				amt = 0;
				count++;
			}
		}
		writer.write(cost+"\n");
		
		reader.close();
		writer.close();
		System.exit(0);
	}
	
	static class Farmer {
		int cost;
		int canSell;
		
		public Farmer (int c, int s) {
			cost = c;
			canSell = s;
		}
	}
}
