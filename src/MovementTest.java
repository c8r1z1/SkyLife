import java.util.ArrayList;
import java.util.List;

// Möglchkeit für neue Bewegung
// Taube und Greifvogel vor, zurück, diagonal.. bei Berührung des Rahmens, zurückprallen, einfallswinkel gleich ausfalls winkel
// Flugzeug von rechts nach links.. bei Berührung des Rahmens.. eintritt auf andere Seite
// Wolkenkratzer bewegen sich nicht
// Meteor senkrechter eintritt in mitte des panels

public class MovementTest extends Thread {

	SkyLife app;
	boolean running = true;
	List<Figur> RemoveListFlug = new ArrayList<Figur>();
	List<Figur> RemoveListTaube = new ArrayList<Figur>();
	List<Figur> RemoveListGreif = new ArrayList<Figur>();
	int countFlug = 0;
	int countTaube = 0;
	int countGreif = 0;

	public MovementTest(SkyLife app) {
		this.app = app;
	}

	public void MovementTaube() {
		synchronized (app.ObjectList) {
			for (Figur f : app.ObjectList) {

				if (f instanceof Taube) {
					countTaube++;
					f.x = computeXTaube(f.x, f.speed, countTaube);
					f.y = computeYTaube(f.y, f.speed, countTaube);
					if (f.y == -666) {
						RemoveListTaube.add(app.ObjectList.get(app.ObjectList
								.indexOf(f)));
						app.comboBoxNameEnt.removeItemAt(app.ObjectList
								.indexOf(f));
					}
				}
			}
		}
		// entfernen der Flugzeuge aus der ObjectListe
		for (int i = 0; i < RemoveListTaube.size(); i++) {
			app.ObjectList.remove(RemoveListTaube.get(i));
			app.countTaube--;
		}
	}

	public void MovementGreifvogel() {
		synchronized (app.ObjectList) {
			for (Figur f : app.ObjectList) {

				if (f instanceof Greifvogel) {
					countGreif++;
					f.x = computeXGreif(f.x, f.speed, countGreif);
					f.y = computeYGreif(f.y, f.speed, countGreif);
					if (f.y == -666) {
						RemoveListGreif.add(app.ObjectList.get(app.ObjectList
								.indexOf(f)));
						app.comboBoxNameEnt.removeItemAt(app.ObjectList
								.indexOf(f));
					}
				}
			}
		}
		// entfernen der Flugzeuge aus der ObjectListe
		for (int i = 0; i < RemoveListGreif.size(); i++) {
			app.ObjectList.remove(RemoveListGreif.get(i));
			app.countGreifvogel--;
		}
	}

	public void MovementMeteor() {
		synchronized (app.ObjectList) {
			for (Figur f : app.ObjectList) {

				if (f instanceof Meteorit) {
					f.x = app.PanelWidth/2;
					f.y += 10;
					if (f.y >= app.PanelHeight - f.height) {
						GameOver("Meteoriteneinschlag, die Natur übt Rache für tote Vögel");
					}
				}
			}
		}
	}

	public void MovementFlugzeug() {
		synchronized (app.ObjectList) {
			for (Figur f : app.ObjectList) {
				if (f instanceof Flugzeug) {
					countFlug++;
					f.x = computeXFlugzeug(f.x, f.speed);
					f.y = computeYFlugzeug(f.y, f.speed, countFlug);
					if (f.y == -666) {
						RemoveListFlug.add(app.ObjectList.get(app.ObjectList
								.indexOf(f)));
						app.comboBoxNameEnt.removeItemAt(app.ObjectList
								.indexOf(f));
					}
				}
			}
		}
		// entfernen des Flugzeuges aus der ObjectListe
		for (int i = 0; i < RemoveListFlug.size(); i++) {
			app.ObjectList.remove(RemoveListFlug.get(i));
			app.countFlugzeug--;
		}
	}

	// Korrektur Flugzeug.. links raus, rechts wieder rein
	public int computeXFlugzeug(int x, int speed) {
		if ((x + 100) < 0) {
			x = app.PanelWidth;
		} else {
			x = x - speed;
		}
		return x;
	}

