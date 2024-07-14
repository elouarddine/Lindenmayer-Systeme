package mainPackage;
import java.util.ArrayList;

import controle.Controleur;
import controle.Mode;
import model.Regle;
import view.TypeAffichage;


/**
 * Classe principale pour l'exécution de l'application en mode console. Parse et utilise les arguments de la ligne de commande
 * pour configurer et lancer l'application avec les paramètres spécifiés pour le système de L-système et l'affichage.
 *
 */
public class MainConsole {

   /** Les arguments attendus sont, dans l'ordre : le type d'affichage (DEUX_D ou TROIS_D), l'axiome de départ, les règles de production (séparées par des virgules),
	 * le nombre d'itérations et l'angle de rotation.
	 *
	 * Cette classe démarre l'application en mode console, permettant une personnalisation avancée du système de L-système
	 * sans interface graphique. Elle crée un nouveau contrôleur avec les paramètres fournis et lance la génération et l'affichage
	 * du modèle basé sur le système de L-système.
	 *
	 *
	 * @param args Les arguments de la ligne de commande nécessaires pour configurer le système de L-système et l'affichage.
	 * @requires args.length >= 5; 
	 * @requires args[2].contains(":"); 
	 * @requires Integer.parseInt(args[3]) > 0; 
	 * @requires Double.parseDouble(args[4]) > 0; 
	 * @ensures Un objet Controleur est créé et configuré avec les paramètres fournis. 
	 */
    public static void main(String[] args) {
        if (args.length < 5) {
            System.out.println("Arguments insuffisants : type de personnalisation, axiome, règles, nombre d'itérations, angle attendus.");
            return;
        }

        String typePersonnalisation = args[0];
        TypeAffichage typeAffichage; 
        
        try {
            typeAffichage = TypeAffichage.valueOf(typePersonnalisation.toUpperCase());
            System.out.println("Type d'affichage sélectionné : " + typeAffichage);
        } catch (IllegalArgumentException e) {
            System.out.println("Entrée invalide. Veuillez entrer DEUX_D ou TROIS_D.");
            return; 
        }

        traiterModele(Mode.CONSOLE, args[1], args[2], Integer.parseInt(args[3]), Double.parseDouble(args[4]), typeAffichage);
    }

    
    
    
    /**
     * Configure et lance l'application en mode console avec les paramètres spécifiés. Cette méthode crée les règles de production
     * à partir d'une chaîne de caractères, initialise le contrôleur avec ces paramètres, et démarre le processus de génération
     * et d'affichage du modèle basé sur le système de L-système.
     * 
     * @param mode Le mode d'exécution de l'application, ici Mode.CONSOLE.
     * @param axiome L'axiome initial pour le système de L-système.
     * @param rule Les règles de production sous forme de chaîne de caractères, chaque règle étant séparée par une virgule,
     *             et chaque paire symbole-translation par deux-points.
     * @param nbIt Le nombre d'itérations à appliquer pour générer le système de L-système.
     * @param angle L'angle de rotation utilisé dans la génération du modèle.
     * @param typeAffichage Le type d'affichage pour le résultat final (DEUX_D ou TROIS_D).
     * 
     * @requires mode != null; 
     * @requires axiome != null && !axiome.isEmpty(); 
     * @requires rule != null && rule.matches(".*:.*"); 
     * @requires nbIt > 0; 
     * @requires angle > 0; 
     * @requires typeAffichage != null; 
     * 
     * @ensures regles != null && !regles.isEmpty(); 
     * @ensures Un objet Controleur est créé et configuré prêt pour la génération et l'affichage du modèle.
     */
    private static void traiterModele(Mode mode, String axiome, String rule, int nbIt, double angle, TypeAffichage typeAffichage) {
        ArrayList<Regle> regles = new ArrayList<>();
        String[] ruleInputs = rule.split(",");
        for (String ruleInput : ruleInputs) {
            String[] parts = ruleInput.split(":");
            if (parts.length == 2) {
                char symbol = parts[0].trim().charAt(0);
                String translation = parts[1].trim();
                regles.add(new Regle(symbol, translation));
            }
        }

        System.out.println("Affichage du modèle...");
        Controleur controle = new Controleur(mode, axiome, nbIt, angle, regles, typeAffichage);
    }
}

