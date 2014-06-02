
public class CollisionThread extends Thread {

	SkyLife app;
	boolean collision = false;

	public CollisionThread(SkyLife app){

		this.app = app;

	}

	public void run(){
		while(true){

			//Auslagerung in Methode mit synchronized

			for (int i = 0; i < app.ObjectList.size() - 1; i++){

				for(int j = 1; j < app.ObjectList.size(); j++){

					collision = collision(app.ObjectList.get(i), app.ObjectList.get(j));
					if(app.ObjectList.get(i).equals(app.ObjectList.get(j))){
						collision = false;
					}

					if(collision){
						app.lblMessageTxt.setText(app.ObjectList.get(i).name + " kollidiert mit " + app.ObjectList.get(j).name);
						//Auslagerung
						if(app.ObjectList.get(i) instanceof Wolkenkratzer){
							if(app.ObjectList.get(j) instanceof Flugobjekt){
								if(app.ObjectList.get(j) instanceof Flugzeug){
									app.deleteObject(app.ObjectList.get(i).name);
									app.deleteObject(app.ObjectList.get(j).name);
									app.ObjectList.remove(i);
									app.ObjectList.remove(j);
									app.lblMessageTxt.setText("Game Over");
									app.tmov.stop();
									app.tcol.stop();

								}
								else{
									app.deleteObject(app.ObjectList.get(j).name);
									app.ObjectList.remove(j);
									app.killedAnimals++;
								}
							}
						}
						if(app.ObjectList.get(i) instanceof Flugobjekt){
							if(app.ObjectList.get(j) instanceof Wolkenkratzer){
								if(app.ObjectList.get(i) instanceof Flugzeug){
									app.deleteObject(app.ObjectList.get(i).name);
									app.deleteObject(app.ObjectList.get(j).name);
									app.ObjectList.remove(j);
									app.ObjectList.remove(i);
									app.lblMessageTxt.setText("Game Over");
									app.tmov.stop();
									app.tcol.stop();

								}
								else{
									app.deleteObject(app.ObjectList.get(i).name);
									app.ObjectList.remove(i);
									app.killedAnimals++;
								}
							}
							else if(app.ObjectList.get(i) instanceof Flugzeug){
								if(app.ObjectList.get(j) instanceof Flugzeug){
									app.deleteObject(app.ObjectList.get(i).name);
									app.deleteObject(app.ObjectList.get(j).name);
									app.ObjectList.remove(i);
									app.ObjectList.remove(j);
									app.lblMessageTxt.setText("Game Over");
									app.tmov.stop();
									app.tcol.stop();
								}
								else if (app.ObjectList.get(j) instanceof Taube || app.ObjectList.get(j) instanceof Greifvogel){
									app.deleteObject(app.ObjectList.get(j).name);
									//random Flugzeugabsturz beim Treffer mit Vogel einbauen!!!
									app.ObjectList.remove(j);
									app.killedAnimals++;
								}
							}
							else{
								if(app.ObjectList.get(i) instanceof Greifvogel && app.ObjectList.get(j) instanceof Taube){
									app.deleteObject(app.ObjectList.get(j).name);
									app.ObjectList.remove(j);
									app.killedAnimals++;
								}
								else if(app.ObjectList.get(j) instanceof Greifvogel && app.ObjectList.get(i) instanceof Taube){
									app.deleteObject(app.ObjectList.get(i).name);
									app.ObjectList.remove(i);
									app.killedAnimals++;
								}
								//Ausweichmanöver bei 2 Objekten gleichen Typs
								else if(app.ObjectList.get(i) instanceof Greifvogel && app.ObjectList.get(j) instanceof Greifvogel || app.ObjectList.get(i) instanceof Taube && app.ObjectList.get(j) instanceof Taube){
									//Seitverschiebung
									if(app.ObjectList.get(i).y <= (app.ObjectList.get(j).y + app.ObjectList.get(j).height) | app.ObjectList.get(j).y <= (app.ObjectList.get(i).y + app.ObjectList.get(i).height)){
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
									else if(app.ObjectList.get(j).x <= (app.ObjectList.get(i).x + app.ObjectList.get(i).width) | app.ObjectList.get(i).x <= (app.ObjectList.get(j).x + app.ObjectList.get(j).width)){
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
							}
						}
					}

				}

			}

			try {
				sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public boolean collision(Figur a, Figur b){

		if(a.x <= (b.x + b.width) && (a.x + a.width) >= b.x && a.y <= (b.y + b.height) && (a.y + a.height) >= b.y){
			return true;
		}


		return false;
	}

}
