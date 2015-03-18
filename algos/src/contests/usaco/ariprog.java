package contests.usaco;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
ID: thatner1
LANG: JAVA
TASK: ariprog
 */
class ariprog {
	public static void main (String [] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("ariprog.in"));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("ariprog.out")));
		
		int N = Integer.parseInt(reader.readLine());
		int M = Integer.parseInt(reader.readLine());
		int bound = (M*M*2);
		
		reader.close();
		
		
		boolean[] bisquares = new boolean[M*M*2 + 1];
		for (int p = 0; p <= M; p++) {
			for (int q = 0; q <= M; q++) {
				int i = p*p + q*q;
				bisquares[i] = true;
				//bisquares.add(i);
			}
		}
		
		ArrayList<int[]> res = new ArrayList<int[]>();
		for (int i = 0; i <= bound; i++) {
			if (!bisquares[i]) continue;
			for (int b = 1; i + b * (N - 1) <= bound; b++) {
				int elem = i + b * (N - 1);
				int len = 1;
				//System.out.print(elem+",");
				while (bisquares[elem]) {
					len++;
					//System.out.print((elem + b) +",");
					if (len == N) {
						//System.out.print("accepted");
						res.add(new int[] {i, b});
						//System.out.println(i + " " + b + "\n");
						//writer.write(i + " " + b + "\n");
						break;
					}
					
					elem -= b;
				}
			}
			
		}
		
		Collections.sort(res, new Comparator<int[]> () {
			public int compare (int[] a, int[] b) {
				int bySecond = a[1] - b[1];
				if (bySecond == 0) {
					return a[0] - b[0];
				} else return bySecond * 100;
			}
		});
		
		System.out.println("Size: " + res.size());
		for (int[] c : res) {
			//System.out.println(Arrays.toString(c));
			writer.write(c[0] + " " + c[1]+"\n");
		}
		
		if (res.size() == 0) writer.write("NONE\n");
		
		
		writer.close();
		
	}
}
