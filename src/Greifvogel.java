
public class Greifvogel extends Flugobjekt{
	static double speed = 2;

	int x,y;
	int width = 50;
	int height = 20;


	public Greifvogel(String name, int x, int y, int speed) {
		super(name, x, y, speed);
		form f = form.Kreis;

	}

}
