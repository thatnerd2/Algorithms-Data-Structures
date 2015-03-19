package string;

import java.util.regex.Pattern;

public class Regices {
	public static void main(String[] args) {
		System.out.println("t.a".matches("\\.")); // not a full match
		System.out.println("t+a".contains(".")); // not a regex
		System.out.println(Pattern.compile("q").matcher("aqa").find());
		System.out.println(Pattern.compile("q").matcher("aqa").matches()); // but not perfect match
		System.out.println();
		
	}
}
