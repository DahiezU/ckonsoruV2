package com.fges.ckonsoru.dao.postgres;


import com.fges.ckonsoru.dao.ListeAttenteDao;
import com.fges.ckonsoru.model.ListeAttente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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

   
    public void MiseAJourListeAttente(LocalDateTime datePlusTard , int id) throws SQLException{
            PreparedStatement stUpdateListe = this.postgresConnexion.conn.prepareStatement(
                "UPDATE listeAttente SET la_creneauPropose = ? WHERE la_id = ? ;");

                Timestamp timestamp = Timestamp.valueOf(datePlusTard);
              
                stUpdateListe.setTimestamp(1,timestamp);
              
                stUpdateListe.setInt(2, id);
              
                stUpdateListe.executeUpdate();
               
    }

    public void RechercheClientLA(LocalDateTime datePlusTard) throws SQLException{
        PreparedStatement stRechercheListe = this.postgresConnexion.conn.prepareStatement(
            "SELECT la_id, la_client, la_numTel, la_dateAuPlusTard,\n"
            +"la_dateDemande, la_creneauPropose \n"
            +"FROM listeAttente \n"
            +"WHERE ? ::date <= la_dateAuPlusTard \n"
            +"AND la_creneauPropose IS NULL \n"
            +"ORDER BY la_dateDemande ASC \n"
            +"LIMIT 1"
        );
        Timestamp timestamp = Timestamp.valueOf(datePlusTard);
        stRechercheListe.setTimestamp(1,timestamp );
        ResultSet rs  = stRechercheListe.executeQuery();
            while (rs.next()){
                int id = rs.getInt("la_id");
                String client = rs.getString("la_client");
                String num = rs.getString("la_numTel");
                LocalDate maDatePlusTard = rs.getDate("la_dateAuPlusTard").toLocalDate();
           
                MiseAJourListeAttente(datePlusTard , id);
            }
           
        
    }


 
    //------------------------------------------------------------------------------------------------------------------------
  
    
    public String CorrespondanceHeureVeto(LocalDateTime dateRdv) throws SQLException{
        if(dateRdv != null){

        
            PreparedStatement st = this.postgresConnexion.conn.prepareStatement(
                "SELECT  vet_nom FROM  rendezvous \n "
                +"LEFT JOIN veterinaire ON rendezvous.vet_id = veterinaire.vet_id \n"
                +"WHERE rendezvous.rv_debut >= to_timestamp(?  , 'yyyy-mm-dd hh24:mi:ss') \n"
                +"LIMIT 1;"
            );

            Timestamp timestamp = Timestamp.valueOf(dateRdv);
            st.setTimestamp(1,timestamp);
            String nomVet = null;
            ResultSet rs = st.executeQuery();
            while (rs.next())
            {
                nomVet = rs.getString("vet_nom");
               
            }
            return nomVet;
        }else{
            return "";
        }
   }


    
	@Override
    public List<ListeAttente> AffichagerListeDAttente() {
        List<ListeAttente> mesListeAttente = new ArrayList<ListeAttente>();
        try {
            PreparedStatement st = this.postgresConnexion.conn.prepareStatement(
                    "SELECT la_id, la_client, la_numTel, la_dateAuPlusTard, la_dateDemande, la_creneauPropose\n" +
                    "FROM listeAttente ;"
                   );
            
            
            ResultSet rs = st.executeQuery();
            while (rs.next())
            {   ListeAttente maListe = null;
                if(rs.getTimestamp("la_creneauPropose") != null){
                    maListe = new ListeAttente(rs.getInt("la_id"),
                    rs.getString("la_client"),
                    rs.getString("la_numTel"),
                    (LocalDate) rs.getObject("la_dateAuPlusTard",LocalDate.class),
                    (LocalDateTime) rs.getObject("la_creneauPropose",LocalDateTime.class),
                    (LocalDateTime) rs.getObject("la_dateDemande",LocalDateTime.class),
                    this.CorrespondanceHeureVeto(rs.getTimestamp("la_creneauPropose").toLocalDateTime())
                    );
                }else{
                    maListe = new ListeAttente(rs.getInt("la_id"),
                    rs.getString("la_client"),
                    rs.getString("la_numTel"),
                    (LocalDate) rs.getObject("la_dateAuPlusTard",LocalDate.class),
                    (LocalDateTime) rs.getObject("la_creneauPropose",LocalDateTime.class),
                    (LocalDateTime) rs.getObject("la_dateDemande",LocalDateTime.class),
                    "");

                }
               

                mesListeAttente.add(maListe);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            System.err.println("Problème lors de la requête AffichagerListeDAttente");
            System.err.println(ex.getMessage());
        }
        return mesListeAttente;
    }
} 
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
    	}
    
	}*/