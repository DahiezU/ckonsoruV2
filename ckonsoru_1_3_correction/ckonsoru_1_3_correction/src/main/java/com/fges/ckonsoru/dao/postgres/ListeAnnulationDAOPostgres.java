package com.fges.ckonsoru.dao.postgres;


import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;

import com.fges.ckonsoru.dao.ListeAnnulationDAO;
import com.fges.ckonsoru.model.RendezVous;

public class ListeAnnulationDAOPostgres  extends DaoPostgres
implements ListeAnnulationDAO{

    public ListeAnnulationDAOPostgres(PostgresConnexion postgresConnexion) {
        super(postgresConnexion);
        
    }

    public void ajouterAnnulation(RendezVous rendezVous) throws SQLException{

            Duration maDuration = Duration.between(rendezVous.getDate(), LocalDateTime.now());
            
            Timestamp timeHeure = new Timestamp(maDuration.getSeconds());
        
            PreparedStatement stVet = this.postgresConnexion.conn.prepareStatement(
                    "SELECT * FROM rendezvous \n" +
                    "WHERE rv_client = ? ;"
                   //"AND rv_debut = ? ;"
                    
            );

            int id = 0 ;
           

            stVet.setObject(1, rendezVous.getNomClient());
        
            ResultSet rs = stVet.executeQuery();
            

            if(rs.next()){
               
                id = rs.getInt("vet_id");
                System.out.println("ma id de vet   " + id );
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
            sth24.close();
        
    }
    
}
