/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fges.ckonsoru.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author julie.jacques
 */
public class Annulation {
    
    protected String nomClient;
    protected LocalDateTime creneau;
    protected String nomVeterinaire;
    protected LocalTime delai;

    public Annulation( String nomClient, LocalDateTime  creneau , String nomVet ,LocalTime  delai){
        this.nomClient = nomClient;
        this.creneau = creneau;
        this.nomVeterinaire = nomVet;
        this.delai = delai;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public LocalDateTime getCreneau() {
        return creneau;
    }

    public void setCreneau(LocalDateTime creneau) {
        this.creneau = creneau;
    }

    public String getNomVeterinaire() {
        return nomVeterinaire;
    }

    public void setNomVeterinaire(String nomVeterinaire) {
        this.nomVeterinaire = nomVeterinaire;
    }

    public LocalTime getDelai() {
        return delai;
    }

    public void setDelai(LocalTime delai) {
        this.delai = delai;
    }

    @Override
    public String toString() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		LocalDateTime debut1 = LocalDateTime.parse(this.creneau.toString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        
        return this.nomClient + " le " + debut1.format(timeFormatter) + " ( " + this.delai +" avant )" ;
    }
    
    
}
