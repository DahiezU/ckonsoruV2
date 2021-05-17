/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fges.ckonsoru.view;

import com.fges.ckonsoru.dao.DisponibilitesDAO;
import com.fges.ckonsoru.dao.ListeAttenteDao;
import com.fges.ckonsoru.model.Disponibilite;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author julie.jacques
 */
public class AfficheCreneauxDateAction
    extends ActionConsole {
    
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    
    protected DisponibilitesDAO dispoDAO;
    protected ListeAttenteDao listeAttenteDAO;

    public AfficheCreneauxDateAction(int numero, String description, 
                                     DisponibilitesDAO dispoDAO , ListeAttenteDao listeAttDAO) {
        super(numero, description);
        this.dispoDAO = dispoDAO;
        this.listeAttenteDAO = listeAttDAO;
    }

    @Override
    public void executer(Scanner scanner) throws SQLException {
        System.out.println("Entrer une date au format JJ/MM/AAAA (ex: 18/03/2021)");
        String sDate = scanner.nextLine();
        LocalDate date = LocalDate.parse(sDate, dateFormatter);
        List<Disponibilite> dispos = dispoDAO.getDisponibilitesPourDate(date);
        System.out.println("Disponibilités pour le " + date.format(dateFormatter));
        if(dispos.size() == 0 ){
            System.out.println("Pas de disponibilités pour le 19/04/2021 \n "+
            "Appuyez sur 1 pour vous inscrire en liste d'attente, 0 pour retourner au menu principal");
            int iAttente = scanner.nextInt();
            scanner.nextLine();
            if(iAttente == 1 ){
                System.out.println("Indiquez votre nom (ex: P. Smith)");
                String sNom= scanner.nextLine();
                System.out.println("Indiquez un numéro auquel on pourra vous rappeler (ex: +33612345678)");
                String sNum = scanner.nextLine();
                this.listeAttenteDAO.InscriptionListeAttente(sNom, date , sNum);
           }
            
        }else{  
            for(Disponibilite dispo : dispos){
                System.out.println(dispo.getVeterinaire() + " : " + dateTimeFormatter.format(dispo.getDebut()));
            }
        }
        
        
    }
    
}
