/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.sql.Statement;

/**
 * Get and Set prepared statement for connection. 
 * @author Mike Bliss
 */
public class DBQuery {
    
    // PreparedStatement reference variable
    public static PreparedStatement preparedStatement;
    
    /**
     * Setter for preparedStatement. 
     * Passes an SQL statement to database.
     * @param connection A connection object
     * @param sqlStatment An SQL statement
     * @throws Exception An exception
     */
    public static void setPreparedStatment(Connection connection, String sqlStatment) throws Exception {
        preparedStatement = connection.prepareStatement(sqlStatment);
    }
    
    /**
     * Getter for preparedStatement. 
     * Returns a preparedStatement.
     * @return preparedStatement A preparedStatement object
     */
    public static PreparedStatement getPreparedStatement() {
        return preparedStatement;
    }
    
}
