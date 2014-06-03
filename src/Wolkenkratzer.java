
public class Wolkenkratzer extends Figur{
		
	public Wolkenkratzer(String name, int x, int y, int speed, int height, int width, String a){
		super(name, x, y, speed, height, width, a);
		//form f = form.Rechteck;
	}

	@Override
	public String toString(){
		return "Wolkenkratzer " + name;
		
	}

}
