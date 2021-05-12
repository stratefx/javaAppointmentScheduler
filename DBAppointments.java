/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseAccess;

import database.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointments;
import model.Users;


/**
 *
 * @author Mike Bliss
 */
public class DBAppointments {
    
    private static ObservableList<Appointments> allAppointments = FXCollections.observableArrayList();
    private static ObservableList<String> types = FXCollections.observableArrayList();
    private static ObservableList<Appointments> appointmentsByWeek = FXCollections.observableArrayList();
    private static ObservableList<Appointments> appointmentsByMonth = FXCollections.observableArrayList();
    
    
    /**
     * Selects all appointments from database. 
     * Selects all appointments from database and adds them to an ObservableList
     * to be displayed in a TableView
     */
    public static void selectAllAppointments() {
        
        try {
            String appointmentSQL = "SELECT * from appointments";
            PreparedStatement prepStateAppt = DBConnection.getConnection().prepareStatement(appointmentSQL);
            prepStateAppt.execute();
            ResultSet resultSetAppt = prepStateAppt.getResultSet();
            
            while(resultSetAppt.next()) {
                int appointment_id = resultSetAppt.getInt("Appointment_ID");
                String title = resultSetAppt.getString("Title");
                String description = resultSetAppt.getString("Description");
                String location = resultSetAppt.getString("Location");
                int contact_id = resultSetAppt.getInt("Contact_ID");
                String type = resultSetAppt.getString("Type");
                LocalDateTime start = resultSetAppt.getTimestamp("Start").toLocalDateTime();
                LocalDateTime end = resultSetAppt.getTimestamp("End").toLocalDateTime();
                int customer_id = resultSetAppt.getInt("Customer_ID");
                LocalDateTime create_date = resultSetAppt.getTimestamp("Create_Date").toLocalDateTime();
                String created_by = resultSetAppt.getString("Created_By");
                LocalDateTime lastUpdate = resultSetAppt.getTimestamp("Last_Update").toLocalDateTime();
                String lastUpdated_by = resultSetAppt.getString("Last_Updated_By");
                int user_ID = resultSetAppt.getInt("User_ID");
            
                Appointments appointment = new Appointments(appointment_id, title, description, location, 
                    type, start, end, create_date, created_by, lastUpdate, lastUpdated_by, 
                    customer_id, user_ID, contact_id);
                
                DBAppointments.addAppointment(appointment); 
            }   
        }catch(Exception e) {
            e.printStackTrace();
        }
        
    }
    
