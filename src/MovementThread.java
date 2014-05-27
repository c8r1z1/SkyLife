import java.util.List;


public class MovementThread extends Thread {
	
	SkyLife app;
	
	public MovementThread(SkyLife app){
		
		this.app = app;
		
	}

	public void run(){

		while(true){

			for (Figur f : app.ObjectList){

				if(f instanceof Flugobjekt){
					
					System.out.println(f.toString() + " vorher ");
					f.x = (int) (f.x + f.speed * Math.random() * 10 - Math.random() * f.speed * 10 );
					f.y = (int) (f.y + f.speed * Math.random() * 10 - Math.random() * f.speed * 10 );
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
