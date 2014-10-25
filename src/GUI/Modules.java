package GUI;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JTextField;
import Program.Brochure;
import Program.DonneesConnexion;
import Program.Tarif;

public class Modules{

	/**
	 * Vérifie que la valeur d'une zone de saisie est un entier naturel
	 * @param La zone de saisie à vérifier [JTextField]
	 * @param Le label où afficher l'erreur [JLabel]
	 */
	public static int checkIntSaisie(JTextField field) throws Exception
	{
		int nb = 0;
		//On vérifie que la zone de saisie est remplie
		checkEmptyField(field);
		//On essaye de caster la saisie en entier, si c'est impossible on lève une exception
    	try{
    		nb = Integer.parseInt(field.getText());
    	} catch (NumberFormatException ex) {
			clearAndFocusField(field);
			throw new Exception("La saisie dans le champ \"" + field.getName() + "\" est incorrecte : <br />" + ex.getMessage(), new Throwable("type"));
		}//Fin catch
    	//Si le nombre est inférieur à zéro, on lève une exception
		if(nb < 0)
		{
			String text = field.getText();
			clearAndFocusField(field);
			throw new Exception("La saisie dans le champ \"" + field.getName() + "\" est incorrecte : <br />\"" + text + "\" is not a natural integer !",new Throwable("type"));
		}//Fin if(nb < 0)
		return nb;
	}//Fin checkIntSaisie()
	
	/**
	 * Vérifie que la valeur d'une zone de saisie est un décimal supérieur à zéro
	 * @param La zone de saisie à vérifier [JTextField]
	 * @param Le label où afficher l'erreur [JLabel]
	 */
	public static double checkDoubleSaisie(JTextField field, JLabel label) throws Exception
	{
		double nb = 0;
		//On vérifie que la zone de saisie est remplie
		checkEmptyField(field);
		//On essaye de caster la saisie en entier, si c'est impossible on lève une nouvelle exception où l'on renseignera quel est le champ incorrect
		try{
			nb = Double.parseDouble(field.getText());
		} catch (NumberFormatException ex) {
			clearAndFocusField(field);
			throw new Exception("La saisie dans le champ \"" + field.getName() + "\" est incorrecte : <br />" + ex.getMessage(), new Throwable("type"));
		}//Fin catch
    	//Si le nombre est inférieur à zéro, on lève une exception
		if(nb < 0)
		{
			String text = field.getText();
			clearAndFocusField(field);
			throw new Exception("La saisie dans le champ \"" + field.getName() + "\" est incorrecte : <br />\"" + text + "\" is not a positif double !",new Throwable("type"));
		}//Fin if(nb < 0)
		return nb;
	}//Fin checkDoubleSaisie()
	
	/**
	 * Vérifie que la zone de saisie passé en paramètre n'est pas vide, sous peine de lever une exception
	 * @param La zone de saisie à contrôler [JTextField]
	 * @throws Lève une exception si le champ est vide [Exception]
	 */
	public static void checkEmptyField(JTextField field) throws Exception
	{
		//si le champ est null, on lève une exception
    	if(field.getText().isEmpty())
        {
    		field.requestFocus();
    		throw new Exception("La saisie dans le champ \"" + field.getName() + "\" est incorrecte : <br />The field is empty !",new Throwable("empty"));
        }//fin if
	}//Fin checkEmptyField()
	
	/**
	 * Affiche le JLabel lblMsgError et modifie son texte gràce au 1er paramètre et change de couleur en fonction du 2ème
	 * @param Le message d'erreur à afficher [chaîne de caractères]
	 * @param Le niveau de l'erreur ; 0:Error (red); 1:Warning (orange)
	 */
	public static void afficherErreur(JLabel label, String message, int niveauDErreur)
	{
		switch (niveauDErreur)
		{
		case 1: label.setForeground(Color.RED);
		label.setText("<html>Error : " + message + "</html>");
			break;
		case 2:	label.setForeground(new Color(230, 168, 17));	//couleur orange-foncé
		label.setText("<html>Warning : " + message + "</html>");
			break;
		default : label.setForeground(Color.RED);
			break;
		}//fin switch()
		label.setVisible(true);
	}//fin afficherErreur()
	
