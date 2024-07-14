package util;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe abstraite représentant un modèle observable dans le pattern Modèle-Écouteur (une variante de l'observateur).
 * Elle maintient une liste d'écouteurs et fournit des méthodes pour ajouter ou retirer des écouteurs,
 * ainsi qu'une méthode pour notifier tous les écouteurs enregistrés d'un changement.
 * 
 * Cette classe est idéale pour les cas où des modifications au modèle nécessitent des mises à jour de la vue ou d'autres parties de l'application.
 */
public class AbstractModeleEcoutable implements ModeleEcoutable {
    
	/**
	 * Liste des écouteurs qui sont notifiés lors des changements du modèle.
	 * Chaque écouteur dans cette liste sera informé chaque fois que le modèle subit une modification nécessitant une mise à jour.
	 */
	public List<EcouteurModele> ecouteurs;

    
    /**
     * Constructeur qui initialise la liste des écouteurs.
     */
    public AbstractModeleEcoutable() {
        ecouteurs = new ArrayList<>();
    }

    
    /**
     * Ajoute un écouteur à la liste des écouteurs notifiés lors des changements du modèle.
     * 
     * @param e L'écouteur à ajouter.
     */
    @Override
    public void ajoutEcouteur(EcouteurModele e) {
        ecouteurs.add(e);
    }

    
    /**
     * Retire un écouteur de la liste des écouteurs.
     * 
     * @param e L'écouteur à retirer.
     */
    @Override
    public void retraitEcouteur(EcouteurModele e) {
        ecouteurs.remove(e);
    }

    
    
    /**
     * Notifie tous les écouteurs enregistrés d'un changement du modèle.
     * Cette méthode doit être appelée après toute modification du modèle nécessitant une mise à jour des écouteurs.
     * 
     * @ensures for all EcouteurModele e; ecouteurs.contains(e); e.modeleMisAJour(this) a été appelé;
     * 
     */
    protected void fireChangement(){
        for (EcouteurModele ecouteur : ecouteurs){
            ecouteur.modeleMisAJour(this);
        }
    }
}
