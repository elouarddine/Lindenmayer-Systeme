
package model;

/**
 * Représente une règle de production dans un L-système. Chaque règle définit comment un symbole doit être remplacé par une chaîne de caractères (traduction) lors de la génération du L-système.
 */
public class Regle {
	
	private char symbole;           
	private String traduction; 
	public Regle(){};
	
	
	/**
	 * Constructeur de Regle avec un symbole et sa traduction spécifiés.
	 * 
	 * @param c Le symbole de la règle.
	 * @param traduction La traduction du symbole.
	 * 
	 * @requires traduction != null;
	 */
	public Regle(char c , String traduction ) {
		
		this.symbole = c;
		this.traduction = traduction;
	}
	
	/**
	 * Retourne le symbole de cette règle.
	 * 
	 * @return Le symbole.
	 */
	public char getVariable() {
		
		return this.symbole;
		
	}
	
	
	/**
	 * Retourne la traduction associée au symbole de cette règle.
	 * 
	 * @return La traduction du symbole.
	 * @ensures result != null;
	 */
	public String  getTraduction() {
		
		return this.traduction;
	}
	
	
	/**
	 * Fournit une représentation sous forme de chaîne de caractères de la règle.
	 * 
	 * @return Une chaîne de caractères représentant la règle sous la forme "(symbole ==> traduction)".
	 */
	public String toString() {
		
		return "("+this.symbole + " ==> " +this.traduction.toString() + ")";
		
	}
	
}
