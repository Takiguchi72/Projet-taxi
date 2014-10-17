package GUI;

import java.awt.Color;
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
	public int checkSaisie(JTextField field, JLabel label){
        OK = false;
        int nb = 0;
        try{
        	//si le champ est null, on lève une exception
        	if(field.getText().isEmpty() == true)
            {
            	throw new Exception("The field is empty !");
            }//fin if
        	else
        	{
        		//On essaye de caster la saisie en entier, et OK = vrai, sinon, on lève une exception et on affiche un message d'erreur
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
        	}//Fin else
        } catch (Exception ex) {
        	String whatIsFalse = null;	
        	switch (field.getName())
        	{
	        	case "txtDep" :	whatIsFalse = "Le numéro de département saisi est incorrect";
	        		break;
	        	case "txtKm" : whatIsFalse = "La distance saisie est incorrecte";
	        		break;
	        	default : whatIsFalse = "La durée saisie est incorrecte";
	        		break;
        	}//fin switch()
        	//Modification du texte et affichage de la zone d'erreur
        	afficherErreur(label, whatIsFalse + " : <br />" + ex.getMessage(), 1);
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
	
	/**
	 * Affiche le JLabel lblMsgError et modifie son texte gràce au 1er paramètre et change de couleur en fonction du 2ème
	 * @param Le message d'erreur à afficher [chaîne de caractères]
	 * @param Le niveau de l'erreur ; 0:Error (red); 1:Warning (orange)
	 */
	private void afficherErreur(JLabel label, String message, int niveauDErreur)
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
	 * Calcule le montant à payer et le retourne
	 * @return Le montant à payer [réel]
	 */
	public double calculPrix()
	{
		if(saisies[2] < 60)
		{
			return (brochure.getListeTarifs().get(check_indice_dep(saisies[0])).getPriseEnCharge() + saisies[1] * check_tarif_km(saisies[0], saisies[3]));
		}//fin if
		else
		{
			return (brochure.getListeTarifs().get(check_indice_dep(saisies[0])).getPriseEnCharge() + saisies[1] * check_tarif_km(saisies[0], saisies[3]) + ((saisies[2] - (saisies[2] % 60))/60) * check_tarif_jn(saisies[0], saisies[3]));
		}//fin else
	}//fin calculPrix()
	
	/**
	 * Retourne l'indice du tarif de la brochure, ayant pour numéro de département, le numéro saisi en paramètre
	 * @param Le numéro du département que l'on cherche dans la brochure [entier]
	 * @return L'indice du tarif ayant pour numéro de département celui passé en paramètre [entier]
	 */
	private int check_indice_dep(int num_dep)
    {
    	OK = false;
    	i = 0;
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
	private double check_tarif_km(int dep, int moment)
    {
    	double valeur = 0;
    	//En fonction du moment passé en paramètre, on va renvoyer un des montants du Tarif correspondant au numéro de département saisi en paramère
    	//Note : On est obligé de mettre le montant dans une variable locale, car si on fait des "return", on ne peut exécuter l'instruction "break"
    	switch (moment)
    	{
	    	case 0:
	    		valeur = brochure.getListeTarifs().get(check_indice_dep(dep)).getKmAlleSimpleJSem();
	    		break;
	    	case 1:
	    		valeur = brochure.getListeTarifs().get(check_indice_dep(dep)).getKmAlleRetourJSem();
	    		break;
	    	case 2:
	    		valeur = brochure.getListeTarifs().get(check_indice_dep(dep)).getKmAlleSimpleNuitDim();
	    		break;
	    	default:
	    		valeur = brochure.getListeTarifs().get(check_indice_dep(dep)).getKmAlleRetourNuitDim();
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
	private double check_tarif_jn(int dep, int moment)
    {
    	//Si moment == {0;1} alors on retourne le tarif horaire de jour
    	if (moment == 0 || moment == 1)
    	{
    		return brochure.getListeTarifs().get(check_indice_dep(dep)).getTarifHoraireJSem();
    	}//fin if
    	//Sinon, on retourne le tarif horaire de nuit
    	else
    	{
    		return brochure.getListeTarifs().get(check_indice_dep(dep)).getTarifHoraireNuitDim();
    	}//fin else
    }//Fin check_tarif_jn()
}//Fin ModuleGUI
