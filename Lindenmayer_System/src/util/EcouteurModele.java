package util;


/**
 * Interface pour les écouteurs dans le modèle d'écoute. Permet aux objets implémentant cette interface
 * d'être notifiés et de réagir lorsque le modèle qu'ils observent est mis à jour.
 * 
 * Principalement utilisée pour permettre une mise à jour réactive des vues ou d'autres composants en réponse
 * aux changements dans le modèle, soutenant ainsi une architecture de conception réactive et flexible.
 */
public interface EcouteurModele {

	
	/**
     * Notifie cet écouteur qu'un changement a eu lieu dans le modèle qu'il observe.
     * 
     * @param source l'objet modèle ayant subi un changement, fournissant le contexte nécessaire
     *               pour que l'écouteur puisse répondre de manière appropriée.
     */
	void modeleMisAJour(Object source);
}

