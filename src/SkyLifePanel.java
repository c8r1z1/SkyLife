import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class SkyLifePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	SkyLife app;

	Image taubeImg = null;
	Image gvImg = null;
	Image fzImg = null;
	Image wkImg = null;
	Image mImg = null;

	public SkyLifePanel(SkyLife app) {
		this.app = app;
		try {
			taubeImg = ImageIO.read(new File("img/taube.png"));
			gvImg = ImageIO.read(new File("img/greifvogel.png"));
			fzImg = ImageIO.read(new File("img/flugzeug.png"));
			wkImg = ImageIO.read(new File("img/wolkenkratzer.png"));
			mImg = ImageIO.read(new File("img/meteorit.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void Draw(Graphics g) {
		synchronized (app.ObjectList) {
			for (Figur f : app.ObjectList) {
				if (f instanceof Taube) {
					g.drawImage(taubeImg, f.x, f.y, null);
				}
				if (f instanceof Greifvogel) {
					g.drawImage(gvImg, f.x, f.y, null);
				}
				if (f instanceof Flugzeug) {
					g.drawImage(fzImg, f.x, f.y, null);
				}
				if (f instanceof Wolkenkratzer) {
					g.drawImage(wkImg, f.x, f.y, null);
				}
				if (f instanceof Meteorit) {
					g.drawImage(mImg, f.x, (f.y - 44), null);
				}
			}
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Draw(g);
	}

}
