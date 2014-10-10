package GUI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JTextField;
import Program.*;

public class ModulesGUI {
	private Boolean OK;
	private int i;
	private int[] saisies = new int[4];
	private Brochure brochure;
	
	/**
	 * Constructeur par défaut de la classe Modules
	 */
	public ModulesGUI()
	{
		initBrochure();
		OK = false;
		i = 0;
		saisies[0] = 0; //Correspond à la saisie du département
		saisies[1] = 0; //Correspond à la saisie de la distance
		saisies[2] = 0; //Correspond à la saisie de la durée
		saisies[3] = 0; //Correspond à la saisie du moment
	}//Fin Modules()
	
	/**
	 * Modificateur de l'attribut saisies[]
	 * @param Valeur permettant de modifier l'attribut [entier]
	 * @param Indice auquel se trouve l'élément à modifier dans le tableau [entier]
	 */
	public void setSaisies(int value, int indice)
	{
		//Petite condition pour éviter un dépassement de tableau, car il n'y a que 4 saisies au total
		if(indice >= 0 && indice < 4)
		{
			saisies[indice] = value;
		}//Fin if
	}//Fin setSaisies()
	
	public int[] getSaisies()
	{
		return saisies;
	}
	
	/**
	 * Vérifie que le numéro passé en paramètre correspond au département d'un tarif de la brochure
	 * @param Le numéro du département [entier]
	 * @return Retourne Vrai si numDepartement correspond à un tarif de la brochure, sinon retroune Faux
	 */
	public boolean checkDepInBrochure(int numDepartement)
	{
		i = 0;
		OK = false;
		//Tant que i est inférieur au nombre de tarifs dans la brochure et
        //qu'on a pas trouvé le tarif correspondant au département saisi
        while (i < brochure.getListeTarifs().size() && OK == false)
        {
        	//si le département saisi correspond au tarif à l'indice i, on met OK à vrai, et on initialise la VG departement avec la saisie effectuée et controlée
            if(numDepartement == brochure.getListeTarifs().get(i).getDepartement())
            {
                OK = true;
            }//fin if
            i++;					//Incrémentation de i pour parcourir la brochure
        }//fin while (i < liste_departements.length && depart_trouve == false)
		return OK;
	}//Fin checkDepInBrochure()
	
	/**
	 * Vérifie que la valeur d'une zone de saisie est bien un nombre entier naturel, et qu'il correspond à un Tarif de la brochure
	 * @param field
	 * @param label
	 * @return
	 */
	public int checkDep(JTextField field, JLabel label){
        OK = false;
        int nb = 0;
        //On essaye de caster la saisie en entier, et OK = vrai, sinon, on lève une exception et on affiche un message d'erreur
        try{
            nb = Integer.parseInt(field.getText());
            //Si nb est inférieur à zéro, on lève une exception
            if(nb < 0)
            {
            	//on créer une variable temporaire pour l'afficher dans l'exception
            	int falseNb = nb;
            	//et on met nb à 0, sinon la zone de texte sera "Disabled"
            	nb = 0;
            	throw new Exception("\"" + falseNb + "\" is not a natural integer !");
            }//Fin if(nb < 0)
        } catch (Exception ex) {
        	//Modification du texte de la zone d'erreur
            label.setText("<html>Erreur : Le numéro de département saisi est incorrect : <br />" + ex.getMessage() + "</html>");
            //La zone d'erreur est rendue visible
            label.setVisible(true);
            //On vide la zone de texte
            field.setText("");
            //On place le focus dans la zone de texte
            field.requestFocus();
        }//Fin catch
        return nb;
    }//fin checkDep()
	
	/**
	 * Initialise la brochure à partir de la base de données
	 */
	private void initBrochure()
	{
		//Création d'un objet de type Saisie() qui a une liste de Tarifs vide
		brochure = new Brochure("Taxi'n'co", new ArrayList<Tarif>());
		
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
    	//Récupération des données de connexion
		MotDePasse login = new MotDePasse();
    	//On essaie d'ouvrir la connexion
    	try{
    		//Connexion à la basse
    		connection = DriverManager.getConnection("jdbc:postgresql://172.16.99.2:5432/fthierry", login.getLogin(), login.getMdp());
    	} catch (Exception e) {
    		System.out.println("Erreur lors de la connexion à la base de donnée locale :\n" + e);
    	}//Fin catch
    	
    	//Si l'on a pas réussi à se connecter à la bdd, on essaye sur une autre
    	if(connection == null)
    	{
    		try{
        		//Connexion à la basse
        		connection = DriverManager.getConnection("jdbc:postgresql://" + login.getAddress() + "/fthierry", login.getLogin(), login.getMdp());
        	} catch (Exception e) {
        		System.out.println("Erreur lors de la connexion à la base de donnée distante :\n" + e);
        	}//Fin catch
    	}//Fin if
    		
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
    				brochure.AddTarif(new Tarif(Integer.parseInt(curseurResultat.getString("departement")),Double.parseDouble(curseurResultat.getString("priseencharge")),Double.parseDouble(curseurResultat.getString("kmarjoursem")),Double.parseDouble(curseurResultat.getString("kmallejoursem")),Double.parseDouble(curseurResultat.getString("kmarnuitdim")),Double.parseDouble(curseurResultat.getString("kmallenuitdim")),Double.parseDouble(curseurResultat.getString("tarifhjsem")),Double.parseDouble(curseurResultat.getString("tarifhnuitd"))));
    			}//fin for
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
}
