package Program;
/**
 * Programme principal du projet Taxi_project
 * 
 * @author Takiguchi
 * @version 2.0
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Main {
	
	public static Saisie initSaisie()
	{
		//Création d'un objet de type Saisie() qui a une liste de Tarifs vide
		Saisie mesSaisies = new Saisie(new Brochure("Taxi'n'co", new ArrayList<Tarif>()));
		
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
    	//On essaie d'ouvrir la connexion
    	try{
    		//Récupération du mot de passe
    		MotDePasse mdp = new MotDePasse();
    		//Connexion à la basse
    		connection = DriverManager.getConnection("jdbc:postgresql://172.16.99.2:5432/fthierry", "f.thierry", mdp.getMdp());
    	} catch (Exception e) {
    		System.out.println("Erreur lors de la connexion à la base de donnée :\n" + e);
    	}//Fin catch
    	
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
    				mesSaisies.getBrochure().AddTarif(new Tarif(Integer.parseInt(curseurResultat.getString("departement")),Double.parseDouble(curseurResultat.getString("priseencharge")),Double.parseDouble(curseurResultat.getString("kmarjoursem")),Double.parseDouble(curseurResultat.getString("kmallejoursem")),Double.parseDouble(curseurResultat.getString("kmarnuitdim")),Double.parseDouble(curseurResultat.getString("kmallenuitdim")),Double.parseDouble(curseurResultat.getString("tarifhjsem")),Double.parseDouble(curseurResultat.getString("tarifhnuitd"))));
    			}//fin for
    			
    			//Affichage du prix à payer
    			System.out.println("Vous devez payer : " + mesSaisies.calculPrixAPayer() + " euros !");
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
		return mesSaisies;
	}//Fin Saisie initSaisie()
	
    /**
     * @param args
     */
    public static void main(String[] args) {
    	Saisie mesSaisies = new Saisie(initSaisie());
    }//Fin main()
}//Fin Class()