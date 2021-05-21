package com.fges.ckonsoru.ListeAttenteObserv;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface DonneeCliniqueObservableListeAttente {
   
	public void enregistrerObservateur(DonneeCliniqueListeAttenteObserver observateur);
	

	public void supprimerObservateur(DonneeCliniqueListeAttenteObserver observateur);
	
	
	public void notifierObservateurs(String nom , String num , LocalDate date);

	public void notifierUpdateObservateurs(LocalDateTime date);
    
}
