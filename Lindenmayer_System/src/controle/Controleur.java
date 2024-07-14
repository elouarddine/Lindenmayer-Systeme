

package controle;


import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.joml.Vector3f;

import com.jogamp.opengl.GL2;

import model.Interpretation;
import model.Lsysteme;
import model.Regle;
import util.EcouteurModele;
import view.ConfigurationPanel;
import view.MainFrame;
import view.Tortue2D;
import view.Tortue3D;
import view.TypeAffichage;

/**
 * Contrôleur central pour gérer les interactions entre l'interface utilisateur et le modèle de données.
 * Cette classe implémente les interfaces VueInteractionListener et EcouteurModele pour recevoir
 * et traiter les événements de l'interface utilisateur ainsi que les mises à jour du modèle.
 */
public class Controleur implements EcouteurModele{
	
	private MainFrame fenetre;
	private Interpretation interpretation ; 
	private Lsysteme lsysteme ; 
	private String axiome,sequence; 
	private int nombreIterations ; 
	private double angle ;
	private ArrayList<Regle> regles = new ArrayList<Regle>() ;
	private TypeAffichage typeAffichage;
	private Tortue2D tortue2D ; 
	private Tortue3D tortue3D ; 
	private ConfigurationPanel configPanel;
	private Mode mode ;
	
	/**
	 * Constructeur pour initialiser le contrôleur en mode graphique.
	 * Crée et affiche la fenêtre principale de l'interface utilisateur, initialise notre L-système,
	 * et prépare les instances de tortue pour le dessin 2D et 3D sans paramètres initiaux spécifiques.
	 * 
	 * @param mode Indique le mode de l'application (GRAPHIQUE dans ce cas). La valeur est conservée mais non utilisée directement dans ce constructeur.
	 */
	public Controleur(Mode mode) {
        this.mode = mode;
        initializeComponents();
		
		SwingUtilities.invokeLater(() -> {
			this.fenetre =new MainFrame(this);
			this.fenetre.setVisible(true);
		});
		
	}
	
	/**
	 * Constructeur pour initialiser le contrôleur en mode console.
	 * Conçu pour une utilisation où l'interface graphique n'est pas nécessaire ou désirée.
	 * Permet la configuration directe des paramètres du système L-système à partir des arguments fournis.
	 * 
	 * @param mode Indique le mode de l'application (CONSOLE dans ce cas).
	 * @param axiome L'axiome initial pour le système de L-système.
	 * @param nombreIterations Le nombre d'itérations à appliquer au système de L-système.
	 * @param angle L'angle de rotation utilisé par les tortues pour dessiner.
	 * @param regles La liste des règles de production pour le système de L-système.
	 */
	 public Controleur(Mode mode, String axiome, int nombreIterations, double angle, ArrayList<Regle> regles, TypeAffichage typeAffichage) {
	        this.mode = mode;
	        this.axiome = axiome;
	        this.nombreIterations = nombreIterations;
	        this.angle = angle;
	        this.regles = regles;
	        this.typeAffichage = typeAffichage;
	        initializeComponents();
	        lsysteme.setAxiome(axiome);
			lsysteme.setNombreIteration(nombreIterations);
			lsysteme.setRegles(regles);
			sequence = lsysteme.generate();
		SwingUtilities.invokeLater(() -> {
			this.fenetre =new MainFrame(this,this.mode , this.typeAffichage);
			this.fenetre.setVisible(true);
		});
	}
	
	 /**
	  * Récupère le mode courant de l'objet.
	  * 
	  * @return le mode actuel
	  */
	 public Mode getMode() {
	     return this.mode;
	 }

	 /**
	  * Récupère l'axiome courant de l'objet.
	  * 
	  * @return l'axiome actuel sous forme de chaîne de caractères
	  */
	 public String getAxiome() {
	     return this.axiome;
	 }

	 /**
	  * Récupère la liste des règles définies pour l'objet.
	  * 
	  * @return la liste des règles
	  */
	 public List<Regle> getRegles() {
	     return this.regles;
	 }

	 /**
	  * Récupère le nombre d'itérations défini pour l'objet.
	  * 
	  * @return le nombre d'itérations
	  */
	 public int getNombreIterations() {
	     return this.nombreIterations;
	 }

	 /**
	  * Récupère l'angle défini pour l'objet.
	  * 
	  * @return l'angle en degrés
	  */
	 public double getAngle() {
	     return this.angle;
	 }

	 
	 public void setMode(Mode mode) {
		 
		 this.mode = mode;
		 
	 }
	
	
	
