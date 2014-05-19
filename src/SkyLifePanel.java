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

		int x = (Integer) app.spinnerX.getValue();
		int y = (Integer) app.spinnerY.getValue();
		g.setColor(Color.cyan);
		g.fillOval(x, y, 30, 30);

		g.setColor(Color.MAGENTA);
		g.drawOval(x - 5, y - 5, 40, 40);

	}
}
