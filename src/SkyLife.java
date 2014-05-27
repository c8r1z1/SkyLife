import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class SkyLife {

	private JFrame frame;

	private JTextField nameFieldEinf;

	JSpinner spinnerX, spinnerY;
	JButton btnEinfgen, btnEntfernen;

	JButton laden, speichern, start, stop, btnSetPanelSize, btnNchsterSchritt;
	JComboBox<String> comboBoxTyp, comboBoxNameEnt;
	JLabel lblNameEinf, lblNameEnt, lblHoehe, lblTyp, lblPosition, lblX, lblY,
			lblHhe, lblBreite, lblPanelgre;
	JTextField txtPanelWidthadd, txtPanelHeightadd;
	private JTextPane txtInfo, txtpnKilledAnimals;

	private JLayeredPane layeredPaneDelete;
	public SkyLifePanel panel;
	private int PanelHeight = 450;
	private int PanelWidth = 900;

	// Objekt Liste für Info-Anzeige unter dem Panel
	public List<Figur> ObjectList = new ArrayList<Figur>();

	// Counter für getötete Vögel(Eventuell Synchronized nutzen wegen kritischem
	// Abschnitt bei Erhöhung)
	public int killedAnimals = 0;
	
	List<String> ListNameEinf = new ArrayList<String>();
	
	static MovementThread tmov;
	

	public static void main(String[] args) {
		SkyLife window = new SkyLife();
		window.frame.setVisible(true);
		tmov = new MovementThread(window);
		//trepaint = new RepaintThread();

	}

	public SkyLife() {

		frame = new JFrame();
		frame.setResizable(true);
		frame.setBounds(100, 100, 1000, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		// Button zum Laden des alten Standes

		laden = new JButton("Laden");
		laden.setBounds(35, 50, 135, 25);
		frame.getContentPane().add(laden);

		// Button zum Starten des alten Standes

		start = new JButton("Start");
		start.setBounds(195, 50, 135, 25);
		frame.getContentPane().add(start);
		
		start.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				tmov.start();

			}
		});

		// Button zum Stoppen des Standes

		stop = new JButton("Stop");
		stop.setBounds(195, 90, 135, 25);
		frame.getContentPane().add(stop);

		// Button zum Speicher des Standes

		speichern = new JButton("Speichern");
		speichern.setBounds(35, 90, 135, 25);
		frame.getContentPane().add(speichern);

		// Button für schrittweises Ablaufen lassen

		btnNchsterSchritt = new JButton("Next Step");
		btnNchsterSchritt.setEnabled(false);
		btnNchsterSchritt.setBounds(205, 137, 117, 29);
		frame.getContentPane().add(btnNchsterSchritt);

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

		// Überarbeitung notwendig
		String errormessage = null;

		if ((int) spinnerX.getValue() > PanelWidth
				|| (int) spinnerY.getValue() > PanelHeight) {
			errormessage = "Objekt liegt nicht innerhalb der Anzeige!";
		} else {
			btnEinfgen.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// So oder schönere Möglichkeit
					boolean nameInUse = false;
					for (int i = 0; i < ObjectList.size(); i++) {
						String name = ObjectList.get(i).name;
						if (nameFieldEinf.getText().equals(name)) {
							nameInUse = true;
							break;
						}
					}

					if (!nameInUse && nameFieldEinf.getText() != "") {
						if (comboBoxTyp.getSelectedItem().toString() == "Taube") {
							Taube taube = new Taube(nameFieldEinf.getText(), (Integer) spinnerX.getValue(), (Integer) spinnerY.getValue(), 1);
							ObjectList.add(taube);
							// System.out.println(nameFieldEinf.getText());
						} else if (comboBoxTyp.getSelectedItem().toString() == "Greifvogel") {
							Greifvogel gv = new Greifvogel(nameFieldEinf
									.getText(), (Integer) spinnerX.getValue(), (Integer) spinnerY.getValue(), 2);
							ObjectList.add(gv);
							// System.out.println(nameFieldEinf.getText());
						} else if (comboBoxTyp.getSelectedItem().toString() == "Flugzeug") {
							Flugzeug fz = new Flugzeug(nameFieldEinf.getText(), (Integer) spinnerX.getValue(), (Integer) spinnerY.getValue(), 5);
							ObjectList.add(fz);
							// System.out.println(nameFieldEinf.getText());
						} else {
							Wolkenkratzer wk = new Wolkenkratzer(nameFieldEinf
									.getText(), (Integer) spinnerX.getValue(), (Integer) spinnerY.getValue(), 0);
							ObjectList.add(wk);
							// System.out.println(nameFieldEinf.getText());
							// System.out.println(ObjectList.toString());
						}
						frame.getContentPane().add(panel);
						panel.repaint();
						
						//Hinzufügen zur Liste für Entfernung
						ListNameEinf.add(nameFieldEinf.getText());
						comboBoxNameEnt.addItem(nameFieldEinf.getText());
						
						//Aktualisierung Info unterhalb des Panels
						txtInfo.setText("Panelgröße:   Höhe: " + PanelHeight
								+ " Breite: " + PanelWidth
								+ "      Anzahl Objekte: " + ObjectList.size());
						
					} else {
						System.out.println("kein gültiger Name einegegben!");
					}
				}
			});

		}

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

		JButton btnSetPanelSize_1 = new JButton("Setzen");
		btnSetPanelSize_1.setBounds(0, 54, 117, 29);
		layeredPanePanelSize.add(btnSetPanelSize_1);

		btnSetPanelSize_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				System.out.println(e.getActionCommand() + " clicked!");
				PanelHeight = Integer.parseInt(txtPanelHeightadd.getText());
				PanelWidth = Integer.parseInt(txtPanelWidthadd.getText());
				panel.setBounds(40, 250, PanelWidth, PanelHeight);
				txtInfo.setBounds(40, 250 + PanelHeight + 5, 450, 25);
				txtInfo.setText("Panelgröße:   Höhe: " + PanelHeight
						+ " Breite: " + PanelWidth
						+ "      Anzahl Objekte: " + ObjectList.size());
				txtpnKilledAnimals.setBounds(798, 250 + PanelHeight + 5, 142,
						25);
				// seems not to be needed‚
				// panel.repaint();
				System.out.println("PanelSize:    Höhe: " + PanelHeight
						+ " Breite: " + PanelWidth);

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
				
				//Entfernung Objektliste
				//Entfernung Objekt seperat notwendig oder reicht Garbage Collector?
				for (int i = 0; i < ObjectList.size(); i++){
					if(ObjectList.get(i).name.equalsIgnoreCase((String) comboBoxNameEnt.getSelectedItem())){
						ObjectList.remove(i);
						System.out.println(ObjectList.toString());
						break;
					}
				}
				
				//Entfernen aus Auswahlliste in ComboBox für Entfernung
				
				for (int i = 0; i < ListNameEinf.size(); i++){
					if(ListNameEinf.get(i).equals((String) comboBoxNameEnt.getSelectedItem())){
						comboBoxNameEnt.removeAllItems();
						ListNameEinf.remove(i);
						System.out.println(ListNameEinf);
						break;
					}
				}
				for (int i = 0; i < ListNameEinf.size(); i++){
					comboBoxNameEnt.addItem(ListNameEinf.get(i));
				}
				
				//Aktualisierung Info Anzeige + Panel
				panel.repaint();
				txtInfo.setText("Panelgröße:   Höhe: " + PanelHeight
						+ " Breite: " + PanelWidth
						+ "      Anzahl Objekte: " + ObjectList.size());

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

		txtpnKilledAnimals = new JTextPane();
		txtpnKilledAnimals.setText("Tote Vögel: " + killedAnimals);
		txtpnKilledAnimals.setBackground(SystemColor.window);
		txtpnKilledAnimals.setEditable(false);
		txtpnKilledAnimals.setBounds(798, 250 + PanelHeight + 5, 142, 25);
		frame.getContentPane().add(txtpnKilledAnimals);

		// Feld für Ausgabe von Fehlermeldungen
		JLabel lblMessage = new JLabel("Message: " + errormessage);
		lblMessage.setForeground(Color.RED);
		lblMessage.setBounds(6, 742, 595, 16);
		frame.getContentPane().add(lblMessage);

	}
}
	
	
