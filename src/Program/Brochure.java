package Program;
/**
 * Représente la brochure tarifaire du projet Taxi_project
 * 
 * @author Takiguchi
 * @version 2.0
 *
 */

import java.util.ArrayList;
import java.util.List;

public class Brochure {
	//***********//
	// Attributs //
	//***********//
	
	private String nom;
	private List<Tarif> listeTarifs;
	
	//************//
	// Accesseurs //
	//************//
	
	/**
	 * Accesseur du nom de la brochure
	 * @return Le nom de la brochure [String]
	 */
	public String getNom()
	{
		return nom;
	}
	
	/**
	 * Accesseur de la liste des tarifs de la brochure
	 * @return La liste des tarifs [List<Tarif>]
	 */
	public List<Tarif> getListeTarifs()
	{
		return listeTarifs;
	}	
	
	//***************//
	// Constructeurs //
	//***************//
	
	/**
	 * Constructeur par défaut de la brochure, avec des valeurs par défaut
	 */
	public Brochure()
	{
		nom = "Brochure du Taxi";
		listeTarifs = new ArrayList<Tarif>();
	}
	
	/**
	 * Constructeur de la brochure avec des paramètres
	 * @param Le nom de la brochure [String]
	 * @param Liste des tarifs [LinkedList<Tarifs>]
	 */
	public Brochure(String nom, List<Tarif> listeTarifs)
	{
		this.nom = nom;
		this.listeTarifs = listeTarifs;
	}
	
	/**
	 * Constructeur de la brochure à partir d'une brochure déjà existante
	 * @param La brochure servant à initialiser la nouvelle brochure [Brochure]
	 */
	public Brochure(Brochure brochure)
	{
		this.nom = brochure.getNom();
		this.listeTarifs = brochure.getListeTarifs();
	}
	
	//**********//
	// Méthodes //
	//**********//
	
	/**
	 * Ajoute un nouveau tarif à la liste, à partir d'un objet instancié de la classe Tarif
	 * @param Instance de la classe Tarif
	 */
	public void AddTarif(Tarif tarif)
	{
		listeTarifs.add(tarif);
	}//Fin AddTarif(Tarif tarif)
	
	/**
	 * Supprime un tarif à partir d'un index
	 * @param index
	 */
	public void RmTarifByIndex(int index)
	{
		listeTarifs.remove(index);
	}//Fin RmTarifByIndex(int index)
}
