import java.awt.Color;
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
<<<<<<< HEAD
import javax.swing.border.LineBorder;
=======

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Font;
>>>>>>> a5b6f1ca359a6a0895bb5a064853e49c25715016

public class SkyLife {

	private JFrame frame;
<<<<<<< HEAD
=======
	private JTextField nameFieldEinf;

	JSpinner spinnerX, spinnerY;
	JButton btnEinfgen, btnEntfernen;
>>>>>>> a5b6f1ca359a6a0895bb5a064853e49c25715016

	JSpinner spinnerX, spinnerY;
	JButton laden, speichern, start, stop, btnEinfgen, btnEntfernen,
			btnSetPanelSize, btnNchsterSchritt;
	JComboBox<String> comboBoxTyp, comboBoxNameEnt;
	JLabel lblTyp, lblNameEinf, lblNameEnt, lblPosition, lblX, lblY, lblBreite,
			lblHoehe;
	JTextField nameFieldEinf, txtPanelWidhtadd, txtPanelHeighthadd;
	JTextPane txtInfo;

<<<<<<< HEAD
	private JLayeredPane layeredPaneDelete, layeredPaneAdd;
=======
	private JLayeredPane layeredPaneDelete;
	private JTextField txtPanelHeightadd;
	private JTextField textPanelWidthadd;
	private JButton btnNchsterSchritt;
>>>>>>> a5b6f1ca359a6a0895bb5a064853e49c25715016
	private JPanel panel;
	private int PanelHeight = 450;
	private int PanelWidth = 900;
	private JTextPane textInformation;
	private JTextPane txtpnKilledAnimals;
	private JLabel lblName;
	private JLabel lblTyp;
	private JLabel lblName_1;
	private JLabel lblPosition;
	private JLabel lblX;
	private JLabel lblY;
	private JLabel lblHhe;
	private JLabel lblBreite;
	private JLabel lblPanelgre;

