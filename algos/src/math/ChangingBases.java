package math;

public class ChangingBases {
	public static void main (String [] args) {
		String inBase2 = "101";
		String inBase4 = "321";
		int inBase10 = 20;
		
		System.out.println(Integer.parseInt(inBase2, 2)); //5
		System.out.println(Integer.parseInt(inBase4, 4)); //57
		System.out.println(Integer.toString(inBase10, 2)); //Converts 20 to base 2, output: 10100
		
		//Converts inBase2 to base 10, then converts base 10 to base 4: output: 11
		System.out.println(Integer.toString(Integer.parseInt(inBase2, 2), 4)); 
		
		/* So to summarize:
		 * Have a number in some strange base and know what base it is?
		 * 		Use "" + number and thus turn it into a String
		 * 		Use Integer.parseInt(string, base) to get the base 10 representation
		 * 
		 * Have a number in base 10 and want to convert it to some weird base?
		 * 		Use Integer.toString(base 10 integer, new base) to get a String representation
		 * 
		 * Have a number in some weird base and want to convert it to another weird base?
		 * 		Combine the first two approaches (see last example)
		 */
	}
}
