
package model;

/**
 * Interface définissant les actions d'une tortue utilisée dans la génération de structures de plantes par notre L-système . 
 * La tortue interprète la séquence générée par notre L-système  pour effectuer des déplacements et réaliser des dessins.
 */
public interface Tortue {
	
	/**
	 * Fait avancer la tortue d'une distance définie par le nombre d'itérations spécifié, en traçant une ligne.
	 * 
	 * @param nbIterations Le nombre d'itérations qui détermine la distance d'avancement.
	 */
	public void avancer(int nbIterations);
	/**
     * Empile l'état actuel de la tortue (position et orientation).
     */
	public void empilerEtat();
	 /**
     * Dépile le dernier état sauvegardé de la tortue et le restaure comme état courant.
     */
	public void depilerEtat();
	 /**
     * Tourne la tortue à gauche.
     */
	public void tournerGauche() ;
	/**
     * Tourne la tortue à droite.
     */
	public void tournerDroite() ;
	 /**
     * Oriente la tortue vers le haut.
     */
	public void pivoterVersLeHaut() ;
	/**
     * Oriente la tortue vers le bas.
     */
	public void pivoterVersLeBas();
	/**
     * Bascule l'orientation de la tortue à droite sans changer sa position.
     */
	public void basculerADroite();
	 /**
     * Bascule l'orientation de la tortue à gauche sans changer sa position.
     */
	public void basculerAGauche() ;
	 /**
     * Fait effectuer un demi-tour à la tortue.
     */
	public void demiTour();
}
