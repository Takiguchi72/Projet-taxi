package GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Program.Brochure;

public class JPanelCalcul extends JPanel{
	private Brochure brochure;
	private JTextField txtDep;
	private JLabel lblKm;
	private JTextField txtKm;
	private JLabel lblDuree;
	private JTextField txtDuree;
	private JRadioButton btnRadASSem;
	private JRadioButton btnRadARSem;
	private JRadioButton btnRadASNuitDim;
	private JRadioButton btnRadARNuitDim;
	private final ButtonGroup radioButton = new ButtonGroup();
	private JButton btnCalculer;
	private JLabel lblMsgError;
	private JLabel lblAffichage;
	
	/**
	 * Constructeur de la classe JPanelCalcul
	 */
	public JPanelCalcul()
	{
		//Initialisation du panel
		super();
		//Initialisation de la brochure
		brochure = new Brochure(Modules.initBrochure());
		//Configuration du panel
		this.setBounds(12, 12, 624, 378);
		this.setLayout(null);
		//Création du label Titre
		JLabel lblTitle = new JLabel("Calculer une course");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(215, 0, 180, 15);
		this.add(lblTitle);
		//Création du label du département
		JLabel lblDep = new JLabel("Département :");
		lblDep.setBounds(50, 30, 113, 15);
		this.add(lblDep);
		//Création du JTextField txtDep
		txtDep = new JTextField();
		txtDep.setBounds(230, 30, 49, 19);
		this.add(txtDep);
		txtDep.setName("txtDep");
		//Création du boutton suivant
		JButton btnSuivant = new JButton("Suivant");
		btnSuivant.setBounds(300, 30, 93, 18);
		btnSuivant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				checkDepInBrochure();
			}
		});
		this.add(btnSuivant);
		//Création du boutton Réinitialiser
		JButton btnReinit = new JButton("Réinitialiser");
		btnReinit.setBounds(410, 30, 125, 18);
		this.add(btnReinit);
		//Création du séparator1
		JSeparator separator1 = new JSeparator();
		separator1.setVisible(false);
		separator1.setBounds(110, 70, 200, 15);
		this.add(separator1);
		//Création du JLabel lblKm
		lblKm = new JLabel("Nombre de kilomètres :");
		lblKm.setVisible(false);
		lblKm.setBounds(50, 90, 165, 15);
		this.add(lblKm);
		//Création du JTextField txtKM
		txtKm = new JTextField();
		txtKm.setVisible(false);
		txtKm.setBounds(230, 90, 114, 19);
		this.add(txtKm);
		txtKm.setName("txtKm");
		//Création du JLabel lblDuree
		lblDuree = new JLabel("Durée (en min) :");
		lblDuree.setVisible(false);
		lblDuree.setBounds(50, 120, 126, 15);
		this.add(lblDuree);
		//Création du JTextField txtDuree
		txtDuree = new JTextField();
		txtDuree.setVisible(false);
		txtDuree.setBounds(230, 120, 114, 19);
		this.add(txtDuree);
		txtDuree.setColumns(10);
		txtDuree.setName("txtDuree");
		//Création du séparator2
		JSeparator separator2 = new JSeparator();
		separator2.setVisible(false);
		separator2.setBounds(110, 160, 200, 15);
		this.add(separator2);
		//Création du JRadioButton ASSem
		btnRadASSem = new JRadioButton("Allé simple - Semaine");
		btnRadASSem.setVisible(false);
		radioButton.add(btnRadASSem);
		btnRadASSem.setBounds(45, 180, 181, 23);
		this.add(btnRadASSem);
		//Création du JRadioButton ARSem
		btnRadARSem = new JRadioButton("Allé retour - Semaine");
		btnRadARSem.setVisible(false);
		radioButton.add(btnRadARSem);
		btnRadARSem.setBounds(45, 210, 180, 23);
		this.add(btnRadARSem);
		//Création du JRadioButton ASNuitDim
		btnRadASNuitDim = new JRadioButton("Allé simple - Nuit/Dimanche");
		btnRadASNuitDim.setVisible(false);
		radioButton.add(btnRadASNuitDim);
		btnRadASNuitDim.setBounds(45, 240, 220, 23);
		this.add(btnRadASNuitDim);
		//Création du JRadioButton ARNuitDim
		btnRadARNuitDim = new JRadioButton("Allé retour - Nuit/Dim");
		btnRadARNuitDim.setVisible(false);
		radioButton.add(btnRadARNuitDim);
		btnRadARNuitDim.setBounds(45, 270, 220, 23);
		this.add(btnRadARNuitDim);
		//Création du JButton Calculer
		btnCalculer = new JButton("Calculer");
		btnCalculer.setVisible(false);
		btnCalculer.setBounds(420, 280, 120, 25);
		this.add(btnCalculer);
		//Création du JLabel d'affichage
		lblAffichage = new JLabel("Vous devez payer : 0.00 €");
		lblAffichage.setVisible(false);
		lblAffichage.setHorizontalAlignment(SwingConstants.CENTER);
		lblAffichage.setBounds(120, 337, 400, 30);
		this.add(lblAffichage);
		//Création du JLabel d'erreurs
		lblMsgError = new JLabel("Message d'erreurs");
		lblMsgError.setVisible(false);
		lblMsgError.setHorizontalAlignment(SwingConstants.LEFT);
		lblMsgError.setForeground(Color.RED);
		lblMsgError.setBounds(45, 310, 550, 40);
		this.add(lblMsgError);
	}//Fin JPanelCalcul()
	
	//*****************//
	// M E T H O D E S //
	//*****************//

}//Fin class