    /**
     * Inserts parameters into database to create a new appointment. 
     * Inserts parameters from user input to database and calls appointment constructor to create
     * a new customer object.
     * @param title String title of appointment
     * @param description String description of appointment
     * @param location String address
     * @param type Type of appointment
     * @param start String Start time for SQL insert
     * @param startDtime Start LocalDateTime object
     * @param end String end time for SQL insert
     * @param endDtime End LocalDateTime object
     * @param create_date Timestamp for date of creation
     * @param created_by User that created customer
     * @param lastUpdate Timestamp of last time/date it was updated
     * @param lastUpdated_by User that last updated appointment
     * @param customer_ID Customer ID
     * @param user_ID ID of user associated with appointment
     * @param contact_ID  Contact ID of associated contact
     */
    public static void createAppointment(String title, String description, String location, 
             String type, String start, LocalDateTime startDtime, String end, LocalDateTime endDtime, LocalDateTime create_date,
             String created_by, LocalDateTime lastUpdate, String lastUpdated_by, 
             int customer_ID, int user_ID, int contact_ID) {
        
        try {
            String addApptSql = "INSERT INTO appointments VALUES(NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement prepStateAddAppt = DBConnection.getConnection().prepareStatement(addApptSql, Statement.RETURN_GENERATED_KEYS);

            prepStateAddAppt.setString(1, title);
            prepStateAddAppt.setString(2, description);
            prepStateAddAppt.setString(3, location);
            prepStateAddAppt.setString(4, type);
            String startDT = start;
            prepStateAddAppt.setString(5, startDT);
            String endDT = end;
            prepStateAddAppt.setString(6, endDT);
            Timestamp createDT = Timestamp.valueOf(create_date);
            prepStateAddAppt.setTimestamp(7, createDT);
            prepStateAddAppt.setString(8, created_by);
            Timestamp lastUpdateDT = Timestamp.valueOf(lastUpdate);
            prepStateAddAppt.setTimestamp(9, lastUpdateDT);
            prepStateAddAppt.setString(10, lastUpdated_by);
            prepStateAddAppt.setInt(11 ,customer_ID);
            prepStateAddAppt.setInt(12, user_ID);
            prepStateAddAppt.setInt(13, contact_ID);
            
            prepStateAddAppt.execute();
            
            ResultSet rs = prepStateAddAppt.getGeneratedKeys();
            rs.next();
            int apptId = rs.getInt(1);
            
            Appointments newAppointment = new Appointments(apptId, title, description, location, 
                type, startDtime, endDtime, create_date, created_by, lastUpdate, lastUpdated_by, customer_ID, user_ID, contact_ID);
            
            DBAppointments.addAppointment(newAppointment);
            
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Deletes appointment from database. 
     * Delete appointment from database via specified appointment ID.
     * @param apptId Int appointment ID 
     */
    public static void deleteAppointment(int apptId) {
        try {
            String sqlDeleteCust = "DELETE from appointments WHERE Appointment_ID = ?";
            PreparedStatement prepStateDelete = DBConnection.getConnection().prepareStatement(sqlDeleteCust);
            prepStateDelete.setInt(1, apptId);
            prepStateDelete.execute();
            
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    

    /**
     * Updates appointment in database.UPdates appointment in database via specified appointment ID.
     * @param apptId int Appointment id
     * @param title String title of appointment
     * @param description String description of appointment
     * @param location String address
     * @param type Type of appointment
     * @param startStr String start
     * @param endStr String end
     * @param lastUpdate Timestamp of last time/date it was updated
     * @param lastUpdated_by User that last updated appointment
     * @param customer_ID Customer ID
     * @param user_ID ID of user associated with appointment
     * @param contact_ID  Contact ID of associated contact
     */
    public static void updateAppointment(int apptId, String title, String description, String location, 
        String type, String startStr, /*LocalDateTime start */ String endStr, /*LocalDateTime end */ LocalDateTime lastUpdate, String lastUpdated_by, 
        int customer_ID, int user_ID, int contact_ID) {
        
        try {
            String updateApptSQL = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Last_Update = ?, Last_Updated_By = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE appointments.Appointment_ID = " + apptId;

            PreparedStatement prepStateAddAppt = DBConnection.getConnection().prepareStatement(updateApptSQL);

            prepStateAddAppt.setString(1, title);
            prepStateAddAppt.setString(2, description);
            prepStateAddAppt.setString(3, location);
            prepStateAddAppt.setString(4, type);
            //Timestamp startDT = Timestamp.valueOf(startDT);
            prepStateAddAppt.setString(5, startStr);
            //Timestamp endDT = Timestamp.valueOf(endDT);
            prepStateAddAppt.setString(6, endStr);
            Timestamp lastUpdateDT = Timestamp.valueOf(lastUpdate);
            prepStateAddAppt.setTimestamp(7, lastUpdateDT);
            prepStateAddAppt.setString(8, lastUpdated_by);
            prepStateAddAppt.setInt(9 ,customer_ID);
            prepStateAddAppt.setInt(10, user_ID);
            prepStateAddAppt.setInt(11, contact_ID);
            
            prepStateAddAppt.execute();
            
            DBAppointments.allAppointments.clear();
            DBAppointments.selectAllAppointments();
            
            
            
            
            //ResultSet rs = prepStateAddAppt.getGeneratedKeys();
            //rs.next();
            //int apptId = rs.getInt(1);
            
            //Appointments newAppointment = new Appointments(apptId, title, description, location, 
            //    type, start, end, lastUpdate, lastUpdated_by, customer_ID, user_ID, contact_ID);
            
            //DBAppointments.addAppointment(newAppointment);
            
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks all appointments to determine if any are upcoming within 15 minutes.
     * Checks all appointments to determine if current user has any appointments
     * upcoming within 15 minutes from login.
     * @param user Current user
     * @return upApptStr StringBuilder that holds current user upcoming appointment info
     */
    public static String upcomingAppointments(Users user) {
        ArrayList<Appointments> apptArray = new ArrayList<Appointments>();
        int userId = user.getUser_id();
        StringBuilder upApptStr = new StringBuilder();
        
        // Loop through all appointments to match current user with appointments user
        for(Appointments a: allAppointments) {
            if(userId == a.getUser_ID()) {
                apptArray.add(a);
                }
            }

        // Loop through appointments who's user matches current user to check if appointments
        // begin within 15 minutes or not
        for(Appointments a: apptArray) {
            LocalDate date = a.getStart().toLocalDate();
            LocalTime time = a.getStart().toLocalTime();
            LocalTime currentTime = LocalTime.now();
            LocalDate currentDate = LocalDate.now();
            int id = a.getAppointment_ID();
           
            long minutes = ChronoUnit.MINUTES.between(currentTime, time);      
            
            if(minutes <= 15 && date.equals(currentDate)) {
                upApptStr.append("Apptointment ID: " + id + "  Date: " + date + "  Time: " + time + "\n"); 
            }
        } 
        
        return upApptStr.toString();
    }

    /**
     * Checks appointment start and end times to see if new or updated appointments overlap. 
     * Checks appointment start and end times to see if new or updated appointments overlap via
     * specified customer ID
     * @param userSelectStart Start time user selected
     * @param userSelectEnd End time user selected
     * @param customerId Selected associated customer
     * @param apptId Selected appointment ID
     * @return overlap Boolean returned true if there's an overlap
     */
    public static boolean checkOverlapUpdate(LocalDateTime userSelectStart, LocalDateTime userSelectEnd, int customerId, int apptId) {
        boolean overlap = false;
        
        LocalDate userSelStartDate = userSelectStart.toLocalDate();
        LocalTime userSelStartTime = userSelectStart.toLocalTime();
        
        LocalDate userSelEndDate = userSelectEnd.toLocalDate();
        LocalTime userSelEndTime = userSelectEnd.toLocalTime();
        
        // setting these up for use inside enhanced for loop
        // they will create LocalDateTime START for each appointment in loop
        LocalDate startDate;
        LocalTime startTime;


        // setting these up for use inside enhanced for loop
        // they will create LocalDateTime END for each appointment in loop
        LocalDate endDate;
        LocalTime endTime;
        
        for(Appointments a: allAppointments) {
            startDate = a.getStart().toLocalDate();
            startTime = a.getStart().toLocalTime();

            endDate = a.getEnd().toLocalDate();
            endTime = a.getEnd().toLocalTime();
            
            if(customerId == a.getCustomer_ID() && userSelStartDate.equals(startDate) && userSelEndDate.equals(endDate)){
                if(userSelStartTime.isBefore(endTime) || userSelStartTime.equals(endTime) && userSelStartTime.isAfter(startTime) || userSelStartTime.equals(startTime)){
                    overlap = true;
                }
                if(userSelEndTime.isAfter(startTime) || userSelEndTime.equals(startTime) && userSelEndTime.isBefore(endTime) || userSelEndTime.equals(endTime)){
                    overlap = true;
                }
                if(apptId == a.getAppointment_ID()){
                    overlap = false;
                }
            }
        }
        return overlap;
    }
    
    /**
     * Checks for time overlaps when adding a new appointment. 
     * Checks for time overlaps when adding  a new appointment against EST.
     * @param userSelectStart Selected start time
     * @param userSelectEnd Selected end time
     * @param customerId Customer ID
     * @return overlap Boolean value returns true if overlap exists
     */
    public static boolean checkOverlapAdd(LocalDateTime userSelectStart, LocalDateTime userSelectEnd, int customerId) {
        boolean overlap = false;
        
        LocalDate userSelStartDate = userSelectStart.toLocalDate();
        LocalTime userSelStartTime = userSelectStart.toLocalTime();
        
        LocalDate userSelEndDate = userSelectEnd.toLocalDate();
        LocalTime userSelEndTime = userSelectEnd.toLocalTime();
        
        // setting these up for use inside enhanced for loop
        // they will create LocalDateTime START for each appointment in loop
        LocalDate startDate;
        LocalTime startTime;


        // setting these up for use inside enhanced for loop
        // they will create LocalDateTime END for each appointment in loop
        LocalDate endDate;
        LocalTime endTime;
        
        for(Appointments a: allAppointments) {
            startDate = a.getStart().toLocalDate();
            startTime = a.getStart().toLocalTime();

            endDate = a.getEnd().toLocalDate();
            endTime = a.getEnd().toLocalTime();
         
        //  ********* TIME ZONE CONVERSION OR CHECK HERE - what affect would that have?***********    
            
        // can this be used somehow
        // ZonedDateTime now = ZonedDateTime.now();
            
            if((customerId == a.getCustomer_ID() && userSelStartDate.equals(startDate)) && userSelEndDate.equals(endDate)){
                if((userSelStartTime.isBefore(endTime) || userSelStartTime.equals(endTime)) && (userSelStartTime.isAfter(startTime) || userSelStartTime.equals(startTime))){
                    overlap = true;
                }
                if((userSelEndTime.isAfter(startTime) || userSelEndTime.equals(startTime)) && (userSelEndTime.isBefore(endTime) || userSelEndTime.equals(endTime))){
                    overlap = true;
                }
            }
            
        }
        return overlap;
    }
    
    /**
     * Checks if user selected days are Saturday or Sunday. 
     * Checks if user selected days are Saturday or Sunday.
     * @param startDate Selected Start date
     * @param endDate Selected End date
     * @return Boolean true or false
     */
    public static boolean checkSatSun(LocalDateTime startDate, LocalDateTime endDate) {
        if(startDate.getDayOfWeek().equals(SATURDAY) || startDate.getDayOfWeek().equals(SUNDAY) || endDate.getDayOfWeek().equals(SATURDAY) || endDate.getDayOfWeek().equals(SUNDAY)) 
            return true;
        else
            return false;
    }
    
    /**
     * Selects appointments for current week. 
     * Selects appointments for current week to filter TableView.
     */
    public static void selectApptByWeek() {
        appointmentsByWeek.clear();
        int currentDayOfWeek = LocalDate.now().getDayOfWeek().getValue();
        //int currentDayOfMonth = LocalDate.now().getDayOfMonth();
        LocalDate currentDayOfMonthTest = LocalDate.now();      //.plusDays(currentDayOfMonth);
        
        for(Appointments a: allAppointments) {
            int appsDayValues = a.getStart().getDayOfMonth();

            // MONDAY
            if(currentDayOfWeek == 1){
                for(int i = 0; i <= 4; i++) {
                    if(a.getStart().getDayOfMonth() == currentDayOfMonthTest.plusDays(i).getDayOfMonth()) {
                        DBAppointments.addApptsByWeek(a);
                    }
                }
            }
                
            // TUESDAY
            if(currentDayOfWeek == 2){
                for(int i = 0; i <= 3; i++) {
                    if(a.getStart().getDayOfMonth() == currentDayOfMonthTest.plusDays(i).getDayOfMonth()) {
                        DBAppointments.addApptsByWeek(a);
                    }
                    if(a.getStart().getDayOfMonth() == currentDayOfMonthTest.minusDays(currentDayOfWeek - 1).getDayOfMonth()) {
                        DBAppointments.addApptsByWeek(a);
                    }
                }  
            }
             
            // WEDNESDAY
            if(currentDayOfWeek == 3){
                for(int i = 0; i <= 2; i++) {
                    if(a.getStart().getDayOfMonth() == currentDayOfMonthTest.plusDays(i).getDayOfMonth()) {
                        DBAppointments.addApptsByWeek(a);
                    }
                    if(a.getStart().getDayOfMonth() == currentDayOfMonthTest.minusDays(currentDayOfWeek - 1).getDayOfMonth()) {
                        DBAppointments.addApptsByWeek(a);
                    }
                } 
            }
            
                
            // THURSDAY
            if(currentDayOfWeek == 4){
                for(int i = 0; i <= 1; i++) {
                    if(a.getStart().getDayOfMonth() == currentDayOfMonthTest.plusDays(i).getDayOfMonth()) {
                        DBAppointments.addApptsByWeek(a);
                    }
                    if(a.getStart().getDayOfMonth() == currentDayOfMonthTest.minusDays(currentDayOfWeek - 1).getDayOfMonth()) {
                        DBAppointments.addApptsByWeek(a);
                    }
                } 
            }
                                                
            // FRIDAY
            if(currentDayOfWeek == 5){
                for(int i = 5; i >= 0; i--) {
                    if(a.getStart().getDayOfMonth() == currentDayOfMonthTest.minusDays(i - 1).getDayOfMonth()) {
                        DBAppointments.addApptsByWeek(a);
                    }
                } 
            }
            
            // SATURDAY
            if(currentDayOfWeek == 6){
                for(int i = 5; i >= 1; i--) {
                    if(a.getStart().getDayOfMonth() == currentDayOfMonthTest.minusDays(i).getDayOfMonth()) {
                        DBAppointments.addApptsByWeek(a);
                    }
                } 
            }
            
            // SUNDAY
            if(currentDayOfWeek == 7){
                for(int i = 5; i >= 0; i--) {
                    if(a.getStart().getDayOfMonth() == currentDayOfMonthTest.minusDays(i + 1).getDayOfMonth()) {
                        DBAppointments.addApptsByWeek(a);
                    }
                } 
            }
        }
    }
    
    /**
     * Selects appointments for current month. 
     * Selects appointments for current month to filter TableView.
     */
    public static void selectApptByMonth() {
        appointmentsByMonth.clear();
        int currentMonth = LocalDate.now().getMonthValue();
        for(Appointments a: allAppointments) {
            if(a.getStart().getMonth().getValue() == currentMonth) {
                DBAppointments.addApptsByMonth(a);
            }
        }
    }
    
    /**
     * Generates customer appointment report. 
     * Generates customer appointment report by type and month.
     * @return contactReport String report
     */
    public static String customerApptReport() {
        String contactReport = "";
        int janPlan = 0, janDeb = 0, febPlan = 0, febDeb = 0, marchPlan = 0, marchDeb = 0,
                aprilPlan = 0, aprilDeb = 0, mayPlan = 0, mayDeb = 0, junePlan = 0, juneDeb = 0,
                julyPlan = 0, julyDeb = 0, augPlan = 0, augDeb = 0, sepPlan = 0, sepDeb = 0,
                octPlan = 0, octDeb = 0, novPlan = 0, novDeb = 0, decPlan = 0, decDeb = 0;
        
        
        for(Appointments a: allAppointments) {
            
            System.out.println(a.getType());
        
            if((a.getStart().getMonthValue() == 1) && (a.getType().matches("Planning Session"))){
                janPlan++;
            }else if(a.getStart().getMonthValue() == 1){
                janDeb++;
            }
            
            if((a.getStart().getMonthValue() == 2) && (a.getType().matches("Planning Session"))){
                febPlan++;
            }else if(a.getStart().getMonthValue() == 2){
                febDeb++;    
            }
            
            if((a.getStart().getMonthValue() == 3) && (a.getType().matches("Planning Session"))){
                marchPlan++;
            }else if(a.getStart().getMonthValue() == 3){
                marchDeb++;
            }
            
            if((a.getStart().getMonthValue() == 4) && (a.getType().matches("Planning Session"))){
                aprilPlan++;
            }else if(a.getStart().getMonthValue() == 4){
                aprilDeb++;
            }
            
            if((a.getStart().getMonthValue() == 5) && (a.getType().matches("Planning Session"))){
                mayPlan++;
            }else if(a.getStart().getMonthValue() == 5){
                mayDeb++;
            }
            
            if((a.getStart().getMonthValue() == 6) && (a.getType().matches("Planning Session"))){
                junePlan++;
            }else if(a.getStart().getMonthValue() == 6){
                juneDeb++;
            }
           
            if((a.getStart().getMonthValue() == 7) && (a.getType().matches("Planning Session"))){
                julyPlan++;
            }else if(a.getStart().getMonthValue() == 7){
                julyDeb++;
            }
            
            if((a.getStart().getMonthValue() == 8) && (a.getType().matches("Planning Session"))){
                augPlan++;
            }else if(a.getStart().getMonthValue() == 8){
                augDeb++;
            }
            
            if((a.getStart().getMonthValue() == 9) && (a.getType().matches("Planning Session"))){
                sepPlan++;
            }else if(a.getStart().getMonthValue() == 9) {
                sepDeb++;
            }
           
            if((a.getStart().getMonthValue() == 10) && (a.getType().matches("Planning Session"))){
                octPlan++;
            }else if(a.getStart().getMonthValue() == 10){
                octDeb++;
            }
            
            if((a.getStart().getMonthValue() == 11) && (a.getType().matches("Planning Session"))){
                novPlan++;
            }else if(a.getStart().getMonthValue() == 11){
                novDeb++;
            }
            
            if((a.getStart().getMonthValue() == 12) && (a.getType().matches("Planning Session"))){
                decPlan++;
            }else if(a.getStart().getMonthValue() == 12){
                decDeb++;
            }
            
            contactReport = "Customer appointments by type and month: \n\n"
                    + "January - Planning: " + janPlan + " Debriefing: " + janDeb + "\n"
                    + "February - Planning: " + febPlan + " Debriefing: " + febDeb + "\n"
                    + "March - Planning: " + marchPlan + " Debriefing: " + marchDeb + "\n"
                    + "April - Planning: " + aprilPlan + " Debriefing: " + aprilDeb + "\n"
                    + "May - Planning: " + mayPlan + " Debriefing: " + mayDeb + "\n"
                    + "June - Planning: " + junePlan + " Debriefing: " + juneDeb + "\n"
                    + "July - Planning: " + julyPlan + " Debriefing: " + julyDeb + "\n"
                    + "August - Planning: " + augPlan + " Debriefing: " + augDeb + "\n"
                    + "September - Planning: " + sepPlan + " Debriefing: " + sepDeb + "\n"
                    + "October - Planning: " + octPlan + " Debriefing: " + octDeb + "\n"
                    + "November - Planning: " + novPlan + " Debriefing: " + novDeb + "\n"
                    + "December - Planning: " + decPlan + " Debriefing: " + decDeb;
            
            }
        
        return contactReport;
    }
    
    /**
     * Generates appointment schedule for each contact. 
     * Generates appointment schedule for each contact and prints to a  String.
     * @return str String report
     */
    public static String contactScheduleReport() {
        ArrayList<String> contact1 = new ArrayList();
        ArrayList<String> contact2 = new ArrayList();
        ArrayList<String> contact3 = new ArrayList();
        
        for(Appointments a: allAppointments) {
            if(a.getContact_ID() == 1) {
                    int apptId = a.getAppointment_ID();
                    String title = a.getTitle();
                    String type = a.getType();
                    String desc = a.getDescription();
                    String start = a.getStart().toString().replace("T", " ");
                    // format or check by EST
                    String end = a.getEnd().toString().replace("T", " ");
                    // format or check be EST
                    int custId = a.getCustomer_ID();
                    contact1.add("Appointment ID: " + apptId + "\n"
                        + "Title: " + title + "\n" + "Type: " + type + "\n" + "Description: " + desc
                        + "\n" + "Start time/date: " + start + "\n" + "End time/date: " + end + "\n"
                        + "Customer ID: " + custId + "\n\n");
            }else if(a.getContact_ID() == 2) {
                    int apptId = a.getAppointment_ID();
                    String title = a.getTitle();
                    String type = a.getType();
                    String desc = a.getDescription();
                    String start = a.getStart().toString().replace("T", " ");
                    // format or check by EST
                    String end = a.getEnd().toString().replace("T", " ");
                    // format or check be EST
                    int custId = a.getCustomer_ID(); 
                    contact2.add("Appointment ID: " + apptId + "\n"
                        + "Title: " + title + "\n" + "Type: " + type + "\n" + "Description: " + desc
                        + "\n" + "Start time/date: " + start + "\n" + "End time/date: " + end + "\n"
                        + "Customer ID: " + custId + "\n\n");
            }else if(a.getContact_ID() == 3) {
                    int apptId = a.getAppointment_ID();
                    String title = a.getTitle();
                    String type = a.getType();
                    String desc = a.getDescription();
                    String start = a.getStart().toString().replace("T", " ");
                    // format or check by EST
                    String end = a.getEnd().toString().replace("T", " ");
                    // format or check be EST
                    int custId = a.getCustomer_ID();
                    contact3.add("Appointment ID: " + apptId + "\n"
                        + "Title: " + title + "\n" + "Type: " + type + "\n" + "Description: " + desc
                        + "\n" + "Start time/date: " + start + "\n" + "End time/date: " + end + "\n"
                        + "Customer ID: " + custId + "\n\n");
            }
            
            
        }
        
        String str = "Contact: Anika Costa" + "\n" +contact1.toString() + "Contact Name: Daniel Garcia" 
                + "\n" + contact2.toString() + "Contact Name: Li Lee" + "\n" + contact3.toString();

        return str;
    }
    
    /**
     * Getter for allAppointments ObservableList. 
     * Returns allAppointments ObservableList.
     * @return allAppointments ObservableList
     */
    public static ObservableList<Appointments> getAllAppointments() {
        return allAppointments;
    }
    
    /**
     * Adds appointments to ObservableList. 
     * Adds appointments to ObservableList allAppointments. 
     * @param appointments ObserableList
     */
    public static void addAppointment(Appointments appointments) {
        allAppointments.add(appointments);
    }
    
    /**
     * Removes selected appointment from ObservableList. 
     * Removes selected appointment from ObservableList.
     * @param appointments ObservableList
     */
    public static void removeApptFromTableView(Appointments appointments){
        allAppointments.remove(appointments);
    }
    
    /**
     * Adds type Strings to Type ObserableList. 
     * Adds type Strings to Type ObservableList.
     * @param type String
     */
    public static void addType(String type) {
        types.add(type);
    }
    
    /**
     * Getter for Types. 
     * Getter for Types.
     * @return types
     */
    public static ObservableList<String> getTypes() {
        return types;
    }
    
    /**
     * Adds appointments to ObservableList. 
     * Adds appointments to ObservableList.
     * @param a Appointment
     */
    public static void addApptsByMonth(Appointments a) {
        appointmentsByMonth.add(a);
    }
    
    /**
     * Getter for ObservableList of appointments filtered by month. 
     * Returns ObservableList of appointments filtered by month.
     * @return appointmentsByMonth ObservableList
     */
    public static ObservableList<Appointments> getApptsByMonth() {
        return appointmentsByMonth;
    }
    
    /**
     * Adds appointments to ObservableList filtered by week. 
     * Adds appointments to ObservableList filtered by week.
     * @param a Appointment
     */
    public static void addApptsByWeek(Appointments a) {
        appointmentsByWeek.add(a);
    }
    
    /**
     * Getter for ObservableList filtered by week. 
     * Returns ObservableList filtered by current week.
     * @return appointmentsByWeek ObservableList
     */
    public static ObservableList<Appointments> getApptsByWeek() {
        return appointmentsByWeek;
    }
            
}
