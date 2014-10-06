
public class Flugzeug extends Flugobjekt {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	static int x = 0, y = 0, height = 80, width = 100, speed = 3;
	static String a = "Rechteck";
	static String Typ = "Flugzeug";

	public Flugzeug(String name, int x, int y) {
		super(name, x, y, speed, height, width, a, Typ);
		Flugzeug.x = x;
		Flugzeug.y = y;
	}

	@Override
	public String toString() {
		return Typ + " " + name + " " + x + " " + y + " " + speed + " "
				+ height + " " + width + " " + f;
	}

}
