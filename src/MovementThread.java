
public class MovementThread extends Thread {
	
	SkyLife app;
	
	public MovementThread(SkyLife app){
		
		this.app = app;
		
	}

	public void run(){

		while(true){
			
			//Auslagerung in Methode mit synchronized

			for (Figur f : app.ObjectList){

				if(f instanceof Flugobjekt){
					
					System.out.println(f.toString() + " vorher ");
					
					f.x = (int) (f.x + f.speed * Math.random() * 10 - Math.random() * f.speed * 10 );
					
					//Korrektur x-Koordinate bei Lage außerhalb des Panels
					if(f.x < 0){
						f.x = 0;
					}
					if(f.x > app.PanelWidth){
						f.x = (app.PanelWidth - f.width);
					}
					
					f.y = (int) (f.y + f.speed * Math.random() * 10 - Math.random() * f.speed * 10 );
					
					//Korrektur y-Koordinate bei Lage außerhalb des Panels
					if(f.y < 0){
						f.y = 0;
					}
					if(f.y > app.PanelHeight){
						f.x = (app.PanelHeight - f.height);
					}
					System.out.println(f.toString() + " nachher ");
					
				}

			}
			
			app.panel.repaint();
			
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}
	}

}
