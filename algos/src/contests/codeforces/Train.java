package contests.codeforces;

public class Train {
	public static void main (String [] args) {
		int n = 5;
		int m = 3;
		int k = 2;
		String dir = "to head";
		String info = "00010001";
		solve(n, m, k, dir, info);
	}
	
	public static void solve (int n, int stowaway, int controller, String dirStr, String info) {
		stowaway -= 1;
		controller -= 1;
		boolean[] moving = new boolean[info.length()];
		for (int i = 0; i < info.length(); i++) {
			if (info.charAt(i) == '0') moving[i] = true;
		}
		int dir = (dirStr.equals("to head")) ? -1 : 1;
		
		System.out.println("init: " + stowaway + " , " + controller);
		boolean invincible = false;
		for (int i = 0; i < moving.length; i++) {
			//Stowaway moves
			//invincible = false;
			if (moving[i]) {
				System.out.println("hey");
				stowaway += dir;
				if (stowaway < 0) stowaway = 0;
				if (stowaway > n - 1) stowaway = n - 1;
			}
			else {
				if (dir == 1) {
					stowaway = 0;
					//invincible = true;
				}
				else if (dir == -1) {
					stowaway = n - 1;
					//invincible = true;
				}
			}
			
			//Controller moves
			controller += dir;
			if (controller == 0 || controller == n - 1) {
				dir *= -1;
			}
			
			if (controller == stowaway && !invincible) {
				System.out.println("CONTROLLER " + (i + 1));
				break;
			}
			System.out.println("S: " + stowaway + " C: " + controller);
		}
		
		System.out.println("STOWAWAY");
		
	}
}
