import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Figur implements Serializable {
	
	public enum form { Kreis, Rechteck }
	public String name;
	public int x = 0;
	public int y = 0;
	
	public Figur(String name, int x, int y){
		this.name = name;
		this.x = x;
		this.y = y;
	}
	
	public String toString(){
		return name;
	}

}
