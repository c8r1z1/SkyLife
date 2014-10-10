
public class Greifvogel extends Flugobjekt{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	static int x = 0, y = 0, height = 20, width = 50, speed = 3;
	static String a = "Rechteck";
	static String Typ = "Greifvogel";
	
	public Greifvogel(String name, int x, int y) {
		super(name, x, y, speed, height, width, a, Typ);
		Greifvogel.x=x;
		Greifvogel.y=y;

	}
	
	@Override
	public String toString() {
		return Typ + " " + name + " " + x + " " + y + " " + speed + " " + height + " " + width  + " " + f ;
	}

}
