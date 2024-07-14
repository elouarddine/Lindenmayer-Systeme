package mainPackage;
import controle.Controleur;
import controle.Mode;



/**
 * Classe principale pour démarrer l'application en mode graphique. Cette classe sert de point d'entrée pour lancer
 * l'interface graphique de l'application, en créant une instance du contrôleur en mode GRAPHIQUE.
 *
 */
public class MainGUI {
	
	/** Utilisée principalement lorsque l'application doit interagir avec l'utilisateur via une interface graphique, elle instancie
	 * le contrôleur qui, à son tour, initialise et affiche la fenêtre principale de l'application.
	 *
	 * @param args Les arguments de la ligne de commande, non utilisés dans cette configuration.
	 * 
	 * @ensures Une instance de Controleur est créée en mode GRAPHIQUE. 
	 *
	 */     
        public static void main(String[] args) {
                
                @SuppressWarnings("unused")
                Controleur controle = new Controleur(Mode.GRAPHIQUE);
                
        }
}