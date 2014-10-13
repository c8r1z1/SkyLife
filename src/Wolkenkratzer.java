public class Wolkenkratzer extends Figur {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static int x = 0, y = 0, height = 325, width = 70, speed = 0;
	static String a = "Rechteck";
	static String Typ = "Wolkenkratzer";

	public Wolkenkratzer(String name, int x, int y) {
		super(Typ, name, x, y, speed, height, width, a);
		Wolkenkratzer.x = x;
		Wolkenkratzer.y = y;
	}

	@Override
	public String toString() {
		return Typ + " " + name + " " + x + " " + y + " " + speed + " "
				+ height + " " + width + " " + f;
	}

}
