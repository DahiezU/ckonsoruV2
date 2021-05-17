/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fges.ckonsoru.dao.postgres;

import com.fges.ckonsoru.dao.RendezVousDAO;
import com.fges.ckonsoru.model.Annulation;
import com.fges.ckonsoru.model.RendezVous;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author julie.jacques
 */
public class RendezVousDaoPostgres 
    extends DaoPostgres
    implements RendezVousDAO {

    public RendezVousDaoPostgres(PostgresConnexion postgresConnexion) {
        super(postgresConnexion);
    }

    @Override
    public void creerRendezVous(RendezVous rendezVous) {
        try {
            PreparedStatement st = this.postgresConnexion.conn.prepareStatement(
                "INSERT INTO rendezvous (vet_id, rv_debut, rv_client)\n" +
                "   VALUES((SELECT vet_id FROM veterinaire WHERE vet_nom = ?),\n" +
                "           ?,\n" +
                "           ?);	");
            st.setObject(1,rendezVous.getVeterinaire());
            st.setObject(2, rendezVous.getDate());
            st.setObject(3, rendezVous.getNomClient());
            st.executeUpdate();
            
        }catch(SQLException e){
            System.err.println("Problème lors de la requête creerRendezVous");
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void supprimerRendezVous(RendezVous rendezVous) {
        try {

            if( Duration.between(rendezVous.getDate(), LocalDateTime.now()).getSeconds() <= 86400){

                Duration maDuration = Duration.between(rendezVous.getDate(), LocalDateTime.now());
                
                Timestamp timeHeure = new Timestamp(maDuration.getSeconds());
               


                PreparedStatement stVet = this.postgresConnexion.conn.prepareStatement(
                        "SELECT vet_id FROM rendezvous \n" +
                        "WHERE rv_client = ? \n" +
                        "AND rv_debut = ? ;"
                    
                );

                int id = 0 ;
                stVet.setObject(1, rendezVous.getNomClient());
                stVet.setObject(2, rendezVous.getDate());
                ResultSet rs = stVet.executeQuery();
                while (rs.next()){
                    id = rs.getInt("vet_id");
                   
                }
                rs.close();
                stVet.close();
                
                PreparedStatement sth24 = this.postgresConnexion.conn.prepareStatement(   
                    "INSERT INTO annulation (ann_client, ann_creneau, vet_id, ann_delai) \n"
                    +"VALUES ( ? ,  ? , ? , ? );"
                );
                sth24.setObject(1,rendezVous.getNomClient());
                sth24.setObject(2, rendezVous.getDate());
                sth24.setInt(3,id);
                sth24.setTimestamp(4 , timeHeure);
                sth24.executeUpdate();
            }

            PreparedStatement st = this.postgresConnexion.conn.prepareStatement(
                "DELETE FROM rendezvous \n" +
                "WHERE rv_client = ? \n" +
                "AND rv_debut = ? ;");
            st.setObject(1,rendezVous.getNomClient());
            st.setObject(2, rendezVous.getDate());
            st.executeUpdate();
            st.close();


            
        }catch(SQLException e){
            System.err.println("Problème lors de la requête supprimerRendezVous");
            System.err.println(e.getMessage());
        }
    }

    @Override
    public List<RendezVous> listeRendezVousPourClient(String nomClient) {
    
        List<RendezVous> rdvs = new ArrayList<>();
        
        try {
            PreparedStatement st = this.postgresConnexion.conn.prepareStatement(
                    "SELECT rv_id, rv_debut, rv_client, vet_nom \n" +
                    "FROM rendezvous\n" +
                    "   INNER JOIN veterinaire \n"+
                    "       ON veterinaire.vet_id = rendezvous.vet_id \n"+
                    "WHERE rv_client = ?\n" +
                    "ORDER BY rv_debut DESC;");
            st.setObject(1, nomClient);
            ResultSet rs = st.executeQuery();
            while (rs.next())
            {
                rdvs.add(parseRdv(rs));
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            System.err.println("Problème lors de la requête listeRendezVousPourClient");
            System.err.println(ex.getMessage());
        }
        return rdvs;
    }

    @Override
    public List<RendezVous> listeRendezVousPourDate(LocalDate date) {
        List<RendezVous> rdvs = new ArrayList<>();
        
        try {
            PreparedStatement st = this.postgresConnexion.conn.prepareStatement(
                    "SELECT rv_id, rv_debut, rv_client\n" +
                    "FROM rendezvous\n" +
                    "WHERE rv_debut \n" +
                    "		BETWEEN ? \n" +
                    "		AND ? +'23:59:59'::time \n" +
                    "ORDER BY rv_debut DESC;");
            st.setObject(1, date);
            st.setObject(2, date);
            ResultSet rs = st.executeQuery();
            while (rs.next())
            {
                rdvs.add(parseRdv(rs));
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            System.err.println("Problème lors de la requête listeRendezVousPourDate");
            System.err.println(ex.getMessage());
        }
        return rdvs;
    }
    
    /**
     * Parse un rendezvous contenu dans un resultset (rv_client, rv_debut, vet_nom)
     * @param rs
     * @return
     * @throws SQLException 
     */
    private RendezVous parseRdv(ResultSet rs) throws SQLException {
        RendezVous rdv = new RendezVous(
                rs.getString("rv_client"),
                (LocalDateTime) rs.getObject("rv_debut",LocalDateTime.class),
                rs.getString("vet_nom"));
        return rdv;
    }



    public List<Annulation> ListeAnnulation() throws SQLException{
        List<Annulation> maListAnnulation = new ArrayList<Annulation>();
        PreparedStatement st = this.postgresConnexion.conn.prepareStatement("SELECT * FROM annulation ;");
            ResultSet rs = st.executeQuery();
                Annulation monAnnulation = null;
                while (rs.next()){
                    String nomClient = rs.getString("ann_client");
                    Timestamp creneau = rs.getTimestamp("ann_creneau");
                    Time delai = rs.getTime("ann_delai");
                    
                    monAnnulation = new Annulation(nomClient, creneau.toLocalDateTime() , "", delai.toLocalTime());
                    maListAnnulation.add(monAnnulation);
                }
            rs.close();
            st.close();
            return maListAnnulation;
        
    }
}
