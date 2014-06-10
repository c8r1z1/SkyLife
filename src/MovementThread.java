
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
			f.x = (int) (10 * Math.random());
		}
		if((f.x + f.width) > app.PanelWidth){
			f.x = (app.PanelWidth - f.width) - (int) (10 * Math.random());
		}
		
	}
	
	public void CorrectYMovement(Figur f){
		
		if(f.y < 0){
			f.y = (int) (10 * Math.random());
		}
		if((f.y + f.height) > app.PanelHeight){
			f.y = (app.PanelHeight - f.height) - (int) (10 * Math.random());
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
				
				f.x = (int) (f.x + f.speed * MathRandomX() * Math.random() * 10 - MathRandomX2() * Math.random() * f.speed * 10 );
				//Korrektur x-Koordinate bei Lage außerhalb des Panels
				CorrectXMovement(f);
				
				f.y = (int) (f.y + f.speed * MathRandomY() * Math.random() * 10 - MathRandomY2() * Math.random() * f.speed * 10 );
				//Korrektur y-Koordinate bei Lage außerhalb des Panels
				CorrectYMovement(f);				
			}
		}
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
