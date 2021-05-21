package com.fges.ckonsoru.ListeAttenteObserv;

import java.time.LocalDate;
import java.time.LocalDateTime;



public interface DonneeCliniqueListeAttenteObserver {
    public void actualiser(String nom , String num , LocalDate date);
    public void updateLA(LocalDateTime datePlusTard);
}
