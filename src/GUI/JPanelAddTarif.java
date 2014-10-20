package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import Program.Brochure;
import Program.Tarif;

public class JPanelAddTarif extends JPanel{
	private Brochure brochure;
	private JTextField txtDep;
	private JTextField txtPriseChg;
	private JTextField txtASJour;
	private JTextField txtARJour;
	private JTextField txtASNuitDim;
	private JTextField txtARNuitDim;
	private JTextField txtHorJour;
	private JTextField txtHorNuitDim;
	private JLabel lblMsgError;
	private JButton btnSave;
	private List listeDepDispo;
	private int saisieDep;
	private double[] saisies;
	
	//*****************************************//
	// L I S T E   D E S   A C C E S S E U R S //
	//*****************************************//
	/**
	 * Accesseur de la brochure
	 * @return L'attribut brochure [Brochure]
	 */
	public Brochure getBrochure()
	{
		return brochure;
	}
	
	/**
	 * Accesseur de txtDep
	 * @return L'attribut txtDep [JTextField]
	 */
	public JTextField getTxtDep()
	{
		return txtDep;
	}
	
	/**
	 * Accesseur de txtPriseChg
	 * @return L'attribut txtPriseChg [JTextField]
	 */
	public JTextField getTxtPriseChg()
	{
		return txtPriseChg;
	}
	
	/**
	 * Accesseur de txtASJour
	 * @return L'attribut txtASJour [JTextField]
	 */
	public JTextField getTxtASJour()
	{
		return txtASJour;
	}
	
	/**
	 * Accesseur de txtARJour
	 * @return L'attribut txtARJour [JTextField]
	 */
	public JTextField getTxtARJour()
	{
		return txtARJour;
	}
	
	/**
	 * Accesseur de txtASNuitDim
	 * @return L'attribut txtASNuitDim [JTextField]
	 */
	public JTextField getTxtASNuitDim()
	{
		return txtASNuitDim;
	}
	
	/**
	 * Accesseur de txtARNuitDim
	 * @return L'attribut txtARNuitDim [JTextField]
	 */
	public JTextField getTxtARNuitDim()
	{
		return txtARNuitDim;
	}
	
	/**
	 * Accesseur de txtHorJour
	 * @return L'attribut txtHorJour [JTextField]
	 */
	public JTextField getTxtHorJour()
	{
		return txtHorJour;
	}
	
	/**
	 * Accesseur de txtHorNuitDim
	 * @return L'attribut txtHorNuitDim [JTextField]
	 */
	public JTextField getTxtHorNuitDim()
	{
		return txtHorNuitDim;
	}
	
	//***************************//
	// C O N S T R U C T E U R S //
	//***************************//
	
