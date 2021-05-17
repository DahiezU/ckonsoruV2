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

/**
 *
 * @author dahie
 */
public class ListeAttenteDAOPostgres 
    extends DaoPostgres
    implements ListeAttenteDao{

    public ListeAttenteDAOPostgres(PostgresConnexion postgresConnexion) {
        super(postgresConnexion);
    }

    //------------------------------------------------------------------------------------------------------------------------
  
    
    
    
	//@Override
    public List<ListeAttente> AffichagerListeDAttente(LocalDate date) {
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
    }
    
    
    
    //------------------------------------------------------------------------------------------------------------------------
    
   
   
    private ListeAttente parseLA(ResultSet rs) throws SQLException {
        ListeAttente rdv = new ListeAttente(
        		rs.getInt("la_id"),
        		rs.getString("la_client"),
        		rs.getString("numTel"),
        		(LocalDate) rs.getObject("dateAuPlusTard",LocalDate.class),
        		(LocalDateTime) rs.getObject("la_creneauPropose",LocalDateTime.class),
        		(LocalDateTime) rs.getObject("la_dateDemande",LocalDateTime.class),
        		"vet"
        		
        		);
        return rdv;
    	}
    
	}
