package Program;

import java.util.ArrayList;
import java.util.List;

/**
* Programme du projet Taxi
* @author takiguchi
* @version 2.0
*/

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Tableau contenant tous les départements de la brochure
		int[] Departements = {21,25,39,44,72,73,74,75,85,90};
		//Tableau contenant tous les tarifs de la brochure
		double[][] tarifs = {{2,0.86,1.72,21.93,1.29,2.58,21.93},{2.1,0.83,1.66,22.5,1.2,2.4,22.5},{2.1,0.83,1.66,22.5,1.23,2.46,25},{2.2,0.79,1.58,24.19,1.19,2.37,24.19},{2.15,0.79,1.58,22.86,1.19,2.38,22.86},{2.4,0.84,1.68,25.4,1.26,2.52,25.4},{3.15,0.92,1.84,17.3,1.38,2.76,17.3},{2.5,1,1.24,0,1.5,1.5,0},{2.3,0.8,1.6,22.2,1.2,2.4,22.2},{2.2,0.83,1.66,21,1.15,2.3,21}};
		//Déclaration d'une liste de Tarifs, qui permettra d'initialiser un objet de la classe Brochure
		List<Tarif> listeTarifs = new ArrayList<Tarif>();
		//Boucle permettant de parcourir le tableau de département afin d'ajouter des Tarifs à la liste
		for (int i = 0 ; i < Departements.length ; i++)
		{
			listeTarifs.add(new Tarif(Departements[i], tarifs[i][0], tarifs[i][1], tarifs[i][2], tarifs[i][3], tarifs[i][4], tarifs[i][5], tarifs[i][6]));
		}//Fin for
		//Déclaration d'un objet Brochure à partir de la liste précédament créée
		Brochure brochure = new Brochure("Brochure de taxi", listeTarifs);
		//Déclaration d'un objet Saisie à partir de la brochure précédament créée
		Saisie lesSaisies = new Saisie(brochure);
		//toutes les saisies et les calculs seront effectués lors d'appel à la fonction calculPrixAPayer()
		System.out.print("Vous devez payer " + lesSaisies.calculPrixAPayer() + " euros.");
	}//Fin main()
}//Fin Main()