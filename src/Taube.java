
public class Taube extends Flugobjekt{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	static int x = 0, y = 0, height = 30, width = 45, speed = 1;
	static String a = "Rechteck";
	static String Typ = "Taube";

	public Taube(String name, int x, int y) {
		super(name, x, y, speed, height, width, a, Typ);
		Taube.x = x;
		Taube.y = y;
	}
	
	@Override
	public String toString() {
		return Typ + " " + name + " " + x + " " + y + " " + speed + " " + height + " " + width  + " " + f ;
	}
	
	
}
