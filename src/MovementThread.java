
public class MovementThread extends Thread {

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

	public MovementThread(SkyLife app){

		this.app = app;

	}

	public void CorrectXMovement(Figur f){

		if(f.x < 0){
			f.x = 0;
		}
		if((f.x + f.width) > app.PanelWidth){
			f.x = (app.PanelWidth - f.width);
		}

	}

	public void CorrectYMovement(Figur f){

		if(f.y < 0){
			f.y = 0;
		}
		if((f.y + f.height) > app.PanelHeight){
			f.y = (app.PanelHeight - f.height);
		}
	}

	public double MathRandomX(){
		if(counterX >= 50){
			mrx = Math.random();
			counterX = 0;
		}
		counterX++;
		return mrx;
	}

	public double MathRandomX2(){
		if(counterX2 >= 50){
			mrx2 = Math.random();
			counterX2 = 0;
		}
		counterX2++;
		return mrx2;
	}

	public double MathRandomY(){
		if(counterY >= 50){
			mry = Math.random();
			counterY = 0;
		}
		counterY++;
		return mry;
	}

	public double MathRandomY2(){
		if(counterY2 >= 50){
			mry2 = Math.random();
			counterY2 = 0;
		}
		counterY2++;
		return mry2;
	}

	public synchronized void Movement(){

		for (Figur f : app.ObjectList){

			if(f instanceof Flugobjekt){
				f.x = (int) (f.x + f.speed * (MathRandomX() - MathRandomX2()) * 3 );
				//Korrektur x-Koordinate bei Lage außerhalb des Panels
				CorrectXMovement(f);

				f.y = (int) (f.y + f.speed * (MathRandomY() - MathRandomY2()) * 3);
				//Korrektur y-Koordinate bei Lage außerhalb des Panels
				CorrectYMovement(f);				
			}
			if(f instanceof Meteorit){
				f.y += 10;
				CorrectYMovement(f);
				if(f.y >= app.PanelHeight - f.height){
					GameOver("Meteoriteneinschlag, die Natur übt Rache für tote Vögel");
				}
			}
		}
	}
	
	
	//Löschung verbelibende Objekte bei Game Over
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
		app.tmov.running = false;
		app.trep.running = false;
		app.tcol.running = false;
	}

	public void run(){

			while(running){

				//Aufruf Movement Methode
				Movement();

				try {
					sleep(250);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}


			}
	}

}
