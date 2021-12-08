/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sports.complex.registration.employees;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Hamna Rauf
 */
public class RemoveEmployeeController implements Initializable {

    @FXML
    private JFXTextField id;
    @FXML
    private Label name;
    @FXML
    private Label contact;
    @FXML
    private Label dob;
    @FXML
    private Label email;
    @FXML
    private Label address;
    @FXML
    private Label dept;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleRemoveBtn(ActionEvent event) {
    }
    
}
