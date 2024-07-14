package controle;

/**
 * L'énumération Mode définit les différents modes de l'interface utilisateur
 * du système de Lindenmayer. Ces modes déterminent la façon dont l'utilisateur interagit
 * avec le programme, que ce soit graphiquement ou via la console, ainsi que le contexte
 * initial de l'application (accueil, exemples, configuration).
 */
public enum Mode {
    /**
     * Le mode graphique permet à l'utilisateur d'interagir avec le système
     * via une interface graphique utilisateur (GUI).
     */
    GRAPHIQUE,
    
    /**
     * Le mode console permet à l'utilisateur d'interagir avec le système
     * via la ligne de commande.
     */
    CONSOLE,
    
    /**
     * Le mode accueil présente à l'utilisateur le panneau d'accueil
     * ou le menu principal de l'application.
     */
    ACCEUIL,
    
    /**
     * Le mode exemples fournit à l'utilisateur une sélection d'exemples 
     * sous forme de bouton chaque bouton represente un dessin d'une plante spécifique
     */
    EXEMPLES,
    
    /**
     * Le mode configuration permet à l'utilisateur d'accéder aux réglages
     * et configurations de l'application, pour personnaliser son expérience.
     */
    CONFIGURATION
}