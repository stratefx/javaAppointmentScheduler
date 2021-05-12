/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Timestamp;

/**
 *
 * @author Mike Bliss
 */
public final class Users {
    
    private int user_id;
    private String user_name;
    private String password;
    private Timestamp create_date;
    private String created_by;
    private Timestamp last_update;
    private String last_updated_by;
    
    private Users currentUser = null;
    private static Users user = null;
    
    /**
     * Users constructor. 
     * Constructor for creating Users objects with no signature.
     */
    public Users() {
        this.user_id = this.getCurrentUser().getUser_id();
        this.user_name = this.getCurrentUser().getUser_name();
        this.password = this.getCurrentUser().getPassword();
        this.create_date = this.getCurrentUser().getCreate_date();
        this.last_update = this.getCurrentUser().getLast_update();
        this.last_updated_by = this.getCurrentUser().getLast_updated_by();
    }

    /**
     * Users constructor. 
     * Creates Users objects.
     * @param user_id int User id
     * @param user_name String user name
     * @param password String user password
     * @param create_date Timestamp date created
     * @param created_by User created by
     * @param last_update Timestamp last update
     * @param last_updated_by User updated by
     */
    public Users(int user_id, String user_name, String password, Timestamp create_date, String created_by, Timestamp last_update, String last_updated_by) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.password = password;
        this.create_date = create_date;
        this.created_by = created_by;
        this.last_update = last_update;
        this.last_updated_by = last_updated_by;
    }

    /**
     * Getter for user id. 
     * Returns user id.
     * @return user_id int user id
     */
    public int getUser_id() {
        return user_id;
    }

    /**
     * Setter for user id. 
     * Sets user id.
     * @param user_id int user id 
     */
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    /**
     * Getter for user name. 
     * Returns user name.
     * @return user_name String user name
     */
    public String getUser_name() {
        return user_name;
    }

    /**
     * Setter for user name. 
     * Sets user name.
     * @param user_name String username
     */
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    /**
     * Getter for user password. 
     * Returns user password.
     * @return password String user password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter for user password. 
     * Sets user password.
     * @param password String user password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter for create date. 
     * Returns create date timestamp.
     * @return create_date Timestamp date created
     */
    public Timestamp getCreate_date() {
        return create_date;
    }

    /**
     * Setter for create date. 
     * Sets create date timestamp.
     * @param create_date Timestamp date created
     */
    public void setCreate_date(Timestamp create_date) {
        this.create_date = create_date;
    }

    /**
     * Getter for created by. 
     * Returns user created by.
     * @return created_by User created by
     */
    public String getCreated_by() {
        return created_by;
    }

    /**
     * Setter for created by. 
     * Sets created by.
     * @param created_by User created by
     */
    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    /**
     * Getter for last update. 
     * Returns timestamp for last update.
     * @return last_update Timestamp last update
     */
    public Timestamp getLast_update() {
        return last_update;
    }

    /**
     * Setter for last update. 
     * Sets last update timestamp.
     * @param last_update Timestamp last update.
     */
    public void setLast_update(Timestamp last_update) {
        this.last_update = last_update;
    }

    /**
     * Getter for last updated by. 
     * Returns user last updated.
     * @return last_updated_by User
     */
    public String getLast_updated_by() {
        return last_updated_by;
    }

    /**
     * Setter for last updated by. 
     * Sets user last updated.
     * @param last_updated_by User last updated by
     */
    public void setLast_updated_by(String last_updated_by) {
        this.last_updated_by = last_updated_by;
    }
    
    /**
     * Getter for current user. 
     * Returns current user logged in.
     * @return user User
     */
    public static Users getCurrentUser() {      
        return user;
    }
    
    /**
     * Setter for current user. 
     * Sets current user according to login credentials.
     * @param user_id int user id
     * @param username String user name
     * @param password String password
     * @param create_date Timestamp create date
     * @param created_by String user name created by
     * @param last_update Timestamp last update
     * @param last_updated_by User last updated by user
     */
    public static void setCurrentUser(int user_id, String username, String password, Timestamp create_date, String created_by, Timestamp last_update, String last_updated_by) {
        Users currentUser = new Users(user_id, username, password, create_date, created_by, last_update, last_updated_by);
        user = currentUser;
    }
  
    /**
     * toString() method for Users class. 
     * Returns a toString() for Users class.
     * @return String
     */
    @Override
    public String toString() {
        return "ID: " + String.valueOf(user_id) + "     " + "Name: " + user_name;
    }
    
}
