package com.fges.ckonsoru.view;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import com.fges.ckonsoru.dao.ListeAttenteDao;
import com.fges.ckonsoru.dao.RendezVousDAO;
import com.fges.ckonsoru.model.ListeAttente;
import com.fges.ckonsoru.model.RendezVous;


public class AfficheListeAttenteAction {
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    protected ListeAttenteDao LADao;
  //protected ListeAttenteDao rdvDao;
    /*public AfficheListeDAttenteDate(int numero, String description, ListeAttenteDao LADao) {
        super(numero, description);
        this.LADao = LADao;
    }

    @Override
    public void executer(Scanner scanner) {
        System.out.println("Affichage des rendez-vous d'un client");
        System.out.println("Indiquer le nom du client");
        String client = scanner.nextLine();
        List<ListeAttente> rdvs = LADao.AffichagerListeDAttente(date);
        
        System.out.println(rdvs.size() + " rendez-vous trouvé(s) pour " + client);
        
        for (RendezVous rdv : rdvs){
            System.out.println(dateTimeFormatter.format(rdv.getDate())+" avec "+rdv.getVeterinaire());
}*/
}