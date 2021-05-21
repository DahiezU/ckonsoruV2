package com.fges.ckonsoru;

import com.fges.ckonsoru.dao.ListeAttenteDao;
import com.fges.ckonsoru.model.RendezVous;

public class trackerListeAttente implements DonneeCliniqueObserver{
    
    protected ListeAttenteDao LADao;
    
    public trackerListeAttente(ListeAttenteDao LADao){
            this.LADao = LADao;
    }

    @Override
    public void actualiser(RendezVous rdv) {
        
        
    }

}
