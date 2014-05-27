
public class Flugobjekt extends Figur{
	
	public Flugobjekt(String name, int x, int y, int speed){
		super(name, x, y, speed);
	}

	
	public boolean collidesWith(Flugzeug flugzeug) {
		// TODO Auto-generated method stub
		return false;
	}

}
