package util;

/**
 * Interface pour ajouter ou retirer des écouteurs d'un modèle, permettant des mises à jour dynamiques.
 */
public interface ModeleEcoutable {
   
	/**
     * Enregistre un nouvel écouteur pour recevoir des notifications de changement du modèle.
     * 
     * @param e L'écouteur à ajouter. Doit être non-null et capable de gérer les notifications de mise à jour.
     */
	void ajoutEcouteur(EcouteurModele e);
   
	 /**
     * Retire un écouteur précédemment enregistré, l'empêchant de recevoir davantage de notifications.
     * 
     * @param e L'écouteur à retirer. Doit être non-null et précédemment enregistré comme écouteur de ce modèle.
     */
	void retraitEcouteur(EcouteurModele e);
}