	/**
	 * Vérifie le type de donnée saisi dans la zone txtDep, et vérifie que cette valeur ne correspond pas à un departement de la brochure
	 * @param La zone de saisie txtDep à vérifier [JTextField]
	 * @param La brochure de tarifs où vérifier si le département y est présent [Brochure]
	 * @return La saisie castée en entier naturel si elle n'est pas dans la brochure
	 */
	public static int checkDepIsNotInBrochure(JTextField field, Brochure brochure) throws Exception
	{
		//On convertit la saisie en entier naturel
		int nb = checkIntSaisie(field);
		//Si la saisie est supérieure ou égale à 100, on lève une exception
		checkUnderThan100(field, nb);
		//Si la saisie est dans la brochure, on lève une exception
		if(Modules.checkDepInBrochure(nb, brochure) == true)
		{
			Modules.clearAndFocusField(field);
			throw new Exception("La saisie dans le champ \"" + field.getName() + "\" est incorrecte : <br />\"" + Integer.toString(nb) + 
								"\" is already registed !", new Throwable("alreadyRegisted"));
		}//Fin if(trouve == true)
		return nb;
	}//Fin checkDepInBrochure()
	
	/**
	 * Vérifie le type de donnée saisi dans la zone txtDep, et vérifie que cette valeur correspond à un departement de la brochure
	 * @param La zone de saisie txtDep à vérifier [JTextField]
	 * @param La brochure de tarifs où vérifier si le département y est présent [Brochure]
	 * @return La saisie castée en entier naturel si elle n'est pas dans la brochure
	 */
	public static int checkDepInBrochure(JTextField field, Brochure brochure) throws Exception
	{
		//On vérifie que la saisie est bien un entier naturel
		int nb = Modules.checkIntSaisie(field);
		//Si la saisie est supérieure ou égale à 100, on lève une exception
		checkUnderThan100(field, nb);
		//Si la saisie n'est pas dans la brochure, on lève une exception
		if(Modules.checkDepInBrochure(nb, brochure) == false)
		{
			Modules.clearAndFocusField(field);
			throw new Exception("La saisie dans le champ \"" + field.getName() + "\" est incorrecte : <br />\"" + Integer.toString(nb) + 
								"\"th department is not recognized !", new Throwable("notRegisted"));
		}//Fin if(trouve == true)
		return nb;
	}//Fin checkDepInBrochure()
	
	/**
	 * Vérifie qu'un nombre est bien inférieur à 100
	 * @param Le JTextfield d'où provient le nombre (pour lever l'exception) [JTextField]
	 * @param Le nombre à vérifier [entier]
	 * @throws Lève une exception si le nombre est supérieur ou égal à 100
	 */
	public static void checkUnderThan100(JTextField field, int nb) throws Exception
	{
		if(nb >= 100)
		{
			Modules.clearAndFocusField(field);
			throw new Exception("La saisie dans le champ \"" + field.getName() + "\" est incorrecte : <br />\"" + Integer.toString(nb) + 
								"\" must be lower than 100 !", new Throwable("moreThan1Hundred"));
		}//Fin if(nb > 100)
	}//Fin checkUnderThan100()
	
	/**
	 * Efface le contenu du label d'erreurs et le cache
	 * @param Le label d'erreurs [JLabel]
	 */
	public static void clearLabel(JLabel label)
	{
		label.setText(null);
		label.setVisible(false);
	}//Fin effacerErreur()
	
	/**
	 * Efface le contenu d'une zone de saisie et place le focus à l'intérieur
	 * @param Le champ à traiter [JTextField]
	 */
	public static void clearAndFocusField(JTextField field)
	{
		field.setText("");
		field.requestFocus();
	}//Fin clearAndFocusField(JTextField field)
	
