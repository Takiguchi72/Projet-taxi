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
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.text.NumberFormat;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JLayeredPane;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;

public class ProjetTaxiGUI_v5 extends JFrame {

	private JPanel contentPane;
	private JTextField txtDep;
	private JTextField txtKm;
	private JTextField txtDuree;
	private JLabel lblMsgError;
	private JLabel lblKm;
	private JLabel lblDuree;
	private JRadioButton btnRadASSem;
	private JRadioButton btnRadARSem;
	private JRadioButton btnRadASNuitDim;
	private JRadioButton btnRadARNuitDim;
	private JButton btnCalculer;
	private JLabel lblAffichage;
	private final ButtonGroup radioButton = new ButtonGroup();
	private final ModulesGUI module = new ModulesGUI();
	private JMenu menuAdmin;
	private JMenuItem mntmAjouterUnTarif;
	private JMenuItem mntmModifierUnTarif;
	private JMenuItem mntmSupprimerUnTarif;
	private JPanel panelCalcul;
	private JMenu menuCalculer;
	private JMenuItem miUneCourse;
	private JPanel panelAddTarif;
	private JTextField pATtxtDep;
	private JTextField pATtxtPriseChg;
	private JTextField pATtxtASJour;
	private JTextField pATtxtARJour;
	private JTextField pATtxtASNuitDim;
	private JTextField pATtxtARNuitDim;
	private JTextField pATtxtHorJour;
	private JTextField pATtxtHorNuitDim;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProjetTaxiGUI_v5 frame = new ProjetTaxiGUI_v5();
					frame.setLocationRelativeTo(null);
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
		setResizable(false);
		setLocationByPlatform(true);
		setTitle("Projet Taxi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 651, 459);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menuFichier = new JMenu("Fichier");
		menuBar.add(menuFichier);
		
		JMenuItem miQuit = new JMenuItem("Quitter");
		miQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		menuFichier.add(miQuit);
		
		menuAdmin = new JMenu("Administration");
		menuBar.add(menuAdmin);
		
		mntmAjouterUnTarif = new JMenuItem("Ajouter un tarif");
		menuAdmin.add(mntmAjouterUnTarif);
		
		mntmModifierUnTarif = new JMenuItem("Modifier un tarif");
		menuAdmin.add(mntmModifierUnTarif);
		
		mntmSupprimerUnTarif = new JMenuItem("Supprimer un tarif");
		menuAdmin.add(mntmSupprimerUnTarif);
		
		menuCalculer = new JMenu("Calculer");
		menuBar.add(menuCalculer);
		
		miUneCourse = new JMenuItem("Une course");
		miUneCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(panelCalcul.isVisible() == true){
					panelCalcul.setVisible(false);
				}//fin if
				else
				{
					panelCalcul.setVisible(true);
				}
			}
		});
		menuCalculer.add(miUneCourse);
		contentPane = new JPanel();
		contentPane.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panelCalcul = new JPanel();
		panelCalcul.setVisible(false);
		
		panelAddTarif = new JPanel();
		panelAddTarif.setBounds(12, 12, 624, 378);
		contentPane.add(panelAddTarif);
		panelAddTarif.setLayout(null);
		
		JLabel pATlblDep = new JLabel("Département :");
		pATlblDep.setBounds(60, 30, 120, 15);
		panelAddTarif.add(pATlblDep);
		
		pATtxtDep = new JTextField();
		pATtxtDep.setBounds(210, 30, 50, 19);
		panelAddTarif.add(pATtxtDep);
		pATtxtDep.setColumns(10);
		
		JLabel pATlblPriseChg = new JLabel("Prise en charge :");
		pATlblPriseChg.setBounds(60, 60, 120, 15);
		panelAddTarif.add(pATlblPriseChg);
		
		pATtxtPriseChg = new JTextField();
		pATtxtPriseChg.setBounds(210, 60, 50, 19);
		panelAddTarif.add(pATtxtPriseChg);
		pATtxtPriseChg.setColumns(10);
		
		JSeparator pATseparator1 = new JSeparator();
		pATseparator1.setBounds(40, 95, 315, 15);
		panelAddTarif.add(pATseparator1);
		
		JLabel pATlblASJour = new JLabel("Tarif au km AS jour Semaine :");
		pATlblASJour.setBounds(30, 110, 210, 15);
		panelAddTarif.add(pATlblASJour);
		
		pATtxtASJour = new JTextField();
		pATtxtASJour.setBounds(268, 110, 70, 19);
		panelAddTarif.add(pATtxtASJour);
		pATtxtASJour.setColumns(10);
		
		JLabel pATlblARJour = new JLabel("Tarif au km AR jour Semaine :");
		pATlblARJour.setBounds(30, 140, 210, 15);
		panelAddTarif.add(pATlblARJour);
		
		pATtxtARJour = new JTextField();
		pATtxtARJour.setBounds(268, 140, 70, 19);
		panelAddTarif.add(pATtxtARJour);
		pATtxtARJour.setColumns(10);
		
		JLabel pATlblASNuitDim = new JLabel("Tarif au km AS nuit Dimanche :");
		pATlblASNuitDim.setBounds(30, 170, 220, 15);
		panelAddTarif.add(pATlblASNuitDim);
		
		pATtxtASNuitDim = new JTextField();
		pATtxtASNuitDim.setBounds(268, 170, 70, 19);
		panelAddTarif.add(pATtxtASNuitDim);
		pATtxtASNuitDim.setColumns(10);
		
		JLabel pATlblARNuitDim = new JLabel("Tarif au km AR nuit Dimanche :");
		pATlblARNuitDim.setBounds(30, 200, 220, 15);
		panelAddTarif.add(pATlblARNuitDim);
		
		pATtxtARNuitDim = new JTextField();
		pATtxtARNuitDim.setBounds(268, 200, 70, 19);
		panelAddTarif.add(pATtxtARNuitDim);
		pATtxtARNuitDim.setColumns(10);
		
		JSeparator pATseparator2 = new JSeparator();
		pATseparator2.setBounds(40, 235, 315, 2);
		panelAddTarif.add(pATseparator2);
		
		JLabel pATlblHorJour = new JLabel("Tarif horaire jour Semaine :");
		pATlblHorJour.setBounds(30, 250, 210, 15);
		panelAddTarif.add(pATlblHorJour);
		
		pATtxtHorJour = new JTextField();
		pATtxtHorJour.setBounds(268, 250, 70, 19);
		panelAddTarif.add(pATtxtHorJour);
		pATtxtHorJour.setColumns(10);
		
		JLabel pATlblHorNuitDim = new JLabel("Tarif horaire nuit Dimanche :");
		pATlblHorNuitDim.setBounds(30, 280, 210, 15);
		panelAddTarif.add(pATlblHorNuitDim);
		
		pATtxtHorNuitDim = new JTextField();
		pATtxtHorNuitDim.setBounds(268, 280, 70, 19);
		panelAddTarif.add(pATtxtHorNuitDim);
		pATtxtHorNuitDim.setColumns(10);
		
		JLabel pATlblMsgError = new JLabel("Label d'erreurs");
		pATlblMsgError.setVisible(false);
		pATlblMsgError.setHorizontalAlignment(SwingConstants.LEFT);
		pATlblMsgError.setForeground(Color.RED);
		pATlblMsgError.setBounds(45, 300, 550, 25);
		panelAddTarif.add(pATlblMsgError);
		
		JLabel pATlblAffichage = new JLabel("Label d'affichage");
		pATlblAffichage.setVisible(false);
		pATlblAffichage.setHorizontalAlignment(SwingConstants.CENTER);
		pATlblAffichage.setBounds(134, 347, 350, 19);
		panelAddTarif.add(pATlblAffichage);
		
		JLabel pATlblTitle = new JLabel("Ajout d'un nouveau tarif");
		pATlblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		pATlblTitle.setBounds(215, 0, 180, 15);
		panelAddTarif.add(pATlblTitle);
		
		JButton pATbtnSave = new JButton("Enregistrer");
		pATbtnSave.setBounds(420, 263, 117, 25);
		panelAddTarif.add(pATbtnSave);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		layeredPane.setForeground(Color.LIGHT_GRAY);
		layeredPane.setBounds(396, 30, 200, 100);
		panelAddTarif.add(layeredPane);
		
		JComboBox pATcbbListT = new JComboBox();
		pATcbbListT.setBounds(75, 65, 50, 19);
		layeredPane.add(pATcbbListT);
		
		JLabel pATlblListDep = new JLabel("<html>Liste des départements<br /> pris en charge :</html>");
		pATlblListDep.setBounds(0, 20, 200, 30);
		layeredPane.add(pATlblListDep);
		pATlblListDep.setHorizontalAlignment(SwingConstants.CENTER);
		panelAddTarif.setBounds(12, 12, 624, 378);
		contentPane.add(panelAddTarif);
		panelAddTarif.setLayout(null);
		
		JLabel lblDep = new JLabel("Département :");
		lblDep.setBounds(30, 30, 110, 15);
		panelCalcul.add(lblDep);
		lblDep.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		lblDep.setPreferredSize(new Dimension(100, 15));
		
		txtDep = new JTextField();
		txtDep.setBounds(150, 30, 50, 19);
		panelCalcul.add(txtDep);
		txtDep.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		txtDep.setColumns(2);
		txtDep.setName("txtDep");
		
		JButton btnSuivant = new JButton("Suivant");
		btnSuivant.setBounds(230, 30, 90, 18);
		panelCalcul.add(btnSuivant);
		btnSuivant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//On effectue un controle de saisie sur la valeur entrée dans la zone de texte
				module.setSaisies(module.checkSaisie(txtDep, lblMsgError), 0);
				//Si la valeur de module.getSaisies()[0] != 0, ça signifie que la saisie est correcte, donc on affiche la suite du formulaire
				if(module.getSaisies()[0] != 0)
				{
					//Si le numéro du département saisie est dans la brochure, on affiche la 2ème partie du formulaire
					if(module.checkDepInBrochure(module.getSaisies()[0]) == true)
					{
						afficherPartie2();
					}//fin if
					//Sinon, on affiche un message d'erreur dans la zone d'erreurs
					else
					{
						cacherPartie2();
						afficherErreur("Le département " + module.getSaisies()[0] + " n'est pas pris en compte<br> Veuillez en saisir un autre !", 2);
					}//fin else
				}//fin if
			}
		});
		btnSuivant.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		
		JButton btnReinit = new JButton("Réinitialiser");
		btnReinit.setBounds(350, 30, 125, 18);
		panelCalcul.add(btnReinit);
		btnReinit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//On vide les zones de saisie
				cacherPartie2();
			}
		});
		btnReinit.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		
		lblKm = new JLabel("Nombre de kilomètres :");
		lblKm.setBounds(30, 80, 165, 15);
		panelCalcul.add(lblKm);
		lblKm.setVisible(false);
		lblKm.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		lblKm.setPreferredSize(new Dimension(160, 15));
		
		txtKm = new JTextField();
		txtKm.setBounds(225, 80, 50, 19);
		panelCalcul.add(txtKm);
		txtKm.setVisible(false);
		txtKm.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		txtKm.setColumns(10);
		txtKm.setName("txtKm");
		
		lblDuree = new JLabel("Durée (en min) :");
		lblDuree.setBounds(30, 120, 126, 15);
		panelCalcul.add(lblDuree);
		lblDuree.setVisible(false);
		lblDuree.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		lblDuree.setPreferredSize(new Dimension(160, 15));
		
		txtDuree = new JTextField();
		txtDuree.setBounds(225, 120, 50, 19);
		panelCalcul.add(txtDuree);
		txtDuree.setVisible(false);
		txtDuree.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		txtDuree.setColumns(10);
		txtDuree.setName("txtDuree");
		
		btnRadASSem = new JRadioButton("Allé simple - Semaine");
		btnRadASSem.setBounds(35, 150, 181, 23);
		panelCalcul.add(btnRadASSem);
		btnRadASSem.setVisible(false);
		btnRadASSem.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		radioButton.add(btnRadASSem);
		
		btnRadARSem = new JRadioButton("Allé retour - Semaine");
		btnRadARSem.setBounds(35, 180, 180, 23);
		panelCalcul.add(btnRadARSem);
		btnRadARSem.setVisible(false);
		btnRadARSem.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		radioButton.add(btnRadARSem);
		
		btnRadASNuitDim = new JRadioButton("Allé simple - Nuit/Dimanche");
		btnRadASNuitDim.setBounds(35, 210, 220, 23);
		panelCalcul.add(btnRadASNuitDim);
		btnRadASNuitDim.setVisible(false);
		btnRadASNuitDim.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		radioButton.add(btnRadASNuitDim);
		
		btnRadARNuitDim = new JRadioButton("Allé retour - Nuit/Dim");
		btnRadARNuitDim.setBounds(35, 240, 220, 23);
		panelCalcul.add(btnRadARNuitDim);
		btnRadARNuitDim.setVisible(false);
		btnRadARNuitDim.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		radioButton.add(btnRadARNuitDim);
		
		btnCalculer = new JButton("Calculer");
		btnCalculer.setBounds(423, 279, 117, 25);
		panelCalcul.add(btnCalculer);
		
		lblMsgError = new JLabel();
		lblMsgError.setBounds(30, 271, 550, 40);
		lblMsgError.setText("Label d'erreurs");
		panelCalcul.add(lblMsgError);
		lblMsgError.setVisible(false);
		lblMsgError.setForeground(Color.RED);
		
		lblAffichage = new JLabel("Label d'affichage");
		lblAffichage.setBounds(169, 316, 270, 30);
		panelCalcul.add(lblAffichage);
		lblAffichage.setVisible(false);
		lblAffichage.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnCalculer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				calculerTotal();
			}
		});
		btnCalculer.setVisible(false);
	}
	
	/**
	 * Rend visible les éléments de la 2ème partie du formulaire
	 */
	public void afficherPartie2()
	{
		txtDep.setEnabled(false);
		lblMsgError.setText("");
		lblKm.setVisible(true);
		txtKm.setVisible(true);
		lblDuree.setVisible(true);
		txtDuree.setVisible(true);
		btnRadASSem.setVisible(true);
		btnRadARSem.setVisible(true);
		btnRadASNuitDim.setVisible(true);
		btnRadARNuitDim.setVisible(true);
		btnCalculer.setVisible(true);
		txtKm.requestFocus();
	}//fin afficherSuiteForm()
	
	/**
	 * Rend invisible les éléments de la 2ème partie du formulaire
	 */
	public void cacherPartie2()
	{
		setEmptyTextBars();
		txtDep.setEnabled(true);
		txtDep.requestFocus();
		lblKm.setVisible(false);
		txtKm.setVisible(false);
		lblDuree.setVisible(false);
		txtDuree.setVisible(false);
		btnRadASSem.setVisible(false);
		btnRadARSem.setVisible(false);
		btnRadASNuitDim.setVisible(false);
		btnRadARNuitDim.setVisible(false);
		btnCalculer.setVisible(false);
		lblAffichage.setText("");
		lblAffichage.setVisible(false);
		txtDep.requestFocus();
		txtDep.setEnabled(true);
		radioButton.clearSelection();
	}//fin cacherPartie2()
	
	/**
	 * Vide les zones de texte
	 */
	public void setEmptyTextBars()
	{
		txtDep.setText("");
		txtKm.setText("");
		txtDuree.setText("");
		lblMsgError.setText("");
	}//fin setEmptyTextBars()
	
	/**
	 * Affiche le JLabel lblMsgError et modifie son texte gràce au 1er paramètre et change de couleur en fonction du 2ème
	 * @param Le message d'erreur à afficher [chaîne de caractères]
	 * @param Le niveau de l'erreur ; 0:Error (red); 1:Warning (orange)
	 */
	public void afficherErreur(String message, int niveauDErreur)
	{
		switch (niveauDErreur)
		{
		case 1: lblMsgError.setForeground(Color.RED);
		lblMsgError.setText("<html>Error : " + message + "</html>");
			break;
		case 2:	lblMsgError.setForeground(new Color(230, 168, 17));		//couleur orange-foncé
		lblMsgError.setText("<html>Warning : " + message + "</html>");
			break;
		default : lblMsgError.setForeground(Color.RED);
			break;
		}//fin switch()
		lblMsgError.setVisible(true);
	}//fin afficherErreur()
	
	/**
	 * Vérifie qu'un boutton radio est bien coché
	 * @return La valeur associé au boutton radio [entier] (1er btn = 0 ; 2ème btn = 1 ; 3ème btn = 2 ; 3ème btn = 4)
	 * @throws Lève une exception si aucun boutton n'est coché [Exception]
	 */
	public int checkBtnRadio() throws Exception
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
			throw new Exception("No radio button is selected");
		}//fin else
	}//Fin checkBtnRadio()
	
	/**
	 * Effectue des contrôles de saisie en fonction des 
	 */
	public void calculerTotal()
	{
		try{
			//Vérification du boutton radio coché
			module.setSaisies(checkBtnRadio(), 3);
			//Vérification de la durée saisie
			module.setSaisies(module.checkSaisie(txtDuree, lblMsgError), 2);
			//Vérification de la distance saisie
			module.setSaisies(module.checkSaisie(txtKm, lblMsgError), 1);
			//Calcul et affichage du total
			checkBeforeCalcul();
		} catch (Exception ex){
			afficherErreur(ex.getMessage(), 2);
		}//Fin catch()
	}//Fin calculerTotal()
	
	/**
	 * Vérifie que les champs "km" et "durée" ont bien été saisis, si oui, on effectue le calcul
	 */
	public void checkBeforeCalcul()
	{
		if(module.getSaisies()[1] != 0 && module.getSaisies()[2] != 0)
		{
			effacerErreur();
			//Création d'un objet permettant d'arrondir le montant
			NumberFormat format = NumberFormat.getInstance();
	        format.setMinimumFractionDigits(2);
			lblAffichage.setText("Vous devez payer " + format.format(module.calculPrix()) + " euros.");
			lblAffichage.setVisible(true);
		}//fin if
	}//fin checkBeforeCalcul()
	
	/**
	 * Vide la zone d'erreur et la rend non visible
	 */
	private void effacerErreur()
	{
		lblMsgError.setText("");
		lblMsgError.setVisible(false);
	}//fin effacerErreur()
}//Fin class

