package GUI;

import java.awt.Color;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import Program.Brochure;
import Program.Tarif;

public class JPanelAddTarif extends JPanel{
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
	private JComboBox cbbListT;
	
	//*****************************************//
	// L I S T E   D E S   A C C E S S E U R S //
	//*****************************************//
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
		//Configuration du panel
		this.setBounds(12, 12, 624, 378);
		this.setLayout(null);
		//Création du label Departement
		JLabel lblDep = new JLabel("Département :");
		lblDep.setBounds(60, 30, 120, 15);
		this.add(lblDep);
		//Création de la zone de texte Departement
		txtDep = new JTextField();
		txtDep.setBounds(210, 30, 50, 19);
		this.add(txtDep);
		txtDep.setColumns(10);
		//Création du panel de prise en charge
		JLabel lblPriseChg = new JLabel("Prise en charge :");
		lblPriseChg.setBounds(60, 60, 120, 15);
		this.add(lblPriseChg);
		//Création de la zone de texte Prise en charge
		txtPriseChg = new JTextField();
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
		txtASJour.setBounds(268, 110, 70, 19);
		this.add(txtASJour);
		txtASJour.setColumns(10);
		//Création du label tarif AR jour sem
		JLabel lblARJour = new JLabel("Tarif au km AR jour Semaine :");
		lblARJour.setBounds(30, 140, 210, 15);
		this.add(lblARJour);
		//Création de la zone de texte tarif AR jour sem
		txtARJour = new JTextField();
		txtARJour.setBounds(268, 140, 70, 19);
		this.add(txtARJour);
		txtARJour.setColumns(10);
		//Création du label tarif AS nuit dim
		JLabel lblASNuitDim = new JLabel("Tarif au km AS nuit Dimanche :");
		lblASNuitDim.setBounds(30, 170, 220, 15);
		this.add(lblASNuitDim);
		//Création de la zone de texte tarif AS nuit dim
		txtASNuitDim = new JTextField();
		txtASNuitDim.setBounds(268, 170, 70, 19);
		this.add(txtASNuitDim);
		txtASNuitDim.setColumns(10);
		//Création du label tarif AR nuit dim
		JLabel lblARNuitDim = new JLabel("Tarif au km AR nuit Dimanche :");
		lblARNuitDim.setBounds(30, 200, 220, 15);
		this.add(lblARNuitDim);
		//Création de la zone de texte AR nuit dim
		txtARNuitDim = new JTextField();
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
		txtHorJour.setBounds(268, 250, 70, 19);
		this.add(txtHorJour);
		txtHorJour.setColumns(10);
		//Création du label tarif horaire nuit dim
		JLabel lblHorNuitDim = new JLabel("Tarif horaire nuit Dimanche :");
		lblHorNuitDim.setBounds(30, 280, 210, 15);
		this.add(lblHorNuitDim);
		//Création de la zone de texte horaire nuit dim
		txtHorNuitDim = new JTextField();
		txtHorNuitDim.setBounds(268, 280, 70, 19);
		this.add(txtHorNuitDim);
		txtHorNuitDim.setColumns(10);
		//Création du label d'erreurs
		lblMsgError = new JLabel("Label d'erreurs");
		lblMsgError.setVisible(false);
		lblMsgError.setHorizontalAlignment(SwingConstants.LEFT);
		lblMsgError.setForeground(Color.RED);
		lblMsgError.setBounds(45, 300, 550, 25);
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
		//Création du LayeredPane
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		layeredPane.setForeground(Color.LIGHT_GRAY);
		layeredPane.setBounds(396, 30, 200, 100);
		this.add(layeredPane);
		//Création de la combobox
		cbbListT = new JComboBox();
		cbbListT.setBounds(470, 65, 70, 19);
		this.add(cbbListT);
		initCbbListT(brochure);
	}
	
	//*****************//
	// M E T H O D E S //
	//*****************//
	private void initCbbListT(Brochure brochure)
	{
		//Création d'un objet permettant d'arrondir le montant
		NumberFormat format = NumberFormat.getInstance();
        format.setMinimumFractionDigits(0);
		for (Tarif tarif : brochure.getListeTarifs())
		{
			cbbListT.addItem(format.format(tarif.getDepartement()));
		}//Fin for each
	}//fin initCbbListT()
}
