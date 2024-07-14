
package model;


/**
 * Classe conçue pour interpréter graphiquement une séquence de caractère en utilisant une tortue.
 * Chaque caractère de la séquence représente une action spécifique que la tortue doit exécuter,
 * permettant ainsi de dessiner des formes complexes. Les actions incluent avancer, tourner, et gérer l'état de la tortue, 
 * parmi d'autres, pour traduire la séquence en dessins graphiques.
 */

public class Interpretation{
	
	private Tortue tortue ; 
	private String sequence ; 
	
	/**
     * Constructeur par défaut. Crée une instance sans tortue ni séquence définies.
     */
	public Interpretation() {}
	
	 /**
     * Constructeur avec une tortue et une séquence spécifiées.
     * 
     * @param tortue L'instance de Tortue à utiliser pour le dessin.
     * @param sequence La séquence de caractères à interpréter pour le dessin.
     */
	public Interpretation(Tortue tortue , String sequence) {
		
		this.sequence =sequence; 
		this.tortue = tortue;
	}
	
	
	/**
	 * Définit l'instance de Tortue pour cet objet.
	 * 
	 * @param tortue l'instance de Tortue à affecter à cet objet
	 */
	public void setTortue(Tortue tortue) {
	    this.tortue = tortue;
	}

	/**
	 * Définit la séquence de caractéres pour cet objet.
	 * 
	 * @param sequence la chaîne représentant la séquence de caractéres à affecter
	 */
	public void setSequence(String sequence) {
	    this.sequence = sequence;
	}
	/**
	 * Récupère l'instance de Tortue associée à cet objet.
	 * 
	 * @return l'instance de Tortue actuellement associée à cet objet
	 */
	public Tortue getTortue() {
	    return this.tortue;
	}

	/**
	 * Récupère la séquence de caractéres associée à cet objet.
	 * 
	 * @return la chaîne représentant la séquence de caractéres
	 */
	public String getSequence() {
	    return this.sequence;
	}

	
	
	
	 /**
     * Interprète la séquence de caractères pour dessiner avec la tortue.
     * Chaque caractère de la séquence correspond à une action spécifique de la tortue.
     * 
     * @param nbIterations Le nombre d'itérations pour le dessin, influençant certaines actions de la tortue.
     */
	public void dessiner(int nbIterations) {
	    for(char c : this.sequence.toCharArray()) {
	        if (c == 'F') {
	            this.tortue.avancer(nbIterations);
	        } else if (c == '+') {
	            this.tortue.tournerGauche();
	        } else if (c == '-') {
	            this.tortue.tournerDroite();
	        } else if (c == '[') {
	            this.tortue.empilerEtat();
	        } else if (c == ']') {
	            this.tortue.depilerEtat();
	        } else if (c == '&') {
	            this.tortue.pivoterVersLeBas();
	        } else if (c == '^') {
	            this.tortue.pivoterVersLeHaut();
	        } else if (c == '/') {
	            this.tortue.basculerADroite();
	        } else if (c == '\\') {
	            this.tortue.basculerAGauche();
	        } else if (c == '|') {
	            this.tortue.demiTour();
	        }
	    }
	}
	
}