	/**
	 * Initialise les instances de Lsysteme, Tortue2D, Tortue3D, et Interpretation. 
	 * Si le mode est graphique, ajoute ce contrôleur comme écouteur de Lsysteme pour recevoir des mises à jour.
	 * 
	 * @requires mode != null; 
	 * @ensures lsysteme != null; 
	 * @ensures tortue2D != null; 
	 * @ensures tortue3D != null; 
	 * @ensures interpretation != null; 
	 * Si le mode est GRAPHIQUE, assure que le contrôleur est ajouté comme écouteur au Lsysteme :
	 * @ensures mode == Mode.GRAPHIQUE => lsysteme.ecouteurs.contains(this);
	 */
	 private void initializeComponents() {
	        lsysteme = new Lsysteme();
	        if (this.mode == Mode.GRAPHIQUE) {
	           lsysteme.ajoutEcouteur(this); 
	        }
	        tortue2D = new Tortue2D();
	        tortue3D = new Tortue3D();
	        interpretation = new Interpretation();
	    }
	
	
	/**
	 * Affiche une représentation 2D de la plante selon les paramètres configurés.
	 * Utilise une instance de Tortue2D pour dessiner la plante dans un contexte graphique 2D.
	 *
	 * @param g2d Contexte graphique 2D dans lequel la plante sera dessinée.
	 * @param dx Position initiale X de la tortue.
	 * @param dy Position initiale Y de la tortue.
	 */
	public void afficherPlante2D(Graphics2D g2d , double dx , double dy) {
		
		tortue2D.setAngle(angle);
		tortue2D.setDirection();
		tortue2D.setG2d(g2d);
		tortue2D.setDx(dx);
		tortue2D.setDy(dy);
		interpretation.setTortue(tortue2D);
		interpretation.setSequence(sequence);
		interpretation.dessiner(nombreIterations);
		
	}
	
	/**
	 * Affiche une représentation 3D de la plante selon les paramètres configurés.
	 * Utilise une instance de Tortue3D pour dessiner la plante dans un contexte OpenGL 3D.
	 *
	 * @param gl Contexte OpenGL dans lequel la plante sera dessinée.
	 * @param positionInitial Position initiale de la tortue en 3D.
	 */
	public void afficherPlante3D(GL2 gl, Vector3f positionInitial) {
		
		tortue3D.setAngle(angle);
		tortue3D.setOrientation();
		tortue3D.setGl(gl);
		tortue3D.setPosition(positionInitial);
		interpretation.setTortue(tortue3D);
		interpretation.setSequence(sequence);
		interpretation.dessiner(nombreIterations);
	}
	
	/**
	 * Redessine la plante en utilisant les données entrées par l'utilisateur dans les champs de texte.
	 * Vérifie la validité des entrées et, si elles sont valides, applique ces données pour générer et dessiner la nouvelle plante.
	 * Une notification est déclenchée aux écouteurs du changement, dans ce cas le contrôleur, car la méthode generate de l'LSystème contient l'appel à fireChangement.
	 *
	 * @param champsTexte Liste des champs de texte contenant les paramètres de la plante.
	 * @param configPanel Panneau de configuration utilisé pour ajuster les paramètres.
	 */
	public void reDessinerPlante(List<JTextField> champsTexte, ConfigurationPanel configPanel) {
		
		if (isValid(champsTexte)) {
			this.configPanel = configPanel;
			lsysteme.setAxiome(axiome);
			lsysteme.setNombreIteration(nombreIterations);
			lsysteme.setRegles(regles);
			sequence = lsysteme.generate();
		}
		
	}
	
	/**
	 * Stocke les données entrées par l'utilisateur dans les variables appropriées.
	 * Extrai les règles, l'axiome, le nombre d'itérations, et l'angle des champs de texte.
	 *
	 * @param champsTexte Liste des champs de texte contenant les paramètres de la plante.
	 */
	public void stockerDonnees(List<JTextField> champsTexte) {
		
		regles.clear();
		axiome = champsTexte.get(0).getText();
		nombreIterations = Integer.parseInt(champsTexte.get(champsTexte.size() - 2).getText());
		angle = Double.parseDouble(champsTexte.get(champsTexte.size() - 1).getText());
		
		List<String> ruleInputs = new ArrayList<>();
		
		for (int i = 1; i <= champsTexte.size() - 3; i++) {
			ruleInputs.add(champsTexte.get(i).getText());
		}
		
		for (String ruleInput : ruleInputs) {
			
			String[] parts = ruleInput.split(":");
			if (parts.length == 2) {
				char symbol = parts[0].trim().charAt(0);
				String translation = parts[1].trim();
				regles.add(new Regle(symbol, translation));
			}
		}
		
	}
	
