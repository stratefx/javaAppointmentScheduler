/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Timestamp;

/**
 * This class holds constructors and variables necessary for building FirstLevDiv objects. 
 * Creates first level division objects.
 * @author Mike Bliss
 */
public class FirstLevDiv {
    
    private int division_id;
    private String division;
    private Timestamp create_date;
    private String created_by;
    private Timestamp last_update;
    private String last_updated_by;
    private int country_id;
    
    /**
     * Constructor for first level division objects. 
     * Constructor for first level division objects.
     * @param division_id int division id
     * @param division String name
     * @param create_date Timestamp create date
     * @param created_by Users user created by
     * @param last_update Timestamp last updated
     * @param last_updated_by Users user last updated by
     * @param country_id int country id
     */
    public FirstLevDiv(int division_id, String division, Timestamp create_date, 
            String created_by, Timestamp last_update, String last_updated_by, int country_id) {
        this.division_id = division_id;
        this.division = division;
        this.create_date = create_date;
        this.created_by = created_by;
        this.last_update = last_update;
        this.last_updated_by = last_updated_by;
        this.country_id = country_id;
    }

    /**
     * Getter for division id. 
     * Returns first level division id.
     * @return division_id
     */
    public int getDivision_id() {
        return division_id;
    }

    /**
     * Sets division id. 
     * Sets first level division id.
     * @param division_id int first level division id
     */
    public void setDivision_id(int division_id) {
        this.division_id = division_id;
    }

    /**
     * Getter for division. 
     * Returns first level division name. 
     * @return division String division name
     */
    public String getDivision() {
        return division;
    }

    /**
     * Setter for division. 
     * Sets first level division name.
     * @param division String first level division name
     */
    public void setDivision(String division) {
        this.division = division;
    }

    /**
     * Getter for create_date. 
     * Returns timestamp for date created.
     * @return create_date Timestamp date created
     */
    public Timestamp getCreate_date() {
        return create_date;
    }

    /**
     * Setter for create date. 
     * Sets timestamp for date created.
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
     * Sets User created by.
     * @param created_by Users user created by
     */
    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    /**
     * Getter for last update. 
     * Returns timestamp for last update.
     * @return last_update Timestamp
     */
    public Timestamp getLast_update() {
        return last_update;
    }

    /**
     * Setter for last update. 
     * Sets timestamp at time of update.
     * @param last_update Timestamp
     */
    public void setLast_update(Timestamp last_update) {
        this.last_update = last_update;
    }

    /**
     * Getter for last updtaed by. 
     * Returns user who last updated.
     * @return last_updated_by Users
     */
    public String getLast_updated_by() {
        return last_updated_by;
    }

    /**
     * Setter for last updated by. 
     * Sets Users object who last updated.
     * @param last_updated_by Users
     */
    public void setLast_updated_by(String last_updated_by) {
        this.last_updated_by = last_updated_by;
    }

    /**
     * Getter for country id. 
     * Returns country id for corresponding country.
     * @return country_id
     */
    public int getCountry_id() {
        return country_id;
    }

    /**
     * Setter for country id. 
     * Sets country id for corresponding country.
     * @param country_id int country id
     */
    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }

    /**
     * toString() for first level division objects. 
     * Returns first level division object's toString().
     * @return division String name
     */
    @Override
    public String toString() {
        //return "FirstLevDiv{" + "division_id=" + division_id + ", division=" + division + ", create_date=" + create_date + ", created_by=" + created_by + ", last_update=" + last_update + ", last_updated_by=" + last_updated_by + ", country_id=" + country_id + '}';
        return division;
    }
    
    
    
    
}
