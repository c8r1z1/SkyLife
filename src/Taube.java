
public class Taube extends Flugobjekt{
	static double speed = 1;

	int x,y;
	int width = 45;
	int height = 30;


	public Taube(String name, int x, int y) {
		super(name, x, y);
		form f = form.Kreis;
	}

}