	/**
	 * Valide si les entrées dans les champs de texte sont correctes pour générer une plante.
	 * Applique un effet visuel en cas d'erreur dans les entrées.
	 * Stocke les données en cas de validité.
	 *
	 * @param champsTexte Liste des champs de texte à valider.
	 * @return true si toutes les entrées sont valides, false sinon.
	 */
	public boolean isValid(List<JTextField> champsTexte) {
		
		boolean estValide = true;
		
		JTextField axiomeField = champsTexte.get(0);
		if (!isValidAxiome(axiomeField.getText())) {
			effetErreur(axiomeField);
			estValide = false; 
		}
		
		// Vérification des règles
		for (int i = 1; i <= champsTexte.size() - 3; i++) {
			JTextField regleField = champsTexte.get(i);
			if (!isValidRule(regleField.getText())) {
				effetErreur(regleField);
				estValide = false; 
			}
		}
		
		// Vérification du nombre d'itérations
		JTextField iterationsField = champsTexte.get(champsTexte.size() - 2);
		if (!isValidIterations(iterationsField.getText())) {
			effetErreur(iterationsField);
			estValide = false; 
		}
		
		// Vérification de l'angle
		JTextField angleField = champsTexte.get(champsTexte.size() - 1);
		if (!isValidAngle(angleField.getText())) {
			effetErreur(angleField);
			estValide = false; 
		}
		
		if (estValide) {
			stockerDonnees(champsTexte);
			return true;
		} else {
			return false;
		}
	}
	
	
	/**
	 * Applique un effet de bordure clignotante sur le champ de texte pour indiquer une erreur de validation.
	 * La bordure et le fond du champ sont temporairement modifiés pour attirer l'attention de l'utilisateur.
	 *
	 * @param champ Champ de texte sur lequel appliquer l'effet d'erreur.
	 */
	private void effetErreur(JTextField champ) {
		
		champ.setForeground(Color.GRAY); 
		champ.setBackground(Color.decode("#FFCCCC")); 
		effetVibration(champ);
		
		// Ajout d'un focus listener pour réinitialiser le champ lorsque l'utilisateur commence à éditer
		champ.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				//champ.setText("");
				champ.setForeground(Color.BLACK); 
				champ.setBackground(Color.WHITE); 
				champ.setBorder(UIManager.getBorder("TextField.border")); 
			}
		});
		
		// Ajout d'un document listener pour réinitialiser la bordure et le fond quand l'utilisateur commence à taper
		champ.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				warn();
			}
			public void removeUpdate(DocumentEvent e) {
				warn();
			}
			public void insertUpdate(DocumentEvent e) {
				warn();
			}
			
			public void warn() {
				champ.setForeground(Color.BLACK); 
				champ.setBorder(UIManager.getBorder("TextField.border")); 
				champ.setBackground(Color.WHITE); 
			}
		});
	}
	
	
	
	/**
	 * Applique un effet de vibration au champ de texte spécifié en alternant la bordure entre l'originale et une bordure rouge.
	 * Cet effet simule une vibration visuelle par un clignotement rapide de la bordure pour attirer l'attention de l'utilisateur.
	 * L'effet consiste en trois répétitions de changements de bordure avec un délai de 100 millisecondes entre chaque changement.
	 * 
	 * @param champ le champ de texte JTextField auquel l'effet de vibration sera appliqué.
	 */
	
	private void effetVibration(JTextField champ) {
		
		final int DELAY = 100; 
		final int REPETITIONS = 3; 
		
		Border originalBorder = champ.getBorder();
		Border errorBorder = BorderFactory.createLineBorder(Color.RED, 2);
		
		Runnable runnable = () -> {
			try {
				for (int i = 0; i < REPETITIONS; i++) {
					SwingUtilities.invokeLater(() -> champ.setBorder(errorBorder));
					Thread.sleep(DELAY);
					SwingUtilities.invokeLater(() -> champ.setBorder(originalBorder));
					Thread.sleep(DELAY);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		};
		
		new Thread(runnable).start();
	}
	
	
	
	/**
	 * Affiche un message dans une boîte de dialogue À propos.
	 * 
	 * @param message Le message à afficher.
	 * @require message != null
	 */
	public void afficherMessageAPropos(String message) {
		// Afficher le message dans un JOptionPane
		JOptionPane.showMessageDialog(null, message, "À propos", JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * Ferme l'application.
	 * 
	 * @ensure Ferme la fenêtre principale et arrête le processus JVM.
	 */
	public void fermerApplication() {
	    int choix = JOptionPane.showConfirmDialog(fenetre, "Voulez-vous vraiment quitter l'application ?", "Confirmation de sortie", JOptionPane.YES_NO_OPTION);
	    if (choix == JOptionPane.YES_OPTION) {
	        this.fenetre.dispose();
	        System.exit(0);
	    }
	}
	
	
	/**
	 * Retourne à la dernière page affichée tout en affichant un texte spécifique.
	 * Cette méthode utilise le mode spécifié pour déterminer la vue à afficher et y ajoute un texte donné.
	 * Elle est utilisée pour naviguer entre les différentes vues de l'application avec un contexte textuel spécifié.
	 *
	 * @param mode Le mode déterminant la vue à laquelle retourner. Ce paramètre ne doit pas être null.
	 * @require mode != null && texte != null  // Assure que ni le mode ni le texte ne sont nuls pour éviter les erreurs à l'exécution.
	 * @ensure Retourne à la dernière page affichée avec le texte spécifié. Le texte spécifié est affiché dans la vue ciblée.
	 */
	public void retournerEnArriere(Mode mode) {
	    this.fenetre.retournerEnArriere(mode);
	}

	
	/**
	 * Met à jour le modèle et active le dessin dans le panneau de configuration.
	 * 
	 * @param source La source qui a déclenché la mise à jour.
	 * @require source != null
	 * @ensure Met à jour le modèle et active le dessin dans le panneau de configuration.
	 */
	@Override
	public void modeleMisAJour(Object source) {
		this.configPanel.setDessiner(true, this.typeAffichage);
	}
	
	
	/**
	 * Redessine l'exemple de plante sélectionné par l'utilisateur à partir d'un bouton représentant la plante désirée.
	 * 
	 * @param configPanel Le panneau de configuration contenant des bouton qui represente nos exemples de plante.
	 * @require configPanel != null
	 * @ensure Redessine l'exemple de plante basé sur le panneau de configuration spécifié.
	 */
	public void reDessinerPlanteExemple(ConfigurationPanel configPanel) {
		this.configPanel = configPanel;
		lsysteme.setAxiome(axiome);
		lsysteme.setNombreIteration(nombreIterations);
		lsysteme.setRegles(regles);
		sequence = lsysteme.generate();
	}
	
	
	/**
	 * Définit le type d'affichage actuel.
	 * 
	 * @param typeAffichage Le type d'affichage à définir.
	 * @require typeAffichage != null
	 * @ensure Définit le type d'affichage actuel.
	 */
	public void setTypeAffichageCourant(TypeAffichage typeAffichage) {
		this.typeAffichage = typeAffichage;
	}
	
	
	/**
	 * Ouvre le panneau de configuration pour le type d'affichage spécifié.
	 * 
	 * @param typeAffichage Le type d'affichage pour lequel ouvrir la configuration.
	 * @require typeAffichage != null
	 * @ensure Ouvre le panneau de configuration pour le type d'affichage spécifié.
	 */    
	 public void ouvrirConfiguration(TypeAffichage typeAffichage) {
		 setTypeAffichageCourant(typeAffichage);
		 this.fenetre.configurationView(typeAffichage);
	 }
	 
	 /**
	  * Récupère le nombre de règles défini par l'utilisateur dans un champ de texte.
	  * 
	  * @param champ Le composant à partir duquel le nombre de règles est récupéré.
	  * @return Le nombre de règles.
	  * @require champ != null
	  * @ensure Retourne le nombre de règles défini par l'utilisateur dans un champ de texte.
	  */
	 public int getNombreDeRegle(Component champ) {
		 
		 JTextField textField = (JTextField) champ;
		 String texte = textField.getText();
		 if (isValidIterations(texte)) {
			 return Integer.parseInt(texte);
			 
		 } else {
			 effetErreur(textField);
			 return -1; 
		 }
	 }
	 
	 
	 
	 /**
	  * Vérifie si l'axiome est valide.
	  * 
	  * @param axiome L'axiome à valider.
	  * @return true si l'axiome est valide, sinon false.
	  * @require axiome != null
	  * @ensure Vérifie si l'axiome est valide.
	  */
	 public boolean isValidAxiome(String axiome) {
		 return axiome.matches(".+");
	 }
	 
	 
	 /**
	  * Vérifie si le nombre d'itérations est valide.
	  * 
	  * @param iterations Le nombre d'itérations à valider.
	  * @return true si le nombre d'itérations est valide, sinon false.
	  * @require iterations != null
	  * @ensure Vérifie si le nombre d'itérations est valide.
	  */
	 public boolean isValidIterations(String iterations) {
		 
		 try {
			 int iter = Integer.parseInt(iterations);
			 return iter > 0;
			 
		 } catch (NumberFormatException e) {
			 return false;
		 }
	 }
	 
	 
	 /**
	  * Vérifie si l'angle est valide.
	  * 
	  * @param angle L'angle à valider.
	  * @return true si l'angle est valide, sinon false.
	  * @require angle != null
	  * @ensure Vérifie si l'angle est valide.
	  */
	 public boolean isValidAngle(String angle) {
		 try {
			 
			 @SuppressWarnings("unused")
			 double ang = Double.parseDouble(angle);
			 return true;
			 
		 } catch (NumberFormatException e) {
			 return false;
		 }
	 }
	 
	 
	 
	 /**
	  * Vérifie si une règle est valide.
	  * 
	  * @param rule La règle à valider.
	  * @return true si la règle est valide, sinon false.
	  * @require rule != null
	  * @ensure Vérifie si une règle est valide.
	  */
	 public boolean isValidRule(String rule) {
		 return rule.matches(".+:.+"); 
	 }
	 

	 
	 
	 
	 /**
	  * Génère un exemple de plante en fonction du numéro spécifié.
	  * 
	  * @param k Le numéro de l'exemple de plante à générer.
	  * @require k > 0
	  * @ensure Génère un exemple de plante en fonction du numéro spécifié.
	  */
	 public void planteExemple(int k) {
		 
		 regles.clear();
		 
		 if (k == 1) {
			 axiome = "F";
			 nombreIterations = 5;
			 angle = 25.7;
			 regles.add(new Regle('F', "F[+F]F[-F]F"));
		 } else if (k == 2) {
			 axiome = "F";
			 nombreIterations = 5;
			 angle = 20;
			 regles.add(new Regle('F', "F[+F]F[-F][F]"));
		 } else if (k == 3) {
			 axiome = "F";
			 nombreIterations = 4;
			 angle = 22.5;
			 regles.add(new Regle('F', "FF-[-F+F+F]+[+F-F-F]"));
		 } else if (k == 4) {
			 axiome = "X";
			 nombreIterations = 6;
			 angle = 20;
			 regles.add(new Regle('X', "F[+X]F[-X]+X")); 
			 regles.add(new Regle('F', "FF"));
		 } else if (k == 5) {
			 axiome = "X";
			 nombreIterations = 6;
			 angle = 25.7;
			 regles.add(new Regle('X', "F[+X][-X]FX")); 
			 regles.add(new Regle('F', "FF"));
		 }else if (k == 6) {
			 axiome = "X";
			 nombreIterations = 5;
			 angle = 22.5;
			 regles.add(new Regle('X', "F-[[X]+X]+F[+FX]-X")); 
			 regles.add(new Regle('F', "FF"));
		 } else if (k == 7) {
			 axiome = "A";
			 nombreIterations = 7;
			 angle = 22.5;
			 regles.add(new Regle('A', "[&FL!A]/////’[&FL!A]///////’[&FL!A]")); 
			 regles.add(new Regle('F', "S ///// F"));
			 regles.add(new Regle('S', "F L"));
			 regles.add(new Regle('L', "[’’’∧∧{-f+f+f-|-f+f+f}]"));
			 
		 } else if (k == 8) {
			 axiome = "F";
			 nombreIterations = 6;
			 angle = 30;
			 regles.add(new Regle('F', "S[&F]///[&F]///[&F]///[&F]")); 
			 regles.add(new Regle('S', "FFF"));
		 }else if (k == 9) {
			 axiome = "F";
			 nombreIterations = 7;
			 angle = 30;
			 regles.add(new Regle('F', "S[&F]///[&F]///[&F]///[&F]")); 
			 regles.add(new Regle('S', "FFF"));
		 }
		 
	 }
	 
	 
	 
	 public void afficherAide() {
		    
		 this.fenetre.helpView();

	 }
	 
}
