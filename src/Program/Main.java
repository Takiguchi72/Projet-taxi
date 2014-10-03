package Program;

import java.util.Scanner;

/**
 * Programme du projet Taxi
 * @author takiguchi
 * @version 1.0
 */

public class Main {
	//Tableau contenant la liste des différentes moments de trajet (Tableau de constantes)
	public static final String[] listeMoments = {"Allé/retour semaine","Allé semaine", "Allé/retour nuit dimanche", "Allé nuit dimanche"};
	//Tableau contenant tous les départements de la brochure
	public static int[] Departements ={21,25,39,44,72,73,74,75,85,90};
	//Tableau contenant tous les tarifs de la brochure
	public static double[][] tarifs = {{2,0.86,1.72,21.93,1.29,2.58,21.93},{2.1,0.83,1.66,22.5,1.2,2.4,22.5},{2.1,0.83,1.66,22.5,1.23,2.46,25},{2.2,0.79,1.58,24.19,1.19,2.37,24.19},{2.15,0.79,1.58,22.86,1.19,2.38,22.86},{2.4,0.84,1.68,25.4,1.26,2.52,25.4},{3.15,0.92,1.84,17.3,1.38,2.76,17.3},{2.5,1,1.24,0,1.5,1.5,0},{2.3,0.8,1.6,22.2,1.2,2.4,22.2},{2.2,0.83,1.66,21,1.15,2.3,21}};
	//Bouléen qui permet de savoir si on a trouvé un élément du tableau ou non lors d'une boucle
	public static boolean OK = false;
	//Compteur général qui sera utilisé dans tout le programme
	public static int i = 0;
	//Objet de la classe Scanner pour effectuer des saisies
	public static Scanner Saisie = new Scanner(System.in);

	/**
	 * Réalise une saisie utilisateur, avec un controle de saisie pour qu'il ne saisisse qu'un entier et pas autre chose
	 * @return Le nombre saisi [entier]
	 */
	public static int int_saisir(){
		int nb = 0;						//Variable qui contiendra la saisie de l'utilisateur
		OK = false;

		while (!OK)
		{
			//On essaye de caster la saisie en entier, et OK = vrai, sinon, on lève une exception et on affiche un message d'erreur
			try{
				nb = Saisie.nextInt();
				OK = true;
			} catch (Exception ex) {
				String s = Saisie.next();
				System.out.println("L'erreur suivante s'est produite : " + ex.toString() + "\nSaisie à refaire !");
			}
		}//fin while
		return nb;
	}//fin int_saisir()
	
	/**
	 * Réalise la saisie du département, et vérifie que le département saisi existe dans le tableau
	 * @return Le numéro du département saisi [entier]
	 */
	public static int saisie_departement()
    {
        int dptmt = 0;					//Variable qui contiendra la saisie de l'utilisateur
        //La variable globale i représente un compteur qui servira à parcourir la brochure
        //La variable globale OK représente un booléen qui servira à indiquer qu'on a trouvé ou non le tarif correspondant au département saisi
        
        //L'user saisi un departement et si il ne correspond à aucun tarif de la brochure, on refait la saisie at vitam eternam :)
        do{
            System.out.println("Saisissez le numéro du département");
            dptmt = int_saisir();       //Saisie du département
            i = 0;						//Réinitialisation du compteur i pour les cas où on boucle plusieurs fois
            OK = false;					//Idem que pour i
            //Tant que i est inférieur à la longueur du tableau contenant les départements, et
            //qu'on a pas trouvé le tarif correspondant au département saisi
            while (i < Departements.length && OK == false)
            {
            	//si le département saisi correspond au tarif à l'indice i, on met OK à vrai, et on initialise la VG departement avec la saisie effectuée et controlée
                if(dptmt == Departements[i])
                {
                    OK = true;
                }//fin if
                i++;					//Incrémentation de i pour parcourir la brochure
            }//fin while (i < liste_departements.length && depart_trouve == false)
            //Si on n'a pas trouvé le numéro saisie, dans la liste, on affiche un message et on redemande de saisir le numéro du département
            if (OK == false)
            {
                System.out.println("Le département saisi n'est pas pris en charge !");
            }//fin if
            
        }while(OK == false);
        return dptmt;
    }//Fin saisie_departement()
	
