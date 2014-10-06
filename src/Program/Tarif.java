package Program;
/**
 * Représente l'ensembles des tarifs correspondants à un département
 * 
 * @author Takiguchi
 * @version 1.0
 *
 */
public class Tarif {
	
	//***********//
	// Attributs //
	//***********//
	
	private int departement;
	private double priseEnCharge;
	private double kmAlleRetourJSem;
	private double kmAlleSimpleJSem;
	private double kmAlleRetourNuitDim;
	private double kmAlleSimpleNuitDim;
	private double tarifHoraireJSem;
	private double tarifHoraireNuitDim;
	
	//************//
	// Accesseurs //
	//************//
	
	/**
	 * Accesseur du numéro du département
	 * @return Le numéro du département [entier]
	 */
	public double getDepartement()
	{
		return departement;
	}
	
	/**
	 * Accesseur du montant de la prise en charge
	 * @return Le montant de prise en charge [décimal]
	 */
	public double getPriseEnCharge()
	{
		return priseEnCharge;
	}
	
	/** 
	 * Accesseur du tarif kilométrique, d'un allé-retour lors d'un jour de semaine
	 * @return Le tarif kilométrique allé-retour d'un jour de semaine [décimal]
	 */
	public double getKmAlleRetourJSem()
	{
		return kmAlleRetourJSem;
	}
	
	/**
	 * Accesseur du tarif kilométrique, d'un allé simple lors d'un jour de semaine
	 * @return Le tarif kilométrique allé simple d'un jour de semaine [décimal]
	 */
	public double getKmAlleSimpleJSem()
	{
		return kmAlleSimpleJSem;
	}
	
	/**
	 * Accesseur du tarif kilométrique, d'un allé-retour de nuit ou le dimanche
	 * @return Le tarif kilométrique allé-retour de nuit ou le dimanche [décimal]
	 */
	public double getKmAlleRetourNuitDim()
	{
		return kmAlleRetourNuitDim;
	}
	
	/**
	 * Accesseur du tarif kilométrique, d'un allé simple de nuit ou le dimanche
	 * @return Le tarif kilométrique allé simple de nuit ou le dimanche [décimal]
	 */
	public double getKmAlleSimpleNuitDim()
	{
		return kmAlleSimpleNuitDim;
	}
	
	/**
	 * Accesseur du tarif horaire lors d'un jour de semaine
	 * @return Le tarif horaire lors d'un jour de semaine [décimal]
	 */
	public double getTarifHoraireJSem()
	{
		return tarifHoraireJSem;
	}	
	
	/**
	 * Accesseur du tarif horaire de nuit ou le dimanche
	 * @return Le tarif horaire de nuit ou le dimanche [décimal]
	 */
	public double getTarifHoraireNuitDim()
	{
		return tarifHoraireNuitDim;
	}

	//***************//
	// Constructeurs //
	//***************//
	
	/**
	 * Constructeur de la classe tarif, avec initialisation à partir de paramètres
	 * @param Numéro du département [int]
	 * @param Montant de la prise en charge [double]
	 * @param Tarif kilométrique allé-retour lors d'un jour de semaine [double]
	 * @param Tarif kilométrique allé simple lors d'un jour de semaine [double]
	 * @param Tarif kilométrique allé-retour de nuit ou le dimanche [double]
	 * @param Tarif kilométrique allé simple de nuit ou le dimanche [double]
	 * @param Tarif horaire d'un jour de semaine [double]
	 * @param Tarif horaire de nuit ou le dimanche [double]
	 */
	public Tarif(int departement, double priseEnCharge, double kmAlleRetourJSem, double kmAlleSimpleJSem, double tarifHoraireJSem, double kmAlleRetourNuitDim, double kmAlleSimpleNuitDim, double tarifHoraireNuitDim)
	{
		this.departement = departement;
		this.priseEnCharge = priseEnCharge;
		this.kmAlleRetourJSem = kmAlleRetourJSem;
		this.kmAlleSimpleJSem = kmAlleSimpleJSem;
		this.kmAlleRetourNuitDim = kmAlleRetourNuitDim;
		this.kmAlleSimpleNuitDim = kmAlleSimpleNuitDim;
		this.tarifHoraireJSem = tarifHoraireJSem;
		this.tarifHoraireNuitDim = tarifHoraireNuitDim;
	}
}
