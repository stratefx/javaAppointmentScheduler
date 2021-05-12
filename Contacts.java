/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * This class builds Contacts objects. 
 * @author Mike Bliss
 */
public class Contacts {
    
    private int contact_id;
    private String contact_name;
    private String email;
    
    /**
     * Contacts constructor. 
     * Builds Contacts objects from database.
     * @param id int
     * @param name String
     * @param email String
     */
    public Contacts(int id, String name, String email) {
        this.contact_id = id;
        this.contact_name = name;
        this.email = email;
    }

    /**
     * Getter for contact id. 
     * Returns contact id.
     * @return contact_id int
     */
    public int getContact_id() {
        return contact_id;
    }

    /**
     * Setter for contact id. 
     * Sets contact id.
     * @param contact_id int
     */
    public void setContact_id(int contact_id) {
        this.contact_id = contact_id;
    }

    /**
     * Getter for contact name. 
     * Returns contact name. 
     * @return contact_name String
     */
    public String getContact_name() {
        return contact_name;
    }

    /**
     * Setter for contact name. 
     * Sets contact name.
     * @param contact_name String 
     */
    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    /**
     * Getter for email. 
     * Returns email.
     * @return email String
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter for email. 
     * Sets email.
     * @param email String 
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * toString() for Contacts objects. 
     * toString() for Contacts objects.
     * @return String
     */
    @Override
    public String toString() {
        return contact_name + " " + contact_id;
    }
    
    
    
}
