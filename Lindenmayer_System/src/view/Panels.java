package view;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import controle.Controleur;
import controle.Mode;
import model.Regle;

/**
 * La classe Panels représente les différents panels de l'interface utilisateur
 * pour notre application. Elle fournit une structure pour l'affichage des
 * différents composants de l'interface, y compris la barre de menu, les panels de configuration, et les boutons.
 */
public class Panels extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private JLabel titre , logo  , labels; 
	private JTextPane  descriptionPane;
	private GridBagConstraints gbc , boutonsGbc , labelsGbc ;
	private JPanel bouttonsPanel , labelsPanel; 
	private JMenuBar barreDeMenu ; 
	private JMenu menuApropos , menuAide ; 
	private ImageIcon pen2D , pen3D, exit , startIcon , deleteIcon , exempleIcon; 
	private JTextField textField ; 
	private List<JTextField> textFields;
	private ConfigurationPanel configPanel; 
	private JTextPane leftPage;
    private JTextPane rightPage;
    private Controleur controleur;
	
	
    public Panels(Controleur controleur, int y) {
        this.controleur = controleur;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;

        leftPage = createPage(loadLeftHtmlContent()); 
        rightPage = createPage(loadRightHtmlContent()); 

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.weighty = 0.7; // Assign less vertical weight to the text panels
        add(leftPage, gbc); 

        gbc.gridx = 1;
        add(rightPage, gbc); 

        JPanel bouttonsPanel = new JPanel();
        bouttonsPanel.setBackground(Color.decode("#E8F5E9"));
        ajouterBouton(bouttonsPanel, "Retour", null, () -> this.controleur.retournerEnArriere(this.controleur.getMode()), gbc, new Color(39, 174, 96), 120, 35);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2; 
        gbc.weighty = 0.3; // Assign more vertical weight to the button panel
        gbc.fill = GridBagConstraints.BOTH; // Allow the button panel to stretch vertically
        gbc.anchor = GridBagConstraints.CENTER; 
        add(bouttonsPanel, gbc);

        setBorderAndBackground();
    }

    private JTextPane createPage(String htmlContent) {
        JTextPane page = new JTextPane();
        page.setContentType("text/html");
        page.setText(htmlContent);
        page.setOpaque(true);
        page.setEditable(false);
        page.setBorder(BorderFactory.createLineBorder(Color.decode("#73be73"), 3));
        return page;
    }

    private void setBorderAndBackground() {
        Border ligneDeBrd = BorderFactory.createLineBorder(Color.decode("#73be73"), 3);
        Border raisedBevel = BorderFactory.createRaisedBevelBorder();
        Border loweredBevel = BorderFactory.createLoweredBevelBorder();
        Border outerCompoundBorder = BorderFactory.createCompoundBorder(ligneDeBrd, raisedBevel);
        Border compoundBorder = BorderFactory.createCompoundBorder(outerCompoundBorder, loweredBevel);
        this.setBorder(compoundBorder);
        this.setBackground(Color.decode("#E8F5E9"));
    }


    private String loadLeftHtmlContent() {
        return "<html>" +
                "<body style='margin: 0; padding: 0;'>" + 
                "<h1 style='background: #4CAF50; color: #FAFAFA; padding: 10px; text-align: center;'>Panneau d'Accueil</h1>" + 
                "<div style='padding: 20px;'>" + 
                "<h3>Introduction aux Fonctionnalités</h3>" +
                "<p>Le bouton <strong>'Générer 2D'</strong> permet de générer des plantes en deux dimensions.</p>" +
                "<p>Le bouton <strong>'Générer 3D'</strong> permet de générer des plantes en trois dimensions et d'interagir avec elles en 3D.</p>" +
                "<p>En cliquant sur ces boutons, vous serez dirigé vers le <strong>Panneau de Configuration</strong>. Dans ce panneau, vous pourrez soit personnaliser votre modèle, soit choisir parmi des modèles prédéfinis.</p>" +
                "</div>" +
                "</body></html>";
    }

        
