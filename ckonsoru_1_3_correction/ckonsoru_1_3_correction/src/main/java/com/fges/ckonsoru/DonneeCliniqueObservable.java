package com.fges.ckonsoru;

public interface DonneeCliniqueObservable {
    public void enregistrerObservateur(DonneeCliniqueObserver observateur);

    public void supprimerObservateur(DonneeCliniqueObserver observateur);
    
    public void notifierObservateurs( DonneeClinique donnee);
    
}
