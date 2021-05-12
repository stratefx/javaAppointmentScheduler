/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseAccess;

import database.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Countries;

/**
 * This class communicates with the database for country DAO activity. 
 * @author Mike Bliss
 */
public class DBCountries {
    
    private static ObservableList<Countries> allCountries = FXCollections.observableArrayList();
    private static ObservableList<Countries> allCountriesByDivision = FXCollections.observableArrayList();
    
    /**
     * Selects all countries from the database. 
     * Pulls all countries from the database and adds them to an ObservableList for a TableView.
     */
    public static void selectAllCountries() {
        try {
            String countriesSQL = "SELECT * FROM countries";
            PreparedStatement prepStateCust = DBConnection.getConnection().prepareStatement(countriesSQL);
            prepStateCust.execute();
            ResultSet resultSet = prepStateCust.getResultSet();
            
            while(resultSet.next()) {
                int country_id = resultSet.getInt("Country_ID");
                String country = resultSet.getString("Country");
                LocalDateTime create_date = resultSet.getTimestamp("Create_Date").toLocalDateTime();
                String created_by = resultSet.getString("Created_By");
                LocalDateTime last_update = resultSet.getTimestamp("Last_Update").toLocalDateTime();
                String last_updated_by = resultSet.getString("Last_Updated_By");
                
                Countries newCountry = new Countries(country_id, country, create_date, created_by, last_update, last_updated_by);
                DBCountries.addCountry(newCountry);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Selects all countries from a database for a given first level division id. 
     * Filters countries by first level division id.
     * @param countryId int Country Id
     * @return newCountry Country object
     */
    public static Countries selectCountryByDivision(int countryId) {
        Countries newCountry = null;
        try{
            String sqlCountryByDiv = "SELECT * FROM countries WHERE countries.Country_ID = " + countryId;
            PreparedStatement prepStateCountryByDiv = DBConnection.getConnection().prepareStatement(sqlCountryByDiv);
            prepStateCountryByDiv.execute();
            ResultSet resultSet = prepStateCountryByDiv.getResultSet();
            
            while(resultSet.next()) {
                int country_id = resultSet.getInt("Country_ID");
                String country = resultSet.getString("Country");
                LocalDateTime create_date = resultSet.getTimestamp("Create_Date").toLocalDateTime();
                String created_by = resultSet.getString("Created_By");
                LocalDateTime last_update = resultSet.getTimestamp("Last_Update").toLocalDateTime();
                String last_updated_by = resultSet.getString("Last_Updated_By");
                
                // DO I REALLY NEED TO CREATE A NEW COUNTRY, OR CAN I JUST RETURN A COUNTRY OBJECT
                newCountry = new Countries(country_id, country, create_date, created_by, last_update, last_updated_by);
                DBCountries.AddCountriesByDivision(newCountry);
            } 
        }catch(Exception e){
            e.printStackTrace();
        }
        return newCountry;
    }
    
    /**
     * Adds country objects to ObservableList. 
     * Adds country objects to ObservableList allCountries for a TableView
     * @param country Country object
     */
    public static void addCountry(Countries country) {
        allCountries.add(country);
    }
    
    /**
     * Getter for allCountries ObservableList. 
     * Returns allCountries observableList.
     * @return allCountries ObservableList
     */
    public static ObservableList<Countries> getAllCountries() {
        return allCountries;
    }
    
    /**
     * Adds countries by first level division id. 
     * Adds countries by first level division id to ObservableList for TableView.
     * @param country Country object
     */
    public static void AddCountriesByDivision(Countries country) {
        allCountriesByDivision.add(country);
    }
    
    /**
     * Getter for ObservableList. 
     * Returns allCountriesByDivision filtered ObservableList.
     * @return allCountriesByDivision ObservableList
     */
    public static ObservableList<Countries> getAllCountriesByDivision() {
        return allCountriesByDivision;
    }
    
}