private String loadRightHtmlContent() {

	return "<html>" +
                "<body style='margin: 0; padding: 0;'>" + 
                "<h1 style='background: #4CAF50; color: #FAFAFA; padding: 10px; text-align: center;'>Panneau de Configuration</h1>" +
                "<div style='padding: 10px;'>" + 
                "<h3>Configuration</h3>" +
                "<p>Le panneau de configuration est divisé en trois parties principales :</p>" +
                "<ul>" +
                "<li><strong>Barre d'outils :</strong> Contient les options pour personnaliser votre modèle, sélectionner des modèles prêts et revenir à l'écran précédent.</li>" +
                "<li><strong>Zone de dessin :</strong> Afficher en cliquant sur l'un des options choisie dans la <strong>'Barre d'outils'</strong>. Affiche votre modèle. Utilisez le bouton droit de la souris pour tourner le modèle en 3D, et la molette pour zoomer et dézoomer.</li>" +
                "<li><strong>Panneau dynamique :</strong> Change en fonction de l'option choisie dans la <strong>'Barre d'outils'</strong>.</li>" +
                "</ul>" +
                "<h3>Personnalisation du Modèle</h3>" +
                "<p>Cliquez sur <strong>'Personnaliser votre modèle'</strong>, entrez le nombre de règles, puis remplissez les champs de texte qui apparaissent dans la <strong>'Barre Dynamique'</strong>.</p>" +
                "<h4>Exemple de Personnalisation</h4>" +
                "<p>Nombre de Règles : 2<br>" +
                "Axiome = 'X'<br>" +
                "Règle 1 : X:F[+X]F[-X]+X<br>" +
                "Règle 2 : F:FF<br>" +
                "Nombre d'itérations = 6;<br>" +
                "Angle = 20;</p>" +
                "<h3>Modèles Prêts</h3>" +
                "<p>En cliquant sur <strong>'Voir modèles prêts'</strong>, la barre dynamique affichera une collection de boutons. Chaque bouton a une image de fond représentant un modèle de plante. En sélectionnant un de ces boutons, le modèle correspondant sera affiché dans la zone de dessin. C'est un moyen rapide et visuel de découvrir les diverses configurations de plantes que vous pouvez générer.</p>" +
                "</div>" +
                "</body></html>";
    }



	
	
	/**
	 * Construit un panel principal avec une barre de menu personnalisée attachée à la fenêtre principale.
	 * Ce constructeur est typiquement utilisé pour initialiser l'interface avec les options de menu.
	 *
	 * @param frame La fenêtre principale à laquelle le panel sera attaché.
	 * @param actionListener L'écouteur d'actions pour gérer les interactions de l'utilisateur.
	 */
	public Panels(JFrame frame , Controleur controleur) {
		
		barreDeMenu = new JMenuBar();
		barreDeMenu.setBackground(Color.decode("#27AE60"));
		barreDeMenu.setForeground(Color.BLACK); 
		barreDeMenu.setFont(new Font("Arial", Font.BOLD, 14));
		
		
		menuApropos = new JMenu("À propos");
		menuApropos.setFont(new Font("Times New Roman", Font.PLAIN, 16)); 
		menuApropos.setForeground(Color.BLACK); 
		
		menuAide = new JMenu("Aide");
		menuAide.setFont(new Font("Times New Roman", Font.PLAIN, 16)); 
		menuAide.setForeground(Color.BLACK); 
		
		
		
		
		JMenuItem aide = new JMenuItem("Aide");
		stylerLeMenuItem(aide);
		
		JMenuItem aPropos = new JMenuItem("À propos de l'application"); // Corrigé "À"
		stylerLeMenuItem(aPropos);
		
		// Définir le message à afficher
		
		String message = "<html><body style='font-family: Arial, sans-serif; margin: 20px;'>" +
				"<h2 style='color: #2E8B57;'>À propos de l'Application</h2>" +
				"<p style='line-height: 1.6;'>Conçue initialement comme un projet universitaire, cette application transcende les<br>" +
				"frontières traditionnelles de la modélisation biologique. Elle offre une interface<br>" +
				"dynamique pour simuler la croissance des plantes et des bactéries, révélant la beauté cachée<br>" +
				"des processus naturels et des structures fractales. Grâce à l'utilisation des Systèmes de Lindenmayer (L-systems),<br>" +
				"et développée avec le langage Java, elle permet de créer des visualisations impressionnantes en 2D et 3D.</p>" +
				"<h3 style='color: #2E8B57;'>Créé par:</h3>" +
				"<ul>" +
				"<li>Elouardi Salah Eddine</li>" +
				"</ul>" +
				"<h3 style='color: #2E8B57;'>Encadrant :</h3>" +
				"<p>Antoine Boiteau</p>" +
				"<div style='text-align: center; width: 100%; margin-top: 10px; color: #808080;'>" +
				"</div>" +
				"</body></html>";
		
		aPropos.addActionListener(e -> controleur.afficherMessageAPropos(message));
		
		
		aide.addActionListener(e -> controleur.afficherAide());

		
		menuApropos.add(aPropos);
		menuAide.add(aide);
		
		barreDeMenu.add(menuApropos);
		barreDeMenu.add(menuAide);
		frame.setJMenuBar(barreDeMenu);
		
	}
	
	/**
	 * Construit un panel d'accueil affichant le titre, la description et les boutons principaux.
	 * Ce constructeur est utilisé pour créer la vue d'accueil de l'application.
	 *
	 * @param actionListener L'écouteur d'actions pour gérer les interactions de l'utilisateur.
	 */	
	public Panels(Controleur controleur) {
		
		this.controleur = controleur; 
		
		this.setLayout(new GridBagLayout());
		
		gbc = new GridBagConstraints();
		boutonsGbc = new GridBagConstraints();
		bouttonsPanel = new JPanel(new GridBagLayout());
		bouttonsPanel.setBackground(Color.decode("#E8F5E9"));
		
		
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		ajouterTitre("Fractales Naturelles : Explorer les L-systèmes", gbc, this, Color.decode("#4CAF50"), Color.decode("#FAFAFA"));
		
		/**********************  Positionnement du Description *****************************/
		
		
		// Configuration de la description
		
		descriptionPane = new JTextPane();
		descriptionPane.setContentType("text/html");
		descriptionPane.setText( "<html><body style='text-align: center;'>"
				+"<h1>Découvrez la beauté des L-systèmes</h1>"
				+"Les boutons <strong>Générer 2D</strong> et <strong>Générer 3D</strong> ouvrent la porte à une expérience de personnalisation unique des L-systèmes.<br>"
				+"En utilisant ces options, vous êtes invité à entrer des paramètres clés tels que l'axiome, les règles de production, et l'angle de rotation.<br>"
				+"Ces données permettent de simuler et d'afficher des structures complexes ressemblant à des arbres ou des plantes,<br>"
				+"offrant ainsi un aperçu visuel fascinant du monde des L-systèmes. Que vous soyez un passionné de botanique,<br>"
				+"un étudiant en biologie ou simplement curieux de la modélisation algorithmique, ces outils vous fourniront un moyen intuitif et interactif<br>"
				+"d'explorer la croissance et la formation des structures naturelles en 2D et en 3D."
				
            +"</body></html>");
		
		descriptionPane.setOpaque(true);
		descriptionPane.setEditable(false);
		
		Border ligneDeBordure = BorderFactory.createLineBorder(Color.decode("#73be73"),3);
		Border espaceEntreTexteEtBordure = BorderFactory.createEmptyBorder(10,10,10,10); // Ajoute un peu d'espace entre la bordure et le texte
		Border bordure = BorderFactory.createCompoundBorder(ligneDeBordure, espaceEntreTexteEtBordure);
		descriptionPane.setBorder(bordure);
		
		gbc.insets = new Insets(100 , 0 , 0 , 0);
		gbc.gridy = 1;
		this.add(descriptionPane, gbc);
		
		
		
		/**********************  Positionnement des Boutons *****************************/
		
		
		boutonsGbc.insets = new Insets(5, 5, 5, 5);
		
		// Bouton pour Générer une plante 2D
		pen2D = ImageLoader.loadImageIcon("2D.png");
		ajouterBouton(bouttonsPanel, "Générer 2D", pen2D, ()  -> this.controleur.ouvrirConfiguration(TypeAffichage.DEUX_D), boutonsGbc, Color.decode("#4CAF50") , 120 , 30);		
		
		
		// Bouton pour Générer une plante 3D
		pen3D = ImageLoader.loadImageIcon("3D.png");
		boutonsGbc.gridx = 1; 
		boutonsGbc.insets = new Insets(5, 30, 5, 5); // Augmente la marge gauche a 20 pour plus d'espace
		ajouterBouton(bouttonsPanel, "Générer 3D", pen3D, () -> this.controleur.ouvrirConfiguration(TypeAffichage.TROIS_D), boutonsGbc, Color.decode("#2196F3"),120,30);		
		
		
		// Bouton pour quitter l'application
		exit = ImageLoader.loadImageIcon("quitter.png");
		boutonsGbc.gridx = 0; 
		boutonsGbc.gridy = 1;
		boutonsGbc.gridwidth = 2; 
		boutonsGbc.insets = new Insets(50, 5, 5, 5);
		ajouterBouton(bouttonsPanel, "Quitter", exit, this.controleur::fermerApplication, boutonsGbc, Color.decode("#D32F2F"),120,30);
		
		
		
		gbc.insets = new Insets(70, 0, 0, 0); 
		
		gbc.gridy = 2; 
		this.add(bouttonsPanel, gbc);
		
		Border ligneDeBrd = BorderFactory.createLineBorder(Color.decode("#73be73"), 3);
		Border raisedBevel = BorderFactory.createRaisedBevelBorder();
		Border loweredBevel = BorderFactory.createLoweredBevelBorder();
		Border outerCompoundBorder = BorderFactory.createCompoundBorder(ligneDeBrd, raisedBevel);
		
		Border compoundBorder = BorderFactory.createCompoundBorder(outerCompoundBorder, loweredBevel);
		
		this.setBorder(compoundBorder);
		this.setBackground(Color.decode("#E8F5E9"));
		
		
	}
	
	
	
	/**
	 * Construit un panel d'accueil contenant uniquement le logo et le titre.
	 * Ce constructeur est utilisé pour afficher une zone simplifiée de l'accueil.
	 *
	 * @param title Le titre de logo à afficher dans le panel.
	 */	
	public Panels(String title) {
		
		this.setLayout(new GridBagLayout()); 
		gbc = new GridBagConstraints();
		
		ImageIcon logoIcon = ImageLoader.loadImageIcon("unicaen.png");
		Image newlogo = logoIcon.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);//redimensionnement du logo
		logoIcon = new ImageIcon(newlogo);
		
		logo = new JLabel(logoIcon, JLabel.CENTER);
		
		
		gbc.gridx = 0; 
		gbc.gridy = 0; 
		gbc.anchor = GridBagConstraints.CENTER; 
		this.add(logo, gbc);
		
		JTextPane textPane = new JTextPane();
		textPane.setContentType("text/html"); 
		textPane.setText("<html><body style='text-align: center;'>"
				+ "Université de Caen Normandie<br>"
				+ "UFR des Sciences<br>"
				+ "Département Informatique<br>"
				+ "2éme année de licence<br>"
				+ "d'informatique</body></html>");
		
		textPane.setOpaque(false);
		
		textPane.setEditable(false);
		textPane.setBorder(null);
		
		gbc.gridy = 1; 
		this.add(textPane, gbc);
		this.setBackground(Color.decode("#E8F5E9"));
		
	}
	
	
	
	/**
	 * Construit un panel de configuration pour les L-systèmes avec des champs de saisie dynamiques.
	 * Ce constructeur est utilisé pour créer un formulaire permettant à l'utilisateur de spécifier
	 * les paramètres des L-systèmes à générer.
	 *
	 * @param actionListener L'écouteur d'actions pour gérer les interactions de l'utilisateur.
	 * @param configPanel Le panel de configuration contenant les champs de saisie.
	 * @param nombreDeRegles Le nombre de règles de production à afficher dans le formulaire.
	 * @requires nombreDeRegles >= 0;
	 */	
	
	public Panels(Controleur controleur,ConfigurationPanel configPanel , int nombreDeRegles) {
		
		this.controleur = controleur;
		
		this.configPanel = configPanel ; 
		this.textFields = new ArrayList<>();
		this.setLayout(new GridBagLayout());
		
		gbc = new GridBagConstraints();
		
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		boutonsGbc = new GridBagConstraints();
		bouttonsPanel = new JPanel(new GridBagLayout());
		bouttonsPanel.setBackground(Color.decode("#E0F2F1"));
		
		labelsGbc = new GridBagConstraints();
		labelsPanel = new JPanel(new GridBagLayout()) ; 
		labelsPanel.setBackground(Color.decode("#E0F2F1"));
		
		/************** Positionnement du titre du SideBar**************************/
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		ajouterTitre("Configuration L-Système", gbc, this, Color.decode("#00695C"), Color.WHITE);
		
		/************** Positionnement des labels et des champs du texte dans la barre latérale(SideBar) **************************/
		
		gbc.insets = new Insets(20, 0, 20, 0);
		gbc.gridy = GridBagConstraints.RELATIVE; // Positionne les éléments les uns après les autres
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		ajouterLabelEtChampTexte(labelsPanel, "Axiome :", 1, labelsGbc);
		
		for (int i = 0; i < nombreDeRegles; i++) {
			
			ajouterLabelEtChampTexte(labelsPanel, "Règle " + (i + 1) + " :", i + 2, labelsGbc);
		}
		
		ajouterLabelEtChampTexte(labelsPanel, "Nombre d'itération :", nombreDeRegles + 2, labelsGbc);
		ajouterLabelEtChampTexte(labelsPanel, "Angle :", nombreDeRegles + 3, labelsGbc);
		
		
		// Ajout du panel de labels
		gbc.gridy = 1; // Après le titre
		this.add(labelsPanel, gbc);
		
		/************** Positionnement des Boutons dans la barre latérale(SideBar)**************************/
		
		// Bouton "Générer"
		
		boutonsGbc.insets = new Insets(5, 5, 5, 5);
		
		startIcon = ImageLoader.loadImageIcon("draw.png");
		
		boutonsGbc.gridx = 0;
		boutonsGbc.gridy = 0;		  
		ajouterBouton(bouttonsPanel, "Générer", startIcon, () -> this.controleur.reDessinerPlante(this.textFields,this.configPanel), boutonsGbc, Color.decode("#4CAF50"),120,30);
		
		// Bouton "Supprimer"
		
		boutonsGbc.insets = new Insets(5, 10, 5, 5);
		deleteIcon = ImageLoader.loadImageIcon("trash.png");
		boutonsGbc.gridx = 1; 
		ajouterBouton(bouttonsPanel, "Supprimer", deleteIcon, () -> effacerChampsTexte(), boutonsGbc, Color.decode("#F9A825"),120,30);
		
		boutonsGbc.insets = new Insets(30, 5, 5, 5);
		
		boutonsGbc.gridx = 0; 
		boutonsGbc.gridy = 1;
		boutonsGbc.gridwidth = 3; 
		
		
		gbc.gridy = 2;
		this.add(bouttonsPanel , gbc);
		
		this.setBackground(Color.decode("#E0F2F1"));
		
		
	}
	
	
	/**
	 * Construit un panel de navigation offrant des options pour personnaliser ou sélectionner des modèles prédéfinis.
	 * Ce constructeur est utilisé pour afficher les options de navigation principales de l'application.
	 *
	 * @param actionListener L'écouteur d'actions pour gérer les interactions de l'utilisateur.
	 * @param configPanel Le panel de configuration associé à ce panel de navigation.
	 */
	public Panels(Controleur controleur, ConfigurationPanel configPanel) {
		
		this.controleur = controleur;
		this.configPanel = configPanel;
		this.textFields = new ArrayList<>();
		setLayout(new BorderLayout());
		
		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(Color.decode("#E0F2F1")); // Un gris perle doux
		ajouterTitre("Panneau de Navigation", new GridBagConstraints(), titlePanel, Color.decode("#00695C"), Color.WHITE); // Vert foncé pour le titre
		add(titlePanel, BorderLayout.NORTH);
		
		JPanel centerPanel = new JPanel(new GridBagLayout());
		centerPanel.setBackground(Color.decode("#E0F2F1"));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(20, 0, 20, 0);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		JPanel personnalisation = new JPanel(new GridBagLayout());
		personnalisation.setBackground(Color.decode("#E0F2F1")); 
		personnalisation.setVisible(false);
		
		GridBagConstraints innerGbc = new GridBagConstraints();
		innerGbc.insets = new Insets(5, 0, 5, 0);
		innerGbc.fill = GridBagConstraints.HORIZONTAL;
		innerGbc.gridx = 0;
		innerGbc.gridy = 0;
		
		ajouterLabelEtChampTexte(personnalisation, "Nombre de règles :", innerGbc.gridy++, innerGbc);
		ajouterBouton(personnalisation, "Appliquer", null, () -> { int nombreDeRegle = controleur.getNombreDeRegle(personnalisation.getComponent(1));
		if (nombreDeRegle >= 0) { 
			configPanel.updateInterfaceForSection(Mode.CONFIGURATION, nombreDeRegle);
		}
		}, innerGbc, Color.decode("#4CAF50"), 120, 30); 
		
		
		ajouterBouton(centerPanel, "Personnaliser votre modèle", null, () -> personnalisation.setVisible(!personnalisation.isVisible()), gbc, Color.decode("#4CAF50"), 200, 38); 
		gbc.gridy++;
		centerPanel.add(personnalisation, gbc);
		
		
		gbc.gridy++;
		ajouterBouton(centerPanel, "Voir des modèles prêts", null, () -> this.configPanel.updateInterfaceForSection(Mode.EXEMPLES, 0), gbc, Color.decode("#87CEEB"), 180, 38); 
		
		
		exit = ImageLoader.loadImageIcon("return.png");
		gbc.gridy++;
		ajouterBouton(centerPanel, "Retour", exit, () -> this.controleur.retournerEnArriere(Mode.ACCEUIL), gbc, Color.decode("#D32F2F"), 120, 30); 
		
		add(centerPanel, BorderLayout.CENTER);
		setBackground(Color.decode("#E0F2F1"));
	}
	
	
	
	
	
	/**
	 * Construit un panel d'exemples permettant à l'utilisateur de sélectionner parmi différents modèles prédéfinis.
	 * Ce constructeur est utilisé pour afficher une galerie d'exemples que l'utilisateur peut explorer.
	 *
	 * @param controleur Le contrôleur gérant les actions de l'utilisateur.
	 * @param configPanel Le panel de configuration associé à ce panel d'exemples.
	 * @param typeAffichage Le type d'affichage (2D ou 3D) pour les exemples.
	 * @param a L'indice de début de la plage d'exemples à afficher.
	 * @param b L'indice de fin de la plage d'exemples à afficher.
	 */
	public Panels(Controleur controleur, ConfigurationPanel configPanel, TypeAffichage typeAffichage, int a , int b) {
		
		this.configPanel = configPanel;
		
		setLayout(new BorderLayout());
		
		// Panel pour le titre
		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(Color.decode("#E0F2F1")); 
		ajouterTitre("Exemples", new GridBagConstraints(),titlePanel, Color.decode("#607D8B"), Color.decode("#FFFFFF"));
		add(titlePanel, BorderLayout.NORTH);
		
		// Panel pour les boutons
		bouttonsPanel = new JPanel(new GridBagLayout());
		bouttonsPanel.setBackground(Color.decode("#E0F2F1"));
		boutonsGbc = new GridBagConstraints();
		boutonsGbc.insets = new Insets(5, 5, 5, 5);
		boutonsGbc.fill = GridBagConstraints.HORIZONTAL;
		boutonsGbc.gridx = 0;
		boutonsGbc.gridy = 0;
		int boutonsParLigne = 2; 
		for (int i = a; i <= b; i++) {
			final int index = i; 
			exempleIcon = ImageLoader.loadImageIcon("image" + index + ".png");
			
			Image image = exempleIcon.getImage(); 
			Image newimg = image.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH); 
			ImageIcon icon = new ImageIcon(newimg); 
			ajouterBouton(bouttonsPanel, "", icon , () -> {
				controleur.planteExemple(index); 
				controleur.reDessinerPlanteExemple(this.configPanel);
				
			}, boutonsGbc, null, 120, 120);
			
			if ((i - a) % boutonsParLigne == 1) { 
				boutonsGbc.gridy++;
				boutonsGbc.gridx = 0; 
			} else {
				boutonsGbc.gridx++; 
			}
		}
		add(bouttonsPanel, BorderLayout.CENTER);
		
		setBackground(Color.decode("#E0F2F1"));
	}
	
	
	
	/**
	 * Construit un panel de configuration affichant les paramètres spécifiés d'un L-système.
	 * Ce constructeur est utilisé pour résumer les paramètres d'un L-système que l'utilisateur a entrés.
	 *
	 *
	 * @param actionListener L'écouteur d'actions pour gérer les interactions de l'utilisateur.
	 * @param configPanel Le panel de configuration associé à ce résumé.
	 * @param axiome L'axiome du L-système.
	 * @param regles Les règles de production du L-système.
	 * @param nombreIterations Le nombre d'itérations pour générer le L-système.
	 * @param angle L'angle d'inclinaison utilisé dans la génération du L-système.
	 */
	public Panels(Controleur controleur , ConfigurationPanel configPanel, String axiome, List<Regle> regles, int nombreIterations, double angle) {
		this.controleur = controleur;
		this.configPanel = configPanel;
	    this.setLayout(new GridBagLayout());

	    gbc = new GridBagConstraints();
	    labelsPanel = new JPanel(new GridBagLayout());
	    labelsPanel.setBackground(Color.decode("#E0F2F1"));

	    // Positionnement du titre du SideBar
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    // Supposons que ajouterTitre est une méthode existante pour ajouter un titre au panel
	    ajouterTitre("Configuration L-Système", gbc, this, Color.decode("#00695C"), Color.WHITE);

	    gbc.insets = new Insets(20, 0, 20, 0);
	    gbc.gridy = GridBagConstraints.RELATIVE;
	    gbc.fill = GridBagConstraints.HORIZONTAL;

	    // Ajout des labels avec les données
	    ajouterLabel("Axiome : " + axiome, gbc, labelsPanel);
	    regles.forEach(regle -> ajouterLabel("Règle : " + regle.getVariable() + " -> " + regle.getTraduction(), gbc, labelsPanel));
	    ajouterLabel("Nombre d'itérations : " + nombreIterations, gbc, labelsPanel);
	    ajouterLabel("Angle : " + angle, gbc, labelsPanel);

	    // Ajout du panel de labels
	    gbc.gridy = 1;
	    this.add(labelsPanel, gbc);

	    // Bouton "Quitter"
	    gbc.gridy = 2;
	    bouttonsPanel = new JPanel(new GridBagLayout());
	    bouttonsPanel.setBackground(Color.decode("#E0F2F1"));
	    boutonsGbc = new GridBagConstraints();
	    boutonsGbc.insets = new Insets(20, 20, 20, 20);
	    boutonsGbc.gridx = 0;
	    boutonsGbc.gridy = 0;
	    exit = ImageLoader.loadImageIcon("quitter.png");
	    // Assurez-vous que actionListener est initialisé correctement avant d'utiliser cette ligne
	    ajouterBouton(bouttonsPanel, "Quitter", exit, () -> this.controleur.fermerApplication(), boutonsGbc, Color.decode("#D32F2F"), 120, 30);
	    this.add(bouttonsPanel, gbc);

	    this.setBackground(Color.decode("#E0F2F1"));
	}

	
	/**
	 * Ajoute un label au panel spécifié.
	 *
	 * @param texte Le texte du label.
	 * @param gbc Les contraintes de positionnement du label.
	 * @param panel Le panel auquel le label sera ajouté.
	 */
	private void ajouterLabel(String texte, GridBagConstraints gbc, JPanel panel) {
		JLabel label = new JLabel(texte);
		label.setForeground(Color.BLACK); 
		panel.add(label, gbc);
	}
	
	/**
	 * Classe utilitaire pour le chargement des icônes d'images.
	 * Fournit une méthode pour charger une icône d'image à partir d'un chemin de fichier.
	 */
	public static class ImageLoader {
		
		private static final String BASE_PATH = "../../ressources/images/";
		
		public static ImageIcon loadImageIcon(String imageName) {
			String path = BASE_PATH + imageName;
			return new ImageIcon(path);
		}
	}
	
	/**
	 * Ajoute un label et un champ de texte au panel spécifié.
	 * Utilisé pour créer des formulaires de saisie dans l'interface utilisateur.
	 *
	 * @param panel Le panel auquel les composants seront ajoutés.
	 * @param labelText Le texte du label.
	 * @param yPos La position y du composant dans le layout du panel.
	 * @param gbc Les contraintes de positionnement des composants.
	 */
	private void ajouterLabelEtChampTexte(JPanel panel, String labelText, int yPos , GridBagConstraints gbc) {
		
		labels = new JLabel(labelText);
		textField = new JTextField(15);
		gbc = new GridBagConstraints();
		
		/********** Positionnement des Labels ***********/
		
		gbc.gridx = 0;
		gbc.gridy = yPos;
		panel.add(labels, gbc);
		
		
		/********** Positionnement des textfield ***********/
		
		gbc.insets = new Insets(10, 4, 4, 4); 
		gbc.gridx = 1;
		panel.add(textField, gbc);
		this.textFields.add(textField);
		
	}
	
	
	/**
	 * Efface le texte de tous les champs de texte dans le panel.
	 * Utilisé pour réinitialiser le formulaire de saisie.
	 */
	private void effacerChampsTexte() {
		
		for (JTextField textField : textFields) {
			
			textField.setText(""); 
			
		}
	}
	
	/**
	 * Applique un style personnalisé aux items du menu.
	 * Modifie l'apparence des items du menu pour les rendre visuellement cohérents avec l'interface de l'application.
	 *
	 * @param menuItem L'item du menu à styliser.
	 */
	private void stylerLeMenuItem(JMenuItem menuItem) {
		
		menuItem.setBackground(new Color(210, 224, 234)); 
		menuItem.setForeground(Color.BLACK); 
		menuItem.setFont(new Font("Arial", Font.ITALIC, 14)); 
		menuItem.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1)); 
		menuItem.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseEntered(MouseEvent e) {
				menuItem.setBackground(menuItem.getBackground().brighter());
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				menuItem.setBackground(new Color(210, 224, 234));			}
		});	}
	
	
	
	/**
	 * Ajoute un titre au panel spécifié.
	 * Utilisé pour les sections de l'interface utilisateur nécessitant un titre distinct.
	 *
	 * @param title Le titre à afficher.
	 * @param gbc Les contraintes de positionnement du titre.
	 * @param panel Le panel auquel le titre sera ajouté.
	 * @param couleurDeBackground La couleur de fond pour le titre.
	 * @param couleurDeForeground La couleur du texte pour le titre.
	 */
	private void ajouterTitre(String title, GridBagConstraints gbc, JPanel panel ,  Color couleurDeBackground , Color couleurDeForeground) {
		
		
		titre = new JLabel(title, SwingConstants.CENTER);
		titre.setFont(new Font("Serif", Font.BOLD, 20));
		titre.setOpaque(true);
		titre.setBackground(couleurDeBackground);  
		titre.setForeground(couleurDeForeground);  
		titre.setBorder(BorderFactory.createRaisedBevelBorder());
		
		panel.add(titre, gbc);
	}
	
	/**
	 * Ajoute un bouton au panel spécifié.
	 * Utilisé pour créer des boutons avec des actions spécifiques dans l'interface utilisateur.
	 *
	 * @param panelDeBoutons Le panel auquel le bouton sera ajouté.
	 * @param attribut Le texte ou l'attribut du bouton.
	 * @param icon L'icône à afficher sur le bouton, si applicable.
	 * @param action L'action à exécuter lorsque le bouton est pressé.
	 * @param boutonsGbc Les contraintes de positionnement du bouton.
	 * @param couleur La couleur de fond du bouton.
	 * @param x La largeur du bouton.
	 * @param y La hauteur du bouton.
	 */
	private void ajouterBouton(JPanel panelDeBoutons, String attribut, ImageIcon icon, Runnable action, GridBagConstraints boutonsGbc, Color couleur, int x, int y) {
		
		JButton bouton = new JButton(attribut , icon);
		if (icon != null) {
			bouton.setIcon(icon);
			bouton.setOpaque(false);
			bouton.setContentAreaFilled(false);
			bouton.setBorderPainted(false);
		}
		
		bouton.setOpaque(true);
		bouton.setForeground(Color.BLACK);
		bouton.setBorder(BorderFactory.createRaisedBevelBorder());
		bouton.setFont(new Font("serif", Font.BOLD, 12));
		bouton.setPreferredSize(new Dimension(x, y));
		bouton.setBackground(couleur);
		bouton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				bouton.setBackground(bouton.getBackground().brighter());
			}
			@Override
			public void mouseExited(MouseEvent e) {
				bouton.setBackground(couleur);
			}
		});
		
		bouton.addActionListener(e -> action.run()); // Utilisez Runnable au lieu de ActionListener pour simplifier
		panelDeBoutons.add(bouton , boutonsGbc);
		
		
	}
	
	
}




