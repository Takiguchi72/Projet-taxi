package GUI;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class ProjetTaxiGUI_v5 extends JFrame {

	private JPanel contentPane;
	private JTextField txtDep;
	private JTextField txtKm;
	private JTextField txtDuree;
	private JLabel lblMsgError;
	private final ButtonGroup radioButton = new ButtonGroup();
	private final Modules module = new Modules();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				int[] saisies = {0,0,0,0};
				try {
					ProjetTaxiGUI_v5 frame = new ProjetTaxiGUI_v5();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ProjetTaxiGUI_v5() {
		setTitle("Projet Taxi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 450);
		contentPane = new JPanel();
		contentPane.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDep = new JLabel("Département :");
		lblDep.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		lblDep.setPreferredSize(new Dimension(100, 15));
		lblDep.setBounds(50, 30, 113, 15);
		contentPane.add(lblDep);
		
		txtDep = new JTextField();
		txtDep.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		txtDep.setBounds(230, 30, 49, 19);
		contentPane.add(txtDep);
		txtDep.setColumns(2);
		
		JButton btnSuivant = new JButton("Suivant");
		btnSuivant.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		btnSuivant.setBounds(300, 30, 93, 18);
		contentPane.add(btnSuivant);
		
		JButton btnReinit = new JButton("Réinitialiser");
		btnReinit.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		btnReinit.setBounds(410, 30, 125, 18);
		contentPane.add(btnReinit);
		
		JLabel lblKm = new JLabel("Nombre de kilomètres :");
		lblKm.setVisible(false);
		lblKm.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		lblKm.setPreferredSize(new Dimension(160, 15));
		lblKm.setBounds(50, 70, 165, 15);
		contentPane.add(lblKm);
		
		txtKm = new JTextField();
		txtKm.setVisible(false);
		txtKm.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		txtKm.setBounds(230, 70, 114, 19);
		contentPane.add(txtKm);
		txtKm.setColumns(10);
		
		JLabel lblDuree = new JLabel("Durée (en min) :");
		lblDuree.setVisible(false);
		lblDuree.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		lblDuree.setPreferredSize(new Dimension(160, 15));
		lblDuree.setBounds(50, 110, 126, 15);
		contentPane.add(lblDuree);
		
		txtDuree = new JTextField();
		txtDuree.setVisible(false);
		txtDuree.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		txtDuree.setBounds(230, 110, 114, 19);
		contentPane.add(txtDuree);
		txtDuree.setColumns(10);
		
		JRadioButton btnRadASSem = new JRadioButton("Allé simple - Semaine");
		btnRadASSem.setVisible(false);
		btnRadASSem.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		radioButton.add(btnRadASSem);
		btnRadASSem.setBounds(45, 150, 181, 23);
		contentPane.add(btnRadASSem);
		
		JRadioButton btnRadARSem = new JRadioButton("Allé retour - Semaine");
		btnRadARSem.setVisible(false);
		btnRadARSem.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		radioButton.add(btnRadARSem);
		btnRadARSem.setBounds(45, 180, 180, 23);
		contentPane.add(btnRadARSem);
		
		JRadioButton btnRadASNuitDim = new JRadioButton("Allé simple - Nuit/Dimanche");
		btnRadASNuitDim.setVisible(false);
		btnRadASNuitDim.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		radioButton.add(btnRadASNuitDim);
		btnRadASNuitDim.setBounds(45, 210, 220, 23);
		contentPane.add(btnRadASNuitDim);
		
		JRadioButton btnRadARNuitDim = new JRadioButton("Allé retour - Nuit/Dim");
		btnRadARNuitDim.setVisible(false);
		btnRadARNuitDim.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		radioButton.add(btnRadARNuitDim);
		btnRadARNuitDim.setBounds(45, 240, 220, 23);
		contentPane.add(btnRadARNuitDim);
		
		JButton btnCalculer = new JButton("New button");
		btnCalculer.setVisible(false);
		btnCalculer.setBounds(70, 300, 117, 25);
		contentPane.add(btnCalculer);
		
		JLabel lblAffichage = new JLabel("Test");
		lblAffichage.setVisible(false);
		lblAffichage.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblAffichage.setBounds(120, 368, 400, 30);
		contentPane.add(lblAffichage);
		
		JLabel lblMsgError = new JLabel("Erreur : ");
		lblMsgError.setForeground(Color.RED);
		lblMsgError.setVisible(false);
		lblMsgError.setBounds(50, 340, 550, 15);
		contentPane.add(lblMsgError);
	}
}
