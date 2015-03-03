package math;

import java.util.Arrays;

public class Fractions {
	
	public static void main (String [] args) {
		/* Represent fractions with int[2] arrays */
		int[] a = {1, 2}; // 1/2
		int[] b = {3, 4}; // 3/4
		System.out.println(Arrays.toString(multiplyFractions(a, b))); // 3/8
		System.out.println(Arrays.toString(addFractions(a, b))); // 5/4
	
	}
	
	public static int[] multiplyFractions (int[] a, int[] b) {
		int[] c = {a[0] * b[0], a[1] * b[1]};
		reduceFraction(c);
		return c;
	}
	
	public static void reduceFraction (int[] a) {
		int b = gcd(a[0], a[1]);
		a[0] /= b;
		a[1] /= b;
	}
	
	public static int[] addFractions (int[] a, int[] b) {
		int denom = lcm(a[1], b[1]);
		int[] c = {denom /a[1] * a[0] + denom/b[1] * b[0], denom};
		reduceFraction(c);
		return c;
	}
	
	
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
}
