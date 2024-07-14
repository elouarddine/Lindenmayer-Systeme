
package view;


import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controle.Controleur;
import controle.Mode;

/**
 * La classe MainFrame représente la fenêtre principale de l'application des L-systèmes.
 * Elle gère l'affichage des différents panels de l'application en utilisant un layout de type CardLayout,
 * permettant ainsi de passer facilement entre les différentes vues (Accueil, Configuration, etc.).
 */
public class MainFrame extends JFrame{
	
	
	private static final long serialVersionUID = 1L;
	public static final int Largeur = 1500;
	public static final int Hauteur = 800;
	private Controleur controleur ; 
	private JPanel cardPanel ;
	private CardLayout card ; 
	
	
	/**
	 * Construit la fenêtre principale de l'application, initialisant le layout, les dimensions et
	 * les composants de base, y compris la barre de menu et le panel principal pour l'affichage des vues.
	 *
	 * @param controleur Le contrôleur qui fait le lien entre les vues et le modèle de l'application.
	 *
	 * @requires controleur != null;
	 * @ensures this.controleur == controleur;
	 */
	public MainFrame(Controleur controleur) {
		
		this.controleur = controleur;
		
		this.card = new CardLayout();
		this.cardPanel = new JPanel(card);
		
		this.setTitle("L-systèmes Application : Création et Exploration");
		this.setSize(Largeur, Hauteur);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		Panels barreDeMenu = new Panels(this, this.controleur);
		add(barreDeMenu);
		initialiseCardPanel();
		add(this.cardPanel, BorderLayout.CENTER);
		
		this.setVisible(true);
	}
	
	
	@SuppressWarnings("unused")
	public MainFrame(Controleur controleur, Mode mode , TypeAffichage typeAffichage) {
		
		this.setTitle("L-systèmes Application : Création et Exploration");
		this.setSize(Largeur, Hauteur);
		  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLocationRelativeTo(null);

	        Panels barreDeMenu = new Panels(this, controleur);
	        add(barreDeMenu, BorderLayout.NORTH); 

	        ConfigurationPanel configurationPanel = new ConfigurationPanel(controleur, mode, typeAffichage);
	        add(configurationPanel, BorderLayout.CENTER); 

	        setVisible(true);
	    }
	

	
	
	
	/**
	 * Initialise le panel principal utilisant un CardLayout. Cette méthode prépare l'application
	 * à afficher différentes vues comme l'écran d'accueil et l'écran de configuration.
	 *
	 * @ensures cardPanel != null;
	 * @ensures card != null;
	 */
	private void initialiseCardPanel() {
		
		AccueilPanel accueilContainer = new AccueilPanel(this.controleur, Largeur, Hauteur); 
		this.cardPanel.add(accueilContainer, "Accueil");
	}
	
	
	
	
	/**
	 * Affiche la vue de configuration selon le type d'affichage sélectionné (2D ou 3D).
	 * Cette méthode crée un nouveau panel de configuration et l'ajoute au CardLayout principal.
	 *
	 * @param typeAffichage Le type d'affichage pour lequel la vue de configuration doit être préparée.
	 *
	 * @requires typeAffichage != null;
	 * @ensures cardPanel.contains(configurationPanel);
	 */
	public void configurationView(TypeAffichage typeAffichage) {
		
		ConfigurationPanel configurationPanel = new ConfigurationPanel(this.controleur, typeAffichage , Mode.GRAPHIQUE);
		this.cardPanel.add(configurationPanel, "Configuration");
		this.card.show(cardPanel, "Configuration");
	}
	
	
	
	
	
	/**
	 * Permet de retourner à la vue précédemment affichée, basée sur le mode spécifié.
	 * Cette méthode est principalement utilisée pour naviguer entre les différentes vues de l'application.
	 * Elle gère la logique de navigation en affichant la vue correspondante au mode passé en paramètre.
	 *
	 * @param mode Le mode qui détermine la vue à laquelle retourner. Ce mode doit correspondre à une des vues préconfigurées dans le gestionnaire de vue.
	 * @requires mode != null;  // Assure que le paramètre 'mode' n'est pas null pour éviter les erreurs à l'exécution.
	 */
	public void retournerEnArriere(Mode mode) {
	    if (Mode.ACCEUIL.equals(mode)) {
	        this.card.show(cardPanel, "Accueil");
	    }
	    else {
	        this.card.show(cardPanel, "Configuration");
	    }
	}


	public void helpView() {
    
		Panels panel = new Panels(this.controleur , 1);		
		this.cardPanel.add(panel, "Aide");
		this.card.show(cardPanel, "Aide");

	}
	
	
}



	

