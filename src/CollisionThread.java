import java.awt.Color;


public class CollisionThread extends Thread {

	boolean running = true;

	SkyLife app;
	boolean collision = false;			// false = keine collsion
	int deletedObjects = 0;				// Anzahl der gelöschten Objekte
	int deletedObjectscleanList = 0;	// Anzahl Objekte in CleanList
	String delObject = null;			// String für überprüfen des Types
	boolean GamOv = false;				// false = kein Game Over
	
	public CollisionThread(SkyLife app) {

		this.app = app;

	}

	// falls Figur außerhalb des Panels
	public void CorrectXCollision(Figur f) {
		if (f.x < 0) {
			f.x = 0;
		}
		if ((f.x + f.width) > app.PanelWidth) {
			f.x = (app.PanelWidth - f.width);
		}

	}
	
	// falls Figur außerhalb des Panels
	public void CorrectYCollision(Figur f) {

		if (f.y < 0) {
			f.y = 0;
		}
		if ((f.y + f.height) > app.PanelHeight) {
			f.y = (app.PanelHeight - f.height);
		}
	}

	//Überprüfung ob Figur ein Tier ist
	public boolean isAnimal(int i) {

		if (app.ObjectList.get(i) instanceof Taube
				|| app.ObjectList.get(i) instanceof Greifvogel) {
			return true;
		}
		return false;
	}
	
	
	public void Collision() {

		synchronized (app.ObjectList) {

			if (app.ObjectList.size() > 1) {
				// Zurücksetzen für neuen Schleifendurchlauf
				deletedObjects = 0;

				for (int i = 0; i < (app.ObjectList.size() - 1 - this.deletedObjects); i++) {

					for (int j = 1; j < (app.ObjectList.size() - this.deletedObjects); j++) {

						collision = app.collision(app.ObjectList.get(i),
								app.ObjectList.get(j));
						if (app.ObjectList.get(i).equals(app.ObjectList.get(j))) {
							collision = false;
						}

						if (collision) {
							//Ausgabe der Message das Collsion stattfindet
							if (app.ObjectList.get(i).getClass()
									.equals(app.ObjectList.get(j).getClass()) == false) {
								// Ausgabe Message: Objekt a kollidiert mit
								// Objekt b
								app.lblMessageTxt
										.setText(app.ObjectList.get(i).Typ
												+ " "
												+ app.ObjectList.get(i).name
												+ " kollidiert mit "
												+ app.ObjectList.get(j).Typ
												+ " "
												+ app.ObjectList.get(j).name);
							}
							// Collision Wolkenkratzer und Flugzeug
							if (app.ObjectList.get(i) instanceof Wolkenkratzer
									&& app.ObjectList.get(j) instanceof Flugzeug) {
								// Game Over da Kollateralschaden entstanden
								GameOver("Flugzeug in Wolkenkratzer gecrasht");
							}
							// Collision Wolkenkratzer mit Taube oder Greifvogel
							if (app.ObjectList.get(i) instanceof Wolkenkratzer
									&& isAnimal(j) == true) {
								// runterzählen des Counters
								delObject = app.ObjectList.get(j).Typ;
								if (delObject == "Taube") {
									app.countTaube--;
								} else {
									app.countGreifvogel--;
								}
								DeleteObjectCollision(j, i);
							}
							//Collision Flugzeug und Wolkenkratzer
							if (app.ObjectList.get(i) instanceof Flugzeug
									&& app.ObjectList.get(j) instanceof Wolkenkratzer) {
								// Game Over da Kollateralschaden entstanden
								GameOver("Flugzeug in Wolkenkratzer gecrasht");
							}
							//Collision Flugzeug und FLugzeug
							if (app.ObjectList.get(i) instanceof Flugzeug
									&& app.ObjectList.get(j) instanceof Flugzeug) {
								// Game Over da Kollateralschaden entstanden
								GameOver("Flugzeug in anderes Flugzeug gecrasht");
							}
							// Collision Flugzeug mit Taube oder Greifvogel
							if (app.ObjectList.get(i) instanceof Flugzeug
									&& isAnimal(j) == true) {
								// chance auf Flugzeugabsturz durch Taube oder Greifvogel
								if (Math.random() * 10 > 9.9) {
									GameOver("Flugzeugabsturz durch Vogel in der Turbine");
								} else {
									// runterzählen des Counters
									delObject = app.ObjectList.get(j).Typ;
									if (delObject == "Taube") {
										app.countTaube--;
									} else {
										app.countGreifvogel--;
									}
									DeleteObjectCollision(j, i);
								}
							}
							// Collision Taube oder Greifvogel mit Fugzeug
							if (app.ObjectList.get(j) instanceof Flugzeug
									&& isAnimal(i) == true) {
								// chance auf Flugzeugabsturz durch Taube oder Greifvogel 
								if (Math.random() * 10 > 9.9) {
									GameOver("Flugzeugabsturz durch Vogel in der Turbine");
								} else {
									// runterzählen des Counters
									delObject = app.ObjectList.get(i).Typ;
									if (delObject == "Taube") {
										app.countTaube--;
									} else {
										app.countGreifvogel--;
									}
									DeleteObjectCollision(i, j);
								}
							}
							//Collision Greifvogel und Wolkenkratzer
							if (app.ObjectList.get(i) instanceof Greifvogel
									&& app.ObjectList.get(j) instanceof Wolkenkratzer) {
								// runterzählen des Counters
								app.countGreifvogel--;
								DeleteObjectCollision(i, j);
							}
							// Collision Greifvogel und Taube
							if (app.ObjectList.get(i) instanceof Greifvogel
									&& app.ObjectList.get(j) instanceof Taube) {
								// runterzählen des Counters
								app.countTaube--;
								DeleteObjectCollision(j, i);
							}
							// Collision Taube und Greifvogel oder Wolkenkratzer
							if (app.ObjectList.get(i) instanceof Taube
									&& (app.ObjectList.get(j) instanceof Greifvogel || app.ObjectList
											.get(j) instanceof Wolkenkratzer)) {
								// runterzählen des Counters
								app.countTaube--;
								DeleteObjectCollision(i, j);
							}
							// Ausweichmanöver bei 2 Objekten gleichen Typs
							if (app.ObjectList.get(i) instanceof Greifvogel
									&& app.ObjectList.get(j) instanceof Greifvogel
									|| app.ObjectList.get(i) instanceof Taube
									&& app.ObjectList.get(j) instanceof Taube) {
								dodge(i, j);
							}
							cleanList();
						}
					}

				}
				// testweise Verschiebung in if Abfragen
				// cleanList();
				app.updateInfo();
			}
		}
	}

