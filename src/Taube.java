
public class Taube extends Flugobjekt{

	int x,y;
	int width = 45;
	int height = 30;


	public Taube(String name, int x, int y, int speed) {
		super(name, x, y, speed);
		form f = form.Kreis;
	}

}
