package com.fges.ckonsoru.view;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.fges.ckonsoru.dao.RendezVousDAO;
import com.fges.ckonsoru.model.Annulation;

public class AfficherAnnulationAction  extends ActionConsole {
    
    protected RendezVousDAO rdvDao;

    public AfficherAnnulationAction(int numero, String description , RendezVousDAO rdvDao) {
        super(numero, description);
        this.rdvDao = rdvDao;
        //TODO Auto-generated constructor stub
    }

    @Override
    public void executer(Scanner scanner) {
        System.out.println("Liste des annulations :");
        List<Annulation> annulations  = null;
        try {
            annulations = rdvDao.ListeAnnulation();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        for (Annulation annulation : annulations){
            System.out.println(annulation);
        }
        
    }

    
}
