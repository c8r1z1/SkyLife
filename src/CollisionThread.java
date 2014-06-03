
public class CollisionThread extends Thread {

	SkyLife app;
	boolean collision = false;
	int deletedObjects = 0;

	public CollisionThread(SkyLife app){

		this.app = app;

	}
	
	public boolean isAnimal(int i){
		
		if(app.ObjectList.get(i) instanceof Taube || app.ObjectList.get(i) instanceof Greifvogel){
			return true;
		}
		return false;
	}
	
	public synchronized void collision(){
		
		for (int i = 0; i < (app.ObjectList.size() - 1 - this.deletedObjects); i++){

			for(int j = 1; j < (app.ObjectList.size() - this.deletedObjects); j++){

				collision = collision(app.ObjectList.get(i), app.ObjectList.get(j));
				if(app.ObjectList.get(i).equals(app.ObjectList.get(j))){
					collision = false;
				}

				if(collision){

					if(app.ObjectList.get(i).getClass().equals(app.ObjectList.get(j).getClass()) == false){
						//Ausgabe Message: Objekt a kollidiert mit Objekt b
						app.lblMessageTxt.setText(app.ObjectList.get(i).toString() + " " + " kollidiert mit " + app.ObjectList.get(j).toString());
					}
					if(app.ObjectList.get(i) instanceof Wolkenkratzer){
						if(app.ObjectList.get(j) instanceof Flugzeug){
							//Game Over da Kollateralschaden entstanden
							GameOver(i, j);
						}
						else if (isAnimal(j)){
							DeleteObjectCollision(j);
						}
					}
					if(app.ObjectList.get(i) instanceof Flugzeug){
						if(app.ObjectList.get(j) instanceof Wolkenkratzer){
							//Game Over da Kollateralschaden entstanden
							GameOver(i, j);
						}
						else if (isAnimal(j)){
							DeleteObjectCollision(j);
						}
					}
					else if(app.ObjectList.get(i) instanceof Greifvogel && app.ObjectList.get(j) instanceof Taube){
						DeleteObjectCollision(j);
					}
					else if(app.ObjectList.get(i) instanceof Taube && app.ObjectList.get(j) instanceof Greifvogel ||
							app.ObjectList.get(i) instanceof Taube && app.ObjectList.get(j) instanceof Flugzeug ||
							app.ObjectList.get(i) instanceof Taube && app.ObjectList.get(j) instanceof Wolkenkratzer){
						DeleteObjectCollision(i);
					}
					//Ausweichmanöver bei 2 Objekten gleichen Typs
					else if(app.ObjectList.get(i) instanceof Greifvogel && app.ObjectList.get(j) instanceof Greifvogel || app.ObjectList.get(i) instanceof Taube && app.ObjectList.get(j) instanceof Taube){
						dodge(i, j);
					}
				}
			}

		}
		
	}

	//Ausweichmanöver; Erweiterung um nicht Ausweichen außerhalb des Panels nötig
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

		}
	}

	//Kollisionsprüfung; Erweiterung um Kreise notwendig
	public boolean collision(Figur a, Figur b){

		if(a.x <= (b.x + b.width) && (a.x + a.width) >= b.x && a.y <= (b.y + b.height) && (a.y + a.height) >= b.y){
			return true;
		}


		return false;
	}

	//Löschung eines Objektes nach Kollision
	public void DeleteObjectCollision(int i){
		if(isAnimal(i)){
			app.killedAnimals++;
			app.lblKilledAnimalsCount.setText(" " + app.killedAnimals);			
		}
		app.deleteObjectList(app.ObjectList.get(i).name);
		app.ObjectList.remove(i);
		deletedObjects++;
		app.updateInfo();
	}

	//Game Over da Kollateralschaden entstanden
	public void GameOver(int i, int j){
		DeleteObjectCollision(i);
		DeleteObjectCollision(j);		
		app.lblMessageTxt.setText("Game Over");
		app.tmov.stop();
		app.tcol.stop();
		app.frame.repaint();
		
	}

	public void run(){
		while(true){

			//Aufruf collsion Methode
			collision();

			try {
				sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
