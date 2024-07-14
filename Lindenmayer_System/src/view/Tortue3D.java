
package view;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import com.jogamp.opengl.GL2;

import model.Tortue;

import java.util.Stack;


/**
 * Implémentation de l'interface Tortue pour une tortue en trois dimensions.
 * Cette classe permet de manipuler une tortue 3D pour dessiner des formes complexes dans un espace tridimensionnel.
 * Elle utilise JOGL, une bibliothèque graphique 3D pour Java, qui fournit une interface directe à l'API OpenGL. Cela permet de réaliser des rendus graphiques 3D avancés et interactifs.
 */

public class Tortue3D implements Tortue{
	
	private double angle ; 
	private Vector3f position , direction;
	
	private Quaternionf orientation;
	private Stack<Vector3f> positionStack =  new Stack<>();
	private Stack<Quaternionf> orientationStack =  new Stack<>();
	private GL2 gl ; 
	
	/**
	 * Constructeur par défaut. Initialise une tortue 3D sans position, direction ou orientation spécifiques.
	 */
	public Tortue3D() {}
	/**
	 * Constructeur avec angle spécifié pour la rotation.
	 * 
	 * @param angle L'angle de rotation initial en degrés.
	 */
	public Tortue3D(double angle) {
		
		this.orientation = new Quaternionf();
		this.angle = angle ; 
		
	}
	/**
	 * Récupère l'angle courant de l'objet en degrés.
	 * 
	 * @return l'angle actuel de l'objet en degrés.
	 */
	public double getAngle() {
	    return this.angle;
	}

	/**
	 * Récupère la position actuelle de l'objet sous forme de vecteur 3D.
	 * 
	 * @return l'objet Vector3f représentant la position actuelle.
	 */
	public Vector3f getPosition() {
	    return this.position;
	}

	/**
	 * Récupère l'orientation actuelle de l'objet sous forme de quaternion.
	 * 
	 * @return l'objet Quaternionf représentant l'orientation actuelle.
	 */
	public Quaternionf getOrientation() {
	    return this.orientation;
	}

	/**
	 * Récupère l'instance actuelle de GL2 utilisée pour le rendu graphique.
	 * 
	 * @return l'instance GL2 actuelle.
	 */
	public GL2 getGl() {
	    return this.gl;
	}

	/**
	 * Réinitialise l'orientation de l'objet à une nouvelle instance de Quaternionf sans rotation.
	 */
	public void setOrientation() {
	    this.orientation = new Quaternionf();
	}

	/**
	 * Définit la position de l'objet avec un vecteur 3D initial.
	 * 
	 * @param positionInitial le vecteur 3D qui définit la nouvelle position de l'objet.
	 */
	public void setPosition(Vector3f positionInitial) {
	    this.position = positionInitial;
	}

	/**
	 * Définit l'angle de l'objet en degrés.
	 * 
	 * @param angle le nouvel angle en degrés pour l'objet.
	 */
	public void setAngle(double angle) {
	    this.angle = angle;
	}

	/**
	 * Définit l'instance GL2 utilisée pour le rendu graphique de l'objet.
	 * 
	 * @param gl la nouvelle instance GL2 pour le rendu graphique.
	 */
	public void setGl(GL2 gl) {
	    this.gl = gl;
	}

	
	/**
	 * Fait avancer la tortue en traçant une ligne dans sa direction actuelle. Utilise des fonctions graphiques 3D pour le rendu des lignes, permettant la visualisation de la trajectoire de la tortue dans un espace tridimensionnel.
	 * 
	 * @param nbIterations Le nombre d'itérations, influant sur la distance parcourue.
	 */
	public void avancer(int nbIterations) {
		
		float distance = (float) (8 / (1.5 + 0.5 * nbIterations));
		
		Vector3f oldPosition = new Vector3f(this.position);
		this.direction = new Vector3f(0.0f, 0.0f, 1.0f);
		
		this.direction.rotate(this.orientation); 
		this.direction.mul(distance); 
		this.position.add(direction); 
		
		getGl().glBegin(GL2.GL_LINES);
		getGl().glVertex3f(oldPosition.x, oldPosition.y, oldPosition.z); 
		getGl().glVertex3f(this.position.x, this.position.y, this.position.z); 
		getGl().glEnd(); 
	}
	
	/**
	 * Effectue une rotation de la tortue autour d'un axe spécifié par un certain angle. Utilise un quaternion pour cette rotation,
	 * ce qui est une façon pratique et stable de gérer les orientations en 3D, évitant les problèmes rencontrés avec d'autres méthodes de rotation.
	 * La méthode ajuste l'orientation actuelle de la tortue de manière fluide. Elle est privée, car elle supporte les fonctionnalités de rotation exposées publiquement.
	 *
	 * @param axe L'axe autour duquel la tortue doit tourner.
	 * @param angle L'angle de rotation, en degrés.
	 */
	public void tourner(Vector3f axe, double angle) {
		
		Quaternionf rotation = new Quaternionf().rotateAxis((float)Math.toRadians(angle), axe);
		this.orientation.mul(rotation); 
	}
	
	
	/**
	 * Tourne la tortue à gauche autour de l'axe Y par un angle défini par l'attribut 'angle'.
	 */
	public void tournerGauche() {
		tourner(new Vector3f(0.0f, 1.0f, 0.0f), -this.angle); 
	}
	/**
	 * Tourne la tortue à droite autour de l'axe Y par un angle défini par l'attribut 'angle'.
	 */
	public void tournerDroite() {
		tourner(new Vector3f(0.0f, 1.0f, 0.0f), this.angle); 
	}
	/**
	 * Pivote la tortue vers le haut en effectuant une rotation autour de l'axe X par un angle défini par l'attribut 'angle'.
	 */
	public void pivoterVersLeHaut() {
		
		tourner(new Vector3f(1.0f, 0.0f, 0.0f), this.angle); 
	}
	
	/**
	 * Pivote la tortue vers le bas en effectuant une rotation autour de l'axe X par un angle défini par l'attribut 'angle'.
	 */
	public void pivoterVersLeBas() {
		
		tourner(new Vector3f(1.0f, 0.0f, 0.0f), -this.angle); 
	}
	
	/**
	 * Bascule la tortue à droite en effectuant une rotation autour de l'axe Z par un angle défini par l'attribut 'angle'.
	 */
	public void basculerADroite() {
		
		tourner(new Vector3f(0.0f, 0.0f, 1.0f), this.angle); 
	}
	
	/**
	 * Bascule la tortue à gauche en effectuant une rotation autour de l'axe Z par un angle défini par l'attribut 'angle'.
	 */
	public void basculerAGauche() {
		
		tourner(new Vector3f(0.0f, 0.0f, 1.0f), -this.angle); 
	}
	/**
	 * Effectue un demi-tour avec la tortue en tournant autour de l'axe Y par 180 degrés.
	 */
	public void demiTour() {
		
		tourner(new Vector3f(0.0f, 1.0f, 0.0f), 180);
	}
	
	/**
	 * Empile l'état actuel de la tortue (position et orientation) pour pouvoir la restaurer ultérieurement.
	 */
	public void empilerEtat() {
		this.positionStack.push(new Vector3f(this.position));
		this.orientationStack.push(new Quaternionf(this.orientation));
	}
	/**
	 * Dépile et restaure la derniere état sauvegardé de la tortue (position et orientation).
	 */
	public void depilerEtat() {
		if (!this.positionStack.isEmpty() && !this.orientationStack.isEmpty()) {
			this.position = this.positionStack.pop();
			this.orientation = this.orientationStack.pop();
		}
	}
	
	
	
}

