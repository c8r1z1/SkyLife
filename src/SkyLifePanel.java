import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class SkyLifePanel extends JPanel {

	SkyLife app;

	Image taubeImg = null;
	Image gvImg = null;
	Image fzImg = null;
	Image wkImg = null;

	public SkyLifePanel(SkyLife app) {
		this.app = app;
		try {
			taubeImg = ImageIO.read(new File("img/taube.png"));
			gvImg = ImageIO.read(new File("img/greifvogel.png"));
			fzImg = ImageIO.read(new File("img/flugzeug.png"));
			wkImg = ImageIO.read(new File("img/wolkenkratzer.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public synchronized void Draw(Graphics g){
		
		for (Figur f : app.ObjectList) {
			if (f instanceof Taube) {
				g.drawImage(taubeImg, f.x, f.y, null);	
			}
			if(f instanceof Greifvogel) {
				g.drawImage(gvImg, f.x, f.y, null);	
			}
			if(f instanceof Flugzeug) {
				g.drawImage(fzImg, f.x, f.y, null);	
			}
			if(f instanceof Wolkenkratzer) {
				g.drawImage(wkImg, f.x, f.y, null);	
			}
		}		
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		
		Image img;

		if(app.comboBoxTyp.getSelectedItem().toString() == "Taube"){
			img = taubeImg;			
		}
		else if (app.comboBoxTyp.getSelectedItem().toString() == "Greifvogel"){
			img = gvImg;
		}
		else if (app.comboBoxTyp.getSelectedItem().toString() == "Flugzeug"){
			img = fzImg;
		}
		else{
			img = wkImg;
		}
		
		Draw(g);
	}
	
}
