import java.io.Serializable;


public class Figur implements Serializable {
	
	public enum form { Kreis, Rechteck }
	public String name;
	
	public Figur(String name){
		this.name = name;
	}

}
