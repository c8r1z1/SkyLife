
public class Wolkenkratzer extends Figur{

	int x,y;
	int width = 70;
	int height = 325;
	
		
	public Wolkenkratzer(String name, int x, int y, int speed){
		super(name, x, y, speed);
		form f = form.Rechteck;
	}

//	@Override
//	public boolean collidesWith(Figur fig) {
//		// TODO Auto-generated method stub
//		return false;
//	}

}
