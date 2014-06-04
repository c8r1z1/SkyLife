
public class CollisionThread extends Thread {

	SkyLife app;
	boolean collision = false;
	int deletedObjects = 0;
	int deletedObjectscleanList = 0;

	public CollisionThread(SkyLife app){

		this.app = app;

	}

	public void CorrectXCollision(Figur f){
		if(f.x < 0){
			f.x = 0;
		}
		if((f.x + f.width) > app.PanelWidth){
			f.x = (app.PanelWidth - f.width);
		}

	}

	public void CorrectYCollision(Figur f){

		if(f.y < 0){
			f.y = 0;
		}
		if((f.y + f.height) > app.PanelHeight){
			f.y = (app.PanelHeight - f.height);
		}
	}

	public boolean isAnimal(int i){

		if(app.ObjectList.get(i) instanceof Taube || app.ObjectList.get(i) instanceof Greifvogel){
			return true;
		}
		return false;
	}

	public synchronized void Collision(){
		
		//Zurücksetzen für neuen Schleifendurchlauf
		deletedObjects = 0;

		for (int i = 0; i < (app.ObjectList.size() - 1 - this.deletedObjects); i++){

			for(int j = 1; j < (app.ObjectList.size() - this.deletedObjects); j++){

				collision = app.collision(app.ObjectList.get(i), app.ObjectList.get(j));
				if(app.ObjectList.get(i).equals(app.ObjectList.get(j))){
					collision = false;
				}

				if(collision){

					if(app.ObjectList.get(i).getClass().equals(app.ObjectList.get(j).getClass()) == false){
						//Ausgabe Message: Objekt a kollidiert mit Objekt b
						app.lblMessageTxt.setText(app.ObjectList.get(i).toString() + " " + " kollidiert mit " + app.ObjectList.get(j).toString());
					}
					if(app.ObjectList.get(i) instanceof Wolkenkratzer && app.ObjectList.get(j) instanceof Flugzeug){
						//Game Over da Kollateralschaden entstanden
						GameOver(i, j);
					}
					if (app.ObjectList.get(i) instanceof Wolkenkratzer && isAnimal(j) == true){
						DeleteObjectCollision(j);
					}
					if(app.ObjectList.get(i) instanceof Flugzeug && app.ObjectList.get(j) instanceof Wolkenkratzer){
						//Game Over da Kollateralschaden entstanden
						GameOver(i, j);
					}
					if(app.ObjectList.get(i) instanceof Flugzeug && app.ObjectList.get(j) instanceof Flugzeug){
						//Game Over da Kollateralschaden entstanden
						GameOver(i, j);
					}
					if (app.ObjectList.get(i) instanceof Flugzeug && isAnimal(j) == true){
						DeleteObjectCollision(j);
					}
					if(app.ObjectList.get(i) instanceof Greifvogel && (app.ObjectList.get(j) instanceof Wolkenkratzer || app.ObjectList.get(j) instanceof Flugzeug)){
						DeleteObjectCollision(i);
					}
					else if(app.ObjectList.get(i) instanceof Greifvogel && app.ObjectList.get(j) instanceof Taube){
						DeleteObjectCollision(j);
					}
					else if(app.ObjectList.get(i) instanceof Taube && (app.ObjectList.get(j) instanceof Greifvogel || app.ObjectList.get(j) instanceof Flugzeug ||app.ObjectList.get(j) instanceof Wolkenkratzer)){
						DeleteObjectCollision(i);
					}
					//Ausweichmanöver bei 2 Objekten gleichen Typs
					else if(app.ObjectList.get(i) instanceof Greifvogel && app.ObjectList.get(j) instanceof Greifvogel || app.ObjectList.get(i) instanceof Taube && app.ObjectList.get(j) instanceof Taube){
						dodge(i, j);
					}
				}
			}

		}
		//Aufräumen der Liste, da "null" Objekte angelegt werden
		cleanList();

	}
	
	public void cleanList(){
		deletedObjectscleanList = 0;
		for(int i = 0; i < app.ObjectList.size() - deletedObjectscleanList; i++){
			if(app.ObjectList.get(i) == null){
				app.ObjectList.remove(i);
				deletedObjectscleanList++;
			}
		}
	}

	//Ausweichmanöver + Überprüfung auf Verlassen des Panels
	public void dodge(int i, int j){
		//Seitverschiebung
		if(app.ObjectList.get(i).y <= (app.ObjectList.get(j).y + app.ObjectList.get(j).height) || app.ObjectList.get(j).y <= (app.ObjectList.get(i).y + app.ObjectList.get(i).height)){
			if(app.ObjectList.get(j).x < ((app.ObjectList.get(i).x + app.ObjectList.get(i).width) / 2)){
				app.ObjectList.get(i).x += (app.ObjectList.get(j).width / 2);
				app.ObjectList.get(j).x -= (app.ObjectList.get(j).width / 2);
			}
			else if(app.ObjectList.get(j).x > ((app.ObjectList.get(i).x + app.ObjectList.get(i).width) / 2)){
				app.ObjectList.get(i).x -= (app.ObjectList.get(j).width / 2);
				app.ObjectList.get(j).x += (app.ObjectList.get(j).width / 2);
			}
			CorrectXCollision(app.ObjectList.get(i));
			CorrectXCollision(app.ObjectList.get(j));

		}
		//Vertikalverschiebung
		else if(app.ObjectList.get(j).x <= (app.ObjectList.get(i).x + app.ObjectList.get(i).width) || app.ObjectList.get(i).x <= (app.ObjectList.get(j).x + app.ObjectList.get(j).width)){
			if(app.ObjectList.get(j).y < ((app.ObjectList.get(i).y + app.ObjectList.get(i).height) / 2)){
				app.ObjectList.get(i).y += (app.ObjectList.get(j).height / 2);
				app.ObjectList.get(j).y -= (app.ObjectList.get(j).height / 2);
			}
			else if(app.ObjectList.get(j).y > ((app.ObjectList.get(i).y + app.ObjectList.get(i).height) / 2)){
				app.ObjectList.get(i).y -= (app.ObjectList.get(j).height / 2);
				app.ObjectList.get(j).y += (app.ObjectList.get(j).height / 2);
			}
			CorrectYCollision(app.ObjectList.get(i));
			CorrectYCollision(app.ObjectList.get(j));

		}
	}

	//Löschung eines Objektes nach Kollision
	public void DeleteObjectCollision(int i){
		if(isAnimal(i)){
			app.killedAnimals++;
			app.lblKilledAnimalsCount.setText(" " + app.killedAnimals);			
		}
		app.deleteObjectList(app.ObjectList.get(i).name);
		app.ObjectList.set(i, null);
		deletedObjects++;
		app.updateInfo();
		//Aktivierung des Buttons zum Setzen der Panelgröße, wenn keine Objekte im Panel
		if(app.ObjectList.size() == 0){
			app.btnSetPanelSize.setEnabled(true);
			app.tmov.stop();
			app.tcol.stop();
			app.trep.stop();
		}
	}

	//Game Over da Kollateralschaden entstanden
	public void GameOver(int i, int j){
		DeleteObjectCollision(i);
		DeleteObjectCollision(j);		
		app.lblMessageTxt.setText("Game Over");
		app.tmov.stop();
		app.tcol.stop();
		app.trep.stop();
	}

	public void run(){
		while(true){

			//Aufruf Collision Methode
			Collision();

			try {
				sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
