
public class MovementThread extends Thread {
	
	SkyLife app;
	
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
	
	public synchronized void Movement(){
		
		for (Figur f : app.ObjectList){

			if(f instanceof Flugobjekt){
				
				f.x = (int) (f.x + f.speed * Math.random() * 10 - Math.random() * f.speed * 10 );
				//Korrektur x-Koordinate bei Lage außerhalb des Panels
				CorrectXMovement(f);
				
				f.y = (int) (f.y + f.speed * Math.random() * 10 - Math.random() * f.speed * 10 );
				//Korrektur y-Koordinate bei Lage außerhalb des Panels
				CorrectYMovement(f);				
			}

		}
	}

	public void run(){

		while(true){
			
			//Aufruf Movement Methode
			Movement();
			
			app.panel.repaint();
			
			try {
				sleep(250);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}
	}

}
