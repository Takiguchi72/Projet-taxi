package GUI;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JTextField;

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
}//Fin classe