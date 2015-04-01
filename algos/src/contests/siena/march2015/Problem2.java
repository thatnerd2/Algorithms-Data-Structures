package contests.siena.march2015;

import java.util.*;

// Remember to make sure the name of the file & class match!
public class Problem2 {
	public static void main(String argnums[]) {
		Scanner sc = new Scanner(System.in);
		int K = sc.nextInt();
		sc.nextLine();
		for (int trialCount = 0; trialCount < K; trialCount++) {
			String[] s = sc.nextLine().split(" ");
			int[] nums = new int[s.length];
			for (int i = 0; i < s.length; i++) {
				nums[i] = Integer.parseInt(s[i]);
			}
			Arrays.sort(nums);

			if (nums[0] == nums[1] && nums[1] == nums[2] && nums[2] == nums[3]) {
				System.out.println("Four-of-a-Kind");

			} 
			else if ((nums[0] == nums[1] && nums[1] == nums[2])
					|| (nums[1] == nums[2] && nums[2] == nums[3])) {
				System.out.println("Three-of-a-Kind");
			} 
			else if ((nums[0] == nums[1] && nums[2] == nums[3])) {
				System.out.println("Two-Pairs");
			} 
			else if (nums[0] == nums[1] || nums[1] == nums[2] || nums[2] == nums[3]) {
				System.out.println("One-Pair");
			} 
			else {
				System.out.println("No-Matches");
			}

		}
		sc.close();
	}

	public static String getCard() {
		return null;
	}
}
