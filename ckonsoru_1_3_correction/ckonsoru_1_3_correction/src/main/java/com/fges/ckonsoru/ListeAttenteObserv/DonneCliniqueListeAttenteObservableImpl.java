package com.fges.ckonsoru.ListeAttenteObserv;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class DonneCliniqueListeAttenteObservableImpl implements DonneeCliniqueObservableListeAttente{
    List<DonneeCliniqueListeAttenteObserver> observateurs = new LinkedList<>();
	
    
	@Override
	public void enregistrerObservateur(
		DonneeCliniqueListeAttenteObserver observateur) {
			observateurs.add( observateur);
	}
	
	@Override
	public void supprimerObservateur(
		DonneeCliniqueListeAttenteObserver observateur) {
			observateurs.remove(observateur);
	}
	
	@Override
	public void notifierObservateurs(String nom , String num , LocalDate date) {
            for(DonneeCliniqueListeAttenteObserver observer : observateurs){
                observer.actualiser(nom , num, date);
            }
	}

	@Override
	public void notifierUpdateObservateurs(LocalDateTime date){
		for(DonneeCliniqueListeAttenteObserver observer : observateurs){
			observer.updateLA(date);
		}
	}

}
