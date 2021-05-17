package com.fges.ckonsoru.dao.postgres;


import com.fges.ckonsoru.dao.ListeAttenteDao;
import com.fges.ckonsoru.model.ListeAttente;
import com.fges.ckonsoru.model.RendezVous;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class ListeAttenteDAOPostgres 
    extends DaoPostgres
    implements ListeAttenteDao{

    public ListeAttenteDAOPostgres(PostgresConnexion postgresConnexion) {
        super(postgresConnexion);
    }


    public void InscriptionListeAttente(String nomClient , LocalDate dateAuPlusTard , String numTel) throws SQLException{
        PreparedStatement stListeAttente = this.postgresConnexion.conn.prepareStatement(   
            "INSERT INTO listeattente (la_client, la_numtel, la_dateauplustard) \n"
            +"VALUES (?, ?, ?);"
        );
        stListeAttente.setString(1,nomClient);
        stListeAttente.setString(2, numTel);
        stListeAttente.setDate(3,java.sql.Date.valueOf(dateAuPlusTard));
        stListeAttente.executeUpdate();

    }


    
    public void MiseAJourListeAttente(LocalDate creneau , int id) throws SQLException{
            /*PreparedStatement stUpdateListe = this.postgresConnexion.conn.prepareStatement(
                "BEGIN; \n"
                +"UPDATE listeAttente \n"
                    +"SET la_creneauPropose = '2021-03-18 16:00:00'\n"
                +"WHERE la_id = 1\n"
                +"SELECT * FROM listeAttente;\n"
                +"ROLLBACK;\n"
                );
                stUpdateListe.setDate(1,creneau);
                stUpdateListe.setInt(2,id);
                stUpdateListe.executeUpdate();*/
    }

    public void RechercheClientLA(LocalDate datePlusTard) throws SQLException{
        /*PreparedStatement stRechercheListe = this.postgresConnexion.conn.prepareStatement(
            "SELECT la_id, la_client, la_numTel, la_dateAuPlusTard,\n"
            +"la_dateDemande, la_creneauPropose \n"
            +"FROM listeAttente \n"
            +"WHERE '2021-05-08'::date <= la_dateAuPlusTard \n"
            +"AND la_creneauPropose IS NULL \n"
            +"ORDER BY la_dateDemande ASC \n"
            +"LIMIT 1"
        );
        stRechercheListe.setDate(1,datePlusTard);
        ResultSet rs  = stRechercheListe.executeQuery();
        while (rs.next()){
            int id = rs.getInt("la_id");
            String client = rs.getString("la_client");
            String num = rs.getString("la_numTel");
            LocalDate daterPlusTard = rs.getDate("la_dateAuPlusTard").toLocalDate();
        }

        if(rs != null){

        }*/
        
    }


    @Override
    public List<ListeAttente> AffichagerListeDAttente(LocalDate date) {
        // TODO Auto-generated method stub
        return null;
    }

    //------------------------------------------------------------------------------------------------------------------------
  
    
    
    
	//@Override
    /*public List<ListeAttente> AffichagerListeDAttente(LocalDate date) {
        List<ListeAttente> rdvs = new ArrayList<>();
        
        try {
            
            PreparedStatement st = this.postgresConnexion.conn.prepareStatement(
                    "SELECT la_id, la_client, la_numTel, la_dateAuPlusTard, la_dateDemande, la_creneauPropose\n" +
                    "FROM listeAttente\n" +
                    "WHERE '2021-05-08'::date <= la_dateAuPlusTard \n" +
                    "AND la_creneauPropose IS NULL \n" +
                    "ORDER BY la_dateDemande ASC \n" +
                    "LIMIT 1;");
            
            
            st.setObject(1, date);
            ResultSet rs = st.executeQuery();
            while (rs.next())
            {
                rdvs.add(parseLA(rs));
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            System.err.println("Problème lors de la requête listeRendezVousPourDate");
            System.err.println(ex.getMessage());
        }
        return rdvs;
    }*/
    
    //------------------------------------------------------------------------------------------------------------------------
    
   
   
    /*private ListeAttente parseLA(ResultSet rs) throws SQLException {
        ListeAttente rdv = new ListeAttente(
        		rs.getInt("la_id"),
        		rs.getString("la_client"),
        		rs.getString("numTel"),
        		(LocalDate) rs.getObject("dateAuPlusTard",LocalDate.class),
        		(LocalDateTime) rs.getObject("la_creneauPropose",LocalDateTime.class),
        		(LocalDateTime) rs.getObject("la_dateDemande",LocalDateTime.class)
        		
        		);
        return rdv;
    	}*/
    
	}