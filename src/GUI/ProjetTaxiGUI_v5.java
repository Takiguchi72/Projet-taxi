package GUI;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
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
	private JMenu menuCalculer;
	private JMenuItem miUneCourse;
	private JPanelAddTarif panelAddTarif;
	private JPanelCalcul panelCalcul;

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
		mntmAjouterUnTarif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelCalcul.setVisible(false);
				panelAddTarif.setVisible(true);
			}
		});
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
				panelAddTarif.setVisible(false);
				panelCalcul.setVisible(true);
			}
		});
		menuCalculer.add(miUneCourse);
		contentPane = new JPanel();
		contentPane.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Ajout du panel d'ajout de tarifs
		panelAddTarif = new JPanelAddTarif();
		panelAddTarif.setBounds(12, 12, 624, 378);
		panelAddTarif.setVisible(false);
		contentPane.add(panelAddTarif);
		panelAddTarif.setLayout(null);
		
		//Ajout du panel pour calculer une course
		panelCalcul = new JPanelCalcul();
		panelCalcul.setBounds(12, 12, 624, 378);
		panelCalcul.setVisible(false);
		contentPane.add(panelCalcul);
		panelCalcul.setLayout(null);
		
		
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

