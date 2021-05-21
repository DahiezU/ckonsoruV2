package com.fges.ckonsoru;

import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;

import com.fges.ckonsoru.dao.ListeAnnulationDAO;
import com.fges.ckonsoru.model.RendezVous;

public class trackerAnnulation  implements DonneeCliniqueObserver {


    protected ListeAnnulationDAO AnnulationDao;
    
    public trackerAnnulation(ListeAnnulationDAO AnnulationDao){
        this.AnnulationDao = AnnulationDao;
    }

    @Override
    public void actualiser(RendezVous rdv) {
        if( Duration.between(rdv.getDate(), LocalDateTime.now()).getSeconds() <= 86400){
            try {
                this.AnnulationDao .ajouterAnnulation(rdv);
            } catch (SQLException e) {
                
                e.printStackTrace();
            }
        }
        
    }
    
}
