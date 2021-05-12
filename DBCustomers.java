/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseAccess;

import com.mysql.cj.jdbc.exceptions.SQLError;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import database.DBConnection;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customers;


/**
 *
 * @author Mike Bliss
 */
public class DBCustomers {
    
    private static ObservableList<Customers> allCustomers = FXCollections.observableArrayList();
    private static int countryId;
    
    /**
     * Adds customer to ObservableList. 
     * Adds all customers to ObservableList allCustomers.
     * @param customer Customer object
     */
    public static void addCustomer(Customers customer) {
        allCustomers.add(customer);
    }
    
    /**
     * Removes customer from TableView. 
     * Removes customer object from ObservableList and TableView.
     * @param customer Customer object
     */
    public static void removeCustomerFromTblView(Customers customer) {
        allCustomers.remove(customer);
    }
    /**
     * Getter for ObservableList. 
     * Returns ObservableList allCustomers.
     * @return allCustomers ObservableList
     */
    public static ObservableList<Customers> getAllCustomers() {
        return allCustomers;
    }
    /**
     * Getter for country ID. 
     * Returns countryId int.
     * @return countryId Int
     */
    public static int getCountryId() {
       return countryId; 
    }
    
    /**
     * Selects all customers from database. 
     * Communicates with database to pull all customers and add them to an ObservableList.
     */
    public static void selectAllCustomers() {
        
        try {
            String customerSQL = "SELECT customers.Address, customers.Create_Date, customers.Created_By, customers.Customer_ID, customers.Customer_Name, customers.Division_ID, customers.Last_Update, customers.Last_Updated_By, customers.Phone, customers.Postal_Code, first_level_divisions.Division_ID, countries.Country_ID FROM customers CROSS JOIN first_level_divisions, countries WHERE first_level_divisions.Division_ID = customers.Division_ID AND first_level_divisions.COUNTRY_ID = countries.Country_ID";

            PreparedStatement prepStateCust = DBConnection.getConnection().prepareStatement(customerSQL);
            prepStateCust.execute();
            ResultSet resultSet = prepStateCust.getResultSet();
        
            while(resultSet.next()) {
                int customerId = resultSet.getInt("Customer_ID");
                String name = resultSet.getString("Customer_Name");
                String address = resultSet.getString("Address");
                String postalCode = resultSet.getString("Postal_Code");
                String phone = resultSet.getString("Phone");
                int divisionId = resultSet.getInt("Division_ID");
                LocalDateTime create_date = resultSet.getTimestamp("Create_Date").toLocalDateTime();
                String created_by = resultSet.getString("Created_By");
                LocalDateTime lastUpdate = resultSet.getTimestamp("Last_Update").toLocalDateTime();
                String lastUpdatedBy = resultSet.getString("Last_Updated_By");
                int country_id = resultSet.getInt("COUNTRY_ID");
                
                Customers customer = new Customers(customerId, name, address, postalCode, phone, divisionId, create_date, created_by, lastUpdate, lastUpdatedBy, country_id);
                DBCustomers.addCustomer(customer);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Inserts new customer parameters into database. 
     * Inserts new customer parameters into database and call constructor to create new 
     * customer objects, then adds them to an ObservableList for use in a TableView.
     * @param customer_name String customer name
     * @param address String customer address
     * @param postal_code String customer postal code
     * @param phone String customer phone number
     * @param create_date Timestamp date created
     * @param created_by User object creator of customer object
     * @param last_update Timestamp last update
     * @param lastUpdated_by User object that last updated
     * @param division_id int first level division id
     * @param country_id  int country id
     */
    public static void createCustomer(String customer_name, String address, String postal_code,
            String phone, LocalDateTime create_date, String created_by, LocalDateTime last_update, 
            String lastUpdated_by, int division_id, int country_id) {
        try {
            String sqlAddCust = "INSERT INTO customers VALUES(NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement prepStateAdd = DBConnection.getConnection().prepareStatement(sqlAddCust, Statement.RETURN_GENERATED_KEYS);
            
            prepStateAdd.setString(1, customer_name);
            prepStateAdd.setString(2, address);
            prepStateAdd.setString(3, postal_code);
            prepStateAdd.setString(4, phone);
            Timestamp create_dateTmStmp = Timestamp.valueOf(create_date);
            prepStateAdd.setTimestamp(5, create_dateTmStmp);
            prepStateAdd.setString(6, created_by);
            Timestamp last_updateTmStmp = Timestamp.valueOf(last_update);
            prepStateAdd.setTimestamp(7, last_updateTmStmp);
            prepStateAdd.setString(8, lastUpdated_by);
            prepStateAdd.setInt(9, division_id);
            
            prepStateAdd.execute();
            
            ResultSet rs = prepStateAdd.getGeneratedKeys();
            rs.next();
            int custId = rs.getInt(1);
            Customers customer = new Customers(custId, customer_name, address, postal_code, phone, division_id, create_date, created_by, last_update, lastUpdated_by, country_id/*countryId*/);
            DBCustomers.addCustomer(customer);
        }catch(Exception e) {
            e.printStackTrace();
        }
        
        
    }


    /**
     * Removes customer object from database. 
     * Removes customer object from database and ObservableList via selected customer id.
     * @param custId int Selected customer id
     */
    public static void deleteCustomer(int custId) {
        try {
            String sqlDeleteCust = "DELETE from customers WHERE Customer_ID = ?";
            PreparedStatement prepStateDelete = DBConnection.getConnection().prepareStatement(sqlDeleteCust);
            prepStateDelete.setInt(1, custId);
            prepStateDelete.execute();
            
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Updates customer object using user input parameters. 
     * @param custId int customer id
     * @param customer_name String customer name
     * @param address String customer address
     * @param postal_code String customer postal code
     * @param phone String customer phone number
     * @param created_by String user created
     * @param last_update Timestamp of last update
     * @param lastUpdated_by User last updated
     * @param division_id int first level division id
     * @param country_id  int country division id
     */
    public static void updateCustomer(int custId, String customer_name, String address, String postal_code,
            String phone, String created_by, LocalDateTime last_update, 
            String lastUpdated_by, int division_id, int country_id) {
        
        int customerId = custId;
        countryId = country_id;

        try {
            String sqlUpdateCust = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, "
                    + "Phone = ?, Last_Update = ?, Last_Updated_By = ?, Division_ID = ? WHERE customers.Customer_ID = " + customerId;
            PreparedStatement prepStateUpdate = DBConnection.getConnection().prepareStatement(sqlUpdateCust);
            
            prepStateUpdate.setString(1, customer_name);
            prepStateUpdate.setString(2, address);
            prepStateUpdate.setString(3, postal_code);
            prepStateUpdate.setString(4, phone);
            Timestamp last_updateTmStmp = Timestamp.valueOf(last_update);
            prepStateUpdate.setTimestamp(5, last_updateTmStmp);
            prepStateUpdate.setString(6, lastUpdated_by);
            prepStateUpdate.setInt(7, division_id);
            
            prepStateUpdate.execute();
            
            DBCustomers.allCustomers.clear();
            DBCustomers.selectAllCustomers();
        }catch(Exception e) {
            e.printStackTrace();
        }

    }
    
    /**
     * Filters customers by country. 
     * Filters customers by country us country id of customer object.
     * @return customersPerCountryReport
     */
    public static String customersPerCountry() {
        String customersPerCountryReport = "";
        int us = 0, uk = 0, canada = 0;
        for(Customers c: allCustomers) {
            if(c.getCountryId() == 1)
                us++;
            if(c.getCountryId() == 2)
                uk++;
            if(c.getCountryId() == 3)
                canada++;
        }
        
        customersPerCountryReport = "United States: " + us + "\n\n" + "United Kingdom: " + uk
                + "\n\n" + "Canada: " + canada;
        
        return customersPerCountryReport;
    }
    
    
}
