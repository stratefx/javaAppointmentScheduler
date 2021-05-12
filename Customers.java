/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * This class creates customer objects.
 * @author Mike Bliss
 */
public class Customers {
    private int customerId;
    private String name;
    private String address;
    private String postalCode;
    private String phone;
    private LocalDateTime create_date;
    private String created_by;
    private LocalDateTime lastUpdate;
    private String lastUpdatedBy;
    private int divisionId;
    private int countryId;
    
    /**
     * Customer object constructor. 
     * Creates customer objects with user specified parameters.
     * @param id int customer id
     * @param name String customer name
     * @param address String customer address
     * @param postalCode String customer postal code
     * @param phone String customer phone number
     * @param divisionId int first level division id
     * @param create_date Timestamp date created
     * @param created_by Users created by user
     * @param lastUpdate Timestamp last update
     * @param lastUpdatedBy Users last updated by user
     * @param country_id int country id
     */
    public Customers(int id, String name, String address, String postalCode, String phone, 
            int divisionId, LocalDateTime create_date, String created_by, LocalDateTime lastUpdate, 
            String lastUpdatedBy, int country_id) {
        this.customerId = id;
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.create_date = create_date;
        this.created_by = created_by;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.divisionId = divisionId;
        this.countryId = country_id;
    }

    

    /**
     * Getter for customer id. 
     * Returns customer id.
     * @return customerId int customer id
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Setter for customer id. 
     * Sets customer id.
     * @param customerId int customer id
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * Getter for customer name. 
     * Returns customer name.
     * @return name String
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for customer name. 
     * Sets customer name.
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for customer address. 
     * Returns customer address.
     * @return address String
     */
    public String getAddress() {
        return address;
    }

    /**
     * Setter for customer address. 
     * Sets customer address.
     * @param address String
     */
    public void setAddress
        (String address) {
        this.address = address;
    }

    /**
     * Getter for customer postal code. 
     * Returns customer postal code.
     * @return postalCode int
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Setter for customer postal code. 
     * Sets customer postal code.
     * @param postalCode int
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Getter for customer phone number. 
     * Returns customer phone number.
     * @return phone String
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Setter for customer phone number. 
     * Sets customer phone number.
     * @param phone String
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Getter for created date. 
     * Returns date created.
     * @return created_date LocalDateTime
     */
    public LocalDateTime getCreatedDate() {
        return create_date;
    }

    /**
     * Setter for created date. 
     * Sets date created.
     * @param createdDate LocalDateTime
     */
    public void setCreatedDate(LocalDateTime createdDate) {
        this.create_date = createdDate;
    }

    /**
     * Getter for created by. 
     * Returns Users object created by.
     * @return created_by Users
     */
    public String getCreatedBy() {
        return created_by;
    }

    /**
     * Setter for created by. 
     * Sets created by user.
     * @param createdBy Users
     */
    public void setCreatedBy(String createdBy) {
        this.created_by = createdBy;
    }

    /**
     * Getter for lastUpdate. 
     * Returns last update timestamp.
     * @return lastUpdate Timestamp
     */
    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Setter for last update. 
     * Sets last update.
     * @param lastUpdate Timestamp 
     */
    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * Getter for last update by. 
     * Returns user who last updated.
     * @return lastUpdatedBy Users
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * Setter for last update by. 
     * Sets Users object who last updated.
     * @param lastUpdatedBy Users
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * Getter for division id. 
     * Returns first level division id.
     * @return divisionId int
     */
    public int getDivisionId() {
        return divisionId;
    }

    /**
     * Setter for division id. 
     * Sets first level division id for customer. 
     * @param divisionId int
     */
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }
    
    /**
     * Sets country id. 
     * Sets country id for customer.
     * @param countryId int
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }
    
    /**
     * Getter for country id. 
     * Returns country id for customer. 
     * @return countryId int
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * toString() for customer object. 
     * Returns customer object toString()
     * @return String
     */
    public String toString() {
        return customerId + "  - " + name + "      ";
        //return String.valueOf(countryId);
    }
    

    
    public String toStringFull() {
        return "Customer ID: " + customerId + " Customer Name: " + name + " Address: " + 
                address + " Postal Code: " + postalCode + " Phone: " + phone + " Create Date: " +
                create_date + " Created By: " + created_by + " Last Update: " + lastUpdate +
                " Last Updated By: " + lastUpdatedBy + " Division ID: " + divisionId +
                " Country ID: " + countryId;
    }


    
}
