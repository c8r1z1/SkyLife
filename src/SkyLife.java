import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class SkyLife {

	public enum FORM {
		Kreis, Rechteck
	};

	JFrame frame;

	JTextField nameFieldEinf;

	JSpinner spinnerX, spinnerY;
	JButton btnEinfgen, btnEntfernen;

	JButton laden, speichern, start, stop, btnSetPanelSize, btnNeu,
			btnNchsterSchritt;
	JComboBox<String> comboBoxTyp, comboBoxNameEnt;
	JLabel lblNameEinf, lblNameEnt, lblHoehe, lblTyp, lblPosition, lblX, lblY,
			lblHhe, lblBreite, lblPanelgre, lblMessage, lblMessageTxt;
	JTextField txtPanelWidthadd, txtPanelHeightadd;
	JTextPane txtInfo, KilledAnimals;

	private JLayeredPane layeredPaneDelete;
	public SkyLifePanel panel;
	public int PanelHeight = 450;
	public int PanelWidth = 900;
	private int startclicked = 0;

	// ToDo's
	// Meteoriten Kollision einbauen
	// Auslagerung Ausweichmanöver und Kollisionsüberprüfung Erzeugung
	// Schrittweises Ablaufen lauffähig machen
	// Save and Load implementieren
	// Button zum Starten des RMI Servers, Feld zur Eingabe von IP-Adresse,
	// Button zum Verbinden zum Server + RMI Innenleben
	// Remote Steueurung einbauen

	// werte bei game over zurücksetzen

	// maximale anzahl an objekten
	// taube
	int countTaube = 0;
	// greifvogel
	int countGreifvogel = 0;
	// flugezeug = 3
	int countFlugzeug = 0;
	// wolkenkratzer = 2
	int countWolkenkratzer = 0;

	// ToTest's
	// Einfügen Objekterzeugung mit Kollisionsüberprüfung
	// Smooth für Bewegung über Zwischenzeichnung -> Veränderung über
	// MovementThread Formel zur Berechnung von neuer Position
	// -> eventuell Änderung Bewegungsabläufe generell

	// Objekt Liste für alle Objekte innerhalb des Panels
	public List<Figur> ObjectList = new ArrayList<Figur>();

	// Counter für getötete Vögel
	public int killedAnimals = 0;

	// Liste für Entfernung von Objekten
	List<String> ListNameEinf = new ArrayList<String>();

	// Liste der geladenen Objekten
	List<Figur> LoadList = new ArrayList<Figur>();

	// Liste für Speicherobjekte
	List<Object> SaveList = new ArrayList<Object>();

	static SkyLife window;

	// Thread für Bewegung der Objekte
	static MovementThread tmov;
	// MovementTestthread
	static MovementTest tmovtest;
	// Thread für Kollisionsüberprüfung
	static CollisionThread tcol;
	// Thread für Neuzeichnung des Panels
	static RepaintThread trep;

	// allgemeine Figur.. für Load
	Figur fig = new Figur(null, null, 0, 0, 0, 0, 0, null);

	// Thread für schrittweise Bewegung der Objekte
	// static MovementStepbyStepThread tmovstep;

	public void createObject() {
		synchronized (ObjectList) {
			boolean nameInUse = false;
			if (ObjectList.size() != 0) {
				for (int i = 0; i < ObjectList.size(); i++) {
					String name = ObjectList.get(i).name;
					if (nameFieldEinf.getText().equals(name)) {
						nameInUse = true;
						break;
					}
				}
			}

			if (!nameInUse && nameFieldEinf.getText() != "") {

				// Überprüfung: Objekte dürfen nicht aufeinander eingefügt
				// werden!!!

				if (comboBoxTyp.getSelectedItem().toString() == "Taube") {

					Taube taube = new Taube(nameFieldEinf.getText(),
							(Integer) spinnerX.getValue(),
							(Integer) spinnerY.getValue());
					ObjectList.add(taube);
					CorrectPosition(taube, ObjectList);
					countTaube++;
					// Text für Message
					lblMessageTxt.setText(Taube.Typ + " " + taube.name
							+ " eingefügt");

				} else if (comboBoxTyp.getSelectedItem().toString() == "Greifvogel") {

					Greifvogel gv = new Greifvogel(nameFieldEinf.getText(),
							(Integer) spinnerX.getValue(),
							(Integer) spinnerY.getValue());
					ObjectList.add(gv);
					CorrectPosition(gv, ObjectList);
					countGreifvogel++;
					// Text für Message
					lblMessageTxt.setText(Greifvogel.Typ + " " + gv.name
							+ " eingefügt");

				} else if (comboBoxTyp.getSelectedItem().toString() == "Flugzeug") {

					Flugzeug fz = new Flugzeug(nameFieldEinf.getText(),
							(Integer) spinnerX.getValue(),
							(Integer) spinnerY.getValue());
					ObjectList.add(fz);
					CorrectPosition(fz, ObjectList);
					countFlugzeug++;
					// Text für Message
					lblMessageTxt.setText(Flugzeug.Typ + " " + fz.name
							+ " eingefügt");

				} else {

					Wolkenkratzer wk = new Wolkenkratzer(
							nameFieldEinf.getText(),
							(Integer) spinnerX.getValue(), PanelHeight
									- Wolkenkratzer.height);
					ObjectList.add(wk);
					correctx(wk);
					CorrectPosition(wk, ObjectList);
					countWolkenkratzer++;
					// Text für Message
					lblMessageTxt.setText(Wolkenkratzer.Typ + " " + wk.name
							+ " eingefügt");

				}
				frame.getContentPane().add(panel);
				panel.repaint();

				// Hinzufügen zur Liste für Entfernung
				ListNameEinf.add(nameFieldEinf.getText());
				comboBoxNameEnt.addItem(nameFieldEinf.getText());

				// Aktualisierung Info unterhalb des Panels
				updateInfo();

			} else {
				lblMessageTxt.setText("Kein gültiger Name eingefügt! ");
			}

			// Deaktivierung Button für Neusetzen der Panelgröße, sobald Objekt
			// enthalten
			if (ObjectList.size() != 0) {
				btnSetPanelSize.setEnabled(false);
			}
		}
	}

	// Erstellen der ObjectList aus dem Load
	public void createObject2(Figur fig) {
		synchronized (ObjectList) {

			// System.out.println(fig);
			// System.out.println(fig.Typ);

			// fig.Typ == "Taube" klappt nicht
			if (fig.Typ.length() == 5) {

				Taube taube = new Taube(fig.name, fig.x, fig.y);
				ObjectList.add(taube);

			} else if (fig.Typ.length() == 10) {

				Greifvogel gv = new Greifvogel(fig.name, fig.x, fig.y);
				ObjectList.add(gv);

			} else if (fig.Typ.length() == 8) {

				Flugzeug fz = new Flugzeug(fig.name, fig.x, fig.y);
				ObjectList.add(fz);

			} else {
				if (fig.y != PanelHeight - Wolkenkratzer.height) {
					fig.y = PanelHeight - Wolkenkratzer.height;
				}
				Wolkenkratzer wk = new Wolkenkratzer(fig.name, fig.x, fig.y);
				ObjectList.add(wk);
			}
			frame.getContentPane().add(panel);
			panel.repaint();

			// Hinzufügen zur Liste für Entfernung
			ListNameEinf.add(fig.name);
			comboBoxNameEnt.addItem(fig.name);

			// Deaktivierung Button für Neusetzen der Panelgröße, sobald Objekt
			// enthalten
			if (ObjectList.size() != 0) {
				btnSetPanelSize.setEnabled(false);
			}
		}
		// System.out.println(ObjectList);
	}

	public void deleteObject() {
		synchronized (ObjectList) {
			for (int i = 0; i < ObjectList.size(); i++) {
				if (ObjectList.get(i).name
						.equalsIgnoreCase((String) comboBoxNameEnt
								.getSelectedItem())) {
					lblMessageTxt.setText(ObjectList.get(i).Typ + " "
							+ ObjectList.get(i).name + " entfernt");
					if (ObjectList.get(i).Typ == "Taube") {
						countTaube--;
					} else if (ObjectList.get(i).Typ == "Greifvogel") {
						countGreifvogel--;
					} else if (ObjectList.get(i).Typ == "Flugzeug") {
						countFlugzeug--;
					} else if (ObjectList.get(i).Typ == "Wolkenkratzer") {
						countWolkenkratzer--;
					}
					ObjectList.remove(i);
					break;
				}
			}
			panel.repaint();
			// Aktivierung Button für Neusetzen der Panelgröße und anhalten der
			// Threads
			if (ObjectList.size() == 0) {
				btnSetPanelSize.setEnabled(true);
				stop.setEnabled(false);
				// tmov.running = false;
				tmovtest.running = false;
				tcol.running = false;
				trep.running = false;
				start.setEnabled(true);
				nameFieldEinf.setText(null);
				spinnerX.setValue(0);
				spinnerY.setValue(0);
			}
		}
	}

	// Aktualisierung der Anzahl der Objekte
	public void updateInfo() {
		txtInfo.setText("Panelgröße:   Höhe: " + PanelHeight + " Breite: "
				+ PanelWidth + "      Anzahl Objekte: " + ObjectList.size());

	}

	// Aktualisierung der Anzahl der getöteten Vögel
	public void updateKilledAnimals() {
		KilledAnimals.setText("Tote Vögel: " + killedAnimals);
	}

	// Kollisionsprüfung; Erweiterung um Kreise notwendig
	public boolean collision(Figur a, Figur b) {
		// Rechteck kollidiert mit Rechteck
		if (a.f.toString() == "Rechteck" && b.f.toString() == "Rechteck") {
			if (a.x <= (b.x + b.width) && (a.x + a.width) >= b.x
					&& a.y <= (b.y + b.height) && (a.y + a.height) >= b.y) {
				return true;
			}
		}
		// Kreis kollidiert mit Kreis
		else if (a.f.toString() == "Kreis" && b.f.toString() == "Kreis") {
			if (pointdistanceCircle(a, b) <= ((a.x / 2) + (b.x / 2))) {
				return true;
			}
		}
		// Kreis mit Rechteck
		else if (a.f.toString() == "Rechteck" && b.f.toString() == "Kreis") {
			if (CirclecolRectangle(a, b)) {
				return true;
			}
		} else if (a.f.toString() == "Kreis" && b.f.toString() == "Rechteck") {
			if (CirclecolRectangle(b, a)) {
				return true;
			}
		}
		if (ObjectList.size() == 0) {
			btnSetPanelSize.setEnabled(true);
		}
		return false;
	}

	// Methode Kollision Rechteck & Kreis
	public boolean CirclecolRectangle(Figur a, Figur b) {
		if (b.middleX() <= (a.x + a.width) && b.middleX() >= a.x
				&& b.middleY() >= a.y && b.middleY() <= (a.y + a.height)) {
			return true;
		} else if (pointDistanceRect(b, a.x, a.y) <= (b.x / 2)
				|| pointDistanceRect(b, (a.x + a.width), a.y) <= (b.x / 2)
				|| pointDistanceRect(b, (a.x + a.width), (a.y + a.height)) <= (b.x / 2)
				|| pointDistanceRect(b, a.x, (a.y + a.height)) <= (b.x / 2)) {
			return true;
		} else if (b.middleX() >= a.x && b.middleX() <= (a.x + a.width)
				&& b.middleY() <= a.y && b.middleY() >= (a.y + (b.y / 2))
				|| b.middleX() >= a.x && b.middleX() <= (a.x + a.width)
				&& b.middleY() >= (a.y + a.height)
				&& b.middleY() <= (a.y + a.height + (b.y / 2))
				|| b.middleY() >= a.y && b.middleY() <= (a.y + a.height)
				&& b.middleX() <= a.x && b.middleX() >= (a.x - (b.x / 2))
				|| b.middleY() >= a.y && b.middleY() <= (a.y + a.height)
				&& b.middleX() >= (a.x + a.width)
				&& b.middleX() <= (a.x + a.width + (b.x / 2))) {
			return true;
		}
		// Überprüfung
		return false;
	}

	// Abstand Kreis zu Kreis
	public double pointdistanceCircle(Figur a, Figur b) {
		double distance = 0.0;
		distance = Math.sqrt((Math.pow((a.middleX() - b.middleX()), 2) + Math
				.pow((a.middleY() - b.middleY()), 2)) * 1.0);
		return distance;
	}

	// Abstand Kreis zu Punkt(Ecke vom Rechteck)
	public double pointDistanceRect(Figur b, int x, int y) {
		double distance = 0.0;
		distance = Math.sqrt((Math.pow((b.middleX() - x), 2) + Math.pow(
				(b.middleY() - y), 2)) * 1.0);
		return distance;
	}

	public void CorrectPosition(Figur f, List<Figur> ObjectList) {
		if (f instanceof Taube || f instanceof Greifvogel
				|| f instanceof Flugzeug) {
			correctx(f);
			correcty(f);
		}
		if (ObjectList.size() > 1) {
			for (int i = 0; i < (ObjectList.size() - 1); i++) {
				boolean collision = collision(f, ObjectList.get(i));
				if (collision) {
					// Änderung der x und y Koordinaten zum Entgehen einer
					// Kollision beim Einfügen
					f.x = (ObjectList.get(i).x + ObjectList.get(i).width + 5);
					if (f.x > (PanelWidth - f.width)) {
						correctxshift(f);
						f.y = (ObjectList.get(i).y + ObjectList.get(i).height + 5);
						if (f.y > (PanelHeight - f.height)) {
							correctyshift(f);
							f.x = (ObjectList.get(i).x - f.width - 5);
							if (f.x < 0) {
								correctxshift(f);
								f.y = (ObjectList.get(i).y - f.height - 5);
								if (f.y < 0) {
									lblMessageTxt
											.setText("Panel ist voll und Objekt kann nicht plaziert werden");
								}
							}
						}
					}
				}
			}
		}
	}

	// Korrektur x-Koordinate bei Lage außerhalb des Panels
	public void correctx(Figur f) {

		if ((Integer) spinnerX.getValue() >= 0
				&& (Integer) spinnerX.getValue() <= (PanelWidth - f.width)) {
			f.x = (Integer) spinnerX.getValue();
		} else if ((Integer) spinnerX.getValue() > (PanelWidth - f.width)) {
			f.x = (PanelWidth - f.width);
		} else if ((Integer) spinnerX.getValue() < 0) {
			f.x = 0;
		}

	}

	public void correctxshift(Figur f) {
		if (f.x > (PanelWidth - f.width)) {
			f.x = (PanelWidth - f.width);
		} else if (f.x < 0) {
			f.x = 0;
		}
	}

	// Korrektur y-Koordinate bei Lage außerhalb des Panels
	public void correcty(Figur f) {

		if ((Integer) spinnerY.getValue() >= 0
				&& (Integer) spinnerY.getValue() <= (PanelHeight - f.height)) {
			f.y = (Integer) spinnerY.getValue();
		} else if ((Integer) spinnerY.getValue() > (PanelHeight - f.height)) {
			f.y = (PanelHeight - f.height);
		} else if ((Integer) spinnerY.getValue() < 0) {
			f.y = 0;
		}

	}

	public void correctyshift(Figur f) {
		if (f.y > (PanelHeight - f.height)) {
			f.y = (PanelHeight - f.height);
		} else if (f.y < 0) {
			f.y = 0;
		}
	}

	public void deleteObjectList(String name) {

		// Entfernen aus Auswahlliste in ComboBox für Entfernung

		for (int i = 0; i < ListNameEinf.size(); i++) {
			if (ListNameEinf.get(i).equals((String) name)) {
				comboBoxNameEnt.removeAllItems();
				ListNameEinf.remove(i);
				// System.out.println(ListNameEinf);
				break;
			}
		}
		for (int i = 0; i < ListNameEinf.size(); i++) {
			comboBoxNameEnt.addItem(ListNameEinf.get(i));
		}

		// Aktualisierung Info Anzeige + Panel
		panel.repaint();
		updateInfo();
	}

	public static void main(String[] args) {
		window = new SkyLife();
		window.frame.setVisible(true);
		// tmov = new MovementThread(window);

		tmovtest = new MovementTest(window);
		tcol = new CollisionThread(window);
		trep = new RepaintThread(window);

	}

	public SkyLife() {

		frame = new JFrame();
		frame.setResizable(true);
		frame.setBounds(100, 100, 1000, 825);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		// Button zum Speichern des Standes

		speichern = new JButton("Speichern");
		speichern.setBounds(35, 90, 135, 25);
		frame.getContentPane().add(speichern);
		speichern.addActionListener(new ActionListener() {
			// int a;

			public void actionPerformed(ActionEvent e) {
				// GUI stoppen
				// tmov.running = false;
				tmovtest.running = false;
				tcol.running = false;
				trep.running = false;

				for (int i = 0; i < ObjectList.size(); i++) {
					SaveList.add(ObjectList.get(i).Typ + " "
							+ ObjectList.get(i).name + " "
							+ ObjectList.get(i).x + " " + ObjectList.get(i).y);
				}
				File file = new File("SkyLife.ser");
				saveObject(SaveList, file);
				// System.out.println(ObjectList);
			}
		});

		// Button zum Laden des alten Standes

		laden = new JButton("Laden");
		laden.setBounds(35, 50, 135, 25);
		frame.getContentPane().add(laden);
		laden.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				BufferedReader br = null;
				String zeile;
				br = loadFile("SkyLife.ser");

				try {
					while ((zeile = br.readLine()) != null) {

						fig.Typ = zeile.toString().substring(0,
								zeile.indexOf(" "));
						fig.name = zeile.toString().substring(
								zeile.indexOf(" ") + 1,
								zeile.indexOf(" ", zeile.indexOf(" ") + 1));
						fig.x = Integer.parseInt(zeile.toString().substring(
								zeile.indexOf(" ", zeile.indexOf(" ") + 1) + 1,
								zeile.indexOf(" ", zeile.indexOf(" ",
										zeile.indexOf(" ") + 1) + 1)));
						fig.y = Integer.parseInt(zeile.toString().substring(
								zeile.indexOf(" ", zeile.indexOf(" ",
										zeile.indexOf(" ") + 1) + 1) + 1,
								zeile.length()));

						createObject2(fig);
					}
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		// Button zum Starten des alten Standes

		start = new JButton("Start");
		start.setBounds(195, 50, 135, 25);
		frame.getContentPane().add(start);

		start.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (startclicked == 0) {
					// tmov.start();
					tmovtest.start();
					tcol.start();
					trep.start();
				} else {
					// tmov = new MovementThread(window);
					tmovtest = new MovementTest(window);
					tcol = new CollisionThread(window);
					trep = new RepaintThread(window);
					// tmov.start();
					tmovtest.start();
					tcol.start();
					trep.start();
				}
				btnNchsterSchritt.setEnabled(false);
				stop.setEnabled(true);
				lblMessageTxt.setText("Spiel gestartet");
				startclicked++;
				start.setEnabled(false);

			}
		});

		// Button zum Stoppen des Standes

		stop = new JButton("Stop");
		stop.setBounds(195, 90, 135, 25);
		stop.setEnabled(false);
		frame.getContentPane().add(stop);

		stop.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// tmov.running = false;
				tmovtest.running = false;
				tcol.running = false;
				trep.running = false;

				// tmovstep.start();
				btnNchsterSchritt.setEnabled(false);
				stop.setEnabled(false);
				start.setEnabled(true);
				lblMessageTxt.setText("Spiel gestoppt");

			}
		});
		
		// Stand zurücksetzen
		btnNeu = new JButton("Neu");
		btnNeu.setBounds(35, 128, 135, 25);
		frame.getContentPane().add(btnNeu);

		btnNeu.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// Items in Listen Entfernen.. Werte zurücksetzen
				ObjectList.clear();
				comboBoxNameEnt.removeAllItems();
				nameFieldEinf.setText(null);
				spinnerX.setValue(0);
				spinnerY.setValue(0);
				panel.repaint();
				// threads stoppen falls gestartet.. start, stop sichtbarkeit
				if (tmovtest.running = true && tcol.running == true
						&& trep.running == true) {
					tmovtest.running = false;
					tcol.running = false;
					trep.running = false;
					start.setEnabled(true);
					stop.setEnabled(false);
				}
			}
		});

		// Button für schrittweises Ablaufen lassen

		btnNchsterSchritt = new JButton("Next Step");
		btnNchsterSchritt.setEnabled(false);
		btnNchsterSchritt.setBounds(205, 137, 117, 29);
		frame.getContentPane().add(btnNchsterSchritt);

		// btnNchsterSchritt.addActionListener(new ActionListener() {
		//
		// public void actionPerformed(ActionEvent e) {
		// notifyAll();
		//
		// }
		// });

		// Neues Objekt einfuegen

		JLayeredPane layeredPaneAdd = new JLayeredPane();
		layeredPaneAdd.setBorder(new LineBorder(new Color(0, 0, 0)));
		layeredPaneAdd.setBounds(375, 50, 250, 170);
		frame.getContentPane().add(layeredPaneAdd);

		lblTyp = new JLabel("Typ:");
		lblTyp.setHorizontalAlignment(SwingConstants.LEFT);
		lblTyp.setBounds(5, 20, 61, 15);
		layeredPaneAdd.add(lblTyp);

		comboBoxTyp = new JComboBox<String>();
		comboBoxTyp.setBounds(70, 15, 150, 25);
		layeredPaneAdd.add(comboBoxTyp);
		// Typen welche Hinzugefügt werden können
		comboBoxTyp.addItem("Taube");
		comboBoxTyp.addItem("Greifvogel");
		comboBoxTyp.addItem("Flugzeug");
		comboBoxTyp.addItem("Wolkenkratzer");

		lblNameEinf = new JLabel("Name:");
		lblNameEinf.setHorizontalAlignment(SwingConstants.LEFT);
		lblNameEinf.setBounds(5, 55, 61, 15);
		layeredPaneAdd.add(lblNameEinf);

		nameFieldEinf = new JTextField();
		nameFieldEinf.setBounds(70, 50, 150, 25);
		layeredPaneAdd.add(nameFieldEinf);
		nameFieldEinf.setColumns(10);

		lblPosition = new JLabel("Position:");
		lblPosition.setHorizontalAlignment(SwingConstants.LEFT);
		lblPosition.setBounds(5, 95, 61, 15);
		layeredPaneAdd.add(lblPosition);

		lblX = new JLabel("X:");
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setBounds(60, 95, 30, 15);
		layeredPaneAdd.add(lblX);

		spinnerX = new JSpinner();
		layeredPaneAdd.setLayer(spinnerX, 0);
		spinnerX.setBounds(90, 90, 61, 25);
		layeredPaneAdd.add(spinnerX);

		lblY = new JLabel("Y:");
		lblY.setHorizontalAlignment(SwingConstants.CENTER);
		lblY.setBounds(150, 94, 24, 16);
		layeredPaneAdd.add(lblY);

		spinnerY = new JSpinner();
		spinnerY.setBounds(170, 90, 61, 25);
		layeredPaneAdd.add(spinnerY);

		btnEinfgen = new JButton("Einf\u00FCgen");
		btnEinfgen.setBounds(5, 130, 100, 25);
		layeredPaneAdd.add(btnEinfgen);

		btnEinfgen.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// Erstellung Objekt
				if (countTaube >= 10) {
					lblMessageTxt.setText("Maximale Anzahl an Tauben!");
				} else if (countGreifvogel >= 10) {
					lblMessageTxt.setText("Maximale Anzahl an Greifvögeln!");
				} else if (countFlugzeug >= 3) {
					lblMessageTxt.setText("Maximale Anzahl an Flugzeugen!");
				} else if (countWolkenkratzer >= 2) {
					lblMessageTxt.setText("Maximale Anzahl an Wolkenkratzern!");
				} else {
					createObject();
				}

			}
		});

		// Panel-Größe einstellen

		JLayeredPane layeredPanePanelSize = new JLayeredPane();
		layeredPanePanelSize.setBorder(new LineBorder(new Color(0, 0, 0)));
		layeredPanePanelSize.setBounds(671, 50, 250, 83);
		frame.getContentPane().add(layeredPanePanelSize);

		lblPanelgre = new JLabel("Panelgröße:");
		lblPanelgre.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblPanelgre.setBounds(6, 6, 92, 16);
		layeredPanePanelSize.add(lblPanelgre);

		lblHhe = new JLabel("Höhe:");
		lblHhe.setHorizontalAlignment(SwingConstants.CENTER);
		lblHhe.setBounds(6, 30, 61, 16);
		layeredPanePanelSize.add(lblHhe);

		txtPanelHeightadd = new JTextField();
		txtPanelHeightadd.setText("450");
		txtPanelHeightadd.setHorizontalAlignment(SwingConstants.RIGHT);
		txtPanelHeightadd.setBounds(62, 24, 50, 28);
		layeredPanePanelSize.add(txtPanelHeightadd);
		txtPanelHeightadd.setColumns(10);

		lblBreite = new JLabel("Breite:");
		lblBreite.setHorizontalAlignment(SwingConstants.CENTER);
		lblBreite.setBounds(113, 30, 61, 16);
		layeredPanePanelSize.add(lblBreite);

		txtPanelWidthadd = new JTextField();
		txtPanelWidthadd.setText("900");
		txtPanelWidthadd.setHorizontalAlignment(SwingConstants.RIGHT);
		txtPanelWidthadd.setBounds(174, 24, 58, 28);
		layeredPanePanelSize.add(txtPanelWidthadd);
		txtPanelWidthadd.setColumns(10);

		// soblad Objekte enthalten im Panel muss Button deaktiviert werden!!!
		btnSetPanelSize = new JButton("Setzen");
		btnSetPanelSize.setBounds(0, 54, 117, 29);
		layeredPanePanelSize.add(btnSetPanelSize);

		btnSetPanelSize.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				PanelHeight = Integer.parseInt(txtPanelHeightadd.getText());
				PanelWidth = Integer.parseInt(txtPanelWidthadd.getText());
				panel.setBounds(40, 250, PanelWidth, PanelHeight);
				txtInfo.setBounds(40, 250 + PanelHeight + 5, 450, 25);
				KilledAnimals.setBounds(788, 250 + PanelHeight + 5, 133, 25);
				updateInfo();
			}
		});

		// Objekt entfernen

		layeredPaneDelete = new JLayeredPane();
		layeredPaneDelete.setBorder(new LineBorder(new Color(0, 0, 0)));
		layeredPaneDelete.setBounds(671, 137, 250, 83);
		frame.getContentPane().add(layeredPaneDelete);

		lblNameEnt = new JLabel("Name:");
		lblNameEnt.setHorizontalAlignment(SwingConstants.CENTER);
		lblNameEnt.setBounds(6, 18, 61, 16);
		layeredPaneDelete.add(lblNameEnt);

		comboBoxNameEnt = new JComboBox<String>();
		comboBoxNameEnt.setBounds(70, 15, 150, 25);
		layeredPaneDelete.add(comboBoxNameEnt);

		btnEntfernen = new JButton("Entfernen");
		btnEntfernen.setBounds(5, 50, 95, 25);
		layeredPaneDelete.add(btnEntfernen);

		btnEntfernen.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				deleteObject();
				// Entfernung aus Auswahlliste für Entfernen von Objekten
				deleteObjectList((String) comboBoxNameEnt.getSelectedItem());
			}
		});

		// Panel
		panel = new SkyLifePanel(this);
		panel.setBackground(Color.WHITE);
		panel.setBounds(40, 250, PanelWidth, PanelHeight);

		// Anzeige unterhalb des Panels

		txtInfo = new JTextPane();
		txtInfo.setText("Panelgröße:   Höhe: " + PanelHeight + " Breite: "
				+ PanelWidth + "      Anzahl Objekte: " + ObjectList.size());
		txtInfo.setBackground(SystemColor.window);
		txtInfo.setEditable(false);
		txtInfo.setBounds(40, 250 + PanelHeight + 5, 450, 25);
		frame.getContentPane().add(txtInfo);

		KilledAnimals = new JTextPane();
		KilledAnimals.setText("Tote Vögel: " + killedAnimals);
		KilledAnimals.setBackground(SystemColor.window);
		KilledAnimals.setEditable(false);
		KilledAnimals.setBounds(788, 250 + PanelHeight + 5, 133, 25);
		frame.getContentPane().add(KilledAnimals);

		// Feld für Ausgabe von Fehlermeldungen
		lblMessage = new JLabel("Message:");
		lblMessage.setForeground(Color.RED);
		lblMessage.setBounds(6, 742, 66, 16);
		frame.getContentPane().add(lblMessage);

		lblMessageTxt = new JLabel("");
		lblMessageTxt.setForeground(Color.RED);
		lblMessageTxt.setBounds(72, 742, 727, 16);
		frame.getContentPane().add(lblMessageTxt);

	}

	public void saveObject(List<Object> saveList, File datei) {
		PrintWriter printWriter = null;

		try {
			printWriter = new PrintWriter(new BufferedWriter(new FileWriter(
					datei)));
			Iterator<Object> iter = saveList.iterator();
			while (iter.hasNext()) {
				Object o = iter.next();
				printWriter.println(o);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (printWriter != null)
				printWriter.close();
		}

	}

	public BufferedReader loadFile(String fileName) {
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return br;
	}
}
