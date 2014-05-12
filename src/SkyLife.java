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
import javax.swing.border.LineBorder;

public class SkyLife {

	private JFrame frame;

	JSpinner spinnerX, spinnerY;
	JButton laden, speichern, start, stop, btnEinfgen, btnEntfernen,
			btnSetPanelSize, btnNchsterSchritt;
	JComboBox<String> comboBoxTyp, comboBoxNameEnt;
	JLabel lblTyp, lblNameEinf, lblNameEnt, lblPosition, lblX, lblY, lblBreite,
			lblHoehe;
	JTextField nameFieldEinf, txtPanelWidhtadd, txtPanelHeighthadd;
	JTextPane txtInfo;

	private JLayeredPane layeredPaneDelete, layeredPaneAdd;
	private JPanel panel;

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

		lblTyp = new JLabel("Typ");
		lblTyp.setBounds(5, 20, 40, 15);
		layeredPaneAdd.add(lblTyp);

		comboBoxTyp = new JComboBox<String>();
		comboBoxTyp.setBounds(70, 15, 150, 25);
		layeredPaneAdd.add(comboBoxTyp);
		// Typen welche Hinzugefügt werden können
		comboBoxTyp.addItem("Taube");
		comboBoxTyp.addItem("Greifvogel");
		comboBoxTyp.addItem("Flugzeug");
		comboBoxTyp.addItem("Wolkenkratzer");

		lblNameEinf = new JLabel("Name");
		lblNameEinf.setBounds(5, 55, 40, 15);
		layeredPaneAdd.add(lblNameEinf);

		nameFieldEinf = new JTextField();
		nameFieldEinf.setBounds(70, 50, 150, 25);
		layeredPaneAdd.add(nameFieldEinf);
		nameFieldEinf.setColumns(10);
		List<String> ListnameEinf = new ArrayList<String>();
		ListnameEinf.add(nameFieldEinf.getText());

		lblPosition = new JLabel("Position");
		lblPosition.setBounds(5, 95, 50, 15);
		layeredPaneAdd.add(lblPosition);

		lblX = new JLabel("X");
		lblX.setBounds(70, 95, 15, 15);
		layeredPaneAdd.add(lblX);

		spinnerX = new JSpinner();
		spinnerX.setBounds(90, 90, 50, 25);
		layeredPaneAdd.add(spinnerX);

		lblY = new JLabel("Y");
		lblY.setBounds(150, 95, 15, 15);
		layeredPaneAdd.add(lblY);

		spinnerY = new JSpinner();
		spinnerY.setBounds(170, 90, 50, 25);
		layeredPaneAdd.add(spinnerY);

		btnEinfgen = new JButton("Einf\u00FCgen");
		btnEinfgen.setBounds(5, 130, 100, 25);
		layeredPaneAdd.add(btnEinfgen);

		// Objekt entfernen

		layeredPaneDelete = new JLayeredPane();
		layeredPaneDelete.setBorder(new LineBorder(new Color(0, 0, 0)));
		layeredPaneDelete.setBounds(670, 130, 250, 90);
		frame.getContentPane().add(layeredPaneDelete);

		comboBoxNameEnt = new JComboBox<String>();
		comboBoxNameEnt.setBounds(70, 15, 150, 25);
		layeredPaneDelete.add(comboBoxNameEnt);
		for (int i = 0; i < ListnameEinf.size(); i++) {
			comboBoxNameEnt.addItem(ListnameEinf.get(i));
		}

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
			}
		});

		panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(40, 250, 900, 450);
		frame.getContentPane().add(panel);

		txtInfo = new JTextPane();
		txtInfo.setText("Panelgröße: 900 x 450 Anzahl Objekte: ...");
		txtInfo.setBackground(SystemColor.window);
		txtInfo.setEditable(false);
		txtInfo.setBounds(40, 705, 400, 25);
		frame.getContentPane().add(txtInfo);
	}
}