	public static void main(String[] args) {
		SkyLife window = new SkyLife();
		window.frame.setVisible(true);
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

		layeredPaneAdd = new JLayeredPane();
		layeredPaneAdd.setBorder(new LineBorder(new Color(0, 0, 0)));
		layeredPaneAdd.setBounds(375, 50, 250, 170);
		frame.getContentPane().add(layeredPaneAdd);
<<<<<<< HEAD

		lblTyp = new JLabel("Typ");
		lblTyp.setBounds(5, 20, 40, 15);
		layeredPaneAdd.add(lblTyp);
=======
>>>>>>> a5b6f1ca359a6a0895bb5a064853e49c25715016

		comboBoxTyp = new JComboBox<String>();
		comboBoxTyp.setBounds(70, 15, 150, 25);
		layeredPaneAdd.add(comboBoxTyp);
		// Typen welche Hinzugefügt werden können
		comboBoxTyp.addItem("Taube");
		comboBoxTyp.addItem("Greifvogel");
		comboBoxTyp.addItem("Flugzeug");
		comboBoxTyp.addItem("Wolkenkratzer");
<<<<<<< HEAD

		lblNameEinf = new JLabel("Name");
		lblNameEinf.setBounds(5, 55, 40, 15);
		layeredPaneAdd.add(lblNameEinf);
=======
>>>>>>> a5b6f1ca359a6a0895bb5a064853e49c25715016

		nameFieldEinf = new JTextField();
		nameFieldEinf.setBounds(70, 50, 150, 25);
		layeredPaneAdd.add(nameFieldEinf);
		nameFieldEinf.setColumns(10);
		List<String> ListnameEinf = new ArrayList<String>();
		ListnameEinf.add(nameFieldEinf.getText());

<<<<<<< HEAD
		lblPosition = new JLabel("Position");
		lblPosition.setBounds(5, 95, 50, 15);
		layeredPaneAdd.add(lblPosition);

		lblX = new JLabel("X");
		lblX.setBounds(70, 95, 15, 15);
		layeredPaneAdd.add(lblX);

=======
>>>>>>> a5b6f1ca359a6a0895bb5a064853e49c25715016
		spinnerX = new JSpinner();
		spinnerX.setBounds(90, 90, 50, 25);
		layeredPaneAdd.add(spinnerX);

<<<<<<< HEAD
		lblY = new JLabel("Y");
		lblY.setBounds(150, 95, 15, 15);
		layeredPaneAdd.add(lblY);

		spinnerY = new JSpinner();
		spinnerY.setBounds(170, 90, 50, 25);
		layeredPaneAdd.add(spinnerY);

=======
		spinnerY = new JSpinner();
		spinnerY.setBounds(170, 90, 50, 25);
		layeredPaneAdd.add(spinnerY);
		
>>>>>>> a5b6f1ca359a6a0895bb5a064853e49c25715016
		btnEinfgen = new JButton("Einf\u00FCgen");
		btnEinfgen.setBounds(5, 130, 100, 25);
		layeredPaneAdd.add(btnEinfgen);
<<<<<<< HEAD

		// Objekt entfernen

		layeredPaneDelete = new JLayeredPane();
		layeredPaneDelete.setBorder(new LineBorder(new Color(0, 0, 0)));
		layeredPaneDelete.setBounds(670, 130, 250, 90);
=======
		
		lblTyp = new JLabel("Typ:");
		lblTyp.setHorizontalAlignment(SwingConstants.CENTER);
		lblTyp.setBounds(5, 18, 61, 16);
		layeredPaneAdd.add(lblTyp);
		
		lblName_1 = new JLabel("Name:");
		lblName_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblName_1.setBounds(9, 55, 61, 16);
		layeredPaneAdd.add(lblName_1);
		
		lblPosition = new JLabel("Position:");
		lblPosition.setHorizontalAlignment(SwingConstants.CENTER);
		lblPosition.setBounds(5, 95, 61, 16);
		layeredPaneAdd.add(lblPosition);
		
		lblX = new JLabel("X:");
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setBounds(62, 95, 31, 16);
		layeredPaneAdd.add(lblX);
		
		lblY = new JLabel("Y:");
		lblY.setHorizontalAlignment(SwingConstants.CENTER);
		lblY.setBounds(144, 95, 24, 16);
		layeredPaneAdd.add(lblY);
		
//		Objekt entfernen
		
		layeredPaneDelete = new JLayeredPane();
		layeredPaneDelete.setBorder(new LineBorder(new Color(0, 0, 0)));
		layeredPaneDelete.setBounds(671, 137, 250, 83);
>>>>>>> a5b6f1ca359a6a0895bb5a064853e49c25715016
		frame.getContentPane().add(layeredPaneDelete);

		comboBoxNameEnt = new JComboBox<String>();
		comboBoxNameEnt.setBounds(70, 15, 150, 25);
		layeredPaneDelete.add(comboBoxNameEnt);
		for (int i = 0; i < ListnameEinf.size(); i++) {
			comboBoxNameEnt.addItem(ListnameEinf.get(i));
		}
<<<<<<< HEAD

		btnEntfernen = new JButton("Entfernen");
		btnEntfernen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnEntfernen.setBounds(5, 50, 100, 25);
		layeredPaneDelete.add(btnEntfernen);

		lblNameEnt = new JLabel("Name");
		lblNameEnt.setBounds(5, 20, 40, 15);
		layeredPaneDelete.add(lblNameEnt);

		// Panel-Größe einstellen

		JLayeredPane layeredPanePanelSize = new JLayeredPane();
		layeredPanePanelSize.setBorder(new LineBorder(new Color(0, 0, 0)));
		layeredPanePanelSize.setBounds(670, 50, 250, 70);
		frame.getContentPane().add(layeredPanePanelSize);

		lblBreite = new JLabel("Breite");
		lblBreite.setBounds(5, 10, 40, 15);
		layeredPanePanelSize.add(lblBreite);

		txtPanelWidhtadd = new JTextField();
		txtPanelWidhtadd.setText("900");
		txtPanelWidhtadd.setBounds(55, 5, 50, 28);
		layeredPanePanelSize.add(txtPanelWidhtadd);
		txtPanelWidhtadd.setColumns(10);

		lblHoehe = new JLabel("Höhe");
		lblHoehe.setBounds(120, 10, 40, 15);
		layeredPanePanelSize.add(lblHoehe);

		txtPanelHeighthadd = new JTextField();
		txtPanelHeighthadd.setText("450");
		txtPanelHeighthadd.setBounds(170, 5, 58, 28);
		layeredPanePanelSize.add(txtPanelHeighthadd);
		txtPanelHeighthadd.setColumns(10);

		btnSetPanelSize = new JButton("Setzen");
		btnSetPanelSize.setBounds(5, 40, 100, 25);
		layeredPanePanelSize.add(btnSetPanelSize);

		btnSetPanelSize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(e.getActionCommand() + " clicked!");
				int PanelHeight = Integer.parseInt(txtPanelHeighthadd.getText());
				int PanelWidth = Integer.parseInt(txtPanelWidhtadd.getText());
				panel.setBounds(40, 250, PanelWidth, PanelHeight);
				txtInfo.setBounds(40, 255 + PanelHeight, 400, 25);
				txtInfo.setText("Panelgröße: " + PanelWidth + " x "
						+ PanelHeight + " Anzahl Objekte:...");
=======
		
		btnEntfernen = new JButton("Entfernen");
		btnEntfernen.setBounds(5, 50, 95, 25);
		layeredPaneDelete.add(btnEntfernen);
		
		lblName = new JLabel("Name:");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setBounds(6, 18, 61, 16);
		layeredPaneDelete.add(lblName);
		
		//Panel-Größe einstellen
		
		JLayeredPane layeredPanePanelSize = new JLayeredPane();
		layeredPanePanelSize.setBorder(new LineBorder(new Color(0, 0, 0)));
		layeredPanePanelSize.setBounds(671, 50, 250, 83);
		frame.getContentPane().add(layeredPanePanelSize);
		
		txtPanelHeightadd = new JTextField();
		txtPanelHeightadd.setHorizontalAlignment(SwingConstants.RIGHT);
		txtPanelHeightadd.setBounds(62, 24, 50, 28);
		layeredPanePanelSize.add(txtPanelHeightadd);
		txtPanelHeightadd.setColumns(10);
		
		textPanelWidthadd = new JTextField();
		textPanelWidthadd.setHorizontalAlignment(SwingConstants.RIGHT);
		textPanelWidthadd.setBounds(174, 24, 58, 28);
		layeredPanePanelSize.add(textPanelWidthadd);
		textPanelWidthadd.setColumns(10);
		
		lblHhe = new JLabel("Höhe:");
		lblHhe.setHorizontalAlignment(SwingConstants.CENTER);
		lblHhe.setBounds(6, 30, 61, 16);
		layeredPanePanelSize.add(lblHhe);
		
		lblBreite = new JLabel("Breite:");
		lblBreite.setHorizontalAlignment(SwingConstants.CENTER);
		lblBreite.setBounds(113, 30, 61, 16);
		layeredPanePanelSize.add(lblBreite);
		
		JButton btnSetPanelSize = new JButton("Setzen");
		btnSetPanelSize.setBounds(0, 54, 117, 29);
		layeredPanePanelSize.add(btnSetPanelSize);
		
		lblPanelgre = new JLabel("Panelgröße:");
		lblPanelgre.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblPanelgre.setBounds(6, 6, 92, 16);
		layeredPanePanelSize.add(lblPanelgre);
		
		btnSetPanelSize.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(e.getActionCommand() + " clicked!");
				PanelHeight = Integer.parseInt(txtPanelHeightadd.getText());
				PanelWidth = Integer.parseInt(textPanelWidthadd.getText());
				panel.setBounds(40, 250, PanelWidth, PanelHeight);
				textInformation.setBounds(40, 250 + PanelHeight + 5, 450, 25);
				textInformation.setText("Panelgröße:   Höhe: " + PanelHeight + " Breite: " + PanelWidth + "      Anzahl Objekte: ...");
				txtpnKilledAnimals.setBounds(798, 250 + PanelHeight + 5, 142, 25);
				//seems not to be needed‚
				//panel.repaint();
				System.out.println("PanelSize:    Höhe: " + PanelHeight + " Breite: " + PanelWidth);
>>>>>>> a5b6f1ca359a6a0895bb5a064853e49c25715016
			}
		});

		panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
