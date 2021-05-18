package com.fges.ckonsoru.dao;

import java.sql.SQLException;

import com.fges.ckonsoru.model.RendezVous;

public interface ListeAnnulationDAO {
    public  void ajouterAnnulation(RendezVous rendezVous) throws SQLException;
}
