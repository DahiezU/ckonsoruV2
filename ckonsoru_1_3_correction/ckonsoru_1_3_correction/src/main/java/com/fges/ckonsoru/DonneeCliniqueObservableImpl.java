package com.fges.ckonsoru;

import java.util.LinkedList;
import java.util.List;

import com.fges.ckonsoru.model.RendezVous;

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
	public void notifierObservateurs(RendezVous rdv) {
		for(DonneeCliniqueObserver observer : observateurs){
			observer.actualiser(rdv);
		}
	}
    
}
