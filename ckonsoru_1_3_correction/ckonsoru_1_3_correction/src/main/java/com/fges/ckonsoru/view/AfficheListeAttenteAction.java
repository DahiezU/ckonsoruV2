package com.fges.ckonsoru.view;


import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import com.fges.ckonsoru.dao.ListeAttenteDao;
import com.fges.ckonsoru.model.ListeAttente;
import com.fges.ckonsoru.model.RendezVous;


public class AfficheListeAttenteAction extends ActionConsole {

    protected ListeAttenteDao LADao;

    public AfficheListeAttenteAction(int numero, String description , ListeAttenteDao LADao) {
        super(numero, description);
        this.LADao = LADao;
        //TODO Auto-generated constructor stub
    }

    @Override
    public void executer(Scanner scanner) throws SQLException {
        System.out.println("Affichage de la liste d'attente \n "
        +"nom client (n°téléphone), créneau proposé, vétérinaire proposé");
        List<ListeAttente> mesListes = LADao.AffichagerListeDAttente();
        for(ListeAttente maListe :  mesListes){
            System.out.println(maListe);
        }
        
    }




    /*DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    protected ListeAttenteDao LADao;

    public void AfficheListeDAttenteDate(int numero, String description, ListeAttenteDao LADao) {
        super(numero, description);
        this.LADao = LADao;
    }

    @Override
    public void executer(Scanner scanner) {
        System.out.println("Affichage des rendez-vous d'un client");
        System.out.println("Indiquer le nom du client");
        String client = scanner.nextLine();
        List<ListeAttente> rdvs = LADao.AffichagerListeDAttente();
        
        System.out.println(rdvs.size() + " rendez-vous trouvé(s) pour " + client);
        
        for (RendezVous rdv : rdvs){
            System.out.println(dateTimeFormatter.format(rdv.getDate())+" avec "+rdv.getVeterinaire());
        }   
    }*/


}