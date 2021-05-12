/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import databaseAccess.DBAppointments;
import databaseAccess.DBContacts;
import databaseAccess.DBCountries;
import databaseAccess.DBCustomers;
import databaseAccess.DBFirstLevDiv;
import databaseAccess.DBUsers;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Appointments;
import model.Contacts;
import model.Countries;
import model.Customers;
import model.FirstLevDiv;
import model.Users;
import model.clearApptInterface;
import utils.TimeProcessing;

/**
 * FXML Controller class
 *
 * @author Mike Bliss
 */
public class MainScreenController implements Initializable {
    
    private Users user = null;
    
    @FXML
    private TableView<Customers> custTblViewMain;

    @FXML
    private TableColumn<Customers, Integer> custIdTblColMain;

    @FXML
    private TableColumn<Customers, String> custNameTblColMain;

    @FXML
    private TableColumn<Customers, String> custAddressTblColMain;

    @FXML
    private TableColumn<Customers, String> custPostalTblColMain;

    @FXML
    private TableColumn<Customers, String> custPhoneTblColMain;

    @FXML
    private TableColumn<Customers, Integer> custDivisionTblColMain;

    @FXML
    private Button addCustBtnMain;

    @FXML
    private Button updateCustBtnMain;
    
    @FXML
    private Button saveUpdateCustBtn;

    @FXML
    private Button deleteCustBtnMain;
    
    @FXML
    private Button clearAllCust;

    @FXML
    private RadioButton radioBtnMonthMain;

    @FXML
    private ToggleGroup sortByMain;

    @FXML
    private RadioButton radioBtnWeekMain;

    @FXML
    private RadioButton radioBtnAllMain;

    @FXML
    private TableView<Appointments> apptTblViewMain;

    @FXML
    private TableColumn<Appointments, Integer> apptIdColMain;

    @FXML
    private TableColumn<Appointments, String> apptTitleColMain;

    @FXML
    private TableColumn<Appointments, String> apptDescriptionColMain;

    @FXML
    private TableColumn<Appointments, String> apptLocationColMain;

    @FXML
    private TableColumn<Appointments, Integer> apptContactColMain;

    @FXML
    private TableColumn<Appointments, String> apptTypeColMain;

    @FXML
    private TableColumn<Appointments, LocalDateTime> apptStartColMain;  

    @FXML
    private TableColumn<Appointments, LocalDateTime> apptEndColMain;   

    @FXML
    private TableColumn<Appointments, Integer> apptCustIdColMain;
    
    @FXML
    private TextField custNameTxt;

    @FXML
    private TextField custAddressTxt;

    @FXML
    private TextField custPostalCodeTxt;

    @FXML
    private TextField custPhoneTxt;
    
    @FXML
    private TextField custIDTxt;

    @FXML
    private ComboBox<FirstLevDiv> divisionCmbo;

    @FXML
    private ComboBox<Countries> countryCmbo;
    
    @FXML
    private TextField apptTitleText;

    @FXML
    private TextField apptDescTxt;

    @FXML
    private TextField apptLocationTxt;

    @FXML
    private ComboBox<Contacts> apptContactNameCmbo;

    @FXML
    private ComboBox<Users> apptUserIDCmbo;

    @FXML
    private TextField apptIDTxt;

    @FXML
    private DatePicker apptStartDate;

    @FXML
    private DatePicker apptEndDate;
    
    @FXML
    private ComboBox<String> apptTypeCmbo;

    @FXML
    private ComboBox<Customers> apptCustIDCmbo;

    @FXML
    private ComboBox<LocalTime> apptStartTimeCmbo;

    @FXML
    private ComboBox<LocalTime> apptEndTimeCmbo;
    
    @FXML
    private Button apptAddBtn;

    @FXML
    private Button apptUpdateBtn;

    @FXML
    private Button apptSaveBtn;

    @FXML
    private Button apptDeleteBtn;

    @FXML
    private Button apptClearAll;
    
    @FXML
    private Label upcomingLabel;
    
    @FXML
    private TextArea custApptReportTxtBox;

    @FXML
    private TextArea contactScheduleReportTxtBox;
    
    @FXML
    private TextArea upcomingApptTextArea;
    
    @FXML
    private TextArea customersPerCountryReportTxt;


    /**
     * This aids in the country and division combo boxes communication. 
     * Both combo boxes are linked. As the country combo box selection changes,
     * so does the list of available divisions.
     * @param event Combo box item is selected
     */
    @FXML
    void onActionCountryCmbo(ActionEvent event) {
        int countryId;
        if(countryCmbo.getValue() != null) {
            countryId = countryCmbo.getSelectionModel().getSelectedItem().getCountry_id();
            DBFirstLevDiv.getAllDivisionsByCountry().clear();
            DBFirstLevDiv.selectAllDivisionsByCountry(countryId);
            divisionCmbo.setItems(DBFirstLevDiv.getAllDivisionsByCountry()); 
        }
    }
    
