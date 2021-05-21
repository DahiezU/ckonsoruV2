package com.fges.ckonsoru;

import java.util.LinkedList;
import java.util.List;

public class DonneeCliniqueObservableImpl implements  DonneeCliniqueObservable {
    List<DonneeCliniqueObserver> observateurs = new LinkedList<>();
	
	@Override
	public void enregistrerObservateur(
		DonneeCliniqueObserver observateur) {
			observateurs.add(observateur);
	}
	
	@Override
	public void supprimerObservateur(
		DonneeCliniqueObserver observateur) {
			observateurs.remove(observateur);
	}
	
	@Override
	public void notifierObservateurs(DonneeClinique donnee) {
		for(DonneeCliniqueObserver observer : observateurs){
			observer.actualiser(donnee);
		}
	}
    
}
