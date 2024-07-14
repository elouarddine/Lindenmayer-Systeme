package view;

import java.awt.Graphics2D;
import java.util.Stack;

import model.Tortue;

/**
 * Implémentation de l'interface Tortue pour une tortue en deux dimensions.
 * Cette classe permet de manipuler une tortue 2D pour dessiner des formes complexes sur une surface graphique en deux dimensions.
 * Elle utilise la bibliothèque graphique Java 2D pour le dessin, offrant une méthode riche et accessible de représentation graphique des structures générées par le L-système en deux dimensions.
 */
public class Tortue2D implements Tortue{
	
	private double dx ,dy ,angle ; 
	private double direction = -90; 
	private Stack<double[]> stack =  new Stack<>(); 
	private Graphics2D g2d ; 
	
	
	/**
	 * Constructeur par défaut. Initialise une tortue 2D sans position ni direction spécifiques.
	 */
	public Tortue2D() {}
	/**
	 * Constructeur avec angle spécifié.
	 * 
	 * @param angle L'angle initial de rotation de la tortue en degrés.
	 */
	public Tortue2D(double angle) {
		this.angle = angle;
	}
	
	/**
	 * Récupère la valeur actuelle de dx, représentant le déplacement horizontal de l'objet.
	 * 
	 * @return la valeur actuelle de dx
	 */
	public double getDx() {
	    return this.dx;
	}

	/**
	 * Récupère la valeur actuelle de dy, représentant le déplacement vertical de l'objet.
	 * 
	 * @return la valeur actuelle de dy
	 */
	public double getDy() {
	    return this.dy;
	}

	/**
	 * Récupère l'angle courant de l'objet en degrés.
	 * 
	 * @return l'angle actuel en degrés
	 */
	public double getAngle() {
	    return this.angle;
	}

	/**
	 * Récupère la direction actuelle de l'objet, souvent utilisée pour calculer la trajectoire ou l'orientation.
	 * 
	 * @return la valeur de la direction actuelle
	 */
	public double getDirection() {
	    return this.direction;
	}
	/**
	 * Définit la valeur de dx, qui représente le déplacement horizontal de l'objet.
	 * 
	 * @param dx la nouvelle valeur pour le déplacement horizontal
	 */
	public void setDx(double dx) {
	    this.dx = dx;
	}

	/**
	 * Définit la valeur de dy, qui représente le déplacement vertical de l'objet.
	 * 
	 * @param dy la nouvelle valeur pour le déplacement vertical
	 */
	public void setDy(double dy) {
	    this.dy = dy;
	}

	/**
	 * Réinitialise la direction de la tortue à -90 degrés.
	 */
	public void setDirection()
	{this.direction = -90;}
	public void setAngle(double angle){this.angle = angle;}
	
	
	
	
	public Graphics2D getG2d(){
		return this.g2d; 
	}
	
	public void setG2d(Graphics2D g2d){
		this.g2d = g2d; 
	}
	
	
	/**
	 * Fait avancer la tortue en dessinant une ligne dans sa direction actuelle.
	 * La distance parcourue diminue avec le nombre d'itérations.
	 * 
	 * @param nbIterations Le nombre d'itérations, influant sur la distance parcourue.
	 */
	public void avancer(int nbIterations) {
		
		float distance = (float) (12.5 / (1 + 0.5 * nbIterations));
		
		double newX = dx + distance * Math.cos(Math.toRadians(direction));
		double newY = dy + distance * Math.sin(Math.toRadians(direction));
		
		getG2d().drawLine((int) dx, (int) dy, (int) newX, (int) newY);
		
		dx = newX;
		dy = newY;
		
	}
	/**
	 * Tourne la tortue à gauche selon l'angle spécifié.
	 */
	public void tournerGauche() {
		
		direction -= getAngle();
		
	}
	/**
	 * Tourne la tortue à droite selon l'angle spécifié.
	 */
	public void tournerDroite() {
		
		direction += getAngle();
		
	}
	
	/**
	 * Empile l'état actuel de la tortue (position et direction).
	 */
	public void empilerEtat() {
		
		stack.push(new double[]{this.dx, this.dy, direction});
		
	}
	
	/**
	 * Dépile et restaure le dernier état sauvegardé de la tortue.
	 */
	public void depilerEtat() {
		
		if (!stack.isEmpty()) {
			double[] etat = stack.pop();
			dx = etat[0];
			dy = etat[1];
			direction = etat[2];
		}
	}
	
	@Override
	public void pivoterVersLeHaut() {}
	@Override
	public void pivoterVersLeBas() {}
	@Override
	public void basculerADroite() {}
	@Override
	public void basculerAGauche() {}
	@Override
	public void demiTour() {}
	
}


