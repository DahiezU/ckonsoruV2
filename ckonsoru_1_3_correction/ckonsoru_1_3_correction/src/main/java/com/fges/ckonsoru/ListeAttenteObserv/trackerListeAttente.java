package com.fges.ckonsoru.ListeAttenteObserv;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fges.ckonsoru.dao.ListeAttenteDao;


public class trackerListeAttente implements DonneeCliniqueListeAttenteObserver{
    
    protected ListeAttenteDao LADao;
    
    public trackerListeAttente(ListeAttenteDao LADao){
            this.LADao = LADao;
    }


    @Override
    public void actualiser(String nom , String num , LocalDate date) {
        try {
            LADao.InscriptionListeAttente(nom,date ,num);
        }catch (SQLException e){
            e.printStackTrace();
        }
        
    }

    @Override
    public void updateLA(LocalDateTime datePlusTard){
        try {
            LADao.RechercheClientLA(datePlusTard);
           System.out.println(" je passe dans  updateLA ");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
