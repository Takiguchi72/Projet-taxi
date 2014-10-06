package Program;
/**
 * Représente l'essemble des saisies et des traitements du projet Taxi_project
 * 
 * @author Takiguchi
 * @version 2.0
 */

import java.util.Scanner;

/**
 * Représent la saisie de l'utilisateur durant l'exécution du programme
 * 
 * @author Takiguchi
 * @version 1.0
 */

public class Saisie {
	
	//Liste des Attributs
	private final String[] listeMoments = {"Allé/retour semaine","Allé semaine", "Allé/retour nuit dimanche", "Allé nuit dimanche"};
	private Scanner saisie;
	private boolean OK;
	private int i;
	private int departement;
	private int distance;
	private int duree;
	private int moment;
	private Brochure brochure;
	
	//***********//
	// Accesseur //
	//***********//
	
	/**
	 * Accesseur de la brochure
	 * @return La brochure de la classe Saisie [Brochure]
	 */
	public Brochure getBrochure()
	{
		return brochure;
	}
	
	//**************//
	// Constructeur //
	//**************//
	
	/**
	 * Constructeur par défaut, qui initialise toutes les variables à 0
	 */
	public Saisie(Brochure brochure)
	{
		departement = 0;
		distance = 0;
		duree = 0;
		moment = 0;
		OK = false;
		i = 0;
		saisie = new Scanner(System.in);
		this.brochure = brochure;
	}
	
	/**
	 * Constructeur de la classe Saisie à partir d'un objet de la classe Saisie
	 * @param L'objet de la classe Saisie qui permet d'initialiser tous les attributs du nouvel objet
	 */
	public Saisie(Saisie saisie)
	{
		this.departement = 0;
		this.distance = 0;
		this.duree = 0;
		this.moment = 0;
		this.OK = false;
		this.i = 0;
		this.saisie = new Scanner(System.in);
		this.brochure = saisie.getBrochure();
	}
	
	//**********//
	// Méthodes //
	//**********//
	
	/**
	 * Réalise une saisie utilisateur, avec un controle de saisie pour qu'il ne saisisse qu'un entier et pas autre chose
	 * @return Le nombre saisi [entier]
	 */
	private int int_saisir(){
        int nb = 0;						//Variable qui contiendra la saisie de l'utilisateur
        OK = false;
        
        while (!OK)
        {
        	//On essaye de caster la saisie en entier, et OK = vrai, sinon, on lève une exception et on affiche un message d'erreur
            try{
                nb = saisie.nextInt();
                OK = true;
            } catch (Exception ex) {
                String s = saisie.next();
                System.out.println("L'erreur suivante s'est produite : " + ex.toString() + "\nSaisie à refaire !");
            }
        }//fin while
        return nb;
    }//fin int_saisir()
	
	/**
	 * Réalise la saisie du département, et vérifie que le département saisi existe dans la brochure
	 * @return Le numéro du département saisi [entier]
	 */
	private void saisie_departement()
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
            //Tant que i est inférieur au nombre de tarif dans la brochure et
            //qu'on a pas trouvé le tarif correspondant au département saisi
            while (i < brochure.getListeTarifs().size() && OK == false)
            {
            	//si le département saisi correspond au tarif à l'indice i, on met OK à vrai, et on initialise la VG departement avec la saisie effectuée et controlée
                if(dptmt == brochure.getListeTarifs().get(i).getDepartement())
                {
                    OK = true;
                    departement = dptmt;
                }//fin if
                i++;					//Incrémentation de i pour parcourir la brochure
            }//fin while (i < liste_departements.length && depart_trouve == false)
            //Si on n'a pas trouvé le numéro saisie, dans la liste, on affiche un message et on redemande de saisir le numéro du département
            if (OK == false)
            {
                System.out.println("Le département saisi n'est pas pris en charge !");
            }//fin if
        } while(OK == false);
    }//Fin saisie_departement()
	
	/**
	 * Réalise la saisie de la distance parcourue, et vérifiant que celle-ci est supérieure à 0
	 * @return La distance parcourue saisie [entier]
	 */
	private void saisie_distance()
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
                distance  = dist;
            }//fin else
        } while(OK == false);
        //Saisie de la durée, en minutes
    }//Fin saisie_distance()
	
	/**
	 * Réalise la saisie de la durée, et vérifie que celle-ci est supérieure à 0
	 * @return La durée saisie, en minutes [entier]
	 */
	private void saisie_duree()
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
                duree = dureeSaisie;
            }//fin else
        } while(OK == false);
    }//Fin saisie_duree()
	
	/**
	 * Affiche la liste des moments
	 * (Ex: "Allé simple jour semaine")
	 */
	private void show_moments()
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
	private void saisie_moment()
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
                moment = momentSaisi;		//et on initialise la VG duree avec la saisie effectuée et controlée
            }//fin if
            else							//Autrement, c'est que le nombre saisi est incorrect
            {
            	System.out.println("Le nombre saisi est incorrect !");
                OK = false;					//Donc on passe le bouleen à faux
            }//fin else
        }while(OK == false);
    }//Fin saisie_moment()
	
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
	    		valeur = brochure.getListeTarifs().get(check_indice_dep(dep)).getKmAlleRetourJSem();
	    		break;
	    	case 1:
	    		valeur = brochure.getListeTarifs().get(check_indice_dep(dep)).getKmAlleSimpleJSem();
	    		break;
	    	case 2:
	    		valeur = brochure.getListeTarifs().get(check_indice_dep(dep)).getKmAlleRetourNuitDim();
	    		break;
	    	default:
	    		valeur = brochure.getListeTarifs().get(check_indice_dep(dep)).getKmAlleSimpleNuitDim();
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
    }//Fin check_tarif_jn(
	
	
	
	public double calculPrixAPayer()
	{
		saisie_departement();
		saisie_distance();
		saisie_duree();
		saisie_moment();
		if(duree < 60)
		{
			return (brochure.getListeTarifs().get(check_indice_dep(departement)).getPriseEnCharge() + distance * check_tarif_km(departement, moment));
		}//fin if
		else
		{
			return (brochure.getListeTarifs().get(check_indice_dep(departement)).getPriseEnCharge() + distance * check_tarif_km(departement, moment) + ((duree - (duree % 60))/60) * check_tarif_jn(departement, moment));
		}//fin else
	}//Fin calculPrixAPayer()
	
	/**
	 * Ferme l'objet de classe Scanner
	 */
	public void closeScanner()
	{
		saisie.close();
	}//Fin closeScanner()
}
