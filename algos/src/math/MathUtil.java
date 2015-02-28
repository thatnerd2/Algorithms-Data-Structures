package math;

public class MathUtil {
	
	/*--------------- GCD Methods -------------*/
	private static int gcd(int a, int b) {
		while (b > 0) {
			int temp = b;
			b = a % b;
			a = temp;
		}
		return a;
	}
	
	public static int gcd(int[] input)
	{
	    int result = input[0];
	    for(int i = 1; i < input.length; i++) 
	    	result = gcd(result, input[i]);
	    return result;
	}
	
	/*------------END GCD Methods -------------*/
	
	/*--------------- LCM Methods -------------*/
	public static int lcm(int a, int b)
	{
	    return a * (b / gcd(a, b));
	}

	public static int lcm(int[] input)
	{
	    int result = input[0];
	    for(int i = 1; i < input.length; i++) 
	    	result = lcm(result, input[i]);
	    
	    return result;
	}
	/*------------END LCM Methods -------------*/
	
	public static boolean isPrime (int a) {
		if (a % 2 == 0) {
			return false;
		}
		
		for (int i = 3; i < Math.sqrt(a) + 1; i++) {
			if (a % i == 0) {
				return false;
			}
		}
		
		return true;
	}

}
