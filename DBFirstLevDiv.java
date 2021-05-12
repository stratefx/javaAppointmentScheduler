/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseAccess;

import database.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.FirstLevDiv;

/**
 *
 * @author Mike Bliss
 */
public class DBFirstLevDiv {
    
    private static ObservableList<FirstLevDiv> allDivisions = FXCollections.observableArrayList();
    private static ObservableList<FirstLevDiv> allDivisionsByCountry = FXCollections.observableArrayList();    
    private static ObservableList<FirstLevDiv> updateDivision = FXCollections.observableArrayList();
    
    /**
     * Selects all first level divisions from database. 
     * Communicates with database to pull all first level divisions and add them
     * to a list.
     */
    public static void selectAllDivisions() {
        try {
            
/*          String divisionSQL = "SELECT fld.*, c.Country_ID AS cID 
            FROM first_level_divisions AS fld
            INNER JOIN countries AS c
            ON fld.COUNTRY_ID = c.Country_ID";
*/


            String divisionSQL = "SELECT * FROM first_level_divisions";
            PreparedStatement prepStateDiv = DBConnection.getConnection().prepareStatement(divisionSQL);
            prepStateDiv.execute();
            ResultSet resultSet = prepStateDiv.getResultSet();
            
            while(resultSet.next()) {
                int division_id = resultSet.getInt("Division_ID");
                String division = resultSet.getString("Division");
                Timestamp create_date = resultSet.getTimestamp("Create_Date");
                String created_by = resultSet.getString("Created_By");
                Timestamp last_update = resultSet.getTimestamp("Last_Update");
                String last_updated_by = resultSet.getString("Last_Updated_By");
                int country_id = resultSet.getInt("COUNTRY_ID");
                
                FirstLevDiv newDivision = new FirstLevDiv(division_id, division, create_date, 
                    created_by, last_update, last_updated_by, country_id);
                DBFirstLevDiv.addDivision(newDivision);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }    
    
    /**
     * Selects all divisions that match a specified country ID. 
     * Communicates with database to pull all first level divisions that match a specified
     * country ID and add them to an ObservableList.
     * @param country_Id Country ID to match first level divisions with
     * @return newDivision A first level division
     */
    public static FirstLevDiv selectAllDivisionsByCountry(int country_Id) {
        FirstLevDiv newDivision = null;
        try {
            String sqlDivByCountry = "SELECT first_level_divisions.COUNTRY_ID, first_level_divisions.Create_Date, first_level_divisions.Created_By, first_level_divisions.Division, first_level_divisions.Division_ID, first_level_divisions.Last_Update, first_level_divisions.Last_Updated_By, countries.Country_ID FROM first_level_divisions CROSS JOIN countries WHERE first_level_divisions.Country_ID = countries.Country_ID AND first_level_divisions.COUNTRY_ID = " + country_Id;
            PreparedStatement prepStateDiv = DBConnection.getConnection().prepareStatement(sqlDivByCountry);
            prepStateDiv.execute();
            ResultSet resultSet = prepStateDiv.getResultSet();
            
            while(resultSet.next()) {
                int division_id = resultSet.getInt("Division_ID");
                String division = resultSet.getString("Division");
                Timestamp create_date = resultSet.getTimestamp("Create_Date");
                String created_by = resultSet.getString("Created_By");
                Timestamp last_update = resultSet.getTimestamp("Last_Update");
                String last_updated_by = resultSet.getString("Last_Updated_By");
                int country_id = resultSet.getInt("Country_ID");
                
                newDivision = new FirstLevDiv(division_id, division, create_date, 
                    created_by, last_update, last_updated_by, country_id);
                DBFirstLevDiv.addDivisionByCountry(newDivision);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return newDivision;
    }
    
    /**
     * Selects one division by specified ID. 
     * Communicates with database to pull one first level division that matches
     * a specified ID.
     * @param divisionId A first level division ID
     * @return newDivision A first level division object
     */
    public static FirstLevDiv selectDivisionForUpdate(int divisionId) {
        FirstLevDiv newDivision = null;
        try {
            String sqlDivForUpdate = "SELECT first_level_divisions.COUNTRY_ID, first_level_divisions.Create_Date, first_level_divisions.Created_By, first_level_divisions.Division, first_level_divisions.Division_ID, first_level_divisions.Last_Update, first_level_divisions.Last_Updated_By FROM first_level_divisions CROSS JOIN countries WHERE first_level_divisions.Division_ID = " + divisionId + " AND first_level_divisions.COUNTRY_ID = countries.Country_ID";
            //String sqlDivForUpdate = "SELECT * FROM first_level_divisions WHERE first_level_divisions.Division_ID = " + divisionId;
            PreparedStatement prepStateDiv = DBConnection.getConnection().prepareStatement(sqlDivForUpdate);
            prepStateDiv.execute();
            ResultSet resultSet = prepStateDiv.getResultSet();
            
            while(resultSet.next()) {
                int division_id = resultSet.getInt("Division_ID");
                String division = resultSet.getString("Division");
                Timestamp create_date = resultSet.getTimestamp("Create_Date");
                String created_by = resultSet.getString("Created_By");
                Timestamp last_update = resultSet.getTimestamp("Last_Update");
                String last_updated_by = resultSet.getString("Last_Updated_By");
                int country_id = resultSet.getInt("Country_ID");
                
                newDivision = new FirstLevDiv(division_id, division, create_date, 
                    created_by, last_update, last_updated_by, country_id);
                DBFirstLevDiv.addDivisionForUpdate(newDivision);
               
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return newDivision;
    }
    
    /**
     * Adds first level division to an ObservableList. 
     * Adds first level division to an ObservableList after updating a first level division object.
     * @param division first level division object
     */
    public static void addDivisionForUpdate(FirstLevDiv division) {
        updateDivision.add(division);
    } 
    
    /**
     * Returns an ObservableList of updated first level divisions. 
     * Returns an ObservableList of updated first level divisions called updateDivisions.
     * @return updateDivision An ObservableList
     */
    public static ObservableList<FirstLevDiv> getUpdateDivision() {
        return updateDivision;
    }
    
    
    /**
     * Adds first level division to ObservableList. 
     * Adds first level division to ObservableList containing all first level divisions.
     * @param division A first level division
     */
    public static void addDivision(FirstLevDiv division) {
        allDivisions.add(division);
    }
    /**
     * Adds first level divisions filtered by country ID. 
     * Adds first level divisions filtered by country ID to ObservableList.
     * @param division First Level Division
     */
    public static void addDivisionByCountry(FirstLevDiv division) {
        allDivisionsByCountry.add(division);
    }
    /**
     * Getter for ObservableList. 
     * Returns allDivisions ObservableList.
     * @return allDivisions ObservableList
     */    
    public static ObservableList<FirstLevDiv> getAllDivisions() {
        return allDivisions;
    }
    /**
     * Getter for ObservableList filtered by country. 
     * Return ObservableList of first level divisions filtered by country ID.
     * @return allDivisionsByCountry ObservableList
     */
    public static ObservableList<FirstLevDiv> getAllDivisionsByCountry() {
        return allDivisionsByCountry;
    }
    
    /**
     * Setter for country ID. 
     * Sets country ID for country object.
     * @param division First Level Division
     * @return country_id Int country ID
     */
    public static int setCountryId(int division) {
        int country_id = -1;
        try {
            String sqlSetCountryId = "SELECT COUNTRY_ID FROM first_level_divisions WHERE first_level_divisions.COUNTRY_ID = " + division;
            PreparedStatement prepStateSetCountryId = DBConnection.getConnection().prepareStatement(sqlSetCountryId);
            prepStateSetCountryId.execute();
            ResultSet resultSet = prepStateSetCountryId.getResultSet();
      
            resultSet.next();
            country_id = resultSet.getInt("COUNTRY_ID");
            System.out.println(country_id);
        }catch(Exception e) {
            e.printStackTrace();
        }
        
        return country_id;
        
    }
    
        
}
