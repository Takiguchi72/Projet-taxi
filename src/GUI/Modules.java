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
	public static int checkIntSaisie(JTextField field, JLabel label) throws Exception
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
	 * Efface le contenu du label d'erreurs et le cache
	 * @param Le label d'erreurs [JLabel]
	 */
	public static void effacerErreur(JLabel label)
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
	public static void initBrochure(Brochure brochure)
	{
		brochure = new Brochure("Taxi'n co", new ArrayList<Tarif>());
		Connection connection = getPreparedConnection();
    		
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
	}//Fin Saisie initSaisie()
	
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
}//Fin classe