import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class SkyLifePanel extends JPanel {

	SkyLife app;


	public SkyLifePanel(SkyLife app) {
		this.app = app;
		
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
<<<<<<< HEAD

		int x = (Integer) app.spinnerX.getValue();
		int y = (Integer) app.spinnerY.getValue();
		g.setColor(Color.cyan);
		g.fillOval(x, y, 30, 30);

		g.setColor(Color.MAGENTA);
		g.drawOval(x - 5, y - 5, 40, 40);

=======
		
		int x = (Integer) app.spinnerX.getValue();
		int y = (Integer) app.spinnerY.getValue();
		
		Image img;
		try {
			img = ImageIO.read(new File("img/flugzeug.png"));
			g.drawImage(img, x, y, null);
		} catch (IOException e) {
			e.printStackTrace();
		}		
>>>>>>> 2f442489b3c04c311d907d051f8276d36858101a
	}
	
}
