import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



public abstract class Figur implements Serializable {
	
	public enum form { Kreis, Rechteck }
	public String name;
	public int x = 0, y = 0, speed = 0,
	height = 0, width = 0;
	
	public Figur(String name, int x, int y, int speed, int height, int width){
		this.name = name;
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.height = height;
		this.width = width;
		
	}
	
	public String toString(){
		return "Name: " + name + " Speed: " + speed + " Koordianten (x/y): " + x + "/" + y + " Höhe x Breite: " + height + " x " + width;
	}
	
	//public abstract boolean collidesWith(Figur fig); 
}
