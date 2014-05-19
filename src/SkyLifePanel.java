import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class SkyLifePanel extends JPanel{

	SkyLife app;
	
	public SkyLifePanel(SkyLife app){
		this.app = app;
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		
		int x = (int) app.spinnerX.getValue();
		int y = (int) (app.spinnerY.getValue());
		g.setColor(Color.cyan);
		g.fillOval(x, y, 30, 30);
		
		g.setColor(Color.green);
		g.drawOval(x - 5, y - 5, 40, 40);
		
//		Image img;
//		try {
<<<<<<< HEAD
//			img = ImageIO.read(new File("img/Fisch.gif"));
//			g.drawImage(img, 10, 10, null);
=======
//			img = ImageIO.read(new File("img/flugzeug.png"));
//			g.drawImage(img, x, y, null);
>>>>>>> 7d290ba6b0f793a002af2a34e11d90224557e734
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		
	}
}
