package string;

import java.util.Arrays;

public class CommonFunctions {
	public static void main (String [] args) {
		String a = "hello hello hi hello bye";
		String[] parts = a.split(" ");
		System.out.println(Arrays.toString(parts));
		// [hello, hello, hi, hello, bye]
		
		String b = "hey.there.you";
		String[] partsB = b.split("\\.");
		System.out.println(Arrays.toString(partsB));
		// [hey, there, you]
		
		String c = "hello I'm Joe";
		System.out.println(c.substring(6, 9));
		// I'm
		/* Notice how the first argument is the starting index,
		 * and the second is the starting index + length you want */
		System.out.println(c.substring(10, 13));
		/* That's right, even though 13 is the length of c, it's still valid.
		 * The right bound of substring is exclusive.*/
		
		String d = "      Boo     ";
		System.out.println(d.trim()); // Prints "Boo" WITHOUT leading or trailing whitespace
		
		String e = "heyWHAT how arWHATe you?WHAT WHAT";
		System.out.println(e.replaceAll("WHAT", "")); // Prints "hey how are you?"
		
		String f = "h    ey      there";
		System.out.println(f.replaceAll("\\s+", "")); 
		//Prints "heythere" because I used a regex that removed all whitespace
		
		String g = "hey     there     you    guys";
		System.out.println(g.replaceAll("\\s+", " "));
		//Prints "hey there you guys" because I replaced all whitespace and multiple spaces with one space.
		
		String h = "2190598wh33atu142p2091590";
		System.out.println(h.replaceAll("[1-9]", ""));
		//Prints "0whatup00" because I replaced all digits 1-9 with nothing
		
		String i = "hello there hello";
		System.out.println(i.indexOf("there"));
		//Prints 6 because "there" starts at index 6
	}
}
