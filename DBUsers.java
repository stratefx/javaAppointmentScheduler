/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseAccess;


import database.DBConnection;
import database.DBQuery;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import model.Users;


/**
 * This class handles user related database functions. 
 * Methods for user related communication with database.
 * @author Mike Bliss
 */
public class DBUsers {
    Stage stage;
    Parent scene;
    Users currentUser;
    
    public static ObservableList<Users> allUsers = FXCollections.observableArrayList();
    
    /**
     * Verifies login credentials and allows access to application. 
     * Communicates with database to compare username and password with credentials entered
     * by user.
     * @param user_name User entered username.
     * @param pass_word User entered password.
     * @return True or false
     * @throws IOException An exception
     */
    public static boolean userLogin(String user_name, String pass_word) throws IOException {
        Boolean validEntry = false;
        
        String fileName = "src/files/login_activity";
        FileWriter fileWriter = new FileWriter(fileName, true);
        PrintWriter file = new PrintWriter(fileWriter);
        
        try{
            // Retrieves text from username/password textfields
            String userName = user_name;
            String passWord = pass_word;


            String explainSQL = "SELECT * FROM users";
            Connection connection = DBConnection.getConnection();  
            DBQuery.setPreparedStatment(connection, explainSQL);                    
            PreparedStatement prepState = DBQuery.getPreparedStatement();

            prepState.execute();
            ResultSet resultSet = prepState.getResultSet();


            while(resultSet.next()) {
                int user_id = resultSet.getInt("User_ID");
                String username = resultSet.getString("User_Name");
                String password = resultSet.getString("Password");
                Timestamp create_date = resultSet.getTimestamp("Create_Date");
                String created_by = resultSet.getString("Created_By");
                Timestamp last_update = resultSet.getTimestamp("Last_Update");
                String last_updated_by = resultSet.getString("Last_Updated_By");


                
                
                // validates username/password & creates currentUser object
                if(userName.matches(username) && passWord.matches(password)){
                    // Logs login attempts if successful to filewriter
                    
                    file.println("Attempted login using username: " + userName +
                        " and password: " + passWord + " at date/time " + LocalDateTime.now() +
                        " - Login successful");
                    file.close();
                    validEntry = true;
                    System.out.println("Login credentials verified");
                    Users.setCurrentUser(user_id,username, password,create_date, created_by, last_update, last_updated_by);
                    return true;
                }
          
            }

            try {
                if(!(validEntry) && Locale.getDefault().getLanguage().equals("fr")) {
                    // Logs login attempts if unsuccessful to filewriter
                   
                    file.println("Attempted login using username: " + userName +
                        " and password: " + passWord + " at date/time " + LocalDateTime.now() +
                        " - Login unsuccessful");
                    file.close();
                    ResourceBundle rb = ResourceBundle.getBundle("model/lang", Locale.getDefault());
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle(rb.getString("password") + " " + rb.getString("Incorrect"));
                    alert.setContentText(rb.getString("again"));
                    Optional<ButtonType> result = alert.showAndWait();
                } else if (!(validEntry) && !(Locale.getDefault().getLanguage().equals("fr"))) {
                    // Logs login attempts if unsuccessful to filewriter
                    file.println("Attempted login using username: " + userName +
                    " and password: " + passWord + " at date/time " + LocalDateTime.now() +
                    " - Login unsuccessful");
                    file.close();
                    
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Incorrect password");
                    alert.setContentText("Try again");
                    Optional<ButtonType> result = alert.showAndWait();
                    }   
            } catch(Exception e) {
                e.printStackTrace();
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        
        return false;
        
    }
    

    /**
     * Selects all users. 
     * Called at launch of application to pull all users from database and add to a list.
     */
        public static void selectAllUsers() {
                try {
            String contactsSQL = "SELECT * from users";
            PreparedStatement prepStateAppt = DBConnection.getConnection().prepareStatement(contactsSQL);
            prepStateAppt.execute();
            ResultSet resultSet = prepStateAppt.getResultSet();
            
            while(resultSet.next()) {
                int user_id = resultSet.getInt("User_ID");
                String username = resultSet.getString("User_Name");
                String password = resultSet.getString("Password");
                Timestamp create_date = resultSet.getTimestamp("Create_Date");
                String created_by = resultSet.getString("Created_By");
                Timestamp last_update = resultSet.getTimestamp("Last_Update");
                String last_updated_by = resultSet.getString("Last_Updated_By");
                
                Users u = new Users(user_id, username, password, create_date, created_by, last_update, last_updated_by);
                DBUsers.addUser(u);
            }
        }catch(Exception e) {
                e.printStackTrace();
            }   
    }
    
    /**
     * Adds user to an ObservableList. 
     * Adds user to allUsers Observable list for later use.
     * @param u A User object
     */
    public static void addUser(Users u) {
            allUsers.add(u);
    }
    
    /**
     * Returns list of users. 
     * Returns ObservableList of all User objects.
     * @return allUsers An ObservableList
     */
    public static ObservableList<Users> getAllUsers() {
        return allUsers;
    }
        
        
    
    
    
    
    
}

           


    
  
    

