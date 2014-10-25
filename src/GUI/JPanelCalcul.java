package GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Format;
import java.text.NumberFormat;

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
	private JButton btnSuivant;
	private JSeparator separator1;
	private JLabel lblKm;
	private JTextField txtKm;
	private JLabel lblDuree;
	private JTextField txtDuree;
	private JSeparator separator2;
	private JRadioButton btnRadASSem;
	private JRadioButton btnRadARSem;
	private JRadioButton btnRadASNuitDim;
	private JRadioButton btnRadARNuitDim;
	private final ButtonGroup radioButton = new ButtonGroup();
	private JButton btnCalculer;
	private JLabel lblMsgError;
	private JLabel lblAffichage;
	private int[] saisies = new int[4];;

	/**
	 * Constructeur de la classe JPanelCalcul
	 */
	public JPanelCalcul()
	{
		//Initialisation du panel
		super();
		//Initialisation de la brochure
		brochure = new Brochure(Modules.initBrochure());
		//Initialisation du tableau de saisies
		initSaisies();
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
		txtDep.setName("département");
		//Création du boutton suivant
		btnSuivant = new JButton("Suivant");
		btnSuivant.setBounds(300, 30, 93, 18);
		btnSuivant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				checkDepartementSaisi();
			}
		});
		this.add(btnSuivant);
		//Création du boutton Réinitialiser
		JButton btnReinit = new JButton("Réinitialiser");
		btnReinit.setBounds(410, 30, 125, 18);
		btnReinit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisibleSecondPart(false);
			}
		});
		this.add(btnReinit);
		//Création du séparator1
		separator1 = new JSeparator();
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
		txtKm.setName("nombre de kilomètres");
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
		txtDuree.setName("duree");
		//Création du séparator2
		separator2 = new JSeparator();
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
		btnCalculer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				afficherMontantAPayer();
			}
		});
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

	/**
	 * Initialise le tableau de saisies avec des '0'
	 */
	private void initSaisies()
	{
		for(int i=0 ; i < 4 ; i++)
		{
			saisies[i] = 0;
		}//fin for()
	}//fin initSaisies()

	/**
	 * Effectue les contrôles de saisie du JTextField txtDep, et affiche la suite du formulaire si il n'y a pas d'erreurs
	 */
	private void checkDepartementSaisi()
	{
		//On vide le label d'erreur au cas où il s'agirait d'un nouvel essai
		Modules.clearLabel(lblMsgError);
		try{
			//On vérifie que la saisie est bien un entier naturel inférieur à 100
			saisies[0] = Modules.checkDepInBrochure(txtDep, brochure);
			//S'il n'y a pas d'erreurs, on affiche la suite
			setVisibleSecondPart(true);
		} catch (Exception ex) {
			//En fonction de l'exception qui a été levée, on va modifier le niveau d'erreur
			switch(ex.getCause().getMessage())
			{
				case "notRegisted" : Modules.afficherErreur(lblMsgError, ex.getMessage(), 2);
					break;
				case "moreThan1Hundred" : Modules.afficherErreur(lblMsgError, ex.getMessage(), 2);
					break;
				default : Modules.afficherErreur(lblMsgError, ex.getMessage(), 1);
					break;
			}//Fin switch()
		}//Fin catch()
	}//Fin checkDepartementSaisi()

	/**
	 * Renvoi un entier en fonction du boutton radio qui est coché
	 * @return Un entier {0;1;2;3} correspondant au boutton radio coché [entier]
	 * @throws Lève une exception si aucun boutton radio n'est coché [Exception]
	 */
	private int checkRadioButtonChecked() throws Exception
	{
		//Si c'est le 1er boutton radio qui est coché, on retourne 0
		if(btnRadASSem.isSelected() == true)
		{
			return 0;
		}//Fin if
		//Sinon, si c'est le 2ème, on retourne 1
		else if(btnRadARSem.isSelected() == true)
		{
			return 1;
		}//fin else if
		//Sinon, si c'est le 3ème, on retourne 2
		else if(btnRadASNuitDim.isSelected() == true)
		{
			return 2;
		}//fin else if
		//Sinon, si c'est le 4ème, on retourne 3
		else if(btnRadARNuitDim.isSelected() == true)
		{
			return 3;
		}//fin else if
		//Autrement, c'est qu'il n'y en avait aucun de coché, donc on retourne 4 (cette valeur permettera de traiter l'erreur)
		else
		{
			throw new Exception("Any radio button selected", new Throwable("anyButtonSelected"));
		}//fin else
	}//fin checkRadioButtonChecked()

	private double calculMontant() throws Exception
	{
		if(saisies[2] < 60)
		{
			return (brochure.getListeTarifs().get(Modules.check_indice_dep(saisies[0], brochure)).getPriseEnCharge() + saisies[1] * Modules.check_tarif_km(saisies[0], saisies[3], brochure));
		}//fin if
		else
		{
			return (brochure.getListeTarifs().get(Modules.check_indice_dep(saisies[0], brochure)).getPriseEnCharge() + saisies[1] * Modules.check_tarif_km(saisies[0], saisies[3], brochure) + ((saisies[2] - (saisies[2] % 60))/60) * Modules.check_tarif_jn(saisies[0], saisies[3], brochure));
		}//fin else
	}//Fin calculMontant()
	
	private void afficherMontantAPayer()
	{
		try{
			//On vérifie que les valeurs sont des entiers naturels inférieurs à 100
			saisies[1] = Modules.checkIntSaisie(txtKm);
			saisies[2] = Modules.checkIntSaisie(txtDuree);
			//On vérifie quel est le boutton radio qui est coché
			saisies[3] = checkRadioButtonChecked();
			//On cache les eventuelles erreurs 
			Modules.clearLabel(lblMsgError);
			//Création d'un objet permettant d'arrondir le montant
			NumberFormat format = NumberFormat.getInstance();
			format.setMinimumFractionDigits(2);
			//puis on affiche le montant à payer
			lblAffichage.setVisible(true);
			lblAffichage.setText("Vous devez payer : " + format.format(calculMontant()) + " €");
		} catch (Exception ex) {
			switch(ex.getCause().getMessage())
			{
				case "notRegisted" : Modules.afficherErreur(lblMsgError, ex.getMessage(), 2);
					break;
				case "moreThan1Hundred" : Modules.afficherErreur(lblMsgError, ex.getMessage(), 2);
					break;
				case "anyButtonSelected" : Modules.afficherErreur(lblMsgError, ex.getMessage(), 2);
					break;
				default : Modules.afficherErreur(lblMsgError, ex.getMessage(), 1);
					break;
			}//Fin switch()
		}//Fin catch()
	}//Fin calculer()

	/**
	 * Affiche ou cache la 2ème partie du formulaire en fonction du paramètre
	 * @param True : Rend visible la 2ème partie, et rend non modifiable txtDep et btnSuivant
	 * @param False : Rend invisible la 2ème partie, et rend modifiable txtDep et btnSuivant et vide txtDep
	 */
	private void setVisibleSecondPart(boolean value)
	{
		if(value == true)
		{
			txtDep.setEnabled(false);
			btnSuivant.setEnabled(false);
			separator1.setVisible(true);
			lblKm.setVisible(true);
			txtKm.setVisible(true);
			lblDuree.setVisible(true);
			txtDuree.setVisible(true);
			separator2.setVisible(true);
			btnRadASSem.setVisible(true);
			btnRadARSem.setVisible(true);
			btnRadASNuitDim.setVisible(true);
			btnRadARNuitDim.setVisible(true);
			btnCalculer.setVisible(true);
		}//Fin if(value == true)
		else
		{
			Modules.clearLabel(lblMsgError);
			Modules.clearLabel(lblAffichage);
			txtDep.setText("");
			txtDep.setEnabled(true);
			txtDep.requestFocus();
			btnSuivant.setEnabled(true);
			separator1.setVisible(false);
			lblKm.setVisible(false);
			txtKm.setVisible(false);
			lblDuree.setVisible(false);
			txtDuree.setVisible(false);
			separator2.setVisible(false);
			btnRadASSem.setVisible(false);
			btnRadARSem.setVisible(false);
			btnRadASNuitDim.setVisible(false);
			btnRadARNuitDim.setVisible(false);
			btnCalculer.setVisible(false);
			radioButton.clearSelection();
		}//Fin else
	}//Fin setVisibleSecondPart()

	/**
	 * Réinitialise le panel en vidant les JTextFields et en cachant la 2ème partie du formulaire
	 */
	private void reinitJPanelCalcul()
	{
		//Mise à jour de la brochure
		brochure = new Brochure(Modules.initBrochure());
		//On cache la 2ème partie du formulaire
		setVisibleSecondPart(false);
	}//Fin reinitJPanelCalcul()

	/**
	 * Redéfinition du modificateur de la visibilité, une réinitialisation du panel a lieu avant de modifier l'attribut
	 */
	public void setVisible(boolean value)
	{
		super.setVisible(value);
		if(value == true)
		{
			reinitJPanelCalcul();
			txtDep.requestFocus();
		}//Fin if(value == true)
	}//Fin setVisible()
}//Fin class
