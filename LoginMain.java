/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import database.DBConnection;
import databaseAccess.DBCustomers;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import databaseAccess.DBAppointments;
import databaseAccess.DBContacts;
import databaseAccess.DBCountries;
import databaseAccess.DBFirstLevDiv;
import databaseAccess.DBUsers;

/**
 * This class is the both the main() method entry point for the application and 
 * the login main screen.
 * @author Mike Bliss
 */
public class LoginMain extends Application {
    
    

    /**
     * Entry point for application. 
     * Calls methods to pull from database to populate TableViews at launch.
     * @param args the command line arguments
     * @throws  Exception exception
     */
    public static void main(String[] args) throws Exception {
        
        // ********TO TEST APPLICATIoN IN FRENCH, UNCOMMENT THIS LINE***********
        //Locale.setDefault(new Locale("fr"));
        
        DBConnection.startConnection(); 
       

        DBCustomers.selectAllCustomers();
        DBAppointments.selectAllAppointments();
        DBAppointments.selectApptByMonth();
        DBAppointments.selectApptByWeek();
        DBCountries.selectAllCountries();
        DBContacts.selectAllContacts();
        DBUsers.selectAllUsers();
        DBAppointments.addType("Planning Session");
        DBAppointments.addType("De-briefing");

        
        
        launch(args);
        DBConnection.closeConnection();
    }

    /**
     * Start method for application. 
     * Called after main() method.
     * @param stage Stage
     * @throws Exception exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/LoginMain.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Build successful!");
        stage.show();
        
        
    }
    
}
