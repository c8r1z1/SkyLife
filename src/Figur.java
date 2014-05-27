import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



public abstract class Figur implements Serializable {
	
	public enum form { Kreis, Rechteck }
	public String name;
	public int x = 0;
	public int y = 0;
	public int speed = 0;
	
	public Figur(String name, int x, int y, int speed){
		this.name = name;
		this.x = x;
		this.y = y;
		this.speed = speed;
	}
	
	public String toString(){
		return "Name: " + name + " Speed: " + speed + " Koordianten (x/y): " + x + "/" + y;
	}
	
	//public abstract boolean collidesWith(Figur fig); 
}