	/**
	 * Réalise la saisie de la distance parcourue, et vérifiant que celle-ci est supérieure à 0
	 * @return La distance parcourue saisie [entier]
	 */
	public static int saisie_distance()
    {
        int dist = 0;					//Variable qui contiendra la saisie de l'utilisateur
        //L'user saisi une distance et si elle n'est pas un entier naturel, on refait la saisie at vitam eternam :)
        do{
            System.out.println("Saisissez la distance parcourue (en km)");
            dist = int_saisir();        //Saisie de la distance parcourue
            //i = 0;
            OK = false;					//Réinitialisation de OK au cas où la saisie est fausse
            
            //Si le nombre saisi est négatif, on affiche un message d'erreur
            if (dist <= 0)
            {
                OK = false;
                System.out.println("La distance parcourue doit etre positive !");
            }//fin if
            //Autrement, OK = vrai pour sortir de la boucle et on initialise la VG distance avec la saisie effectuée et controlée
            else
            {
                OK = true;
            }//fin else
        } while(OK == false);
        return dist;
        //Saisie de la durée, en minutes
    }//Fin saisie_distance()
	
	/**
	 * Réalise la saisie de la durée, et vérifie que celle-ci est supérieure à 0
	 * @return La durée saisie, en minutes [entier]
	 */
	public static int saisie_duree()
    {
    	int dureeSaisie = 0;				//Variable qui contiendra la saisie de l'utilisateur
    	//L'user saisi une durée et si elle n'est pas supérieure à 0, on refait la saisie at vitam eternam :)
    	do{
            System.out.println("Saisissez la durée (en minutes)");
            dureeSaisie = int_saisir();     //Saisie de la durée
            OK = false;						//Réinitialisation de OK au cas où la saisie est fausse
            //Si le nombre saisi est négatif, on affiche un message d'erreur
            if (dureeSaisie <= 0)
            {
                OK = false;
                System.out.println("La durée doit etre positive !");
            }//fin if
            //Autrement, OK = vrai pour sortir de la boucle et on initialise la VG duree avec la saisie effectuée et controlée
            else
            {
                OK = true;
            }//fin else
        } while(OK == false);
    	return dureeSaisie;
    }//Fin saisie_duree()
	
	/**
	 * Affiche la liste des moments
	 * (Ex: "Allé simple jour semaine")
	 */
	public static void show_moments()
    {
    	System.out.println("Saisir le numéro correspondant au moment :");
    	//On parcours le tableau listeMoments pour afficher tous ses tuples
        for (i = 0 ; i < listeMoments.length ; i++)
        {
            System.out.println("(" + i + ") " + listeMoments[i]);
        }//fin for
    }//Fin show_moment()
	
	/**
	 * Réalise la saisie du moment par son index dans le tableau, et vérifie que le nombre saisi correspond à un indice du tableau
	 * @return L'index du moment saisi [entier]
	 */
	public static int saisie_moment()
    {
    	int momentSaisi;					//Variable qui contiendra la saisie de l'utilisateur
        //L'user saisi un des code moment et si il n'est pas dans le tableau, on refait la saisie at vitam eternam :)
        do{
            show_moments();					//Affichage de la liste des moments
        	momentSaisi = int_saisir();        	//Saisie de l'user
        	//momentSaisi = 0;
        	OK = false;						//Réinitialisation de OK au cas où la saisie est fausse
        	//si le moment saisi est supérieur ou égal à 0 et inférieur au nombre max de moments,
            if (momentSaisi >= 0 && momentSaisi < listeMoments.length)//???
            {
                OK = true;					//Le nombre saisi est correct
            }//fin if
            else							//Autrement, c'est que le nombre saisi est incorrect
            {
            	System.out.println("Le nombre saisi est incorrect !");
                OK = false;					//Donc on passe le bouleen à faux
            }//fin else
        }while(OK == false);
        return momentSaisi;
    }//Fin saisie_moment()
	
