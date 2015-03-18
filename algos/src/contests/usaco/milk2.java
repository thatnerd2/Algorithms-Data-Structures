package contests.usaco;
/*
ID: thatner1
LANG: JAVA
TASK: milk2
*/


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

class milk2 {
	public static void main (String [] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("milk2.in"));
		
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("milk2.out")));
		
		int n = Integer.parseInt(reader.readLine());
		Farmer[] farmers = new Farmer[n];
		for (int i = 0; i < n; i++) {
			String[] tokens = reader.readLine().split(" ");
			farmers[i] = new Farmer(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
		}
		
		int record = 0;
		int recordIdle = 0;
		int minStartTime = Integer.MAX_VALUE;
		int maxEndTime = 0;
		for (int i = 0; i < farmers.length; i++) {
			minStartTime = Math.min(farmers[i].start, minStartTime);
			maxEndTime = Math.max(farmers[i].end, maxEndTime);
		}
		
		int idleCount = 0;
		int workCount = 0;
		for (int i = minStartTime; i < maxEndTime; i++) {
			boolean idle = true;
			for (int j = 0; j < farmers.length; j++) {
				if (i >= farmers[j].start && i < farmers[j].end){
					idle = false;
					break;
				}
			}
			if (idle) {
				idleCount++;
				record = Math.max(record, workCount);
				workCount = 0;
			}
			else {
				workCount++;
				recordIdle = Math.max(recordIdle, idleCount);
				idleCount = 0;
			}
		}
		record = Math.max(record, workCount);
		recordIdle = Math.max(recordIdle, idleCount);
		
		writer.write(record + " " + recordIdle + "\n");
		reader.close();
		writer.close();
		System.exit(0);
	}
	
	static class Farmer {
		int start;
		int end;
		
		public Farmer (int s, int e) {
			start = s;
			end = e;
		}
	}
}