	/**
	 * Constructeur de la classe panelAddTarif
	 * @param La brochure de tarifs [Brochure]
	 */
	public JPanelAddTarif(Brochure brochure)
	{
		//Initialisation du panel
		super();
		//Initialisation de la brochure
		this.brochure = new Brochure("une brochure", brochure.getListeTarifs());
		//Configuration du panel
		this.setBounds(12, 12, 624, 378);
		this.setLayout(null);
		//Création du label Departement
		JLabel lblDep = new JLabel("Département :");
		lblDep.setBounds(60, 30, 120, 15);
		this.add(lblDep);
		//Création de la zone de texte Departement
		txtDep = new JTextField();
		txtDep.setName("département");
		txtDep.setBounds(210, 30, 50, 19);
		this.add(txtDep);
		txtDep.setColumns(10);
		//Création du panel de prise en charge
		JLabel lblPriseChg = new JLabel("Prise en charge :");
		lblPriseChg.setBounds(60, 60, 120, 15);
		this.add(lblPriseChg);
		//Création de la zone de texte Prise en charge
		txtPriseChg = new JTextField();
		txtPriseChg.setName("prise en charge");
		txtPriseChg.setBounds(210, 60, 50, 19);
		this.add(txtPriseChg);
		txtPriseChg.setColumns(10);
		//Création du séparator1
		JSeparator separator1 = new JSeparator();
		separator1.setBounds(40, 95, 315, 15);
		this.add(separator1);
		//Création du label tarif AS jour sem
		JLabel lblASJour = new JLabel("Tarif au km AS jour Semaine :");
		lblASJour.setBounds(30, 110, 210, 15);
		this.add(lblASJour);
		//Création de la zone de texte tarif AS jour sem
		txtASJour = new JTextField();
		txtASJour.setName("tarif Allé-simple (jour semaine)");
		txtASJour.setBounds(268, 110, 70, 19);
		this.add(txtASJour);
		txtASJour.setColumns(10);
		//Création du label tarif AR jour sem
		JLabel lblARJour = new JLabel("Tarif au km AR jour Semaine :");
		lblARJour.setBounds(30, 140, 210, 15);
		this.add(lblARJour);
		//Création de la zone de texte tarif AR jour sem
		txtARJour = new JTextField();
		txtARJour.setName("tarif Allé-retour (jour semaine)");
		txtARJour.setBounds(268, 140, 70, 19);
		this.add(txtARJour);
		txtARJour.setColumns(10);
		//Création du label tarif AS nuit dim
		JLabel lblASNuitDim = new JLabel("Tarif au km AS nuit Dimanche :");
		lblASNuitDim.setBounds(30, 170, 220, 15);
		this.add(lblASNuitDim);
		//Création de la zone de texte tarif AS nuit dim
		txtASNuitDim = new JTextField();
		txtASNuitDim.setName("tarif Allé-simple (Nuit et dimanche)");
		txtASNuitDim.setBounds(268, 170, 70, 19);
		this.add(txtASNuitDim);
		txtASNuitDim.setColumns(10);
		//Création du label tarif AR nuit dim
		JLabel lblARNuitDim = new JLabel("Tarif au km AR nuit Dimanche :");
		lblARNuitDim.setBounds(30, 200, 220, 15);
		this.add(lblARNuitDim);
		//Création de la zone de texte AR nuit dim
		txtARNuitDim = new JTextField();
		txtARNuitDim.setName("tarif Allé-retour (Nuit et dimanche)");
		txtARNuitDim.setBounds(268, 200, 70, 19);
		this.add(txtARNuitDim);
		txtARNuitDim.setColumns(10);
		//Création du 2ème séparateur
		JSeparator separator2 = new JSeparator();
		separator2.setBounds(40, 235, 315, 2);
		this.add(separator2);
		//Création du label tarif horaire jour sem
		JLabel lblHorJour = new JLabel("Tarif horaire jour Semaine :");
		lblHorJour.setBounds(30, 250, 210, 15);
		this.add(lblHorJour);
		//Création de la zone de texte horaire jour sem
		txtHorJour = new JTextField();
		txtHorJour.setName("tarif horaire (jour-semaine)");
		txtHorJour.setBounds(268, 250, 70, 19);
		this.add(txtHorJour);
		txtHorJour.setColumns(10);
		//Création du label tarif horaire nuit dim
		JLabel lblHorNuitDim = new JLabel("Tarif horaire nuit Dimanche :");
		lblHorNuitDim.setBounds(30, 280, 210, 15);
		this.add(lblHorNuitDim);
		//Création de la zone de texte horaire nuit dim
		txtHorNuitDim = new JTextField();
		txtHorNuitDim.setName("tarif horaire (Nuit et dimanche)");
		txtHorNuitDim.setBounds(268, 280, 70, 19);
		this.add(txtHorNuitDim);
		txtHorNuitDim.setColumns(10);
		//Création du label d'erreurs
		lblMsgError = new JLabel("Label d'erreurs");
		lblMsgError.setVisible(false);
		lblMsgError.setHorizontalAlignment(SwingConstants.LEFT);
		lblMsgError.setForeground(Color.RED);
		lblMsgError.setBounds(45, 325, 550, 45);
		this.add(lblMsgError);
		//Création du label d'affichage
		JLabel lblAffichage = new JLabel("Label d'affichage");
		lblAffichage.setVisible(false);
		lblAffichage.setHorizontalAlignment(SwingConstants.CENTER);
		lblAffichage.setBounds(134, 347, 350, 19);
		this.add(lblAffichage);
		//Création du label Titre
		JLabel lblTitle = new JLabel("Ajout d'un nouveau tarif");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(215, 0, 180, 15);
		this.add(lblTitle);
		//Création du boutton enregistrer
		btnSave = new JButton("Enregistrer");
		btnSave.setBounds(420, 263, 117, 25);
		this.add(btnSave);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				enregistrerTarif(JPanelAddTarif.this.brochure);
			}
		});
		//Création du label liste de départements
		JLabel lblListeDep = new JLabel("<html><center>Liste des départements<br />disponibles :</center></html>");
		lblListeDep.setBounds(410, 40, 350, 45);
		this.add(lblListeDep);
		//Création de la List de départements pris en charge
		listeDepDispo = new List();
		listeDepDispo.setBounds(470, 90, 50, 72);
		initListeDep(brochure);
		this.add(listeDepDispo);
		//Déclaration du tableau de saisies
		initSaisiesAZero();
	}//Fin JPanelAddTarif(Brochure brochure)
	
	//*****************//
	// M E T H O D E S //
	//*****************//
	
	/**
	 * Initialise la List de départements à partir des Département des tarifs de la brochure
	 * @param La brochure de tarif passé en paramètre lors de l'appel du constructeur [Brochure]
	 */
	private void initListeDep(Brochure brochure)
	{
		//Pour i allant de 1 à 99,	
		for(int i = 1 ; i < 100 ; i++)
		{
			boolean trouve = false;
			//Si i est présent dans la liste des departements, on met "trouve" à True
			for (Tarif tarif : brochure.getListeTarifs())
			{
				if(i == tarif.getDepartement())
				{
					trouve = true;
				}//fin if(i == tarif.getDepartement())
			}//Fin for each
			//Si i n'est pas dans la brochure, on l'ajoute à la liste
			if(trouve == false)
			{
				listeDepDispo.add(Integer.toString(i));
			}//Fin if(trouve == false)
		}//Fin for(int i = 1 ; i < 100 ; i++)
	}//fin initListeDep()
	
	/**
	 * Initialise l'entier saisieDep et le tableau de saisies avec la valeur 0 et 0.0
	 */
	private void initSaisiesAZero()
	{
		saisieDep = 0;
		saisies = new double[7];
		//Boucle pour attribuer la valeur 0.0 à chaque ligne du tableau saisies[]
		for(int i = 0; i < 7; i++)
		{
			saisies[i] = 0;
		}//fin for
	}//Fin initSaisiesAZero()
	
	/**
	 * Vérifie que les zones de saisies sont correctement remplies
	 */
	private void checkSaisies(Brochure brochure) throws Exception
	{
		Modules.effacerErreur(lblMsgError);
		saisieDep = checkDepInBrochure(txtDep, brochure);
		saisies[0] = Modules.checkDoubleSaisie(txtPriseChg, lblMsgError);
		saisies[1] = Modules.checkDoubleSaisie(txtASJour, lblMsgError);
		saisies[2] = Modules.checkDoubleSaisie(txtARJour, lblMsgError);
		saisies[3] = Modules.checkDoubleSaisie(txtASNuitDim, lblMsgError);
		saisies[4] = Modules.checkDoubleSaisie(txtARNuitDim, lblMsgError);
		saisies[5] = Modules.checkDoubleSaisie(txtHorJour, lblMsgError);
		saisies[6] = Modules.checkDoubleSaisie(txtHorNuitDim, lblMsgError);
	}//Fin checkSaisies()
	
	/**
	 * Vérifie le type de donnée saisi dans la zone txtDep, et vérifie que cette valeur ne correspond pas à un departement de la brochure
	 * @param La zone de saisie txtDep à vérifier [JTextField]
	 * @param La brochure de tarifs où vérifier si le département y est présent [Brochure]
	 * @return La saisie castée en entier naturel si elle n'est pas dans la brochure
	 */
	private int checkDepInBrochure(JTextField field, Brochure brochure) throws Exception
	{
		//On convertit la saisie en entier naturel
		int nb = Modules.checkIntSaisie(txtDep, lblMsgError);
		//Si la saisie est supérieure à 100, on lève une exception
		if(nb > 100)
		{
			throw new Exception("La saisie dans le champ \"" + field.getName() + "\" est incorrecte : <br />\"" + Integer.toString(nb) + 
								"\" must be lower than 100 !", new Throwable("moreThan1Hundred"));
		}//Fin if(nb > 100)
		boolean trouve = false;
		//Si la saisie correspond à un departement de la brochure, on met "trouve" à True pour lever une Exception plus tard
		for(Tarif tarif : brochure.getListeTarifs())
		{
			if(nb == tarif.getDepartement())
			{
				trouve = true;
			}//Fin if(Dep == tarif.getDepartement())
		}//Fin for each
		//Si à la fin de la boucle, trouve est à True, on lève une exception
		if(trouve == true)
		{
			Modules.clearAndFocusField(field);
			throw new Exception("La saisie dans le champ \"" + field.getName() + "\" est incorrecte : <br />\"" + Integer.toString(nb) + 
								"\" is already registed !", new Throwable("alreadyRegisted"));
		}//Fin if(trouve == true)
		return nb;
	}//Fin checkDepInBrochure()

	/**
	 * Effectue les contrôles des saisies et enregistre le nouveau tarif dans la bdd s'il n'y a pas d'erreurs
	 * @param La brochure [Brochure]
	 */
	public void enregistrerTarif(Brochure brochure)
	{
		try{
			checkSaisies(brochure);
		}
		catch (Exception ex)
		{
			switch(ex.getCause().getMessage())
			{
			case "alreadyRegisted" : Modules.afficherErreur(lblMsgError, ex.getMessage(), 2);
				break;
			case "moreThan1Hundred" : Modules.afficherErreur(lblMsgError, ex.getMessage(), 2);
				break;
			default : Modules.afficherErreur(lblMsgError, ex.getMessage(), 1);
				break;
			}//Fin switch()
		}//Fin catch
	}//Fin enregistrerTarif()
}