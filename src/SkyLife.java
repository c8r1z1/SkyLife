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
import javax.swing.JTextPane;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.JLabel;

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
<<<<<<< HEAD
	JComboBox<String> comboBoxTyp, comboBoxNameEnt;
=======
	private JLayeredPane layeredPaneDelete;
	private JTextField txtPanelHeight;
	private JTextField txtPanelHeightadd;
	private JTextField txtPanelWidth;
	private JTextField textPanelWidthadd;
	private JButton btnNchsterSchritt;
	private JPanel panel;
>>>>>>> Christoph

	public static void main(String[] args) {
		SkyLife window = new SkyLife();
		window.frame.setVisible(true);
	}

	public SkyLife() {

		frame = new JFrame();
		frame.setResizable(false);
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
<<<<<<< HEAD

		// Neues Objekt einf�gen

		JLayeredPane layeredPaneLeft = new JLayeredPane();
		layeredPaneLeft.setBorder(new LineBorder(new Color(0, 0, 0)));
		layeredPaneLeft.setBounds(375, 50, 250, 170);
		frame.getContentPane().add(layeredPaneLeft);

=======
		
//		Neues Objekt einf�gen
		
		JLayeredPane layeredPaneAdd = new JLayeredPane();
		layeredPaneAdd.setBorder(new LineBorder(new Color(0, 0, 0)));
		layeredPaneAdd.setBounds(375, 50, 250, 170);
		frame.getContentPane().add(layeredPaneAdd);
		
>>>>>>> Christoph
		txtTyp = new JTextField();
		txtTyp.setEditable(false);
		txtTyp.setBackground(Color.WHITE);
		txtTyp.setText("Typ");
		txtTyp.setBounds(5, 15, 40, 25);
		layeredPaneAdd.add(txtTyp);
		txtTyp.setColumns(10);
<<<<<<< HEAD

		comboBoxTyp = new JComboBox<String>();
		comboBoxTyp.setBounds(70, 15, 150, 25);
		layeredPaneLeft.add(comboBoxTyp);
		comboBoxTyp.addItem("Taube");
		comboBoxTyp.addItem("Greifvogel");
		comboBoxTyp.addItem("Flugzeug");
		comboBoxTyp.addItem("Wolkenkratzer");

=======
		
		typField = new JTextField();
		typField.setBounds(70, 15, 130, 25);
		layeredPaneAdd.add(typField);
		typField.setColumns(10);
		
		JComboBox comboBoxTyp = new JComboBox();
		comboBoxTyp.setBounds(200, 15, 20, 25);
		layeredPaneAdd.add(comboBoxTyp);
		
>>>>>>> Christoph
		txtNameEinf = new JTextField();
		txtNameEinf.setEditable(false);
		txtNameEinf.setText("Name");
		txtNameEinf.setBounds(5, 50, 50, 25);
		layeredPaneAdd.add(txtNameEinf);
		txtNameEinf.setColumns(10);

		nameFieldEinf = new JTextField();
		nameFieldEinf.setBounds(70, 50, 150, 25);
		layeredPaneAdd.add(nameFieldEinf);
		nameFieldEinf.setColumns(10);
		List<String> ListnameEinf = new ArrayList<String>();
		ListnameEinf.add(nameFieldEinf.getText());

		txtPosition = new JTextField();
		txtPosition.setEditable(false);
		txtPosition.setText("Position");
		txtPosition.setBounds(5, 90, 65, 25);
		layeredPaneAdd.add(txtPosition);
		txtPosition.setColumns(10);

		Xneu = new JTextField();
		Xneu.setEditable(false);
		Xneu.setText("X");
		Xneu.setBounds(70, 90, 23, 25);
		layeredPaneAdd.add(Xneu);
		Xneu.setColumns(10);

		spinnerLeft = new JSpinner();
		spinnerLeft.setBounds(90, 90, 50, 25);
<<<<<<< HEAD
		layeredPaneLeft.add(spinnerLeft);

=======
		layeredPaneAdd.add(spinnerLeft);
		
>>>>>>> Christoph
		Yneu = new JTextField();
		Yneu.setEditable(false);
		Yneu.setText("Y");
		Yneu.setBounds(150, 90, 23, 25);
		layeredPaneAdd.add(Yneu);
		Yneu.setColumns(10);

		spinnerRigth = new JSpinner();
		spinnerRigth.setBounds(170, 90, 50, 25);
<<<<<<< HEAD
		layeredPaneLeft.add(spinnerRigth);

=======
		layeredPaneAdd.add(spinnerRigth);
		
>>>>>>> Christoph
		btnEinfgen = new JButton("Einf\u00FCgen");
		btnEinfgen.setBounds(5, 130, 95, 25);
		layeredPaneAdd.add(btnEinfgen);
		
<<<<<<< HEAD

		// Objekt entfernen

		JLayeredPane layeredPaneRigth = new JLayeredPane();
		layeredPaneRigth.setBorder(new LineBorder(new Color(0, 0, 0)));
		layeredPaneRigth.setBounds(670, 50, 250, 90);
		frame.getContentPane().add(layeredPaneRigth);

=======
//		Objekt entfernen
		
		JLayeredPane layeredPaneDelete;
		layeredPaneDelete = new JLayeredPane();
		layeredPaneDelete.setBorder(new LineBorder(new Color(0, 0, 0)));
		layeredPaneDelete.setBounds(671, 130, 250, 90);
		frame.getContentPane().add(layeredPaneDelete);
		
>>>>>>> Christoph
		txtNameEnt = new JTextField();
		txtNameEnt.setEditable(false);
		txtNameEnt.setText("Name");
		txtNameEnt.setBounds(5, 15, 50, 25);
		layeredPaneDelete.add(txtNameEnt);
		txtNameEnt.setColumns(10);
<<<<<<< HEAD

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
=======
		
		nameFieldEnt = new JTextField();
		nameFieldEnt.setBounds(70, 15, 130, 25);
		layeredPaneDelete.add(nameFieldEnt);
		nameFieldEnt.setColumns(10);
		
		JComboBox comboBoxName = new JComboBox();
		comboBoxName.setBounds(200, 15, 20, 25);
		layeredPaneDelete.add(comboBoxName);
		
		btnEntfernen = new JButton("Entfernen");
		btnEntfernen.setBounds(5, 50, 95, 25);
		layeredPaneDelete.add(btnEntfernen);
		
		
		JTextPane textInformation = new JTextPane();
		textInformation.setText("Panelgröße: ... Anzahl Objekte: ...");
		textInformation.setBackground(SystemColor.window);
		textInformation.setEditable(false);
		textInformation.setBounds(40, 705, 800, 25);
		frame.getContentPane().add(textInformation);
		
		//Panel-Größe einstellen
		
		JLayeredPane layeredPanePanelSize = new JLayeredPane();
		layeredPanePanelSize.setBorder(new LineBorder(new Color(0, 0, 0)));
		layeredPanePanelSize.setBounds(671, 50, 250, 70);
		frame.getContentPane().add(layeredPanePanelSize);
		
		txtPanelHeight = new JTextField();
		txtPanelHeight.setEditable(false);
		txtPanelHeight.setText("Höhe");
		txtPanelHeight.setBounds(6, 6, 50, 28);
		layeredPanePanelSize.add(txtPanelHeight);
		txtPanelHeight.setColumns(10);
		
		txtPanelHeightadd = new JTextField();
		txtPanelHeightadd.setHorizontalAlignment(SwingConstants.RIGHT);
		txtPanelHeightadd.setText("450");
		txtPanelHeightadd.setBounds(62, 6, 50, 28);
		layeredPanePanelSize.add(txtPanelHeightadd);
		txtPanelHeightadd.setColumns(10);
		int PanelHeight = Integer.parseInt(txtPanelHeightadd.getText());
		
		txtPanelWidth = new JTextField();
		txtPanelWidth.setEditable(false);
		txtPanelWidth.setText("Breite");
		txtPanelWidth.setBounds(124, 6, 50, 28);
		layeredPanePanelSize.add(txtPanelWidth);
		txtPanelWidth.setColumns(10);
		
		textPanelWidthadd = new JTextField();
		textPanelWidthadd.setText("900");
		textPanelWidthadd.setBounds(186, 6, 58, 28);
		layeredPanePanelSize.add(textPanelWidthadd);
		textPanelWidthadd.setColumns(10);
		int PanelWidth = Integer.parseInt(textPanelWidthadd.getText());
		
		JButton btnSetPanelSize = new JButton("Setzen");
		btnSetPanelSize.setBounds(737, 88, 117, 29);
		frame.getContentPane().add(btnSetPanelSize);
		
		btnSetPanelSize.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(e.getActionCommand() + " clicked!");
				panel.repaint();
			}
			
		});
		
		panel = new JPanel();
>>>>>>> Christoph
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(40, 250, PanelWidth, PanelHeight);
		frame.getContentPane().add(panel);
		
		//Button für schrittweises Ablaufen lassen
		
		btnNchsterSchritt = new JButton("Next Step");
		btnNchsterSchritt.setEnabled(false);
		btnNchsterSchritt.setBounds(205, 137, 117, 29);
		frame.getContentPane().add(btnNchsterSchritt);
	}
}
