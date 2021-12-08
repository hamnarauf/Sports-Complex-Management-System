/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sports.complex.menu;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Hamna Rauf
 */
public class EditProfileController implements Initializable {

    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField dob;
    @FXML
    private JFXTextField contact;
    @FXML
    private JFXTextField contactEmer;
    @FXML
    private JFXTextField address;
    @FXML
    private JFXTextField dept;
    @FXML
    private JFXTextField id;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField gender;
    @FXML
    private JFXTextField role;
    @FXML
    private JFXTextField bloodGroup;
    @FXML
    private JFXTextField allergies;
    @FXML
    private JFXTextField salary;
    @FXML
    private JFXTextField cnic;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleEditBtn(ActionEvent event) {
    }
    
}
