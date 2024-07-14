package view;

/**
 * L'énumération TypeAffichage définit les modes d'affichage supportés par l'application.
 * Elle est utilisée pour spécifier si les visualisations doivent être rendues en deux dimensions (2D)
 * ou en trois dimensions (3D). Cette distinction est importante pour configurer correctement les
 * composants d'affichage et les opérations de dessin correspondantes.
 */
public enum TypeAffichage {
    /**
     * Mode d'affichage en deux dimensions (2D). Ce mode est généralement utilisé pour des dessins
     * plats ou des représentations simplifiées.
     */
    DEUX_D,
    
    /**
     * Mode d'affichage en trois dimensions (3D). Ce mode permet des visualisations plus complexes.
     */
    TROIS_D
}