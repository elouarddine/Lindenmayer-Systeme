package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

import controle.Controleur;
import controle.Mode;

/**
 * La classe AccueilPanel est un conteneur principal pour l'écran d'accueil
 * de l'application. Elle organise et affiche les composants initiaux de l'interface utilisateur,
 * comme les panneaux de bienvenue et les logos.
 */
public class AccueilPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private Controleur controleur ; 
	
	/**
	 * Construit le panel d'accueil de l'application. Ce panel agence les différents composants
	 * de l'interface utilisateur qui sont présentés à l'utilisateur lors du démarrage de l'application.
	 *
	 * @param controleur Le contrôleur qui gère les interactions entre la vue et le modèle.
	 * @param largeurDufenetre La largeur préférée pour le panel d'accueil, influençant sa présentation.
	 * @param hauteurDufenetre La hauteur préférée pour le panel d'accueil, influençant sa présentation.
	 */
	public AccueilPanel(Controleur controleur , int largeurDufenetre , int hauteurDufenetre) {
		
		controleur.setMode(Mode.ACCEUIL);
		this.controleur = controleur ; 
		
		this.setLayout(new BorderLayout()); // Espacement horizontal et vertical
		
		Panels panel1 = new Panels(this.controleur);
		this.add(panel1, BorderLayout.CENTER);
		
		Panels panel3 = new Panels("logo");
		panel3.setPreferredSize(new Dimension(largeurDufenetre / 2 - 400, hauteurDufenetre / 5));
		this.add(panel3, BorderLayout.SOUTH);
		
		
		
	}
	
	
}



