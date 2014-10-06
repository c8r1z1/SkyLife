import java.io.Serializable;

public  class Figur implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum FORM {
		Kreis, Rechteck
	};

	public String name;
	public String Typ;
	public int x = 0, y = 0, speed = 0, height = 0, width = 0;
	FORM f;

	public Figur(String Typ, String name, int x, int y, int speed, int height, int width,
			String a) {
		this.name = name;
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.height = height;
		this.width = width;
		this.Typ = Typ;
		if (a == "Kreis") {
			f = FORM.Kreis;
		} else if (a == "Rechteck") {
			f = FORM.Rechteck;
		}

	}

	public String toString() {
		return Typ + " " + name + " " + x + " " + y + " " + speed + " " + height + " " + width  + " " + f ;
	}

	// Bestimmung x-Koordinate Mittelpunkt
	public double middleX() {
		double X = 0.0;
		X = (this.x + this.x / 2) * 1.0;
		return X;
	}

	// Bestimmung y-Koordinate Mittelpunkt
	public double middleY() {
		double Y = 0.0;
		Y = (this.y + this.y / 2) * 1.0;
		return Y;
	}
}
