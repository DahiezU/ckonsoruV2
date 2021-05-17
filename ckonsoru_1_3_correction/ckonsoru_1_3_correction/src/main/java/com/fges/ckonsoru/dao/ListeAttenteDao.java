package com.fges.ckonsoru.dao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fges.ckonsoru.model.ListeAttente;

public interface ListeAttenteDao {

	public String CorrespondanceHeureVeto(LocalDateTime dateRdv) throws SQLException;
	public List<ListeAttente>  AffichagerListeDAttente();
	public  void InscriptionListeAttente(String nomClient , LocalDate dateAuPlusTard , String numTel) throws SQLException ;
	public void MiseAJourListeAttente(LocalDateTime creneau , int id) throws SQLException;
	public void RechercheClientLA(LocalDateTime datePlusTard) throws SQLException;
	
}