	/**
	 * Retourne l'indice du tarif du tableau de tarifs, en fonction du numéro de département, saisi, en paramètre
	 * @param Le numéro du département que l'on cherche dans le tableau [entier]
	 * @return L'indice du tarif ayant pour numéro de département celui passé en paramètre [entier]
	 */
	public static int check_indice_dep(int num_dep)
    {
    	OK = false;
    	i = 0;
    	//Tant que i est inférieur ou égal à l'indice max et que le booleen est à faux (donc on n'a pas trouvé le département)
    	while (i < Departements.length && OK == false)
    	{
    		//Si le num_dep correspond au tarif à l'indice i de la brochure,
    		if (num_dep == Departements[i])
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
	 * @param Le numéro de département, permettant de récupérer le tarif d'un Tarif du tableau [entier]
	 * @param L'indice du moment dans la liste de moments [entier]
	 * @return Le montant correspondant au tarif ayant pour département la valeur de "dep", en fonction de l'indice "moment"
	 */
	public static double check_tarif_km(int dep, int moment)
    {
    	double valeur = 0;
    	//En fonction du moment passé en paramètre, on va renvoyer un des montants du Tarif correspondant au numéro de département saisi en paramère
    	//Note : On est obligé de mettre le montant dans une variable locale, car si on fait des "return", on ne peut exécuter l'instruction "break"
    	switch (moment)
    	{
	    	case 0:
	    		valeur = tarifs[check_indice_dep(dep)][1];
	    		break;
	    	case 1:
	    		valeur = tarifs[check_indice_dep(dep)][2];;
	    		break;
	    	case 2:
	    		valeur = tarifs[check_indice_dep(dep)][4];
	    		break;
	    	default:
	    		valeur = tarifs[check_indice_dep(dep)][5];;
	    		break;
    	}//Fin switch (moment)
    	return valeur;
    }//fin check_tarif_km()
	
	/**
	 * Retourne le tarif horaire correspondant au tarif ayant pour département la valeur du paramètre "dep"
	 * @param Le numéro de département, permettant de récupérer le tarif d'un Tarif du tableau [entier]
	 * @param L'indice du moment dans la liste de moments [entier]
	 * @return Le montant du tarif horaire au tarif ayant pour département la valeur de "dep"
	 */
	public static double check_tarif_jn(int dep, int moment)
    {
    	//Si moment == {0;1} alors on retourne le tarif horaire de jour
    	if (moment == 0 || moment == 1)
    	{
    		return tarifs[check_indice_dep(dep)][3];
    	}//fin if
    	//Sinon, on retourne le tarif horaire de nuit
    	else
    	{
    		return tarifs[check_indice_dep(dep)][6];
    	}//fin else
    }//Fin check_tarif_jn(
	
	/**
	 * Effectue les saisies nécessaires et retourne le montant à payer
	 * @return Le montant à payer [décimal]
	 */
	public static double calculPrixAPayer()
	{
		int departement = saisie_departement();
		int distance = saisie_distance();
		int duree = saisie_duree();
		int moment = saisie_moment();
		if(duree < 60)
		{
			return (tarifs[check_indice_dep(departement)][0] + distance * check_tarif_km(departement, moment));
		}//fin if
		else
		{
			return (tarifs[check_indice_dep(departement)][0] + distance * check_tarif_km(departement, moment) + ((duree - (duree % 60))/60) * check_tarif_jn(departement, moment));
		}//fin else
	}//Fin calculPrixAPayer()
	
	/**
	 * Ferme l'objet de la classe Scanner
	 */
	public static void closeScanner()
	{
		Saisie.close();
	}//Fin closeScanner()
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.print("Vous devez payer " + calculPrixAPayer() + " euros.");
	}

}
