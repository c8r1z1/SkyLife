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
		
		int x = (Integer) app.spinnerX.getValue();
		int y = (Integer) app.spinnerY.getValue();
		
		Image img;
		try {
			img = ImageIO.read(new File("img/flugzeug.png"));
			g.drawImage(img, x, y, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
}
