


public class Flugzeug extends Figur {

	static double speed = 5;
	int x, y;
	int width = 100;
	int height = 80;


	public Flugzeug(String name, int x, int y) {
		super(name, x, y);
		form f = form.Rechteck;
	}

	public boolean collidesWith(Flugzeug flugzeug) {
		if (x <= flugzeug.x + flugzeug.width && x + width >= flugzeug.x
				&& y >= flugzeug.y - flugzeug.height && y - height <= flugzeug.y) {
			return true;
		}
		return false;
	}
	
	public boolean collidesWith(Greifvogel greifvogel) {
		if (x <= greifvogel.x + greifvogel.width && x + width >= greifvogel.x && y >= greifvogel.y - greifvogel.height && y - height <= greifvogel.y) {
			return true;
		}
		return false;
	}
	public boolean collidesWith(Wolkenkratzer wolkenkratzer) {
		if (x <= wolkenkratzer.x + wolkenkratzer.width && x + width >= wolkenkratzer.x && y >= wolkenkratzer.y - wolkenkratzer.height && y - height <= wolkenkratzer.y) {
			return true;
		}
		return false;
	}

}