     /**
     * This aids in the country and division combo boxes communication. 
     * Both combo boxes are linked. As the country combo box selection changes,
     * so does the list of available divisions.
     * @param event Combo box item is selected
     */
    @FXML
    void onActionDivisionCmbo(ActionEvent event) {
        divisionCmbo.setItems(DBFirstLevDiv.getAllDivisionsByCountry());
    }
    
    /**
     * This onAction is for the add new customer button. 
     * When clicked, it passes all parameters to the database and calls
     * the customer constructor.
     * @param event When the button is clicked.
     */
    @FXML
    void onActionAddCustBtnMain(ActionEvent event) {       
        String customer_name = custNameTxt.getText();
        String address = custAddressTxt.getText();
        String postal_code = custPostalCodeTxt.getText();
        String phone = custPhoneTxt.getText();
        LocalDateTime create_date = ZonedDateTime.now().toLocalDateTime();
        String created_by = user.getUser_name();
        LocalDateTime last_update = ZonedDateTime.now().toLocalDateTime();
        String lastUpdated_by = user.getUser_name();
        int division_id = divisionCmbo.getValue().getDivision_id();
        int country_id = countryCmbo.getValue().getCountry_id();
        DBCustomers.createCustomer(customer_name, address, postal_code, phone, create_date, created_by, last_update, lastUpdated_by, division_id, country_id);
        clearAllCust();
    }

