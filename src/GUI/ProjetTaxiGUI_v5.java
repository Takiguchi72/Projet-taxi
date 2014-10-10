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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProjetTaxiGUI_v5 frame = new ProjetTaxiGUI_v5();
					frame.setVisible(true);
					frame.txtDep.requestFocus();
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
		txtDep.setName("txtDep");
		
		JButton btnSuivant = new JButton("Suivant");
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
						afficherErreur("Le département " + module.getSaisies()[0] + " n'est pas pris en compte<br> Veuillez en saisir un autre !", 2);
					}//fin else
				}//fin if
			}
		});
		btnSuivant.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		btnSuivant.setBounds(300, 30, 93, 18);
		contentPane.add(btnSuivant);
		
		JButton btnReinit = new JButton("Réinitialiser");
		btnReinit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//On vide les zones de saisie
				txtDep.requestFocus();
				txtDep.setEnabled(true);
				cacherPartie2();
			}
		});
		btnReinit.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		btnReinit.setBounds(410, 30, 125, 18);
		contentPane.add(btnReinit);
		
		lblKm = new JLabel("Nombre de kilomètres :");
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
		txtKm.setName("txtKm");
		
		lblDuree = new JLabel("Durée (en min) :");
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
		txtDuree.setName("txtDuree");
		
		btnRadASSem = new JRadioButton("Allé simple - Semaine");
		btnRadASSem.setVisible(false);
		btnRadASSem.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		radioButton.add(btnRadASSem);
		btnRadASSem.setBounds(45, 150, 181, 23);
		contentPane.add(btnRadASSem);
		
		btnRadARSem = new JRadioButton("Allé retour - Semaine");
		btnRadARSem.setVisible(false);
		btnRadARSem.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		radioButton.add(btnRadARSem);
		btnRadARSem.setBounds(45, 180, 180, 23);
		contentPane.add(btnRadARSem);
		
		btnRadASNuitDim = new JRadioButton("Allé simple - Nuit/Dimanche");
		btnRadASNuitDim.setVisible(false);
		btnRadASNuitDim.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		radioButton.add(btnRadASNuitDim);
		btnRadASNuitDim.setBounds(45, 210, 220, 23);
		contentPane.add(btnRadASNuitDim);
		
		btnRadARNuitDim = new JRadioButton("Allé retour - Nuit/Dim");
		btnRadARNuitDim.setVisible(false);
		btnRadARNuitDim.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		radioButton.add(btnRadARNuitDim);
		btnRadARNuitDim.setBounds(45, 240, 220, 23);
		contentPane.add(btnRadARNuitDim);
		
		btnCalculer = new JButton("Calculer");
		btnCalculer.setVisible(false);
		btnCalculer.setBounds(70, 300, 117, 25);
		contentPane.add(btnCalculer);
		
		lblAffichage = new JLabel("Test");
		lblAffichage.setVisible(false);
		lblAffichage.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblAffichage.setBounds(120, 368, 400, 30);
		contentPane.add(lblAffichage);
		
		lblMsgError = new JLabel();
		lblMsgError.setVisible(false);
		lblMsgError.setForeground(Color.RED);
		lblMsgError.setBounds(50, 340, 550, 40);
		contentPane.add(lblMsgError);
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
	}//fin afficherSuiteForm()
	
	/**
	 * Rend invisible les éléments de la 2ème partie du formulaire
	 */
	public void cacherPartie2()
	{
		setEmptyTextBars();
		txtDep.setEnabled(true);
		lblKm.setVisible(false);
		txtKm.setVisible(false);
		lblDuree.setVisible(false);
		txtDuree.setVisible(false);
		btnRadASSem.setVisible(false);
		btnRadARSem.setVisible(false);
		btnRadASNuitDim.setVisible(false);
		btnRadARNuitDim.setVisible(false);
		btnCalculer.setVisible(false);
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
}
