


public class Flugzeug extends Flugobjekt {
	
	static int x = 0, y = 0, height = 80, width = 100, speed = 3;
	static String a = "Rechteck";

	public Flugzeug(String name) {
		super(name, x, y, speed, height, width, a);
	}

	@Override
	public String toString(){
		return "Flugzeug " + name;
		
	}

}
