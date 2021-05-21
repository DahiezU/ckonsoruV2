package com.fges.ckonsoru;

import com.fges.ckonsoru.model.RendezVous;

public class DonneeClinique {
    protected RendezVous rendezVousSupprimer;

    
    public void setrendezVousSupprimer(RendezVous rdv){
        this.rendezVousSupprimer = rdv;
    }

    public RendezVous getrendezVousSupprimer(){
        return rendezVousSupprimer;
    }
}
