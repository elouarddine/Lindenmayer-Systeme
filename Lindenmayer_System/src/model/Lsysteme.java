package model;

import java.util.ArrayList;

import util.AbstractModeleEcoutable;

/**
 * Classe représentant un Systeme de Lindenmayer pour la génération de formes fractales de plantes
 * en utilisant une grammaire formelle. Hérite de AbstractModeleEcoutable pour la notification des écouteurs en cas de modification.
 */
public class Lsysteme extends AbstractModeleEcoutable{
	
	private String axiom;   
	private ArrayList<Regle> regles ;
	private int nombreIteration ; 
	
	
	/**
	 * Constructeur par défaut. Initialise un L-système sans axiome, règles et avec un nombre d'itérations à 0.
	 */
	public Lsysteme(){};
	
	
	/**
	 * Constructeur de Lsysteme avec paramètres spécifiés.
	 * 
	 * @param axiom L'axiome initial du L-système.
	 * @param regle La liste des règles de production.
	 * @param nbIt Le nombre d'itérations pour générer le L-système.
	 * 
	 * @requires axiom != null;
	 * @requires regle != null;
	 * @requires nbIt >= 0;
	 */
	public Lsysteme(String axiom ,  ArrayList<Regle> regle , int nbIt) {
		
		this.nombreIteration = nbIt; 
		this.regles = regle ; 
		this.axiom = axiom ; 
		
	}
	
	/**
	 * Retourne l'axiome du L-système.
	 * 
	 * @return L'axiome actuel.
	 * @ensures result != null;
	 */
	public String getAxiome() { return this.axiom; }
	
	/**
	 * Retourne la liste des règles de production.
	 * 
	 * @return La liste des règles.
	 * @ensures result != null;
	 */
	public ArrayList<Regle>  getRegles() { return this.regles; }
	
	
	/**
	 * Retourne le nombre d'itérations défini pour ce L-système.
	 * 
	 * @return Le nombre d'itérations.
	 */
	
	public int getNombreIteration() { return this.nombreIteration; }
	
	/**
	 * Définit l'axiome du L-système.
	 * 
	 * @param axiome Le nouvel axiome à définir.
	 * @requires axiome != null;
	 */
	public void setAxiome(String axiome) { this.axiom = axiome; }
	
	/**
	 * Définit les règles de production pour ce L-système.
	 * 
	 * @param regles La nouvelle liste des règles à utiliser.
	 * @requires regles != null;
	 */
	public void setRegles(ArrayList<Regle> regles) { this.regles = regles; }
	
	/**
	 * Définit le nombre d'itérations pour la génération du L-système.
	 * 
	 * @param nombreIteration Le nouveau nombre d'itérations.
	 * @requires nombreIteration >= 0;
	 */
	public void setNombreIteration(int nombreIteration) { this.nombreIteration = nombreIteration; }
	
	
	/**
	 * Fournit une représentation sous forme de chaîne de caractères du L-système.
	 * 
	 * @return Une chaîne représentant l'état actuel du L-système.
	 */
	public String toString() {
		return "\nAxiome : "+this.axiom+
				"\nRégle : "+this.regles;
	}
	
	
	/**
	 * Remplace un caractère donné par sa traduction, ou par lui-même si la traduction est vide.
	 * 
	 * @param currentChar Le caractère à remplacer.
	 * @return La chaîne résultante après remplacement.
	 * @ensures Si le caractère correspond à une règle, result.equals(regle.getTraduction()) est vrai. Sinon, result.equals(String.valueOf(currentChar)) est vrai.
	 * @ensures result != null;
	 */
	public String replace(char currentChar) {
		
		StringBuilder remplacement = new StringBuilder();
		
		for (Regle regle : this.regles) {
			
			if (currentChar == regle.getVariable() && !regle.getTraduction().isEmpty()) {
				
				remplacement.append(regle.getTraduction());
				return remplacement.toString(); 
			}
		}
		
		remplacement.append(currentChar);
		return remplacement.toString();
	}
	
	
	
	/**
	 * Génère une séquence de caractères en appliquant les règles de production sur l'axiome pour le nombre d'itérations spécifié.
	 * Notifie les écouteurs du changement.
	 * 
	 * @return L'axiome final après application des itérations.
	 * @ensures result != null;
	 */
	public String generate() {
		
		for (int i = 0; i <= this.nombreIteration ; i++) { 
			
			StringBuilder nouveauAxiome = new StringBuilder();
			
			for(char caractere : this.axiom.toCharArray()){
				
				nouveauAxiome.append(replace(caractere));
			}
			
			this.axiom = nouveauAxiome.toString();
			
		}
		
		fireChangement(); 
		return this.axiom;
	}
	
	
}