	/**
	 * Initialise la brochure à partir de la base de données
	 */
	public static Brochure initBrochure()
	{
		Brochure brochure = new Brochure("Taxi'n co", new ArrayList<Tarif>());
		Connection connection = getConnection();
    	
    	//Si la connexion a été établie, on va effectuer des requêtes
    	if (connection != null)
    	{
    		try{
    			//Création d'un objet de type Statement qui permettra d'effectuer des requêtes
    			Statement req = connection.createStatement();
    			//Execution de la requête
    			ResultSet curseurResultat = req.executeQuery("select * from \"taxi_project\".tarif");
    			//Pour chaque tuple dans le résultat de la requête, on va utiliser la donnée 
    			while(curseurResultat.next())
    			{
    				//On ajoute un Tarif dans la liste de tarifs de la brochure de l'objet de classe Saisie
    				brochure.AddTarif(new Tarif(Integer.parseInt(curseurResultat.getString("departement")),
    											Double.parseDouble(curseurResultat.getString("priseencharge")),
    											Double.parseDouble(curseurResultat.getString("kmarjoursem")),
    											Double.parseDouble(curseurResultat.getString("kmallejoursem")),
    											Double.parseDouble(curseurResultat.getString("kmarnuitdim")),
    											Double.parseDouble(curseurResultat.getString("kmallenuitdim")),
    											Double.parseDouble(curseurResultat.getString("tarifhjsem")),
    											Double.parseDouble(curseurResultat.getString("tarifhnuitd"))));
    			}//fin for
    			req.close();
    		} catch (Exception e){
    			System.out.println("Erreur : " + e.getMessage());
    		}//Fin catch
    		
    		//Fermeture de la connexion si elle a été ouverte
        	try{
        		connection.close();
        	} catch (Exception e){
        		System.out.println("Failed to close connection!");
        	}//Fin catch
		}//Fin if
    	else
    	{
			System.out.println("Failed to make connection!");
		}//Fin else
    	return brochure;
	}//Fin Saisie initSaisie()
	
	/**
	 * Obtient un objet correspondant à une connexion préparée (pour les requêtes préparées
	 * @return La connexion préparée [Connection]
	 */
	public static Connection getPreparedConnection()
	{
		//Localisation du driver JDBC
    	try{
    		Class.forName("org.postgresql.Driver");
    	} catch (Exception e) {
    		System.out.println("Where is your PostgreSQL JDBC Driver? "
					+ "Include in your library path!");
			e.printStackTrace();
    	}//Fin catch
    	//Création d'un objet de type Connection
    	Connection connection = null;
    	try{
    		//Connexion à la basse
    		connection = DriverManager.getConnection("jdbc:postgresql://" + DonneesConnexion.getAddress() + "/fthierry", DonneesConnexion.getLogin(), DonneesConnexion.getMdp());
    	} catch (Exception e) {
    		System.out.println("Erreur lors de la connexion à la base de donnée :\n" + e);
    	}//Fin catch
    	return connection;
	}//Fin getPreparedConnection()
	
	/**
	 * Obtient un objet correspondant à une connexion classique
	 * @return La connexion [Connection]
	 */
	public static Connection getConnection()
	{
		//Localisation du driver JDBC
    	try{
    		Class.forName("org.postgresql.Driver");
    	} catch (Exception e) {
    		System.out.println("Where is your PostgreSQL JDBC Driver? "
					+ "Include in your library path!");
			e.printStackTrace();
    	}//Fin catch
    	//Création d'un objet de type Connection
    	Connection connection = null;
    	try{
    		//Connexion à la basse
    		connection = DriverManager.getConnection("jdbc:postgresql://" + DonneesConnexion.getAddress() + "/fthierry", DonneesConnexion.getLogin(), DonneesConnexion.getMdp());
    	} catch (Exception e) {
    		System.out.println("Erreur lors de la connexion à la base de donnée :\n" + e);
    	}//Fin catch
    	return connection;
	}//Fin getConnection()
	
