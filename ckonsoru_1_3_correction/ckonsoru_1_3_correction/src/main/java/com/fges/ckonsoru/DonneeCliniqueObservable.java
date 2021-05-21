package com.fges.ckonsoru;


import com.fges.ckonsoru.model.RendezVous;

public interface DonneeCliniqueObservable {
    public void enregistrerObservateur(DonneeCliniqueObserver observateur);

    public void supprimerObservateur(DonneeCliniqueObserver observateur);
    
    public void notifierObservateurs( RendezVous rdv);
    
}
