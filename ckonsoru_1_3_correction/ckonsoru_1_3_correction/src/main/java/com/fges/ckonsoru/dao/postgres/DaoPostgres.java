/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fges.ckonsoru.dao.postgres;

/**
 *
 * @author julie.jacques
 */
public class DaoPostgres {
    
    protected PostgresConnexion postgresConnexion;
    
    public DaoPostgres(PostgresConnexion postgresConnexion){
        this.postgresConnexion = postgresConnexion;
    }
}
