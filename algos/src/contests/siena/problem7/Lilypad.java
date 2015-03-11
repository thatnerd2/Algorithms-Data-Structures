package contests.siena.problem7;

import java.util.ArrayList;

public class Lilypad {
	int id;
	ArrayList<Conn> connections;
	Frog f;
	
	public Lilypad (int id) {
		this.id = id;
		connections = new ArrayList<Conn>();
	}
	
	public void putFrog (Frog f) {
		this.f = f;
	}
	
	public void deleteFrog () {
		f = null;
	}
	
	public Conn getNextValid () {
		for (int i = 0; i < connections.size(); i++) {
			Conn c = connections.get(i);
			//System.out.println("My id : " + id + " Over (" + c.over.id + ") : " + c.over.f + " and to (" + c.to.id + "): " + c.to.f);
			if (c.over.f != null && c.to.f == null) {
				
				return c;
			}
		}
		return null;
	}
	
	public void addConn (Conn c) {
		connections.add(c);
	}
}
