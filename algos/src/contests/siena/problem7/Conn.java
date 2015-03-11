package contests.siena.problem7;

public class Conn {
	Lilypad from;
	Lilypad to;
	Lilypad over;
	
	public Conn (Lilypad f, Lilypad o, Lilypad t) {
		from = f;
		to = t;
		over = o;
	}
}