	// Aufräumen der Liste, da "null" Objekte angelegt werden
	public void cleanList() {
		deletedObjectscleanList = 0;
		for (int i = 0; i < app.ObjectList.size() - deletedObjectscleanList; i++) {
			if (app.ObjectList.get(i) == null) {
				app.ObjectList.remove(i);
				deletedObjectscleanList++;
			}
		}
	}

	// Ausweichmanöver + Überprüfung auf Verlassen des Panels
	public void dodge(int i, int j) {
		app.lblMessageTxt.setText("Ausweichmanöver: "
				+ app.ObjectList.get(i).Typ + " " + app.ObjectList.get(i).name
				+ " kollidiert mit " + app.ObjectList.get(j).Typ + " "
				+ app.ObjectList.get(j).name);
		// Horizontalverschiebung
		if (app.ObjectList.get(i).y <= (app.ObjectList.get(j).y + app.ObjectList
				.get(j).height)
				|| app.ObjectList.get(j).y <= (app.ObjectList.get(i).y + app.ObjectList
						.get(i).height)) {
			if (app.ObjectList.get(j).x < ((app.ObjectList.get(i).x + app.ObjectList
					.get(i).width) / 2)) {
				app.ObjectList.get(i).x += (app.ObjectList.get(j).width / 2);
				app.ObjectList.get(j).x -= (app.ObjectList.get(j).width / 2);
			} else if (app.ObjectList.get(j).x > ((app.ObjectList.get(i).x + app.ObjectList
					.get(i).width) / 2)) {
				app.ObjectList.get(i).x -= (app.ObjectList.get(j).width / 2);
				app.ObjectList.get(j).x += (app.ObjectList.get(j).width / 2);
			}
			CorrectXCollision(app.ObjectList.get(i));
			CorrectXCollision(app.ObjectList.get(j));

		}
		// Vertikalverschiebung
		else if (app.ObjectList.get(j).x <= (app.ObjectList.get(i).x + app.ObjectList
				.get(i).width)
				|| app.ObjectList.get(i).x <= (app.ObjectList.get(j).x + app.ObjectList
						.get(j).width)) {
			if (app.ObjectList.get(j).y < ((app.ObjectList.get(i).y + app.ObjectList
					.get(i).height) / 2)) {
				app.ObjectList.get(i).y += (app.ObjectList.get(j).height / 2);
				app.ObjectList.get(j).y -= (app.ObjectList.get(j).height / 2);
			} else if (app.ObjectList.get(j).y > ((app.ObjectList.get(i).y + app.ObjectList
					.get(i).height) / 2)) {
				app.ObjectList.get(i).y -= (app.ObjectList.get(j).height / 2);
				app.ObjectList.get(j).y += (app.ObjectList.get(j).height / 2);
			}
			CorrectYCollision(app.ObjectList.get(i));
			CorrectYCollision(app.ObjectList.get(j));

		}
	}

