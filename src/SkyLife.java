import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class SkyLife {

	private JFrame frame;
	private JTextField txtTyp;
	private JTextField txtNameEinf;
	private JTextField txtPosition;
	private JTextField Xneu;
	private JTextField Yneu;
	private JTextField txtNameEnt;
	private JTextField nameFieldEinf;

	JSpinner spinnerLeft, spinnerRigth;
	JButton btnEinfgen, btnEntfernen;
	JComboBox<String> comboBoxTyp, comboBoxNameEnt;

	public static void main(String[] args) {
		SkyLife window = new SkyLife();
		window.frame.setVisible(true);
	}

	public SkyLife() {

		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton laden = new JButton("Laden");
		laden.setBounds(35, 50, 135, 25);
		frame.getContentPane().add(laden);

		JButton start = new JButton("Start");
		start.setBounds(195, 50, 135, 25);
		frame.getContentPane().add(start);

		JButton stop = new JButton("Stop");
		stop.setBounds(195, 90, 135, 25);
		frame.getContentPane().add(stop);

		JButton speichern = new JButton("Speichern");
		speichern.setBounds(35, 90, 135, 25);
		frame.getContentPane().add(speichern);

		// Neues Objekt einf�gen

		JLayeredPane layeredPaneLeft = new JLayeredPane();
		layeredPaneLeft.setBorder(new LineBorder(new Color(0, 0, 0)));
		layeredPaneLeft.setBounds(375, 50, 250, 170);
		frame.getContentPane().add(layeredPaneLeft);

		txtTyp = new JTextField();
		txtTyp.setBackground(Color.WHITE);
		txtTyp.setText("Typ");
		txtTyp.setBounds(5, 15, 40, 25);
		layeredPaneLeft.add(txtTyp);
		txtTyp.setColumns(10);

		comboBoxTyp = new JComboBox<String>();
		comboBoxTyp.setBounds(70, 15, 150, 25);
		layeredPaneLeft.add(comboBoxTyp);
		comboBoxTyp.addItem("Taube");
		comboBoxTyp.addItem("Greifvogel");
		comboBoxTyp.addItem("Flugzeug");
		comboBoxTyp.addItem("Wolkenkratzer");

		txtNameEinf = new JTextField();
		txtNameEinf.setText("Name");
		txtNameEinf.setBounds(5, 50, 50, 25);
		layeredPaneLeft.add(txtNameEinf);
		txtNameEinf.setColumns(10);

		nameFieldEinf = new JTextField();
		nameFieldEinf.setBounds(70, 50, 150, 25);
		layeredPaneLeft.add(nameFieldEinf);
		nameFieldEinf.setColumns(10);
		List<String> ListnameEinf = new ArrayList<String>();
		ListnameEinf.add(nameFieldEinf.getText());

		txtPosition = new JTextField();
		txtPosition.setText("Position");
		txtPosition.setBounds(5, 90, 65, 25);
		layeredPaneLeft.add(txtPosition);
		txtPosition.setColumns(10);

		Xneu = new JTextField();
		Xneu.setText("X");
		Xneu.setBounds(70, 90, 23, 25);
		layeredPaneLeft.add(Xneu);
		Xneu.setColumns(10);

		spinnerLeft = new JSpinner();
		spinnerLeft.setBounds(90, 90, 50, 25);
		layeredPaneLeft.add(spinnerLeft);

		Yneu = new JTextField();
		Yneu.setText("Y");
		Yneu.setBounds(150, 90, 23, 25);
		layeredPaneLeft.add(Yneu);
		Yneu.setColumns(10);

		spinnerRigth = new JSpinner();
		spinnerRigth.setBounds(170, 90, 50, 25);
		layeredPaneLeft.add(spinnerRigth);

		btnEinfgen = new JButton("Einf\u00FCgen");
		btnEinfgen.setBounds(5, 130, 95, 25);
		layeredPaneLeft.add(btnEinfgen);
		

		// Objekt entfernen

		JLayeredPane layeredPaneRigth = new JLayeredPane();
		layeredPaneRigth.setBorder(new LineBorder(new Color(0, 0, 0)));
		layeredPaneRigth.setBounds(670, 50, 250, 90);
		frame.getContentPane().add(layeredPaneRigth);

		txtNameEnt = new JTextField();
		txtNameEnt.setText("Name");
		txtNameEnt.setBounds(5, 15, 50, 25);
		layeredPaneRigth.add(txtNameEnt);
		txtNameEnt.setColumns(10);

		comboBoxNameEnt = new JComboBox<String>();
		comboBoxNameEnt.setBounds(70, 15, 150, 25);
		layeredPaneRigth.add(comboBoxNameEnt);
		for (int i = 0; i < ListnameEinf.size(); i++) {
			comboBoxNameEnt.addItem(ListnameEinf.get(i));
		}

		btnEntfernen = new JButton("Entfernen");
		btnEntfernen.setBounds(5, 50, 95, 25);
		layeredPaneRigth.add(btnEntfernen);

		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(40, 250, 900, 450);
		frame.getContentPane().add(panel);
	}
}