	// wenn Flugzeug oben aus dem Panel rausgeht soll es komplett verschwinden.
	public int computeYFlugzeug(int y, int speed, int countFlug) {
		if ((y + 80) < 0) {
			y = -666;
		} else {
			// nach 10 Berechnungen Anstieg
			if ((countFlug % 10) == 0 || (countFlug % 10) == 1) {
				y -= 2;
			}
		}
		return y;
	}

	// koeffizient zur besstimmung der richtung
	double i = 1;
	int mult = 1;

	// berechnung von x der taube
	public int computeXTaube(int x, int speed, int countTaube) {

		if ((countTaube % 150) == 0 || (countTaube % 150) == 1
				|| (countTaube % 150) == 2) {
			i = Math.random();
		}
		if (i < 0.5) {
			mult = -1;
		} else {
			mult = 1;
		}
		if ((x + 45) < 0) {
			x = app.PanelWidth;
		} else if ((x - 45) > app.PanelWidth) {
			x = 0;
		}
		// änderung berechnungen
		if ((countTaube % 3) == 0) {
			x = x + mult * speed;
		}
		return x;
	}

	// berechnung von y der taube
	public int computeYTaube(int y, int speed, int countTaube) {
		if ((y + 30) < 0 || (y - 30) > app.PanelHeight) {
			y = -666;
		} else {

			if ((countTaube % 150) == 0 || (countTaube % 150) == 1
					|| (countTaube % 150) == 2) {
				i = Math.random();
			}
			System.out.println(i);
			if (i < 0.5) {
				mult = -1;
			} else {
				mult = 1;
			}
			System.out.println(mult);

			// änderung berechnungen
			if ((countTaube % 5) == 0) {
				y = y + (mult * speed) / 2;
			}
		}
		return y;
	}

	public int computeXGreif(int x, int speed, int countGreif) {

		if ((countGreif % 150) == 0 || (countGreif % 150) == 1
				|| (countGreif % 150) == 2) {
			i = Math.random();
		}
		if (i < 0.5) {
			mult = -1;
		} else {
			mult = 1;
		}
		if ((x + 50) < 0) {
			x = app.PanelWidth;
		} else if ((x - 50) > app.PanelWidth) {
			x = 0;
		}
		// änderung berechnungen
		if ((countGreif % 3) == 0) {
			x = (int) 1.2 * (x + mult * speed);
		}
		return x;
	}

	public int computeYGreif(int y, int speed, int countGreif) {
		if ((y + 20) < 0 || (y - 20) > app.PanelHeight) {
			y = -666;
		} else {

			if ((countGreif % 150) == 0 || (countGreif % 150) == 1
					|| (countGreif % 150) == 2) {
				i = Math.random();
			}
			System.out.println(i);
			if (i < 0.5) {
				mult = -1;
			} else {
				mult = 1;
			}
			System.out.println(mult);

			// änderung berechnungen
			if ((countGreif % 5) == 0) {
				y = (int) 1.2 * (y + (mult * speed) / 2);
			}
		}
		return y;
	}

	// Löschung verbliebende Objekte bei Game Over
	public void DeleteObjectCollisionAll() {
		app.ObjectList.clear();
		app.comboBoxNameEnt.removeAllItems();
		app.panel.repaint();
	}

	// Game Over da Kollateralschaden entstanden
	public void GameOver(String a) {
		app.lblMessageTxt.setText("Game Over: " + a);
		// Löschen aller verbleibenden Objekte in der Liste
		DeleteObjectCollisionAll();
		// Aktivierung des Buttons zum Setzen der Panelgröße, wenn keine Objekte
		// im Panel
		app.btnSetPanelSize.setEnabled(true);
		app.stop.setEnabled(false);
		app.updateInfo();
		app.killedAnimals = 0;
		app.updateKilledAnimals();
		SkyLife.tmov.running = false;
		SkyLife.trep.running = false;
		SkyLife.tcol.running = false;
	}

	public void run() {

		while (running) {

			// Aufruf Movement Methode
			MovementTaube();
			MovementGreifvogel();
			MovementFlugzeug();

			try {
				sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

}
