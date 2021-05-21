/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fges.ckonsoru.view;

import com.fges.ckonsoru.DonneeClinique;
import com.fges.ckonsoru.DonneeCliniqueObservableImpl;
import com.fges.ckonsoru.dao.ListeAttenteDao;
import com.fges.ckonsoru.dao.RendezVousDAO;
import com.fges.ckonsoru.model.RendezVous;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author julie.jacques
 */
public class SupprimerRdvAction 
    extends ActionConsole {

    protected RendezVousDAO rdvDao;
    protected ListeAttenteDao attenteDao;
    protected DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    protected DonneeCliniqueObservableImpl observable ;
    
    public SupprimerRdvAction(int numero, String description, RendezVousDAO rdvDao , ListeAttenteDao attenteDao, DonneeCliniqueObservableImpl observable) {
        super(numero, description);
        this.rdvDao = rdvDao; 
        this.attenteDao = attenteDao;
        this.observable = observable;
    }

    @Override
    public void executer(Scanner scanner) throws SQLException {
        System.out.println("Suppression de rendez-vous");
        System.out.println("Indiquer une date et heure de début au format JJ/MM/AAAA HH:MM (ex: 18/03/2021 15:00)");
        String sDebut = scanner.nextLine();
        LocalDateTime debut = LocalDateTime.parse(sDebut, timeFormatter);
        System.out.println("Indiquer le nom du client");
        String client = scanner.nextLine();
        RendezVous delRdv = new RendezVous(client,debut,"");
        this.rdvDao.supprimerRendezVous(delRdv);
        System.out.println("Un rendez-vous pour "+client+" le  "+ timeFormatter.format(debut) + " a été supprimé");
        
        this.attenteDao.RechercheClientLA(debut);
        System.out.println("L'annulation a été tracée \n" + "Un client en liste d'attente sera rappelé.");

        

        DonneeClinique resultat = new DonneeClinique();
        resultat.setrendezVousSupprimer(delRdv);
        this.observable.notifierObservateurs(delRdv);

        

    }
    
}
