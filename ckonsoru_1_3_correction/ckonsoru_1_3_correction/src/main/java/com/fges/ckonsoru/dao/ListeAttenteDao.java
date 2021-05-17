package com.fges.ckonsoru.dao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.fges.ckonsoru.model.ListeAttente;

public interface ListeAttenteDao {

	
	public List<ListeAttente>  AffichagerListeDAttente(LocalDate date);
	public  void InscriptionListeAttente(String nomClient , LocalDate dateAuPlusTard , String numTel) throws SQLException ;
	
}