	// Löschung eines Objektes nach Kollision
	public void DeleteObjectCollision(int i, int j) {
		if (isAnimal(i) && !isAnimal(j)) {
			app.killedAnimals++;
			app.updateKilledAnimals();
			meteoritProof();
			app.deleteObjectList(app.ObjectList.get(i).name);
			app.ObjectList.set(i, null);
			deletedObjects++;
		} else if (isAnimal(i) && isAnimal(j)) {
			app.killedAnimals++;
			app.updateKilledAnimals();
			meteoritProof();
			if (app.ObjectList.get(i).Typ == "Taube") {
				app.deleteObjectList(app.ObjectList.get(i).name);
				app.ObjectList.set(i, null);
				deletedObjects++;
			}
			if(app.ObjectList.get(j).Typ == "Taube"){
				app.deleteObjectList(app.ObjectList.get(j).name);
				app.ObjectList.set(j, null);
				deletedObjects++;
			}
		}
	}

	// Bei 10 getöteten Vögeln, rächt sich die Natur
	public void meteoritProof() {
		if (app.killedAnimals == 10) {
			Meteorit meteor = new Meteorit("Destroyer", app.PanelWidth);
			app.ObjectList.add(meteor);
			// Zerstörung aller Objekte auf dem Weg
			// Einschlag: GameOver
		}

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

		// Aktivierung des Buttons zum Setzen der Panelgröße, wenn keine Objekte im Panel
		// zurücksetzen der Counter
		// zurücksetzen andere Felder
		// stoppen der Threads
		app.btnSetPanelSize.setEnabled(true);
		app.stop.setEnabled(false);
		app.updateInfo();
		app.killedAnimals = 0;
		app.countTaube = 0;
		app.countGreifvogel = 0;
		app.countFlugzeug = 0;
		app.countWolkenkratzer = 0;
		GamOv = true;
		app.updateKilledAnimals();
		app.nameFieldEinf.setText(null);
		app.spinnerX.setValue(0);
		app.spinnerY.setValue(0);
		SkyLife.tmov.running = false;
		SkyLife.trep.running = false;
		SkyLife.tcol.running = false;
	}

	public void run() {
		while (running) {

			// Aufruf Collision Methode
			Collision();

			try {
				sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// wenn Gam Over dann Objekte löschen
		if (GamOv == true) {
			app.panel.setBackground(Color.black);
			DeleteObjectCollisionAll();
			GamOv = false;
		}
	}
}
