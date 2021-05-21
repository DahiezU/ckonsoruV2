package com.fges.ckonsoru.ListeAttenteObserv;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DonneeCliniqueListeAttente {
    protected String nomLA;
    protected String NumTelLA;
    protected LocalDate date;
    protected LocalDateTime datePlusTard;

    public String getNomLA(){
        return this.nomLA;
    }

    public void setNomLA(String nom){
        this.nomLA = nom;
    }


    public String getNumTelLA(){
        return this.NumTelLA ;
    }

    public void setNumTelLA(String num){
        this.NumTelLA = num;
    }


    public LocalDate getdate(){
        return this.date;
    }
    
    public void setdate(LocalDate date){
        this.date = date;
    }


    public void setdatePlusTard(LocalDateTime date){
        this.datePlusTard = date;
    }



}
