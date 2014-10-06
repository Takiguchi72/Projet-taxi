package Program;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Programme du projet Taxi
 * @author takiguchi
 * @version 3.0
 */

public class Main {	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int i = 0;
		//Ouverture du fichier grilleTarifs.txt
		try {
			BufferedReader monBuffer = new BufferedReader(new FileReader("/home/takiguchi/Documents/BTS/SLAM/Java/Projet-Taxi/src/Program/grilleTarifs.txt"));
			//On récupère chaque ligne du fichier
			String ligne;
			//Déclaration d'une liste de Tarifs, qui permettra d'initialiser un objet de la classe Brochure
			List<Tarif> listeTarifs = new ArrayList<Tarif>();
			//Boucle permettant de découper le fichier en lignes et les lignes en fonction du caractère "," pour initialiser une liste de tarifs
			while ((ligne = monBuffer.readLine()) != null)
			{
				String[] split = ligne.split(",");
				listeTarifs.add(new Tarif(Integer.parseInt(split[0]), Double.parseDouble(split[1]), Double.parseDouble(split[2]), Double.parseDouble(split[3]), Double.parseDouble(split[4]), Double.parseDouble(split[5]), Double.parseDouble(split[6]), Double.parseDouble(split[7])));
				i++;
			}//Fin while
			//Déclaration d'un objet Brochure à partir de la liste précédament créée
			Brochure brochure = new Brochure("Brochure de taxi", listeTarifs);
			//Déclaration d'un objet Saisie à partir de la brochure précédament créée
			Saisie lesSaisies = new Saisie(brochure);
			//toutes les saisies et les calculs seront effectués lors d'appel à la fonction calculPrixAPayer()
			System.out.print("Vous devez payer " + lesSaisies.calculPrixAPayer() + " euros.");
			monBuffer.close();
		} catch (Exception ex) {
			System.out.print("Erreur : " + ex.getMessage());
		}//Fin catch()
	}//Fin main()
}//Fin Main()
