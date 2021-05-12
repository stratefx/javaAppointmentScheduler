/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import model.ScreenChangeInterface;
import databaseAccess.DBUsers;

/**
 * FXML Controller class
 *
 * @author Mike Bliss
 */
public class LoginMainController implements Initializable {
    

    Stage stage;
    Parent scene;
    final String address = "/view/MainScreen.fxml";

    @FXML
    private PasswordField loginUsernameText;

    @FXML
    private PasswordField loginPasswordTxt;

    @FXML
    private Button loginBtn;

    @FXML
    private Label loginUserLocationLbl;
    
    @FXML
    private Label loginLocationDisplayLbl;
    
    @FXML
    private Label passwordLbl;
    
    @FXML
    private Label usernameLbl;
    
     /** Lambda expression for login button. 
     * This method executes when the login button is clicked.
     * Lambda expression for changing screens. 
     * Lambda expression used due to repetitive nature of needing to change screens. 
     * 
     * @param event When the button is clicked
     * @throws IOException An input/output exception
     * @throws Exception An exception
     */
    @FXML
    public void onActionLogin(ActionEvent event) throws IOException, Exception {

        // Retrieves text from username/password textfields
        String userName = loginUsernameText.getText();
        String passWord = loginPasswordTxt.getText();
        

        if(DBUsers.userLogin(userName, passWord)) {
            /*
            * Lambda expression for changing screens. 
            * Lambda expression used due to repetitive nature of needing to change screens. 
            */
            ScreenChangeInterface login = () -> {
                try {
                    stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                    scene = FXMLLoader.load(getClass().getResource(address));
                    stage.setScene(new Scene(scene));
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(LoginMainController.class.getName()).log(Level.SEVERE, null, ex);
                }
            };
            login.changeScreen();
        }
     
    }

    
    
    
    /**
     * Initializes the controller class. 
     * @param url A url
     * @param rb A ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String userLocation = ZoneId.systemDefault().toString();
        loginUserLocationLbl.setText(userLocation);
         
        if(Locale.getDefault().getLanguage().equals("fr")) {
            ResourceBundle rb2 = ResourceBundle.getBundle("model/lang", Locale.getDefault());
            usernameLbl.setText(rb2.getString("Username"));
            passwordLbl.setText(rb2.getString("Password"));
            loginBtn.setText(rb2.getString("Login"));
            loginLocationDisplayLbl.setText(rb2.getString("Location"));
        }
    
        
    }    
    
}
