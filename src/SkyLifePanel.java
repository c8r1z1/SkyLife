import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class SkyLifePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	SkyLife app;

	BufferedImage taubeImg = null;
	BufferedImage gvImg = null;
	BufferedImage fzImg = null;
	BufferedImage wkImg = null;
	BufferedImage mImg = null;

	public SkyLifePanel(SkyLife app) {
		this.app = app;
		try {
			taubeImg = ImageIO.read(this.getClass().getResource(
					"/Image/taube.png"));
			gvImg = ImageIO.read(this.getClass().getResource(
					"/Image/greifvogel.png"));
			fzImg = ImageIO.read(this.getClass().getResource(
					"/Image/flugzeug.png"));
			wkImg = ImageIO.read(this.getClass().getResource(
					"/Image/wolkenkratzer.png"));
			mImg = ImageIO.read(this.getClass().getResource(
					"/Image/meteorit.jpg"));
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