    /**
     * Deletes selected customer when clicked. 
     * When clicked removes selected customer object from database.
     * @param event When clicked
     */
    @FXML
    void onActionDeleteCustBtnMain(ActionEvent event) {
        try{ 
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("WARNING");
            alert.setContentText("All associated appointments for this customer will be deleted\nif customer is deleted.");
            Optional<ButtonType> result = alert.showAndWait();

            if(result.isPresent() && result.get() == ButtonType.OK) {
                int selectedCustId = custTblViewMain.getSelectionModel().getSelectedItem().getCustomerId();
                Customers customer = custTblViewMain.getSelectionModel().getSelectedItem();
                ArrayList<Appointments> apptArray = new ArrayList<Appointments>();
                StringBuilder str = new StringBuilder();

                // Adds appointments to ArrayList if customerId matches id of selected customer
                for(Appointments a: apptTblViewMain.getItems()) {
                    if(selectedCustId == a.getCustomer_ID()) {
                        apptArray.add(a);
                        str.append(a.getAppointment_ID() + "    " + a.getType() + "\n");
                    }
                }

                // Deletes all appointments stored in ArrayList
                for(Appointments a: apptArray) {
                    DBAppointments.deleteAppointment(a.getAppointment_ID());
                    DBAppointments.removeApptFromTableView(a);
                }

                DBCustomers.deleteCustomer(selectedCustId);
                DBCustomers.removeCustomerFromTblView(customer);
                
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("Deletion Notification");
                alert2.setContentText("The following data has been deleted: \n\n" 
                + "Customers by ID and Name:\n"
                + customer.toString() + "\n\n"
                + "Appointments by ID and Name:\n"
                + str);
                Optional<ButtonType> result2 = alert2.showAndWait();
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Populates update fields with selected customer object attributes. 
     * Populates update fields with selected customer object attributes.
     * @param event When clicked.
     */
    @FXML
    void onActionUpdateCustBtnMain(ActionEvent event) {
        updateCustBtnMain.setText("Updating...");
        updateCustBtnMain.setUnderline(true);

        String custId = String.valueOf(custTblViewMain.getSelectionModel().getSelectedItem().getCustomerId());
        String name = custTblViewMain.getSelectionModel().getSelectedItem().getName();
        String address = custTblViewMain.getSelectionModel().getSelectedItem().getAddress();
        String postalCode = custTblViewMain.getSelectionModel().getSelectedItem().getPostalCode();
        String phone = custTblViewMain.getSelectionModel().getSelectedItem().getPhone();
        int countryId = custTblViewMain.getSelectionModel().getSelectedItem().getCountryId();
        int divisionId = custTblViewMain.getSelectionModel().getSelectedItem().getDivisionId();
        
        custIDTxt.setText(custId);
        custNameTxt.setText(name);
        custAddressTxt.setText(address);
        custPostalCodeTxt.setText(postalCode);
        custPhoneTxt.setText(phone);
    
        for(Countries c: countryCmbo.getItems()) {
            if(countryId == c.getCountry_id()) {
                countryCmbo.setValue(c);
                break;
            }
        }
        
        for(FirstLevDiv d: divisionCmbo.getItems()) {
            if(divisionId == d.getDivision_id()) {
                divisionCmbo.setValue(d);
                break;
            }
        }        
    }
    
    /**
     * Passes updated customer info to database when clicked. 
     * Takes info from update fields and passes them into the database.
     * @param event When clicked.
     */
    @FXML
    void onActionSaveUpdateCustBtnMain(ActionEvent event) {
        
        int custId = custTblViewMain.getSelectionModel().getSelectedItem().getCustomerId();
        String customer_name = custNameTxt.getText();
        String address = custAddressTxt.getText();
        String postal_code = custPostalCodeTxt.getText();
        String phone = custPhoneTxt.getText();
        //LocalDateTime create_date = ZonedDateTime.now().toLocalDateTime();
        String created_by = user.getUser_name();
        LocalDateTime last_update = ZonedDateTime.now().toLocalDateTime();
        String lastUpdated_by = user.getUser_name();
        int division_id = divisionCmbo.getValue().getDivision_id();
        int country_id = countryCmbo.getValue().getCountry_id();

        DBCustomers.updateCustomer(custId, customer_name, address, postal_code, 
                phone, created_by, last_update, lastUpdated_by, 
                division_id, country_id);
        custTblViewMain.setItems(DBCustomers.getAllCustomers());        
        
        clearAllCust();
    }
    
    /**
     * Does nothing. 
     * Application blows up if removed from source code.
     * @param event When clicked. 
     */
    @FXML
    void onActionApptContactNameCmbo(ActionEvent event) {
        
    }

     /**
     * Does nothing. 
     * Application blows up if removed from source code.
     * @param event When clicked. 
     */
    @FXML
    void onActionApptCustIDCmbo(ActionEvent event) {

    }

     /**
     * Does nothing. 
     * Application blows up if removed from source code.
     * @param event When clicked. 
     */
    @FXML
    void onActionApptDescTxt(ActionEvent event) {

    }

     /**
     * Does nothing. 
     * Application blows up if removed from source code.
     * @param event When clicked. 
     */
    @FXML
    void onActionApptEndDate(ActionEvent event) {

    }
    
     /**
     * Does nothing. 
     * Application blows up if removed from source code.
     * @param event When clicked. 
     */
    @FXML
    void onActionApptEndTime(ActionEvent event) {

    }

     /**
     * Does nothing. 
     * Application blows up if removed from source code.
     * @param event When clicked. 
     */
    @FXML
    void onActionApptIDTxt(ActionEvent event) {

    }
    
    /**
     * Does nothing. 
     * Application blows up if removed from source code.
     * @param event When clicked. 
     */
    @FXML
    void onActionApptLocationTxt(ActionEvent event) {

    }

     /**
     * Does nothing. 
     * Application blows up if removed from source code.
     * @param event When clicked. 
     */
    @FXML
    void onActionApptStartDate(ActionEvent event) {

    }

     /**
     * Does nothing. 
     * Application blows up if removed from source code.
     * @param event When clicked. 
     */
    @FXML
    void onActionApptStartTime(ActionEvent event) {

    }

     /**
     * Does nothing. 
     * Application blows up if removed from source code.
     * @param event When clicked. 
     */
    @FXML
    void onActionApptTitleTxt(ActionEvent event) {

    }

     /**
     * Does nothing. 
     * Application blows up if removed from source code.
     * @param event When clicked. 
     */
    @FXML
    void onActionApptUserIDCmbo(ActionEvent event) {

    }

     /**
     * Does nothing. 
     * Application blows up if removed from source code.
     * @param event When clicked. 
     */
    @FXML
    void onActionApptTypeCmbo(ActionEvent event) {

    }
  

    /**
     * Creates new appointment. 
     * Adds new appointment to database and calls constructor.
     * @param event When clicked.
     */
    @FXML
    void onActionApptAddBtn(ActionEvent event) {
        String title = apptTitleText.getText();
        String description = apptDescTxt.getText();
        String location = apptLocationTxt.getText();
        int contact_ID = apptContactNameCmbo.getSelectionModel().getSelectedItem().getContact_id();
        int userId = apptUserIDCmbo.getSelectionModel().getSelectedItem().getUser_id();
        LocalDate startDate = apptStartDate.getValue();
        LocalDate endDate = apptEndDate.getValue();
        String type = apptTypeCmbo.getSelectionModel().getSelectedItem();
        int customer_ID = apptCustIDCmbo.getSelectionModel().getSelectedItem().getCustomerId();
        LocalTime startTime = apptStartTimeCmbo.getSelectionModel().getSelectedItem();
        LocalTime endTime = apptEndTimeCmbo.getSelectionModel().getSelectedItem();
        LocalDateTime create_date = ZonedDateTime.now().toLocalDateTime();
        LocalDateTime last_update = ZonedDateTime.now().toLocalDateTime();
        String lastUpdated_by = user.getUser_name();
        // specify zoneId for this in a class with static method
        LocalDateTime startDT = LocalDateTime.of(startDate, startTime);
        String strStartDate = TimeProcessing.easternToUTC(startDT);
        LocalDateTime endDT = LocalDateTime.of(endDate, endTime);
        String strEndDate = TimeProcessing.easternToUTC(endDT);
        String created_by = user.getUser_name();
        
        // Check for nulls
        boolean textEmpty = false;
        boolean descEmpty = false;
        boolean locationEmpty = false;
        boolean contactEmpty = false;
        boolean userEmpty = false;
        boolean typeEmpty = false;
        boolean custEmpty = false;
        boolean startEmpty = false;
        boolean endEmpty = false;
        boolean afterBeforeTime = false;
        boolean afterBeforeDate = false;
        boolean outsideBizHours = false;
        boolean daysDiff = false;
        
        if(apptTitleText.getText().isEmpty())
            textEmpty = true;
        if(apptDescTxt.getText().isEmpty())
            descEmpty = true;
        if(apptLocationTxt.getText().isEmpty())
            locationEmpty = true;
        if(apptContactNameCmbo.getSelectionModel().isEmpty())
            contactEmpty = true;
        if(apptUserIDCmbo.getSelectionModel().isEmpty())
            userEmpty = true;
        if(apptTypeCmbo.getSelectionModel().isEmpty())
            typeEmpty = true;
        if(apptCustIDCmbo.getSelectionModel().isEmpty())
            custEmpty = true;
        if(apptStartTimeCmbo.getSelectionModel().isEmpty())
            startEmpty = true;
        if(apptEndTimeCmbo.getSelectionModel().isEmpty())
            endEmpty = true;
        if(endTime.isBefore(startTime) || endTime.equals(startTime))
            afterBeforeTime = true;
        if(endDate.isBefore(startDate))
            afterBeforeDate = true;
        if(TimeProcessing.compareToEST(startDT) || TimeProcessing.compareToEST(endDT))
            outsideBizHours = true;
        if(!(startDate.equals(endDate)))
            daysDiff = true;
            
        
        
        
        // Begin logical error checks
        if(DBAppointments.checkSatSun(startDT, endDT) || outsideBizHours || daysDiff) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Schedule Confliction");
            alert1.setContentText("Please schedule durring regular business hours, \n"
                    + "Monday - Friday, Eastern Standard Time.");
            Optional<ButtonType> result = alert1.showAndWait();
        }else if(DBAppointments.checkOverlapAdd(startDT, endDT, customer_ID)) {
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setTitle("Schedule Overlap Not Allowed");
            alert2.setContentText("Please adjust selected time to avoid overlapping appointments\n" + 
                "for customers with the following customer Id: " + customer_ID);
            Optional<ButtonType> result = alert2.showAndWait();
        }else if(textEmpty || descEmpty || locationEmpty || contactEmpty || userEmpty || typeEmpty || custEmpty || startEmpty || endEmpty){
            Alert alert3 = new Alert(Alert.AlertType.ERROR);
            alert3.setTitle("One or more fields is empty");
            alert3.setContentText("One or more fields is empty;");
            Optional<ButtonType> result = alert3.showAndWait();
        }else if(afterBeforeTime || afterBeforeDate) {
            Alert alert4 = new Alert(Alert.AlertType.ERROR);
            alert4.setTitle("Time/Date Logic Error");
            alert4.setContentText("End time/date cannot be before or equal to start time/date.");
            Optional<ButtonType> result = alert4.showAndWait();
        }else{
            DBAppointments.createAppointment(title, description, location, type, strStartDate, startDT, strEndDate, endDT, create_date, created_by, last_update, lastUpdated_by,  customer_ID, userId, contact_ID);
            clearAllAppt();
        }
        
    }
    
    /**
     * Deletes appointment. 
     * Deletes selected appointment and removes from database.
     * @param event When clicked.
     */
    @FXML
    void onActionApptDeleteBtn(ActionEvent event) {
        int selectedApptId = apptTblViewMain.getSelectionModel().getSelectedItem().getAppointment_ID();
        Appointments appointment = apptTblViewMain.getSelectionModel().getSelectedItem();
        DBAppointments.deleteAppointment(selectedApptId);
        DBAppointments.removeApptFromTableView(appointment);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Appointment Deleted");
        alert.setContentText("The following appointment has been canceled: \n\n" 
                + "Title: " + appointment.getTitle() + "\n" + "Appointment ID: " + appointment.getAppointment_ID());
        Optional<ButtonType> result = alert.showAndWait();
    }
    
    /**
     * Fills in update fields with info from selected appointment object.
     * Fills in update fields with info from selected appointment object.
     * @param event When clicked.
     */
     @FXML
    void onActionApptUpdateBtn(ActionEvent event) {

        apptUpdateBtn.setText("Updating...");
        apptUpdateBtn.setUnderline(true);
        
        String apptId = String.valueOf(apptTblViewMain.getSelectionModel().getSelectedItem().getAppointment_ID());
        String title = apptTblViewMain.getSelectionModel().getSelectedItem().getTitle();
        String description = apptTblViewMain.getSelectionModel().getSelectedItem().getDescription();
        String location = apptTblViewMain.getSelectionModel().getSelectedItem().getLocation();
        int contactId = apptTblViewMain.getSelectionModel().getSelectedItem().getContact_ID();
        int userId = apptTblViewMain.getSelectionModel().getSelectedItem().getUser_ID();
        String type = apptTblViewMain.getSelectionModel().getSelectedItem().getType();
        int custId = apptTblViewMain.getSelectionModel().getSelectedItem().getCustomer_ID();
        LocalDateTime start = apptTblViewMain.getSelectionModel().getSelectedItem().getStart();
        LocalDateTime end = apptTblViewMain.getSelectionModel().getSelectedItem().getEnd();
        LocalTime startTime = start.toLocalTime();
        LocalTime endTime = end.toLocalTime();
        LocalDate startDate = start.toLocalDate();
        LocalDate endDate = end.toLocalDate();

        apptIDTxt.setText(apptId);
        apptTitleText.setText(title);
        apptDescTxt.setText(description);
        apptLocationTxt.setText(location);
          
        for(Contacts c: apptContactNameCmbo.getItems()) {
            if(contactId == c.getContact_id()) {
                apptContactNameCmbo.setValue(c);
                break;
            }
        }

        for(Users u: apptUserIDCmbo.getItems()) {
            if(userId == u.getUser_id()) {
                apptUserIDCmbo.setValue(u);
                break;
            }
        }

        for(Customers c: apptCustIDCmbo.getItems()) {
            if(custId == c.getCustomerId()) {
                apptCustIDCmbo.setValue(c);
                break;
            }
        }
    /*      ****** FIGURE THIS OUT *******
        for(String t: apptTypeCmbo.getItems()) {
            if(type.matches(t)) {
                //apptTypeCmbo.setValue(type);
                apptTypeCmbo.getSelectionModel().select(type);
                break;
            }
        }
    */
        //apptTypeCmbo.getSelectionModel().select(type);
        apptTypeCmbo.setValue(type);
        
        apptStartDate.setValue(startDate);
        apptEndDate.setValue(endDate);
        apptStartTimeCmbo.getSelectionModel().select(startTime);
        apptEndTimeCmbo.getSelectionModel().select(endTime); 
        
        

    }
    
    /**
     * Saves updated appointment. 
     * Replaces selected appointment object with new updated info in database.
     * @param event When clicked.
     */
    @FXML
    void onActionApptSaveBtn(ActionEvent event) {
        int apptId = apptTblViewMain.getSelectionModel().getSelectedItem().getAppointment_ID();
        String title = apptTitleText.getText();
        String description = apptDescTxt.getText();
        String location = apptLocationTxt.getText();
        int contact_ID = apptContactNameCmbo.getSelectionModel().getSelectedItem().getContact_id();
        int userId = apptUserIDCmbo.getSelectionModel().getSelectedItem().getUser_id();
        String type = apptTypeCmbo.getSelectionModel().getSelectedItem();
        int customer_ID = apptCustIDCmbo.getSelectionModel().getSelectedItem().getCustomerId();
        String lastUpdated_by = user.getUser_name();
        
        LocalTime startTime = apptStartTimeCmbo.getSelectionModel().getSelectedItem();
        LocalTime endTime = apptEndTimeCmbo.getSelectionModel().getSelectedItem();
        LocalDate startDate = apptStartDate.getValue();
        LocalDate endDate = apptEndDate.getValue();
        LocalDateTime last_update = ZonedDateTime.now().toLocalDateTime();
        LocalDateTime startDT = LocalDateTime.of(startDate, startTime);
        LocalDateTime endDT = LocalDateTime.of(endDate, endTime);
        
                // Check for nulls
        boolean textEmpty = false;
        boolean descEmpty = false;
        boolean locationEmpty = false;
        boolean contactEmpty = false;
        boolean userEmpty = false;
        boolean typeEmpty = false;
        boolean custEmpty = false;
        boolean startEmpty = false;
        boolean endEmpty = false;
        boolean afterBeforeTime = false;
        boolean afterBeforeDate = false;
        boolean outsideBizHours = false;
        boolean daysDiff = false;
        
        if(apptTitleText.getText().isEmpty())
            textEmpty = true;
        if(apptDescTxt.getText().isEmpty())
            descEmpty = true;
        if(apptLocationTxt.getText().isEmpty())
            locationEmpty = true;
        if(apptContactNameCmbo.getSelectionModel().isEmpty())
            contactEmpty = true;
        if(apptUserIDCmbo.getSelectionModel().isEmpty())
            userEmpty = true;
        if(apptTypeCmbo.getSelectionModel().isEmpty())
            typeEmpty = true;
        if(apptCustIDCmbo.getSelectionModel().isEmpty())
            custEmpty = true;
        if(apptStartTimeCmbo.getSelectionModel().isEmpty())
            startEmpty = true;
        if(apptEndTimeCmbo.getSelectionModel().isEmpty())
            endEmpty = true;
        if(endTime.isBefore(startTime) || endTime.equals(startTime))
            afterBeforeTime = true;
        if(endDate.isBefore(startDate))
            afterBeforeDate = true;
        if(TimeProcessing.compareToEST(startDT) || TimeProcessing.compareToEST(endDT))
            outsideBizHours = true;
        if(!(startDate.equals(endDate)))
            daysDiff = true;
            
        
        
        // Begin logical error checks
        if(DBAppointments.checkSatSun(startDT, endDT) || outsideBizHours || daysDiff) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Schedule Confliction");
            alert1.setContentText("Please schedule durring regular business hour, \n"
                    + "Monday - Friday, Eastern Standard Time.");
            Optional<ButtonType> result = alert1.showAndWait();
            
        //  ********** FIX ME *************
        }else if(DBAppointments.checkOverlapUpdate(startDT, endDT, customer_ID, apptId)) {
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setTitle("Schedule Overlap Not Allowed");
            alert2.setContentText("Please adjust selected time to avoid overlapping appointments\n" 
                    + "for customers with the following customer Id: " + customer_ID);
           Optional<ButtonType> result = alert2.showAndWait();
        }else if(textEmpty || descEmpty || locationEmpty || contactEmpty || userEmpty || typeEmpty || custEmpty || startEmpty || endEmpty){
            Alert alert3 = new Alert(Alert.AlertType.ERROR);
            alert3.setTitle("One or more fields is empty");
            alert3.setContentText("One or more fields is empty;");
            Optional<ButtonType> result = alert3.showAndWait();
        }else if(afterBeforeTime || afterBeforeDate){  
            Alert alert4 = new Alert(Alert.AlertType.ERROR);
            alert4.setTitle("Time/Date Logic Error");
            alert4.setContentText("End time/date cannot be before or equal to start time/date.");
            Optional<ButtonType> result = alert4.showAndWait();  
        
        }else{
        String strStartDate = TimeProcessing.easternToUTC(startDT);
        String strEndDate = TimeProcessing.easternToUTC(endDT);
        DBAppointments.updateAppointment(apptId, title, description, location, type, strStartDate, strEndDate, last_update, lastUpdated_by,  customer_ID, userId, contact_ID);        
        apptTblViewMain.setItems(DBAppointments.getAllAppointments());
        clearAllAppt();
        }
    }

    /**
     * Clears all appointment update fields. 
     * Clears all appointment update fields when clicked. 
     * @param event When clicked.
     */
    @FXML
    void onActionApptClearAll(ActionEvent event) { 
        clearAllAppt();   
    }
    
        /**
     * Clears all customer update fields. 
     * Clears all customer update fields when clicked. 
     * @param event When clicked.
     */
    @FXML
    void onActionClearAllCust(ActionEvent event) {
        clearAllCust();
    }
    

    /**
     * Clears all customer update fields. 
     * Clears all customer update fields when called from onAction events.
     */
    public void clearAllCust() {
        custNameTxt.clear();
        custAddressTxt.clear();
        custPostalCodeTxt.clear();
        custPhoneTxt.clear();
        custIDTxt.clear();
        updateCustBtnMain.setText("Update Existing");
        updateCustBtnMain.setUnderline(false);
        
        countryCmbo.setItems(DBCountries.getAllCountries());
        countryCmbo.setVisibleRowCount(5);
        countryCmbo.getSelectionModel().selectFirst();
        int countryId = countryCmbo.getSelectionModel().getSelectedItem().getCountry_id();
        DBFirstLevDiv.selectAllDivisionsByCountry(countryId);

        divisionCmbo.setItems(DBFirstLevDiv.getAllDivisionsByCountry());
        divisionCmbo.setVisibleRowCount(5);
        divisionCmbo.getSelectionModel().selectFirst();
    }
    
    /**
     * Lambda Expression for clearing appointment fields. 
     * Used due to repetitive need for clearing appointment fields.
     */
    public void clearAllAppt() {
        clearApptInterface clearAppt = () -> {
            
        apptUpdateBtn.setText("Update Existing");
        apptUpdateBtn.setUnderline(false);
        
        apptIDTxt.clear();
        apptTitleText.clear();
        apptLocationTxt.clear();
        apptDescTxt.clear();
        apptUserIDCmbo.getSelectionModel().clearSelection();
        apptStartDate.setValue(LocalDate.now());
        apptEndDate.setValue(LocalDate.now());
        apptTypeCmbo.getSelectionModel().clearSelection();
        apptCustIDCmbo.getSelectionModel().clearSelection();
        apptStartTimeCmbo.getSelectionModel().clearSelection();
        apptEndTimeCmbo.getSelectionModel().clearSelection();
        apptContactNameCmbo.getSelectionModel().clearSelection();
        };
        clearAppt.clearAppt();
    }
    
    /**
     * Filters appointment TableView by showing all appointments. 
     * Displays the full list of all appointments.
     * @param event When clicked.
     */
    @FXML
    void onActionRadioBtnAll(ActionEvent event) {
        apptTblViewMain.setItems(DBAppointments.getAllAppointments());
    }

    /**
     * Filters appointment table view by month. 
     * Displays only appointments in current month in TableView.
     * @param event When clicked.
     */
    @FXML
    void onActionRadioBtnMonth(ActionEvent event) {
        DBAppointments.selectApptByMonth();
        apptTblViewMain.setItems(DBAppointments.getApptsByMonth());
    }

    /**
     * Filters appointment TableView by week. 
     * Displays only appointments in current week in TableView.
     * @param event 
     */
    @FXML
    void onActionRadioBtnWeek(ActionEvent event) {
        DBAppointments.selectApptByWeek();
        apptTblViewMain.setItems(DBAppointments.getApptsByWeek());
    }

    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            custTblViewMain.setItems(DBCustomers.getAllCustomers());
            custIdTblColMain.setCellValueFactory(new PropertyValueFactory<>("customerId"));
            custNameTblColMain.setCellValueFactory(new PropertyValueFactory<>("name"));
            custAddressTblColMain.setCellValueFactory(new PropertyValueFactory<>("Address"));
            custPostalTblColMain.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
            custPhoneTblColMain.setCellValueFactory(new PropertyValueFactory<>("phone"));
            custDivisionTblColMain.setCellValueFactory(new PropertyValueFactory<>("divisionId"));
            
            apptTblViewMain.setItems(DBAppointments.getAllAppointments());
            apptIdColMain.setCellValueFactory(new PropertyValueFactory<>("Appointment_ID"));
            apptTitleColMain.setCellValueFactory(new PropertyValueFactory<>("Title"));
            apptDescriptionColMain.setCellValueFactory(new PropertyValueFactory<>("Description"));
            apptLocationColMain.setCellValueFactory(new PropertyValueFactory<>("Location"));
            apptContactColMain.setCellValueFactory(new PropertyValueFactory<>("Contact_ID"));
            apptTypeColMain.setCellValueFactory(new PropertyValueFactory<>("Type"));
            apptStartColMain.setCellValueFactory(new PropertyValueFactory<>("Start"));
            apptEndColMain.setCellValueFactory(new PropertyValueFactory<>("End"));
            apptCustIdColMain.setCellValueFactory(new PropertyValueFactory<>("Customer_ID"));
            
            
            countryCmbo.setItems(DBCountries.getAllCountries());
            countryCmbo.setPromptText("Choose country selection");
            countryCmbo.setVisibleRowCount(5);
            countryCmbo.getSelectionModel().selectFirst();
            int countryId = countryCmbo.getSelectionModel().getSelectedItem().getCountry_id();
            DBFirstLevDiv.selectAllDivisionsByCountry(countryId);

            
            divisionCmbo.setItems(DBFirstLevDiv.getAllDivisionsByCountry());
            divisionCmbo.setPromptText("Choose division selection");
            divisionCmbo.setVisibleRowCount(5);
            

            apptContactNameCmbo.setItems(DBContacts.getAllContacts());
            apptUserIDCmbo.setItems(DBUsers.getAllUsers());
            apptTypeCmbo.setItems(DBAppointments.getTypes());
            apptCustIDCmbo.setItems(DBCustomers.getAllCustomers());
            
            
            
            
            
            apptStartTimeCmbo.getItems().add(LocalTime.of(4, 00));
            apptStartTimeCmbo.getItems().add(LocalTime.of(4, 30));
            apptStartTimeCmbo.getItems().add(LocalTime.of(5, 00));
            apptStartTimeCmbo.getItems().add(LocalTime.of(5, 30));
            apptStartTimeCmbo.getItems().add(LocalTime.of(6, 00));
            apptStartTimeCmbo.getItems().add(LocalTime.of(6, 30));
            apptStartTimeCmbo.getItems().add(LocalTime.of(7, 00));
            apptStartTimeCmbo.getItems().add(LocalTime.of(7, 30));
            apptStartTimeCmbo.getItems().add(LocalTime.of(8, 00));
            apptStartTimeCmbo.getItems().add(LocalTime.of(8, 30));
            apptStartTimeCmbo.getItems().add(LocalTime.of(9, 00));
            apptStartTimeCmbo.getItems().add(LocalTime.of(9, 30));
            apptStartTimeCmbo.getItems().add(LocalTime.of(10, 00));
            apptStartTimeCmbo.getItems().add(LocalTime.of(10, 30));
            apptStartTimeCmbo.getItems().add(LocalTime.of(11, 00));
            apptStartTimeCmbo.getItems().add(LocalTime.of(11, 30));
            apptStartTimeCmbo.getItems().add(LocalTime.of(12, 00));
            apptStartTimeCmbo.getItems().add(LocalTime.of(12, 30));
            apptStartTimeCmbo.getItems().add(LocalTime.of(13, 00));
            apptStartTimeCmbo.getItems().add(LocalTime.of(13, 30));
            apptStartTimeCmbo.getItems().add(LocalTime.of(14, 00));
            apptStartTimeCmbo.getItems().add(LocalTime.of(14, 30));
            apptStartTimeCmbo.getItems().add(LocalTime.of(15, 00));
            apptStartTimeCmbo.getItems().add(LocalTime.of(15, 30));
            apptStartTimeCmbo.getItems().add(LocalTime.of(16, 00));
            apptStartTimeCmbo.getItems().add(LocalTime.of(16, 30));
            apptStartTimeCmbo.getItems().add(LocalTime.of(17, 00));
            apptStartTimeCmbo.getItems().add(LocalTime.of(17, 30));
            apptStartTimeCmbo.getItems().add(LocalTime.of(18, 00));
            apptStartTimeCmbo.getItems().add(LocalTime.of(18, 30));
            apptStartTimeCmbo.getItems().add(LocalTime.of(19, 00));
            apptStartTimeCmbo.getItems().add(LocalTime.of(19, 30));
            apptStartTimeCmbo.getItems().add(LocalTime.of(20, 00));
            apptStartTimeCmbo.getItems().add(LocalTime.of(20, 30));
            apptStartTimeCmbo.getItems().add(LocalTime.of(21, 00));
            apptStartTimeCmbo.getItems().add(LocalTime.of(21, 30));
            
            
            apptEndTimeCmbo.getItems().add(LocalTime.of(8, 30));
            apptEndTimeCmbo.getItems().add(LocalTime.of(9, 00));
            apptEndTimeCmbo.getItems().add(LocalTime.of(9, 30));
            apptEndTimeCmbo.getItems().add(LocalTime.of(10, 00));
            apptEndTimeCmbo.getItems().add(LocalTime.of(10, 30));
            apptEndTimeCmbo.getItems().add(LocalTime.of(11, 00));
            apptEndTimeCmbo.getItems().add(LocalTime.of(11, 30));
            apptEndTimeCmbo.getItems().add(LocalTime.of(12, 00));
            apptEndTimeCmbo.getItems().add(LocalTime.of(12, 30));
            apptEndTimeCmbo.getItems().add(LocalTime.of(13, 00));
            apptEndTimeCmbo.getItems().add(LocalTime.of(13, 30));
            apptEndTimeCmbo.getItems().add(LocalTime.of(14, 00));
            apptEndTimeCmbo.getItems().add(LocalTime.of(14, 30));
            apptEndTimeCmbo.getItems().add(LocalTime.of(15, 00));
            apptEndTimeCmbo.getItems().add(LocalTime.of(15, 30));
            apptEndTimeCmbo.getItems().add(LocalTime.of(16, 00));
            apptEndTimeCmbo.getItems().add(LocalTime.of(16, 30));
            apptEndTimeCmbo.getItems().add(LocalTime.of(17, 00));
            apptEndTimeCmbo.getItems().add(LocalTime.of(17, 30));
            apptEndTimeCmbo.getItems().add(LocalTime.of(18, 00));
            apptEndTimeCmbo.getItems().add(LocalTime.of(18, 30));
            apptEndTimeCmbo.getItems().add(LocalTime.of(19, 00));
            apptEndTimeCmbo.getItems().add(LocalTime.of(19, 30));
            apptEndTimeCmbo.getItems().add(LocalTime.of(20, 00));
            apptEndTimeCmbo.getItems().add(LocalTime.of(20, 30));
            apptEndTimeCmbo.getItems().add(LocalTime.of(21, 00));
            apptEndTimeCmbo.getItems().add(LocalTime.of(21, 30));
            apptEndTimeCmbo.getItems().add(LocalTime.of(22, 00));

        /*
            while(earliest.isBefore(latest)) {
                apptStartTimeCmbo.getItems().add(earliest);
                earliest.plusMinutes(15);
            }
        */

            apptEndDate.setValue(LocalDate.now());
            apptStartDate.setValue(LocalDate.now());


            
      
            user = Users.getCurrentUser();
            String upcomingAppointments = DBAppointments.upcomingAppointments(user);
            if(upcomingAppointments.isEmpty())
                upcomingApptTextArea.setText("No upcoming appointments");
            else
                upcomingApptTextArea.setText(upcomingAppointments);
            
            custApptReportTxtBox.setText(DBAppointments.customerApptReport());
            contactScheduleReportTxtBox.setText(DBAppointments.contactScheduleReport());
            customersPerCountryReportTxt.setText(DBCustomers.customersPerCountry());
            
            
            // This line will print all Zone Ids supported by OS
            //ZoneId.getAvailableZoneIds().stream().forEach(System.out::println);
            
            // same as above but filters by continent
            //ZoneId.getAvailableZoneIds().stream().filter(c -> c.contains("America")).forEach(System.out::println);
    }    
    

            
    
}