<<<<<<< HEAD
		panel.setBounds(40, 250, 900, 450);
		frame.getContentPane().add(panel);

		txtInfo = new JTextPane();
		txtInfo.setText("Panelgröße: 900 x 450 Anzahl Objekte: ...");
		txtInfo.setBackground(SystemColor.window);
		txtInfo.setEditable(false);
		txtInfo.setBounds(40, 705, 400, 25);
		frame.getContentPane().add(txtInfo);
=======
		frame.getContentPane().add(panel);
		panel.setBounds(40, 250, PanelWidth, PanelHeight);
		
		//Anzeige unterhalb des Panels
		
		textInformation = new JTextPane();
		textInformation.setText("Panelgröße:   Höhe: " + PanelHeight + " Breite: " + PanelWidth + "      Anzahl Objekte: ...");
		textInformation.setBackground(SystemColor.window);
		textInformation.setEditable(false);
		textInformation.setBounds(40, 250 + PanelHeight + 5, 450, 25);
		frame.getContentPane().add(textInformation);
		
		txtpnKilledAnimals = new JTextPane();
		txtpnKilledAnimals.setText("Killed animals: ...");
		txtpnKilledAnimals.setBackground(SystemColor.window);
		txtpnKilledAnimals.setEditable(false);
		txtpnKilledAnimals.setBounds(798, 250 + PanelHeight + 5, 142, 25);
		frame.getContentPane().add(txtpnKilledAnimals);
		
		//Button für schrittweises Ablaufen lassen
		
		btnNchsterSchritt = new JButton("Next Step");
		btnNchsterSchritt.setEnabled(false);
		btnNchsterSchritt.setBounds(205, 137, 117, 29);
		frame.getContentPane().add(btnNchsterSchritt);
		

>>>>>>> a5b6f1ca359a6a0895bb5a064853e49c25715016
	}
}
