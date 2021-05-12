/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Timestamp;
import java.time.LocalDateTime;


/**
 * This class builds Appointments objects. 
 * This class builds Appointments objects. 
 * @author Mike Bliss
 */
public class Appointments {
    
    private int appointment_ID;
    private String title;
    private String description;
    private String location;
    private String type;
    private LocalDateTime start;
    private LocalDateTime end;
    private LocalDateTime create_date;
    private String created_by;
    private LocalDateTime lastUpdate; 
    private String lastUpdated_by;
    private int customer_ID;
    private int user_ID;
    private int contact_ID;
    private String contact;
       
    /**
     * Constructor for Appointments objects. 
     * Constructor for Appointments objects.
     * @param appointment_ID int
     * @param title String
     * @param description String
     * @param location String
     * @param type String
     * @param start LocalDateTime
     * @param end LocalDateTime
     * @param create_date Timestamp
     * @param created_by Users
     * @param lastUpdate Timestamp
     * @param lastUpdated_by Users
     * @param customer_ID int
     * @param user_ID int
     * @param contact_ID int
     */
    public Appointments(int appointment_ID, String title, String description, String location, 
             String type, LocalDateTime start, LocalDateTime end, LocalDateTime create_date,
             String created_by, LocalDateTime lastUpdate, String lastUpdated_by, 
             int customer_ID, int user_ID, int contact_ID) {
        
        this.appointment_ID = appointment_ID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.create_date = create_date;
        this.created_by = created_by;
        this.lastUpdate = lastUpdate;
        this.lastUpdated_by = lastUpdated_by;
        this.customer_ID = customer_ID;
        this.user_ID = user_ID;
        this.contact_ID = contact_ID;
    }

    /**
     * Getter for appointment id. 
     * Returns appointments id.
     * @return appointment_id int
     */
    public int getAppointment_ID() {
        return appointment_ID;
    }

    /**
     * Setter for appointment id. 
     * Sets appointment id.
     * @param appointment_ID int 
     */
    public void setAppointment_ID(int appointment_ID) {
        this.appointment_ID = appointment_ID;
    }

    /**
     * Getter for title. 
     * Returns title.
     * @return title String
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter for title. 
     * Sets title.
     * @param title String 
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Getter for description. 
     * Sets description.
     * @return description String
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for description. 
     * Sets description.
     * @param description String 
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter for location. 
     * Returns location.
     * @return location String
     */
    public String getLocation() {
        return location;
    }

    /**
     * Setter for location. 
     * Sets location.
     * @param location String
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Getter for type. 
     * Returns type.
     * @return type String
     */
    public String getType() {
        return type;
    }

    /**
     * Setter for type. 
     * Sets type.
     * @param type String 
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Getter for start. 
     * Returns start LocalDateTime object.
     * @return start LocalDateTime
     */
    public LocalDateTime getStart() {
        return start;
    }

    /**
     * Setter for start. 
     * Sets start LocalDateTime object.
     * @param start LocalDateTime
     */
    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    /**
     * Getter for end. 
     * Returns end LocalDateTime object.
     * @return end LocalDateTime
     */
    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * Setter for end. 
     * Sets end LocalDateTime object.
     * @param end LocalDateTime
     */
    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    /**
     * Getter for create date. 
     * Returns create date timestamp.
     * @return create_date Timestamp
     */
    public LocalDateTime getCreate_date() {
        return create_date;
    }

    /**
     * Setter for create date. 
     * Sets create date timestamp.
     * @param create_date Timestamp
     */
    public void setCreate_date(LocalDateTime create_date) {
        this.create_date = create_date;
    }

    /**
     * Getter for last update. 
     * Returns last update timestamp.
     * @return lastUpdate LocalDateTime
     */
    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Setter for last update. 
     * Sets last update timestamp.
     * @param lastUpdate LocalDateTime
     */
    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * Getter for last updated by. 
     * Returns last updated by user.
     * @return lastUpdated_by Users
     */
    public String getLastUpdated_by() {
        return lastUpdated_by;
    }

    /**
     * Setter for last updated by. 
     * Sets last updated by Users object.
     * @param lastUpdated_by Users
     */
    public void setLastUpdated_by(String lastUpdated_by) {
        this.lastUpdated_by = lastUpdated_by;
    }

    /**
     * Getter for customer id. 
     * Returns customer id.
     * @return customer_ID int
     */
    public int getCustomer_ID() {
        return customer_ID;
    }

    /**
     * Setter for customer id. 
     * Sets customer id.
     * @param customer_ID int 
     */
    public void setCustomer_ID(int customer_ID) {
        this.customer_ID = customer_ID;
    }

    /**
     * Getter for user id. 
     * Returns user id.
     * @return user_ID int
     */
    public int getUser_ID() {
        return user_ID;
    }

    /**
     * Setter for user id. 
     * Sets user id.
     * @param user_ID int 
     */
    public void setUser_ID(int user_ID) {
        this.user_ID = user_ID;
    }

    /**
     * Getter for contact id. 
     * Returns contact id.
     * @return contact_ID int
     */
    public int getContact_ID() {
        return contact_ID;
    }

    /**
     * Setter for contact id. 
     * Sets contact id.
     * @param contact_ID int 
     */
    public void setContact_ID(int contact_ID) {
        this.contact_ID = contact_ID;
    }
    
    /**
     * Getter for contact. 
     * Returns contact. 
     * @return contact 
     */
    public String getContact() {
        return contact;
    }
    
    /**
     * Setter for contact. 
     * Sets contact. 
     * @param contact Contacts object
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * Appointments object toString(). 
     * Returns Appointments object toString().
     * @return String
     */
    @Override
    public String toString() {
        return "Appointments{" + "appointment_ID=" + appointment_ID + ", title=" + title + ", description=" + description + ", location=" + location + ", type=" + type + ", start=" + start + ", end=" + end + ", create_date=" + create_date + ", lastUpdate=" + lastUpdate + ", lastUpdated_by=" + lastUpdated_by + ", customer_ID=" + customer_ID + ", user_ID=" + user_ID + ", contact_ID=" + contact_ID + ", contact=" + contact + '}';
    }


    
}
