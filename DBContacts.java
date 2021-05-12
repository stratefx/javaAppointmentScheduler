/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseAccess;

import database.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contacts;

/**
 * This class communicates with database for all Contacts related queries. 
 * @author Mike Bliss
 */
public class DBContacts {
    public static ObservableList<Contacts> allContacts = FXCollections.observableArrayList();
    public static ObservableList<Contacts> oneContact = FXCollections.observableArrayList();
    
    /**
     * Selects all countries from database. 
     * Pulls all countries from database and adds them to an ObservabelList for
     * a TableView.
     */
    public static void selectAllContacts() {
        try {
            String contactsSQL = "SELECT Contact_ID, Contact_Name, Email FROM contacts";
            PreparedStatement prepStateAppt = DBConnection.getConnection().prepareStatement(contactsSQL);
            prepStateAppt.execute();
            ResultSet resultSetContacts = prepStateAppt.getResultSet();
            
            while(resultSetContacts.next()) {
                int contactId = resultSetContacts.getInt("Contact_ID");
                String contactName = resultSetContacts.getString("Contact_Name");
                String email = resultSetContacts.getString("Email");
                
                Contacts c = new Contacts(contactId, contactName, email);
                DBContacts.addContacts(c);
            }
        }catch(Exception e) {
                e.printStackTrace();
            }   
    }
    
        /**
         * Selects one contact via a specified contact id. 
         * Returns one contact via a user specified contact id. 
         * @param contactId int Contact id
         * @return c Contact object
         */
        public static Contacts selectOneContact(int contactId) {
            Contacts c = null;
            try {
                String contactsSQL = "SELECT * FROM contacts WHERE contacts.Contact_ID = " + contactId;
                PreparedStatement prepStateAppt = DBConnection.getConnection().prepareStatement(contactsSQL);
                prepStateAppt.execute();
                ResultSet resultSetContacts = prepStateAppt.getResultSet();
            
                while(resultSetContacts.next()) {
                    int contact_id = resultSetContacts.getInt("Contact_ID");
                    String contactName = resultSetContacts.getString("Contact_Name");
                    String email = resultSetContacts.getString("Email");

                    c = new Contacts(contact_id, contactName, email);
                    DBContacts.addOneContact(c);
                    
            }
        }catch(Exception e) {
                e.printStackTrace();
            } 
        return c;
    }
    
    
    /**
     * Adds Contacts objects to ObervableList. 
     * Adds Contacts objects to allContacts ObservableList for TableView.
     * @param c Contacts object
     */
    public static void addContacts(Contacts c) {
        allContacts.add(c);
    }
    
    /**
     * Getter for allContacts ObservableList. 
     * Returns allContacts ObservabelList.
     * @return allContacts ObservableList
     */
    public static ObservableList<Contacts> getAllContacts() {
        return allContacts;
    }
    
    /**
     * Adds one Contacts object to ObservableList. 
     * Adds one Contacts object to ObservabelList.
     * @param c Contacts object
     */
    public static void addOneContact(Contacts c) {
        oneContact.add(c);
    }
    
    /**
     * Getter for ObservabelList. 
     * Returns oneContact ObservabelList for TabelView.
     * @return oneContact ObservabelList
     */
    public static ObservableList<Contacts> getOneContact() {
        return oneContact;
    }
    
}
