package contests.usaco.notprintedyet;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

/*
ID: thatner1
LANG: JAVA
TASK: concom
 */
class concom {
	public static void main (String [] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("concom.in"));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("concom.out")));
		
		
		int numCompanies = Integer.parseInt(reader.readLine());
		Company[] companies = new Company[100];
		for (int i = 0; i < 100; i++) {
			companies[i] = new Company(i);
		}
		
		for (int i = 0; i < numCompanies; i++) {
			String[] parts = reader.readLine().split(" ");
			//A owns B
			Company A = companyById(Integer.parseInt(parts[0]), companies);
			Company B = companyById(Integer.parseInt(parts[1]), companies);
			int percentage = Integer.parseInt(parts[2]);
			A.addCompany(B, percentage);
		}
		
			for (int j = 0; j < companies.length;j++) {
				companies[j].computeOwnership();
			}
		
		
		for (int i = 0; i < companies.length; i++) {
			for (int j = 0; j < companies[i].percs.length; j++) {
				if (companies[i].percs[j] > 50) {
					System.out.println((companies[i].id) + " " + (j));
					writer.write(companies[i].id + " " + j + "\n");
				}
			}
		}
		
		reader.close();
		
		writer.close();
	}
	
	public static Company companyById (int id, Company[] companies) {
		for (Company c : companies) {
			if (c.id == id) {
				return c;
			}
		}
		return null;
	}
	
	static class Company {
		int id;
		Company[] owns;
		int[] percs;
		
		public Company (int id) {
			this.id = id;
			owns = new Company[100];
			percs = new int[100];
		}
		
		public void addCompany (Company c, int percentage) {
			percs[c.id] = percentage;
			owns[c.id] = c;
		}
		
		public void computeOwnership () {
			boolean[] assimilated = new boolean[100];
			boolean again = true;
			while (again) {
				again = false;
				for (int i = 0; i < owns.length; i++) {
					if (percs[i] > 50 && owns[i] != null && !assimilated[i]) {
						assimilated[i] = true;
						for (int j = 0; j < owns[i].percs.length; j++) {
							
							percs[j] += owns[i].percs[j];
							
							again = true;
						}
					}
				}
			}
			
		}
		
		/*public ArrayList<Integer> getAllOwned () {
			ArrayList<Integer> res = new ArrayList<Integer>();
			for (int i = 0; i < owns.size(); i++) {
				if (percs.get(i) > 50) {
					res.add(owns.get(i).id);
					res.addAll(owns.get(i).getAllOwned());
				}
			}
			return res;
		}*/
	}
}
