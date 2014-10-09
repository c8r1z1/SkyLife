
// M�glchkeit f�r neue Bewegung
// Taube und Greifvogel vor, zur�ck, diagonal.. bei Ber�hrung des Rahmens, zur�ckprallen, einfallswinkel gleich ausfalls winkel
// Flugzeug von rechts nach links.. bei Ber�hrung des Rahmens.. eintritt auf andere Seite
// Wolkenkratzer bewegen sich nicht
// Meteor senkrechter eintritt in mitte des panels


public class MovementTest extends Thread {

	boolean running = true;

	SkyLife app;
	double mrx = Math.random();
	int counterX = 0;
	double mrx2 = Math.random();
	int counterX2 = 0;
	double mry = Math.random();
	int counterY = 0;
	double mry2 = Math.random();
	int counterY2 = 0;

	public MovementTest(SkyLife app){

		this.app = app;

	}

	public void CorrectXMovement(Figur f){

		if(f.x < 0){
			f.x = 0;
			changeDirectionX();
		}
		if((f.x + f.width) > app.PanelWidth){
			f.x = (app.PanelWidth - f.width);
			changeDirectionX();
		}

	}

	public void CorrectYMovement(Figur f){

		if(f.y < 0){
			f.y = 0;
			changeDirectionY();
		}
		if((f.y + f.height) > app.PanelHeight){
			f.y = (app.PanelHeight - f.height);
			changeDirectionY();
		}
	}
	//F�hrt zur Neuausrichtung der Bewegung bei Kontakt mit dem Panelrand
	public void changeDirectionY() {
		counterY = 200;
		counterY2 = 200;
	}
	public void changeDirectionX() {
		counterX = 200;
		counterX2 = 200;
	}

	public double MathRandomX(){
		if(counterX >= 150){
			mrx = Math.random();
			counterX = 0;
		}
		counterX++;
		return mrx;
	}

	public double MathRandomX2(){
		if(counterX2 >= 150){
			mrx2 = Math.random();
			counterX2 = 0;
		}
		counterX2++;
		return mrx2;
	}

	public double MathRandomY(){
		if(counterY >= 150){
			mry = Math.random();
			counterY = 0;
		}
		counterY++;
		return mry;
	}

	public double MathRandomY2(){
		if(counterY2 >= 150){
			mry2 = Math.random();
			counterY2 = 0;
		}
		counterY2++;
		return mry2;
	}

	public void Movement(){
		synchronized (app.ObjectList){
			for (Figur f : app.ObjectList){

				if(f instanceof Taube || f instanceof Greifvogel){
					f.x = (int) (f.x + f.speed * (MathRandomX() - MathRandomX2()) * 2 + 1);
					//Korrektur x-Koordinate bei Lage au�erhalb des Panels
					CorrectXMovement(f);

					f.y = (int) (f.y + f.speed * (MathRandomY() - MathRandomY2()) * 2 + 1);
					//Korrektur y-Koordinate bei Lage au�erhalb des Panels
					CorrectYMovement(f);				
				}
				if(f instanceof Flugzeug){
					f.x = f.x - f.speed;
					f.x = correctXFlugzeug(f.x);
					f.y = f.y - f.speed/2;
					f.y = correctYFlugzeug(f.y);
					if(f.y == -666){
						app.ObjectList.remove(app.ObjectList.indexOf(f));
					}
				}
				
				if(f instanceof Meteorit){
					f.y += 10;
					CorrectYMovement(f);
					if(f.y >= app.PanelHeight - f.height){
						GameOver("Meteoriteneinschlag, die Natur �bt Rache f�r tote V�gel");
					}
				}
			}
		}
	}
	
	//Korrektur Flugzeug.. links raus, rechts wieder rein
	public int correctXFlugzeug(int x){
		if((x + 100) < 0){
			x = app.PanelWidth;
		}
		return x;
	}
	
	// wenn Flugzeug oben aus dem Panel rausgeht soll es komplett verschwinden.
	// aus Objectliste, comboBoxEnt l�schen
	public int correctYFlugzeug(int y){
		if((y + 80) < 0){
			y = -666;
		}
		
		return y;
	}


	//L�schung verbliebende Objekte bei Game Over
	public void DeleteObjectCollisionAll(){
		app.ObjectList.clear();
		app.comboBoxNameEnt.removeAllItems();
		app.panel.repaint();
	}

	//Game Over da Kollateralschaden entstanden
	public void GameOver(String a){		
		app.lblMessageTxt.setText("Game Over: " + a);
		//L�schen aller verbleibenden Objekte in der Liste
		DeleteObjectCollisionAll();
		//Aktivierung des Buttons zum Setzen der Panelgr��e, wenn keine Objekte im Panel
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
			Movement();

			try{
				sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}


		}
	}

}
