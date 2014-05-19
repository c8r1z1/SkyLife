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
		
		
		int x = Integer.parseInt(app.spinnerX.getName());
		int y = Integer.parseInt(app.spinnerY.getName());
		g.setColor(Color.cyan);
		g.fillOval(x, y, 30, 30);
		
		g.setColor(Color.green);
		g.drawOval(x - 5, y - 5, 40, 40);
		
//		Image img;
//		try {
//			img = ImageIO.read(new File("img/Fisch.gif"));
//			g.drawImage(img, 10, 10, null);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		
	}
}
