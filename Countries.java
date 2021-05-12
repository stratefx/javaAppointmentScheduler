/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * This class builds Countries objects. 
 * @author Mike Bliss
 */
public class Countries {
    
    private int country_id;
    private String country;
    private LocalDateTime create_date;
    private String created_by;
    private LocalDateTime last_update;
    private String last_updated_by;
    
    /**
     * Countries constructor with now signature. 
     * Countries constructor with now signature.
     */
    public Countries() {
        country = "United States";
    }

    /**
     * Countries object constructor. 
     * Builds Countries objects with user specified parameters. 
     * @param country_id int country id
     * @param country String country name
     * @param create_date Timestamp date created
     * @param created_by Users created by user
     * @param last_update Timestamp last update
     * @param last_updated_by Users last updated by user
     */
    public Countries(int country_id, String country, LocalDateTime create_date, String created_by, LocalDateTime last_update, String last_updated_by) {
        this.country_id = country_id;
        this.country = country;
        this.create_date = create_date;
        this.created_by = created_by;
        this.last_update = last_update;
        this.last_updated_by = last_updated_by;
    }

    /**
     * Getter for country id. 
     * Returns country id.
     * @return country_id int
     */
    public int getCountry_id() {
        return country_id;
    }

    /**
     * Setter for country_id. 
     * Sets country id.
     * @param country_id int 
     */
    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }

    /**
     * Getter for country. 
     * Returns country name.
     * @return country String name
     */
    public String getCountry() {
        return country;
    }

    /**
     * Setter for country. 
     * Sets country name.
     * @param country String name
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Getter for Countries create date. 
     * Returns Countries object create date.
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
     * Getter for created by.
     * Returns created by Users object.
     * @return Created_by  Users
     */
    public String getCreated_by() {
        return created_by;
    }

    /**
     * Setter for create by. 
     * Sets created_by Users objects.
     * @param created_by Users
     */
    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    /**
     * Getter for last update. 
     * Returns timestamp for last update.
     * @return last_update Timestamp
     */
    public LocalDateTime getLast_update() {
        return last_update;
    }

    /**
     * Setter for last update. 
     * Sets timestamp for last update. 
     * @param last_update Timestamp
     */
    public void setLast_update(LocalDateTime last_update) {
        this.last_update = last_update;
    }

    /**
     * Getter for last updated by. 
     * Returns Users object for last update by.
     * @return last_updated_by Users
     */
    public String getLast_updated_by() {
        return last_updated_by;
    }

    /**
     * Setter for last update by. 
     * Sets Users object for last updated by.
     * @param last_updated_by Users
     */
    public void setLast_updated_by(String last_updated_by) {
        this.last_updated_by = last_updated_by;
    }

    /**
     * toString() for Countries class. 
     * Returns toString() for Countries objects.
     * @return country String
     */
    @Override
    public String toString() {
        //return "Countries{" + "country_id=" + country_id + ", country=" + country + ", create_date=" + create_date + ", created_by=" + created_by + ", last_update=" + last_update + ", last_updated_by=" + last_updated_by + '}';
        return country;
    }
    
    
    
}