	/**
	 * Vérifie si le 1er paramètre correspond au département d'un tarif de la brochure passée en paramètre
	 * @param Le departement à vérifier
	 * @param La brochure dans laquelle chercher
	 * @return Si le département est trouvé, on retourne True, sinon, on retourne False
	 */
	public static boolean checkDepInBrochure(int departement, Brochure brochure)
	{
		boolean trouve = false;
		//Si la saisie correspond à un departement de la brochure, on met "trouve" à True pour lever une Exception plus tard
		for(Tarif tarif : brochure.getListeTarifs())
		{
			if(departement == tarif.getDepartement())
			{
				trouve = true;
			}//Fin if(Dep == tarif.getDepartement())
		}//Fin for each
		return trouve;
	}//fin checkDepInBrochure()
	
	/**
	 * Retourne l'indice du tarif de la brochure, ayant pour numéro de département, le numéro saisi en paramètre
	 * @param Le numéro du département que l'on cherche dans la brochure [entier]
	 * @return L'indice du tarif ayant pour numéro de département celui passé en paramètre [entier]
	 */
	public static int check_indice_dep(int num_dep, Brochure brochure)
	{
		boolean OK = false;
		int i = 0;
		//Tant que i est inférieur ou égal à l'indice max et que le booleen est à faux (donc on n'a pas trouvé le département)
		while (i < brochure.getListeTarifs().size() && OK == false)
		{
			//Si le num_dep correspond au tarif à l'indice i de la brochure,
			if (num_dep == brochure.getListeTarifs().get(i).getDepartement())
			{
				OK = true;
			}//fin if
			else
			{
				OK = false;
				i++;
			}//fin else
		}//Fin while
		//On retourne la valeur de i qui correspondra à l'indice du tarif de la brochure, qui aura pour departement celui passé en paramètre
		return i;
	}//Fin check_indice_dep()
	
	/**
	 * Retourne un montant correspondant au tarif ayant pour département la valeur du paramètre "dep", en fonction d'un moment, passé en paramètre
	 * @param Le numéro de département, permettant de récupérer le tarif d'un Tarif de la brochure [entier]
	 * @param L'indice du moment dans la liste de moments [entier]
	 * @return Le montant correspondant au tarif ayant pour département la valeur de "dep", en fonction de l'indice "moment"
	 */
	public static double check_tarif_km(int dep, int moment, Brochure brochure)
	{
		double valeur = 0;
		//En fonction du moment passé en paramètre, on va renvoyer un des montants du Tarif correspondant au numéro de département saisi en paramère
		//Note : On est obligé de mettre le montant dans une variable locale, car si on fait des "return", on ne peut exécuter l'instruction "break"
		switch (moment)
		{
		case 0:
			valeur = brochure.getListeTarifs().get(Modules.check_indice_dep(dep, brochure)).getKmAlleSimpleJSem();
			break;
		case 1:
			valeur = brochure.getListeTarifs().get(Modules.check_indice_dep(dep, brochure)).getKmAlleRetourJSem();
			break;
		case 2:
			valeur = brochure.getListeTarifs().get(Modules.check_indice_dep(dep, brochure)).getKmAlleSimpleNuitDim();
			break;
		default:
			valeur = brochure.getListeTarifs().get(Modules.check_indice_dep(dep, brochure)).getKmAlleRetourNuitDim();
			break;
		}//Fin switch (moment)
		return valeur;
	}//fin check_tarif_km()
	
	/**
	 * Retourne le tarif horaire correspondant au tarif ayant pour département la valeur du paramètre "dep"
	 * @param Le numéro de département, permettant de récupérer le tarif d'un Tarif de la brochure [entier]
	 * @param L'indice du moment dans la liste de moments [entier]
	 * @return Le montant du tarif horaire au tarif ayant pour département la valeur de "dep"
	 */
	public static double check_tarif_jn(int dep, int moment, Brochure brochure)
	{
		//Si moment == {0;1} alors on retourne le tarif horaire de jour
		if (moment == 0 || moment == 1)
		{
			return brochure.getListeTarifs().get(Modules.check_indice_dep(dep, brochure)).getTarifHoraireJSem();
		}//fin if
		//Sinon, on retourne le tarif horaire de nuit
		else
		{
			return brochure.getListeTarifs().get(Modules.check_indice_dep(dep, brochure)).getTarifHoraireNuitDim();
		}//fin else
	}//Fin check_tarif_jn()
}//Fin classe