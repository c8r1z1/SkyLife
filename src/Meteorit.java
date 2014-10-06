
public class Meteorit extends Figur {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	static String name = "Destroyer", a = "Rechteck";
	static int x = 0, y = 0, speed = 3, height = 285, width = 99;
	static String Typ = "Meteorit";
	
	public Meteorit(String name, int PanelWidth) {
		super(Typ, name, (PanelWidth / 2), y, speed, height, width, a);
	}
	
	

}
