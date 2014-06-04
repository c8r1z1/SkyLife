
public class RepaintThread extends Thread{

	SkyLife app;

	public RepaintThread(SkyLife app){
		this.app = app;
	}

	public void run(){
		while(true){
			app.panel.repaint();
			
			try {
				sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
