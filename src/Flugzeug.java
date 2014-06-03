


public class Flugzeug extends Flugobjekt {


	public Flugzeug(String name, int x, int y, int speed, int height, int width, String a) {
		super(name, x, y, speed, height, width, a);
		//form f = form.Kreis;
	}

	@Override
	public String toString(){
		return "Flugzeug " + name;
		
	}

}
