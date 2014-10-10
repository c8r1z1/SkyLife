import java.util.ArrayList;
import java.util.List;


// Möglchkeit für neue Bewegung
// Taube und Greifvogel vor, zurück, diagonal.. bei Berührung des Rahmens, zurückprallen, einfallswinkel gleich ausfalls winkel
// Flugzeug von rechts nach links.. bei Berührung des Rahmens.. eintritt auf andere Seite
// Wolkenkratzer bewegen sich nicht
// Meteor senkrechter eintritt in mitte des panels


public class MovementTest extends Thread {
	
	SkyLife app;
	boolean running = true;
	List<Figur> RemoveListFlug = new ArrayList<Figur>();
	int countFlug = 0;
	int countTaube = 0;

	

	public MovementTest(SkyLife app){
		this.app = app;
	}




	public void MovementTaube(){
		synchronized (app.ObjectList){
			for (Figur f : app.ObjectList){

				if(f instanceof Taube){
					countFlug++;
					f.x = computeXTaube(f.x, f.speed, countFlug);
					f.y = computeYTaube(f.y, f.speed, countFlug);
					
				}
			
				if(f instanceof Meteorit){
					f.y += 10;
					if(f.y >= app.PanelHeight - f.height){
						GameOver("Meteoriteneinschlag, die Natur übt Rache für tote Vögel");
					}
				}
			}
		}
		// entfernen der Flugzeuge aus der ObjectListe
		for(int i = 0; i < RemoveListFlug.size(); i++){
			app.ObjectList.remove(RemoveListFlug.get(i));
		}
	}
	
	
	public void MovementFlugzeug(){
		synchronized (app.ObjectList){
			for (Figur f : app.ObjectList){
				if(f instanceof Flugzeug){
					countFlug++;
					f.x = computeXFlugzeug(f.x, f.speed);
					f.y = computeYFlugzeug(f.y, f.speed, countFlug);
					if(f.y == -666){
						RemoveListFlug.add(app.ObjectList.get(app.ObjectList.indexOf(f)));
						app.comboBoxNameEnt.removeItemAt(app.ObjectList.indexOf(f));
					}
				}
			}
		}
		// entfernen der Flugzeuge aus der ObjectListe
		for(int i = 0; i < RemoveListFlug.size(); i++){
			app.ObjectList.remove(RemoveListFlug.get(i));
		}
	}
	
	
	
	
	
	//Korrektur Flugzeug.. links raus, rechts wieder rein
	public int computeXFlugzeug(int x, int speed){
		if((x + 100) < 0){
			x = app.PanelWidth;
		} else{
			x = x - speed;
		}
		return x;
	}
	
	// wenn Flugzeug oben aus dem Panel rausgeht soll es komplett verschwinden.
	// aus Objectliste, comboBoxEnt löschen
	public int computeYFlugzeug(int y,int speed, int countFlug){
		if((y + 80) < 0){
			y = -666;
		} else{
	// nach 10 Berechnungen Anstieg	
			if((countFlug % 10) == 0 || (countFlug % 10) == 1){
				y -= 2;
			}
		}
		return y;
	}

	public double randXTaube(){
		double x = Math.random();
		return x;
	}
	
	// koeffizient zur besstimmung der richtung
	double i = 1;
	//berechnung von x der taube
	public int computeXTaube(int x, int speed, int countFlug){
		
		if (countFlug % 100 == 0){
			i = Math.random();
		}
		int mult = 1;
		if(i < 0.5){
			mult = -1;
		}
		// änderung berechnungen
		if((countFlug % 3) == 0){
			x = x + mult * speed;
		}
		return x;
	}
	// berechnung von y der taube
	public int computeYTaube(int y, int speed, int countFlug){
		if (countFlug % 150 == 0){
			i = Math.random();
		}
		int mult = 1;
		if(i < 0.5){
			mult = -1;
		}
		// änderung berechnungen
		if((countFlug % 10) == 0){
			y = y + (mult * speed)/2;
		}
		return y;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//Löschung verbliebende Objekte bei Game Over
	public void DeleteObjectCollisionAll(){
		app.ObjectList.clear();
		app.comboBoxNameEnt.removeAllItems();
		app.panel.repaint();
	}

	//Game Over da Kollateralschaden entstanden
	public void GameOver(String a){		
		app.lblMessageTxt.setText("Game Over: " + a);
		//Löschen aller verbleibenden Objekte in der Liste
		DeleteObjectCollisionAll();
		//Aktivierung des Buttons zum Setzen der Panelgröße, wenn keine Objekte im Panel
		app.btnSetPanelSize.setEnabled(true);
		app.stop.setEnabled(false);
		app.updateInfo();
		app.killedAnimals = 0;
		app.updateKilledAnimals();
		SkyLife.tmov.running = false;
		SkyLife.trep.running = false;
		SkyLife.tcol.running = false;
	}

	public void run(){

		while(running){
						
			//Aufruf Movement Methode
			MovementTaube();
			MovementFlugzeug();

			try{
				sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}


		}
	}

}
